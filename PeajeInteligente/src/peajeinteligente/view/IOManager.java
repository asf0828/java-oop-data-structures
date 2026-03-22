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

    public String getString(String prompt) throws IOException {
        System.out.print(prompt);
        return reader.readLine().trim();
    }

    public int getInt(String prompt) throws IOException {
        System.out.print(prompt);
        return Integer.parseInt(reader.readLine().trim());
    }

    // =========================================================
    // SALIDA GENERAL
    // =========================================================

    public void showMessage(String message) {
        System.out.println(message);
    }

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

    public void showBoothWeeklyTotal(int boothNum, double total) {
        System.out.println("  ------------------------------------------");
        System.out.println("  Total caseta " + boothNum + " : $ " + (long) total);
    }

    public void showWeeklyGrandTotal(double total) {
        System.out.println();
        System.out.println("############################################");
        System.out.println("  TOTAL GENERAL SEMANA : $ " + (long) total);
        System.out.println("############################################");
    }
}
