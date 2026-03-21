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
        System.out.println("=== Peaje Inteligente ===");
        System.out.println("1. Registrar vehiculo");
        System.out.println("2. Atender vehiculo");
        System.out.println("3. Revertir ultima atencion");
        System.out.println("4. Ver historial");
        System.out.println("5. Salir");
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
}
