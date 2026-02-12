package operacioneslistas.runner;

import operacioneslistas.controlador.ControladorCompararListas;
import operacioneslistas.controlador.ControladorContarLetras;
import operacioneslistas.controlador.ControladorEliminarRepetidos;
import operacioneslistas.controlador.ControladorMenu;
import operacioneslistas.vista.Vista;

/**
 * Clase Runner - Punto de entrada de la aplicacion.
 * Inicializa los componentes MVC y ejecuta el controlador de menu.
 *
 * Integrantes del equipo:
 *  - Maria Elena Molina Torres. Codigo 200631
 *  - Alexander Jose Sandoval Figueredo. Codigo 200582
 *  - Yarik Valeria Serrano Recaman. Codigo 200583
 */
public class Runner {

    /**
     * Metodo principal que inicia la aplicacion.
     * @param args Argumentos de linea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        // Instanciar la vista
        Vista vista = new Vista();

        // Instanciar los controladores con la vista
        ControladorContarLetras contarLetras = new ControladorContarLetras(vista);
        ControladorCompararListas compararListas = new ControladorCompararListas(vista);
        ControladorEliminarRepetidos eliminarRepetidos = new ControladorEliminarRepetidos(vista);

        // Instanciar el controlador de menu y ejecutar la aplicacion
        ControladorMenu menu = new ControladorMenu(vista, contarLetras, compararListas, eliminarRepetidos);
        menu.ejecutar();
    }
}
