package peajeinteligente.view;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * Gestiona toda la entrada y salida del programa.
 * Solo contiene metodos para pedir y mostrar datos. Sin logica de negocio.
 */
public class IOManager {

    /** Lector de entrada estandar. */
    private static final BufferedReader reader =
            new BufferedReader(new InputStreamReader(System.in));

    // =========================================================
    // MENUS
    // =========================================================

    /**
     * Muestra el menu principal y retorna la opcion seleccionada.
     *
     * @return opcion ingresada por el usuario
     * @throws IOException si ocurre un error de lectura
     */
    public int showMenu() throws IOException {
        System.out.println();
        System.out.println("=== Peaje Inteligente ===");
        System.out.println("1. Registrar vehiculo (manual)");
        System.out.println("2. Registrar vehiculos (automatico)");
        System.out.println("3. Ver estado actual");
        System.out.println("4. Atender caseta");
        System.out.println("5. Revertir ultima atencion");
        System.out.println("6. Ver historial del dia");
        System.out.println("7. Reportes");
        System.out.println("0. Salir");
        System.out.print("Opcion: ");
        return Integer.parseInt(reader.readLine().trim());
    }

    /**
     * Muestra el submenu de reportes con el dia actual e retorna la opcion.
     *
     * @param currentDay numero del dia actual dentro de la semana
     * @return opcion ingresada por el usuario
     * @throws IOException si ocurre un error de lectura
     */
    public int showReportMenu(int currentDay) throws IOException {
        System.out.println();
        System.out.println("=== Reportes  [Dia actual: " + currentDay + "] ===");
        System.out.println("1. Reporte de recaudo del dia actual");
        System.out.println("2. Cerrar dia (arqueo de caja)");
        System.out.println("3. Reporte semanal del supervisor");
        System.out.println("0. Volver");
        System.out.print("Opcion: ");
        return Integer.parseInt(reader.readLine().trim());
    }

    // =========================================================
    // ENTRADA
    // =========================================================

    /**
     * Muestra un mensaje y lee una linea de texto.
     *
     * @param prompt texto que se muestra antes de leer
     * @return cadena ingresada por el usuario
     * @throws IOException si ocurre un error de lectura
     */
    public String getString(String prompt) throws IOException {
        System.out.print(prompt);
        return reader.readLine().trim();
    }

    /**
     * Muestra un mensaje y lee un numero entero.
     *
     * @param prompt texto que se muestra antes de leer
     * @return entero ingresado por el usuario
     * @throws IOException si ocurre un error de lectura
     */
    public int getInt(String prompt) throws IOException {
        System.out.print(prompt);
        return Integer.parseInt(reader.readLine().trim());
    }

    // =========================================================
    // SALIDA GENERAL
    // =========================================================

    /**
     * Imprime un mensaje en la consola.
     *
     * @param message mensaje a mostrar
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Muestra el estado actual de las cuatro casetas, la pila de deshacer
     * y el total de vehiculos atendidos en el dia.
     *
     * @param size1       vehiculos en espera en caseta 1
     * @param size2       vehiculos en espera en caseta 2
     * @param size3       vehiculos en espera en caseta 3
     * @param size4       vehiculos en espera en caseta 4
     * @param undoSize    operaciones disponibles para revertir
     * @param historySize vehiculos atendidos en el dia actual
     */
    public void showState(int size1, int size2, int size3, int size4,
                          int undoSize, int historySize) {
        System.out.println();
        System.out.println("=== Estado actual ===");
        System.out.println("Caseta 1: " + size1 + " vehiculo(s) en espera");
        System.out.println("Caseta 2: " + size2 + " vehiculo(s) en espera");
        System.out.println("Caseta 3: " + size3 + " vehiculo(s) en espera");
        System.out.println("Caseta 4: " + size4 + " vehiculo(s) en espera");
        System.out.println("Pila deshacer : " + undoSize);
        System.out.println("Historial dia : " + historySize + " vehiculo(s) atendido(s)");
    }

    // =========================================================
    // REPORTE 1 — Recaudo del dia
    // Paso 7: firma con variables individuales, sin arrays
    // =========================================================

    /**
     * Muestra el reporte de recaudo del dia con totales por caseta y por categoria.
     *
     * @param day          numero del dia
     * @param totalCaseta1 recaudo de caseta 1
     * @param vehicCaseta1 vehiculos atendidos en caseta 1
     * @param totalCaseta2 recaudo de caseta 2
     * @param vehicCaseta2 vehiculos atendidos en caseta 2
     * @param totalCaseta3 recaudo de caseta 3
     * @param vehicCaseta3 vehiculos atendidos en caseta 3
     * @param totalCaseta4 recaudo de caseta 4
     * @param vehicCaseta4 vehiculos atendidos en caseta 4
     * @param totalCat1    recaudo categoria 1
     * @param vehicCat1    vehiculos categoria 1
     * @param totalCat2    recaudo categoria 2
     * @param vehicCat2    vehiculos categoria 2
     * @param totalCat3    recaudo categoria 3
     * @param vehicCat3    vehiculos categoria 3
     * @param totalDia     total general del dia
     */
    public void showDayReport(int day,
                              double totalCaseta1, int vehicCaseta1,
                              double totalCaseta2, int vehicCaseta2,
                              double totalCaseta3, int vehicCaseta3,
                              double totalCaseta4, int vehicCaseta4,
                              double totalCat1, int vehicCat1,
                              double totalCat2, int vehicCat2,
                              double totalCat3, int vehicCat3,
                              double totalDia) {
        System.out.println();
        System.out.println("============================================");
        System.out.println("     REPORTE DE RECAUDO  —  DIA " + day);
        System.out.println("============================================");
        System.out.println();
        System.out.println("  Por caseta:");
        System.out.println("    Caseta 1 : " + vehicCaseta1 + " vehiculo(s)  |  $ " + (long) totalCaseta1);
        System.out.println("    Caseta 2 : " + vehicCaseta2 + " vehiculo(s)  |  $ " + (long) totalCaseta2);
        System.out.println("    Caseta 3 : " + vehicCaseta3 + " vehiculo(s)  |  $ " + (long) totalCaseta3);
        System.out.println("    Caseta 4 : " + vehicCaseta4 + " vehiculo(s)  |  $ " + (long) totalCaseta4);
        System.out.println();
        System.out.println("  Por categoria:");
        System.out.println("    Cat. I   (Livianos)         : " + vehicCat1 + " vehiculo(s)  |  $ " + (long) totalCat1);
        System.out.println("    Cat. II  (Buses/Microbuses) : " + vehicCat2 + " vehiculo(s)  |  $ " + (long) totalCat2);
        System.out.println("    Cat. III (Camiones)         : " + vehicCat3 + " vehiculo(s)  |  $ " + (long) totalCat3);
        System.out.println();
        System.out.println("============================================");
        System.out.println("  TOTAL DEL DIA : $ " + (long) totalDia);
        System.out.println("============================================");
    }

    // =========================================================
    // REPORTE 3 — Historico semanal del supervisor
    // =========================================================

    /**
     * Muestra el encabezado del reporte semanal del supervisor.
     *
     * @param diasCerrados cantidad de dias cerrados en la semana actual
     */
    public void showWeeklyReportHeader(int diasCerrados) {
        System.out.println();
        System.out.println("############################################");
        System.out.println("#      REPORTE SEMANAL — SUPERVISOR        #");
        System.out.println("#      Dias cerrados: " + diasCerrados + "                    #");
        System.out.println("############################################");
    }

    /**
     * Encabezado de cada registro en el reporte semanal.
     * Muestra caseta, dia y cantidad de vehiculos.
     *
     * @param boothNum   numero de caseta
     * @param dayNumber  numero de dia
     * @param count      vehiculos atendidos ese dia en esa caseta
     */
    public void showBoothWeeklyHeader(int boothNum, int dayNumber, int count) {
        System.out.println();
        System.out.println("  ==========================================");
        System.out.println("  Caseta " + boothNum + "  —  Dia " + dayNumber
                + "  (" + count + " vehiculo(s))");
        System.out.println("  ==========================================");
    }

    /**
     * Muestra el total recaudado por una caseta en un dia del reporte semanal.
     *
     * @param boothNum numero de caseta
     * @param total    total recaudado
     */
    public void showBoothWeeklyTotal(int boothNum, double total) {
        System.out.println("  ------------------------------------------");
        System.out.println("  Total caseta " + boothNum + " : $ " + (long) total);
    }

    /**
     * Muestra el total general de recaudo de la semana.
     *
     * @param total total recaudado en la semana
     */
    public void showWeeklyGrandTotal(double total) {
        System.out.println();
        System.out.println("############################################");
        System.out.println("  TOTAL GENERAL SEMANA : $ " + (long) total);
        System.out.println("############################################");
    }
}
