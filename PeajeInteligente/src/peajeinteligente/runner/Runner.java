package peajeinteligente.runner;

import peajeinteligente.controller.Controller;
import peajeinteligente.view.IOManager;

import java.io.IOException;

/**
 * Punto de entrada de la aplicacion Peaje Inteligente.
 * Instancia el gestor de I/O y el controlador, luego delega la ejecucion.
 *
 * <p>Integrantes:
 * <ul>
 *   <li>Maria Elena Molina Torres (200631)</li>
 *   <li>Alexander Jose Sandoval Figueredo (200582)</li>
 *   <li>Yarik Valeria Serrano Recaman (200583)</li>
 * </ul>
 */
public class Runner {

    /**
     * Metodo principal.
     *
     * @param args argumentos de linea de comandos (no se usan)
     */
    public static void main(String[] args) {
        IOManager io = new IOManager();
        Controller controller = new Controller(io);
        try {
            controller.ejecutar();
        } catch (IOException e) {
            System.out.println("Error de entrada: " + e.getMessage());
        }
    }
}
