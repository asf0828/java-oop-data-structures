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
 * Mantiene cuatro listas diarias separadas (una por caseta). El vehiculo
 * no sabe por cual caseta paso; el Controller organiza esa informacion
 * con listas independientes, eliminando la necesidad de filtrar.
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

    /**
     * Pila paralela al undoStack: registra el numero de caseta (1-4)
     * de cada vehiculo apilado, para saber de cual histCaseta removerlo
     * al revertir.
     */
    private Stack<Integer> undoBooth;

    // --- Paso 2: cuatro listas diarias, una por caseta ---
    private List<Vehicle> histCaseta1;
    private List<Vehicle> histCaseta2;
    private List<Vehicle> histCaseta3;
    private List<Vehicle> histCaseta4;

    /**
     * Semana actual: lista de DailyRecord cerrados.
     * Maximo 28 registros (4 casetas x 7 dias).
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
        this.io    = io;
        booth1     = new Queue<>();
        booth2     = new Queue<>();
        booth3     = new Queue<>();
        booth4     = new Queue<>();
        undoStack  = new Stack<>();
        undoBooth  = new Stack<>();
        histCaseta1 = new List<>();
        histCaseta2 = new List<>();
        histCaseta3 = new List<>();
        histCaseta4 = new List<>();
        week       = new List<>();
        currentDay = 1;
        random     = new Random();
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
                case 1: registrar();          break;
                case 2: registrarAleatorio(); break;
                case 3: mostrarEstado();      break;
                case 4: atender();            break;
                case 5: revertir();           break;
                case 6: mostrarHistorial();   break;
                case 7: menuReportes();       break;
                case 0: io.showMessage("Hasta luego."); break;
                default: io.showMessage("Opcion no valida.");
            }
        } while (op != 0);
    }

    // =========================================================
    // MENU DE REPORTES (opcion 7)
    // =========================================================

    /**
     * Submenú de reportes con su propio do-while y switch.
     *
     * @throws IOException si ocurre un error de lectura
     */
    private void menuReportes() throws IOException {
        int op;
        do {
            op = io.showReportMenu(currentDay);
            switch (op) {
                case 1: reporteRecaudoDia(); break;
                case 2: cerrarDia();         break;
                case 3: reporteSemanal();    break;
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
     * Paso 4: itera cada lista por separado y acumula con variables
     * nombradas — sin arrays, sin filtrar por booth.
     */
    private void reporteRecaudoDia() {
        int total = histCaseta1.getSize() + histCaseta2.getSize()
                  + histCaseta3.getSize() + histCaseta4.getSize();
        if (total == 0) {
            io.showMessage("No hay vehiculos atendidos en el dia actual.");
            return;
        }

        double totalCaseta1 = 0; int vehicCaseta1 = 0;
        double totalCaseta2 = 0; int vehicCaseta2 = 0;
        double totalCaseta3 = 0; int vehicCaseta3 = 0;
        double totalCaseta4 = 0; int vehicCaseta4 = 0;
        double totalCat1 = 0;   int vehicCat1 = 0;
        double totalCat2 = 0;   int vehicCat2 = 0;
        double totalCat3 = 0;   int vehicCat3 = 0;
        double totalDia  = 0;

        for (int i = 0; i < histCaseta1.getSize(); i++) {
            Vehicle v = histCaseta1.get(i);
            totalCaseta1 += v.getToll(); vehicCaseta1++;
            totalDia     += v.getToll();
            if      (v.getCategory() == 1) { totalCat1 += v.getToll(); vehicCat1++; }
            else if (v.getCategory() == 2) { totalCat2 += v.getToll(); vehicCat2++; }
            else if (v.getCategory() == 3) { totalCat3 += v.getToll(); vehicCat3++; }
        }
        for (int i = 0; i < histCaseta2.getSize(); i++) {
            Vehicle v = histCaseta2.get(i);
            totalCaseta2 += v.getToll(); vehicCaseta2++;
            totalDia     += v.getToll();
            if      (v.getCategory() == 1) { totalCat1 += v.getToll(); vehicCat1++; }
            else if (v.getCategory() == 2) { totalCat2 += v.getToll(); vehicCat2++; }
            else if (v.getCategory() == 3) { totalCat3 += v.getToll(); vehicCat3++; }
        }
        for (int i = 0; i < histCaseta3.getSize(); i++) {
            Vehicle v = histCaseta3.get(i);
            totalCaseta3 += v.getToll(); vehicCaseta3++;
            totalDia     += v.getToll();
            if      (v.getCategory() == 1) { totalCat1 += v.getToll(); vehicCat1++; }
            else if (v.getCategory() == 2) { totalCat2 += v.getToll(); vehicCat2++; }
            else if (v.getCategory() == 3) { totalCat3 += v.getToll(); vehicCat3++; }
        }
        for (int i = 0; i < histCaseta4.getSize(); i++) {
            Vehicle v = histCaseta4.get(i);
            totalCaseta4 += v.getToll(); vehicCaseta4++;
            totalDia     += v.getToll();
            if      (v.getCategory() == 1) { totalCat1 += v.getToll(); vehicCat1++; }
            else if (v.getCategory() == 2) { totalCat2 += v.getToll(); vehicCat2++; }
            else if (v.getCategory() == 3) { totalCat3 += v.getToll(); vehicCat3++; }
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
     * Cierra el dia actual.
     * Paso 5: cada DailyRecord se construye directamente desde su lista,
     * sin filtrar por caseta.
     */
    private void cerrarDia() {
        int total = histCaseta1.getSize() + histCaseta2.getSize()
                  + histCaseta3.getSize() + histCaseta4.getSize();
        if (total == 0) {
            io.showMessage("No hay vehiculos atendidos. No se puede cerrar el dia.");
            return;
        }

        DailyRecord rec1 = buildRecord(1, currentDay, histCaseta1);
        DailyRecord rec2 = buildRecord(2, currentDay, histCaseta2);
        DailyRecord rec3 = buildRecord(3, currentDay, histCaseta3);
        DailyRecord rec4 = buildRecord(4, currentDay, histCaseta4);

        if (rec1.getVehicleCount() > 0) week.add(rec1);
        if (rec2.getVehicleCount() > 0) week.add(rec2);
        if (rec3.getVehicleCount() > 0) week.add(rec3);
        if (rec4.getVehicleCount() > 0) week.add(rec4);

        io.showMessage("Arqueo de caja completado. Dia " + currentDay + " cerrado.");

        if (currentDay >= 7) {
            io.showMessage("Semana completa. Reiniciando semana...");
            week       = new List<>();
            currentDay = 1;
        } else {
            currentDay++;
        }

        booth1      = new Queue<>();
        booth2      = new Queue<>();
        booth3      = new Queue<>();
        booth4      = new Queue<>();
        histCaseta1 = new List<>();
        histCaseta2 = new List<>();
        histCaseta3 = new List<>();
        histCaseta4 = new List<>();
        undoStack   = new Stack<>();
        undoBooth   = new Stack<>();
        io.showMessage("Sistema listo para el dia " + currentDay + ".");
    }

    /**
     * Crea un DailyRecord para la caseta y dia indicados, poblado con
     * los vehiculos de la lista correspondiente.
     *
     * @param boothNum numero de caseta
     * @param day      numero de dia
     * @param hist     lista de vehiculos de esa caseta
     * @return DailyRecord listo para guardar en la semana
     */
    private DailyRecord buildRecord(int boothNum, int day, List<Vehicle> hist) {
        DailyRecord rec = new DailyRecord(boothNum, day);
        for (int i = 0; i < hist.getSize(); i++) {
            rec.addVehicle(hist.get(i));
        }
        return rec;
    }

    // =========================================================
    // REPORTE 3 — Historico semanal del supervisor
    // =========================================================

    /**
     * Muestra el reporte semanal del supervisor.
     * Paso 6: sin continue ni boolean tieneDatos — cada DailyRecord
     * ya pertenece a una caseta especifica, no hay que filtrar.
     * La pila se vacia con pop() mostrando del ultimo al primero (LIFO).
     */
    private void reporteSemanal() {
        if (week.getSize() == 0) {
            io.showMessage("No hay dias cerrados esta semana aun.");
            return;
        }

        io.showWeeklyReportHeader(currentDay - 1);

        for (int i = 0; i < week.getSize(); i++) {
            DailyRecord rec = week.get(i);

            io.showBoothWeeklyHeader(rec.getBoothNumber(), rec.getDayNumber(),
                    rec.getVehicleCount());

            Stack<Vehicle> pila = rec.getVehicles();
            while (!pila.isEmpty()) {
                io.showMessage("    " + pila.pop());
            }

            io.showBoothWeeklyTotal(rec.getBoothNumber(), rec.getTotal());
        }

        double granTotal = 0;
        for (int i = 0; i < week.getSize(); i++) {
            granTotal += week.get(i).getTotal();
        }
        io.showWeeklyGrandTotal(granTotal);
    }

    // =========================================================
    // OPERACIONES BASICAS
    // =========================================================

    private void registrar() throws IOException {
        String plate     = io.getString("Placa: ");
        int category     = io.getInt("Categoria (1-3): ");
        double toll      = calcularPeaje(category);
        String timestamp = LocalTime.now().format(FORMATO_HORA);
        Vehicle vehicle  = new Vehicle(plate, category, toll, timestamp);
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
        int histTotal = histCaseta1.getSize() + histCaseta2.getSize()
                      + histCaseta3.getSize() + histCaseta4.getSize();
        io.showState(booth1.getSize(), booth2.getSize(),
                booth3.getSize(), booth4.getSize(),
                undoStack.getSize(), histTotal);
    }

    /**
     * Atiende todos los vehiculos de la caseta seleccionada en orden FIFO.
     * Paso 3: cada vehiculo se agrega directamente a la lista de su caseta.
     * No se asigna ningun campo booth al vehiculo.
     *
     * @throws IOException si ocurre un error de lectura
     */
    private void atender() throws IOException {
        int boothNum = io.getInt("Caseta (1-4): ");
        Queue<Vehicle> booth   = boothByNumber(boothNum);
        List<Vehicle>  histDia = histByNumber(boothNum);

        if (booth == null) { io.showMessage("Caseta invalida."); return; }
        if (booth.isEmpty()) {
            io.showMessage("La caseta " + boothNum + " esta vacia.");
            return;
        }

        io.showMessage("=== Atendiendo caseta " + boothNum + " ===");
        while (!booth.isEmpty()) {
            Vehicle vehicle = booth.dequeue();
            undoStack.push(vehicle);
            undoBooth.push(boothNum);
            histDia.add(vehicle);
            io.showMessage("  Atendido: " + vehicle);
        }
    }

    private void revertir() {
        if (undoStack.isEmpty()) {
            io.showMessage("No hay operaciones para revertir.");
            return;
        }
        Vehicle vehicle = undoStack.pop();
        int boothNum    = undoBooth.pop();
        histByNumber(boothNum).removeLast();
        io.showMessage("Revertido: " + vehicle);
    }

    /**
     * Muestra el historial del dia por caseta.
     */
    private void mostrarHistorial() {
        io.showMessage("=== Historial dia " + currentDay + " ===");
        mostrarListaCaseta(1, histCaseta1);
        mostrarListaCaseta(2, histCaseta2);
        mostrarListaCaseta(3, histCaseta3);
        mostrarListaCaseta(4, histCaseta4);
    }

    private void mostrarListaCaseta(int num, List<Vehicle> hist) {
        if (hist.getSize() == 0) return;
        io.showMessage("  -- Caseta " + num + " --");
        for (int i = 0; i < hist.getSize(); i++) {
            io.showMessage("    " + hist.get(i));
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

    private List<Vehicle> histByNumber(int number) {
        switch (number) {
            case 1: return histCaseta1;
            case 2: return histCaseta2;
            case 3: return histCaseta3;
            case 4: return histCaseta4;
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

    /** Tarifas del Peaje La Linea (Tolima) 2026. */
    private double calcularPeaje(int category) {
        if (category == 1) return 13000;
        if (category == 2) return 14600;
        if (category == 3) return 29800;
        return 0;
    }
}
