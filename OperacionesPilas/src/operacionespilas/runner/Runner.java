package operacionespilas.runner;

import operacionespilas.controlador.ControladorCompararPilas;
import operacionespilas.controlador.ControladorMenu;
import operacionespilas.controlador.ControladorPalindromo;
import operacionespilas.controlador.ControladorParentesis;
import operacionespilas.vista.Vista;

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
        ControladorPalindromo palindromo = new ControladorPalindromo(vista);
        ControladorCompararPilas compararPilas = new ControladorCompararPilas(vista);
        ControladorParentesis parentesis = new ControladorParentesis(vista);

        // Instanciar el controlador de menu y ejecutar la aplicacion
        ControladorMenu menu = new ControladorMenu(vista, palindromo, compararPilas, parentesis);
        menu.ejecutar();
    }
}
