package operacionespilas.controlador;

import operacionespilas.vista.Vista;

/**
 * Clase ControladorMenu gestiona el flujo principal de la aplicacion.
 * Muestra el menu de ejercicios, lee la opcion del usuario
 * y delega la ejecucion al controlador correspondiente.
 */
public class ControladorMenu {

    private Vista vista;
    private ControladorPalindromo palindromo;
    private ControladorCompararPilas compararPilas;
    private ControladorParentesis parentesis;

    /**
     * Constructor del controlador de menu.
     * @param vista La vista a utilizar para interactuar con el usuario
     * @param palindromo Controlador del ejercicio 1
     * @param compararPilas Controlador del ejercicio 2
     * @param parentesis Controlador del ejercicio 3
     */
    public ControladorMenu(Vista vista,
                           ControladorPalindromo palindromo,
                           ControladorCompararPilas compararPilas,
                           ControladorParentesis parentesis) {
        this.vista = vista;
        this.palindromo = palindromo;
        this.compararPilas = compararPilas;
        this.parentesis = parentesis;
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
                    palindromo.ejecutar();
                    break;
                case 2:
                    compararPilas.ejecutar();
                    break;
                case 3:
                    parentesis.ejecutar();
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
