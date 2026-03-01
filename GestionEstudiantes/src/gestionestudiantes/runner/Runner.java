package gestionestudiantes.runner;

import gestionestudiantes.controlador.ControladorGestion;
import gestionestudiantes.vista.Vista;

/**
 * Clase Runner - Punto de entrada de la aplicacion.
 * Inicializa los componentes MVC y ejecuta el controlador.
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
        Vista vista = new Vista();
        ControladorGestion controlador = new ControladorGestion(vista);
        controlador.ejecutar();
    }
}
