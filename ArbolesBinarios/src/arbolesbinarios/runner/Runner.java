package arbolesbinarios.runner;

import arbolesbinarios.controller.TreeController;
import arbolesbinarios.view.IOManager;

/**
 * Runner is the entry point of the application.
 *
 * <p>It follows the MVC wiring pattern: create the view first,
 * inject it into the controller, then call {@code execute()}.</p>
 *
 * <p>No business logic lives here — Runner only assembles the
 * three MVC layers and starts execution.</p>
 *
 * <p>Course: Programacion III — Taller III (Arboles) — 2026-I</p>
 *
 * <p>Team members:</p>
 * <ul>
 *   <li>Maria Elena Molina Torres. Codigo 200631</li>
 *   <li>Alexander Jose Sandoval Figueredo. Codigo 200582</li>
 *   <li>Yarik Valeria Serrano Recaman. Codigo 200583</li>
 * </ul>
 */
public class Runner {

    /**
     * Application entry point.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        IOManager io = new IOManager();
        TreeController controller = new TreeController(io);
        try {
            controller.execute();
        } catch (Exception e) {
            System.err.println("Error reading input: " + e.getMessage());
        }
    }
}
