package peajeinteligente.controller;

import peajeinteligente.model.DailyRecord;
import peajeinteligente.model.List;
import peajeinteligente.model.Queue;
import peajeinteligente.model.Stack;
import peajeinteligente.model.Vehicle;
import peajeinteligente.view.IOManager;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * Controlador principal del sistema de peaje.
 * Coordina las operaciones de registro, atencion, reversion, historial,
 * reporte de recaudo, arqueo de caja y reporte semanal del supervisor.
 * Contiene el bucle de menu y todos los switch de despacho.
 */
public class Controller {

    /** Gestor de entrada/salida. */
    private IOManager io;

    // --- Casetas activas (colas FIFO) ---
    private Queue<Vehicle> booth1;
    private Queue<Vehicle> booth2;
    private Queue<Vehicle> booth3;
    private Queue<Vehicle> booth4;

    /** Pila de deshacer: permite revertir la ultima atencion. */
    private Stack<Vehicle> undoStack;

    /** Historial cronologico de vehiculos atendidos en el dia actual. */
    private List<Vehicle> history;

    /**
     * Semana actual: lista de DailyRecord cerrados.
     * Cada elemento es un registro diario de una caseta.
     * Se acumulan al hacer arqueo de caja (cerrar dia).
     * Maximo 28 registros por semana (4 casetas x 7 dias).
     */
    private List<DailyRecord> week;

    /** Numero del dia actual dentro de la semana (1-7). */
    private int currentDay;

    /** Generador de valores aleatorios para el registro masivo. */
    private Random random;

    private static final DateTimeFormatter FORMATO_HORA =
            DateTimeFormatter.ofPattern("HH:mm:ss");

    /**
     * Crea el controlador e inicializa todas las estructuras.
     *
     * @param io gestor de entrada/salida
     */
    public Controller(IOManager io) {
        this.io = io;
        booth1    = new Queue<>();
        booth2    = new Queue<>();
        booth3    = new Queue<>();
        booth4    = new Queue<>();
        undoStack = new Stack<>();
        history   = new List<>();
        week      = new List<>();
        currentDay = 1;
        random    = new Random();
    }

    /**
     * Ejecuta el bucle principal del programa.
     *
     * @throws IOException si ocurre un error de lectura
     */
    public void ejecutar() throws IOException {
        int op;
        do {
            op = io.showMenu();
            switch (op) {
                case 1: registrar();        break;
                case 2: registrarAleatorio(); break;
                case 3: mostrarEstado();    break;
                case 4: atender();          break;
                case 5: revertir();         break;
                case 6: mostrarHistorial(); break;
                case 7: menuReportes();     break;
                case 0: io.showMessage("Hasta luego."); break;
                default: io.showMessage("Opcion no valida.");
            }
        } while (op != 0);
    }

    // =========================================================
    // MENU DE REPORTES (opcion 7) — submenus
    // =========================================================

    /**
     * Submenú de reportes. Muestra tres opciones:
     * 1. Reporte de recaudo del dia.
     * 2. Cerrar dia (arqueo de caja).
     * 3. Reporte semanal del supervisor.
     *
     * @throws IOException si ocurre un error de lectura
     */
    private void menuReportes() throws IOException {
        int op;
        do {
            op = io.showReportMenu(currentDay);
            switch (op) {
                case 1: reporteRecaudoDia();   break;
                case 2: cerrarDia();           break;
                case 3: reporteSemanal();      break;
                case 0: break;
                default: io.showMessage("Opcion no valida.");
            }
        } while (op != 0);
    }

    // =========================================================
    // REPORTE 1 — Recaudo del dia actual
    // =========================================================

    /**
     * Genera el reporte de recaudo del dia actual.
     * Recorre el historial UNA sola vez acumulando totales por caseta
     * y por categoria. No destruye ninguna estructura.
     */
    private void reporteRecaudoDia() {
        if (history.getSize() == 0) {
            io.showMessage("No hay vehiculos atendidos en el dia actual.");
            return;
        }

        // Totales por caseta — variables nombradas, sin arreglos
        double totalCaseta1 = 0, totalCaseta2 = 0, totalCaseta3 = 0, totalCaseta4 = 0;
        int    vehicCaseta1 = 0, vehicCaseta2 = 0, vehicCaseta3 = 0, vehicCaseta4 = 0;

        // Totales por categoria
        double totalCat1 = 0, totalCat2 = 0, totalCat3 = 0;
        int    vehicCat1 = 0, vehicCat2 = 0, vehicCat3 = 0;

        double totalDia = 0;

        for (int i = 0; i < history.getSize(); i++) {
            Vehicle v = history.get(i);
            double tarifa = v.getToll();

            // Acumular por caseta
            if (v.getBooth() == 1)      { totalCaseta1 += tarifa; vehicCaseta1++; }
            else if (v.getBooth() == 2) { totalCaseta2 += tarifa; vehicCaseta2++; }
            else if (v.getBooth() == 3) { totalCaseta3 += tarifa; vehicCaseta3++; }
            else if (v.getBooth() == 4) { totalCaseta4 += tarifa; vehicCaseta4++; }

            // Acumular por categoria
            if (v.getCategory() == 1)      { totalCat1 += tarifa; vehicCat1++; }
            else if (v.getCategory() == 2) { totalCat2 += tarifa; vehicCat2++; }
            else if (v.getCategory() == 3) { totalCat3 += tarifa; vehicCat3++; }

            totalDia += tarifa;
        }

        io.showDayReport(currentDay,
                totalCaseta1, vehicCaseta1,
                totalCaseta2, vehicCaseta2,
                totalCaseta3, vehicCaseta3,
                totalCaseta4, vehicCaseta4,
                totalCat1, vehicCat1,
                totalCat2, vehicCat2,
                totalCat3, vehicCat3,
                totalDia);
    }

    // =========================================================
    // REPORTE 2 — Cerrar dia (arqueo de caja)
    // =========================================================

    /**
     * Cierra el dia actual: crea un DailyRecord por cada caseta con los
     * vehiculos del historial que le corresponden, los guarda en la semana
     * y limpia las estructuras del dia para el siguiente.
     * Si ya se cerraron 7 dias, reinicia la semana.
     */
    private void cerrarDia() {
        if (history.getSize() == 0) {
            io.showMessage("No hay vehiculos atendidos. No se puede cerrar el dia.");
            return;
        }

        // Crear un DailyRecord por caseta
        DailyRecord rec1 = new DailyRecord(1, currentDay);
        DailyRecord rec2 = new DailyRecord(2, currentDay);
        DailyRecord rec3 = new DailyRecord(3, currentDay);
        DailyRecord rec4 = new DailyRecord(4, currentDay);

        // Distribuir historial en los registros segun caseta
        for (int i = 0; i < history.getSize(); i++) {
            Vehicle v = history.get(i);
            switch (v.getBooth()) {
                case 1: rec1.addVehicle(v); break;
                case 2: rec2.addVehicle(v); break;
                case 3: rec3.addVehicle(v); break;
                case 4: rec4.addVehicle(v); break;
            }
        }

        // Guardar en la semana solo los registros con vehiculos
        if (rec1.getVehicleCount() > 0) week.add(rec1);
        if (rec2.getVehicleCount() > 0) week.add(rec2);
        if (rec3.getVehicleCount() > 0) week.add(rec3);
        if (rec4.getVehicleCount() > 0) week.add(rec4);

        io.showMessage("Arqueo de caja completado. Dia " + currentDay + " cerrado.");

        // Avanzar dia o reiniciar semana
        if (currentDay >= 7) {
            io.showMessage("Semana completa. Reiniciando semana...");
            week      = new List<>();
            currentDay = 1;
        } else {
            currentDay++;
        }

        // Limpiar estructuras del dia
        history   = new List<>();
        undoStack = new Stack<>();
        io.showMessage("Sistema listo para el dia " + currentDay + ".");
    }

    // =========================================================
    // REPORTE 3 — Historico semanal del supervisor
    // =========================================================

    /**
     * Muestra el reporte semanal del supervisor.
     * Por cada caseta, muestra sus DailyRecord de la semana.
     * Dentro de cada dia, vacia la pila de vehiculos mostrando
     * del ultimo al primero (LIFO), calcula total por dia y total semanal.
     *
     * NOTA: Este reporte consume las pilas de vehiculos de cada DailyRecord.
     * Esto es correcto segun el enunciado: el supervisor "revisa" la operacion,
     * y el uso correcto de las estructuras exige borrar los nodos al acceder.
     */
    private void reporteSemanal() {
        if (week.getSize() == 0) {
            io.showMessage("No hay dias cerrados esta semana aun.");
            return;
        }

        io.showWeeklyReportHeader(currentDay - 1);

        // Recorrer las 4 casetas
        for (int boothNum = 1; boothNum <= 4; boothNum++) {
            double totalSemanalCaseta = 0;
            boolean tieneDatos = false;

            io.showBoothWeeklyHeader(boothNum);

            // Buscar todos los DailyRecord de esta caseta en la semana
            for (int i = 0; i < week.getSize(); i++) {
                DailyRecord rec = week.get(i);
                if (rec.getBoothNumber() != boothNum) continue;

                tieneDatos = true;
                double totalDia = 0;
                int countDia    = rec.getVehicleCount();

                io.showMessage("  --- Dia " + rec.getDayNumber()
                        + " (" + countDia + " vehiculo(s)) ---");

                // Vaciar la pila: muestra del ultimo al primero (LIFO)
                Stack<Vehicle> pila = rec.getVehicles();
                while (!pila.isEmpty()) {
                    Vehicle v = pila.pop();
                    totalDia += v.getToll();
                    io.showMessage("    " + v);
                }

                io.showMessage("  Total dia " + rec.getDayNumber()
                        + ": $ " + (long) rec.getTotal());
                totalSemanalCaseta += rec.getTotal();
            }

            if (!tieneDatos) {
                io.showMessage("  Sin operaciones registradas.");
            } else {
                io.showBoothWeeklyTotal(boothNum, totalSemanalCaseta);
            }
        }

        // Gran total semanal
        double granTotal = 0;
        for (int i = 0; i < week.getSize(); i++) {
            granTotal += week.get(i).getTotal();
        }
        io.showWeeklyGrandTotal(granTotal);
    }

    // =========================================================
    // OPERACIONES BASICAS (sin cambios de logica)
    // =========================================================

    private void registrar() throws IOException {
        String plate    = io.getString("Placa: ");
        int category    = io.getInt("Categoria (1-3): ");
        double toll     = calcularPeaje(category);
        String timestamp = LocalTime.now().format(FORMATO_HORA);
        Vehicle vehicle = new Vehicle(plate, category, toll, timestamp);
        findShortestBooth().enqueue(vehicle);
        io.showMessage("Vehiculo registrado: " + vehicle);
    }

    private void registrarAleatorio() throws IOException {
        int n = io.getInt("Ingrese la cantidad de vehiculos: ");
        LocalTime tiempo = LocalTime.now();
        for (int i = 0; i < n; i++) {
            Vehicle vehicle = generarVehiculo(tiempo.format(FORMATO_HORA));
            findShortestBooth().enqueue(vehicle);
            io.showMessage("Vehiculo registrado: " + vehicle);
            tiempo = tiempo.plusSeconds(500);
        }
    }

    private void mostrarEstado() {
        io.showState(booth1.getSize(), booth2.getSize(),
                booth3.getSize(), booth4.getSize(),
                undoStack.getSize(), history.getSize());
    }

    /**
     * Atiende todos los vehiculos de la caseta seleccionada en orden FIFO.
     * Asigna el numero de caseta al vehiculo, lo apila en undoStack
     * y lo agrega al historial del dia.
     */
    private void atender() throws IOException {
        int boothNum = io.getInt("Caseta (1-4): ");
        Queue<Vehicle> booth = boothByNumber(boothNum);
        if (booth == null) { io.showMessage("Caseta invalida."); return; }
        if (booth.isEmpty()) { io.showMessage("La caseta " + boothNum + " esta vacia."); return; }

        io.showMessage("=== Atendiendo caseta " + boothNum + " ===");
        while (!booth.isEmpty()) {
            Vehicle vehicle = booth.dequeue();
            vehicle.setBooth(boothNum);
            undoStack.push(vehicle);
            history.add(vehicle);
            io.showMessage("  Atendido: " + vehicle);
        }
    }

    private void revertir() {
        if (undoStack.isEmpty()) { io.showMessage("No hay operaciones para revertir."); return; }
        Vehicle vehicle = undoStack.pop();
        io.showMessage("Revertido: " + vehicle);
    }

    /**
     * Muestra el historial del dia usando get(i) — sin imprimir desde el modelo.
     */
    private void mostrarHistorial() {
        io.showMessage("=== Historial dia " + currentDay + " ===");
        if (history.getSize() == 0) {
            io.showMessage("  (vacio)");
            return;
        }
        for (int i = 0; i < history.getSize(); i++) {
            io.showMessage("  " + history.get(i));
        }
    }

    // =========================================================
    // HELPERS
    // =========================================================

    private Queue<Vehicle> findShortestBooth() {
        Queue<Vehicle> shortest = booth1;
        if (booth2.getSize() < shortest.getSize()) shortest = booth2;
        if (booth3.getSize() < shortest.getSize()) shortest = booth3;
        if (booth4.getSize() < shortest.getSize()) shortest = booth4;
        return shortest;
    }

    private Queue<Vehicle> boothByNumber(int number) {
        switch (number) {
            case 1: return booth1;
            case 2: return booth2;
            case 3: return booth3;
            case 4: return booth4;
            default: return null;
        }
    }

    private Vehicle generarVehiculo(String timestamp) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) sb.append((char)('A' + random.nextInt(26)));
        for (int i = 0; i < 3; i++) sb.append(random.nextInt(10));
        int category = random.nextInt(3) + 1;
        return new Vehicle(sb.toString(), category, calcularPeaje(category), timestamp);
    }

    /**
     * Tarifas del Peaje T Tolima - La Linea (Tolima) 2026.
     */
    private double calcularPeaje(int category) {
        if (category == 1) return 13000;
        if (category == 2) return 14600;
        if (category == 3) return 29800;
        return 0;
    }
}
