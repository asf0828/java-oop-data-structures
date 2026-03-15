
package view;

import controller.TreeController;

/**
 * Vista del sistema.
 * Interfaz por consola.
 */

public class ConsoleView {

    private TreeController controller;

    public ConsoleView(){
        controller = new TreeController();
    }

    public void start(){
        controller.ejecutar();
    }
}
