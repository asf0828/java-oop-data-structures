package listasimple.aplicacion;

import listasimple.controlador.Controlador;
import listasimple.vista.Vista;

/**
 * Clase Main - Punto de entrada de la aplicacion.
 * Inicializa los componentes MVC y ejecuta el controlador.
 * 
 */
public class Main {
    /**
     * Metodo principal que inicia la aplicacion.
     * @param args Argumentos de linea de comandos (no utilizados)
     * Integrantes del equipo:
     *  - Maria Elena Molina Torres. Código 200631
     *  - Alexander José Sandoval Figueredo. Código 200582
     *  - Yarik Valeria Serrano Recamán. Código 200583
     */
    public static void main(String[] args) {
        // Instanciar la vista
        Vista vista = new Vista();

        // Instanciar el controlador con la vista
        Controlador controlador = new Controlador(vista);

        // Ejecutar la aplicacion
        controlador.ejecutar();
    }
}
