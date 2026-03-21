package peajeinteligente.view;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * Gestiona toda la entrada y salida del programa.
 * Utiliza {@link BufferedReader} para la lectura de datos del usuario.
 * No contiene logica de negocio.
 */
public class IOManager {

    /** Lector de entrada estandar. */
    private static final BufferedReader reader =
            new BufferedReader(new InputStreamReader(System.in));

    /**
     * Muestra el menu principal y retorna la opcion seleccionada.
     *
     * @return numero de opcion elegida por el usuario
     * @throws IOException si ocurre un error de lectura
     */
    public int showMenu() throws IOException {
        System.out.println();
        System.out.println("=== Peaje Inteligente ===");
        System.out.println("1. Registrar vehiculo (manual)");
        System.out.println("2. Registrar vehiculos (automatico: placa y categoria aleatorias)");
        System.out.println("3. Ver estado actual");
        System.out.println("4. Atender caseta");
        System.out.println("5. Revertir ultima atencion");
        System.out.println("6. Ver historial");
        System.out.println("0. Salir");
        System.out.print("Opcion: ");
        return Integer.parseInt(reader.readLine().trim());
    }

    /**
     * Lee una cadena de texto con el mensaje indicado como prompt.
     *
     * @param prompt mensaje a mostrar antes de leer
     * @return texto ingresado por el usuario
     * @throws IOException si ocurre un error de lectura
     */
    public String getString(String prompt) throws IOException {
        System.out.print(prompt);
        return reader.readLine().trim();
    }

    /**
     * Lee un numero entero con el mensaje indicado como prompt.
     *
     * @param prompt mensaje a mostrar antes de leer
     * @return entero ingresado por el usuario
     * @throws IOException si ocurre un error de lectura
     */
    public int getInt(String prompt) throws IOException {
        System.out.print(prompt);
        return Integer.parseInt(reader.readLine().trim());
    }

    /**
     * Muestra un mensaje por consola.
     *
     * @param message mensaje a mostrar
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Muestra el estado actual de las 4 casetas, la pila de deshacer y el historial.
     * Solo muestra cantidades — no accede al contenido de las estructuras.
     *
     * @param size1       vehiculos en caseta 1
     * @param size2       vehiculos en caseta 2
     * @param size3       vehiculos en caseta 3
     * @param size4       vehiculos en caseta 4
     * @param undoSize    operaciones reversibles en la pila
     * @param historySize vehiculos registrados en el historial
     */
    public void showState(int size1, int size2, int size3, int size4,
                          int undoSize, int historySize) {
        System.out.println();
        System.out.println("=== Estado actual ===");
        System.out.println("Caseta 1: " + size1 + " vehiculo(s)");
        System.out.println("Caseta 2: " + size2 + " vehiculo(s)");
        System.out.println("Caseta 3: " + size3 + " vehiculo(s)");
        System.out.println("Caseta 4: " + size4 + " vehiculo(s)");
        System.out.println("Pila deshacer: " + undoSize
                + "  |  Historial: " + historySize);
    }
}
