package arbolesbinarios.view;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import arbolesbinarios.model.List;
import arbolesbinarios.model.ListNode;

/**
 * Maneja toda la entrada y salida por consola de la aplicación.
 *
 * Utiliza un {@code BufferedReader} compartido para leer desde la entrada
 * estándar. Los métodos de entrada declaran {@code throws IOException} para
 * que el controlador decida cómo manejar los errores de lectura.
 * No contiene lógica de negocio.
 */
public class IOManager {

    /** Lector compartido para la entrada estándar. */
    private static final BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));

    /**
     * Imprime un mensaje en consola seguido de un salto de línea.
     *
     * @param message el texto a mostrar
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Imprime un mensaje y retorna la línea ingresada por el usuario.
     *
     * @param message el mensaje a mostrar antes de leer
     * @return la línea leída
     * @throws IOException si no se puede leer desde la entrada estándar
     */
    public String getString(String message) throws IOException {
        System.out.println(message);
        return br.readLine();
    }

    /**
     * Imprime un mensaje y retorna el entero ingresado por el usuario.
     *
     * @param message el mensaje a mostrar antes de leer
     * @return el entero leído
     * @throws IOException si no se puede leer desde la entrada estándar
     */
    public int getInt(String message) throws IOException {
        System.out.println(message);
        return Integer.parseInt(br.readLine());
    }

    /**
     * Muestra todos los valores de una {@link List} seguidos del total de elementos.
     *
     * @param label el encabezado a imprimir antes de los valores
     * @param list  la lista cuyos valores se mostrarán
     */
    public void showList(String label, List list) {
        System.out.print(label + " ");
        ListNode current = list.getFirst();
        while (current != null) {
            System.out.print(current.getValue());
            if (current.getNext() != null) {
                System.out.print(", ");
            }
            current = current.getNext();
        }
        System.out.println("  (" + list.size() + " nodos)");
    }

    /**
     * Muestra una etiqueta seguida de un valor entero en una sola línea.
     *
     * @param label el encabezado a imprimir antes del entero
     * @param value el valor entero a mostrar
     */
    public void showInt(String label, int value) {
        System.out.println(label + " " + value);
    }
}
