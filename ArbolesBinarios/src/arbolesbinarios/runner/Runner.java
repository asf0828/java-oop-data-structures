package arbolesbinarios.runner;

import arbolesbinarios.controller.TreeController;
import arbolesbinarios.view.IOManager;

/**
 * Punto de entrada de la aplicación.
 *
 * Sigue el patrón MVC: crea la vista, la inyecta en el controlador
 * y delega la ejecución llamando a {@code execute()}.
 * No contiene lógica de negocio.
 *
 * Integrantes del equipo:
 * - Maria Elena Molina Torres. Codigo 200631
 * - Alexander Jose Sandoval Figueredo. Codigo 200582
 * - Yarik Valeria Serrano Recaman. Codigo 200583
 */
public class Runner {

    /**
     * Método principal de la aplicación.
     *
     * @param args argumentos de línea de comandos (no se usan)
     */
    public static void main(String[] args) {
        IOManager io = new IOManager();
        TreeController controller = new TreeController(io);
        try {
            controller.execute();
        } catch (Exception e) {
            System.err.println("Error leyendo entrada: " + e.getMessage());
        }
    }
}
