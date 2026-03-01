package turnosbanco.vista;

import turnosbanco.modelo.Cola;
import turnosbanco.modelo.Nodo;
import java.util.Scanner;

/**
 * Clase Vista maneja la interaccion con el usuario.
 * Se encarga de mostrar informacion y capturar entradas.
 * Esta clase pertenece a la vista en la arquitectura MVC.
 */
public class Vista {

    private Scanner scanner;

    /**
     * Constructor de la vista.
     * Inicializa el scanner para capturar entradas del usuario.
     */
    public Vista() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Muestra un mensaje y lee una linea de texto ingresada por el usuario.
     * @param mensaje El mensaje a mostrar antes de leer
     * @return El texto ingresado por el usuario
     */
    public String leerTexto(String mensaje) { return null; }

    /**
     * Muestra un mensaje y lee un numero entero ingresado por el usuario.
     * Incluye validacion para evitar errores si se ingresa texto.
     * @param mensaje El mensaje a mostrar antes de leer
     * @return El numero entero ingresado por el usuario
     */
    public int leerEntero(String mensaje) { return 0; }

    /**
     * Muestra el contenido de una fila de frente a fin, separado por comas.
     * El recorrido es no destructivo: no extrae elementos de la cola.
     * @param etiqueta El titulo o etiqueta a mostrar antes de la fila
     * @param cola     La cola cuyos elementos se mostraran
     */
    public void mostrarFila(String etiqueta, Cola cola) { }

    /**
     * Muestra un mensaje generico por consola.
     * @param mensaje El mensaje a mostrar
     */
    public void mostrarMensaje(String mensaje) { }

    /**
     * Muestra una linea divisoria decorativa.
     */
    public void mostrarLinea() { }

}
