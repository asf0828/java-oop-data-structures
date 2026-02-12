package operacioneslistas.controlador;

import operacioneslistas.vista.Vista;

/**
 * Clase ControladorMenu gestiona el flujo principal de la aplicacion.
 * Muestra el menu de ejercicios, lee la opcion del usuario
 * y delega la ejecucion al controlador correspondiente.
 */
public class ControladorMenu {

    private Vista vista;
    private ControladorContarLetras contarLetras;
    private ControladorCompararListas compararListas;
    private ControladorEliminarRepetidos eliminarRepetidos;

    /**
     * Constructor del controlador de menu.
     * @param vista La vista a utilizar para interactuar con el usuario
     * @param contarLetras Controlador del ejercicio 1
     * @param compararListas Controlador del ejercicio 2
     * @param eliminarRepetidos Controlador del ejercicio 3
     */
    public ControladorMenu(Vista vista,
                           ControladorContarLetras contarLetras,
                           ControladorCompararListas compararListas,
                           ControladorEliminarRepetidos eliminarRepetidos) {
        this.vista = vista;
        this.contarLetras = contarLetras;
        this.compararListas = compararListas;
        this.eliminarRepetidos = eliminarRepetidos;
    }

    /**
     * Ejecuta el bucle principal del menu.
     * Permanece activo hasta que el usuario ingrese 0 para salir.
     */
    public void ejecutar() {
        int opcion = -1;
        while (opcion != 0) {
            opcion = vista.leerOpcionMenu();
            switch (opcion) {
                case 1:
                    contarLetras.ejecutar();
                    break;
                case 2:
                    compararListas.ejecutar();
                    break;
                case 3:
                    eliminarRepetidos.ejecutar();
                    break;
                case 0:
                    vista.mostrarMensaje("Hasta luego!");
                    vista.cerrar();
                    break;
                default:
                    vista.mostrarMensaje("Opcion no valida. Intente de nuevo.");
            }
        }
    }
}
