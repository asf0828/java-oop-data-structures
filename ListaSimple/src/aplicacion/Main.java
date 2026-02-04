package aplicacion;

import controlador.Controlador;
import vista.Vista;

/**
 * Clase Main - Punto de entrada de la aplicacion.
 * Inicializa los componentes MVC y ejecuta el controlador.
 * 
 */
public class Main {
    /**
     * Metodo principal que inicia la aplicacion.
     * @param args Argumentos de linea de comandos (no utilizados)
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
