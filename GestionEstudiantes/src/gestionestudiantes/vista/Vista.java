package gestionestudiantes.vista;

import gestionestudiantes.modelo.Cola;
import gestionestudiantes.modelo.Nodo;
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
    public String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }

    /**
     * Muestra un mensaje y lee un numero entero ingresado por el usuario.
     * Incluye validacion para evitar errores si se ingresa texto.
     * @param mensaje El mensaje a mostrar antes de leer
     * @return El numero entero ingresado por el usuario
     */
    public int leerEntero(String mensaje) {
        System.out.print(mensaje);
        while (!scanner.hasNextInt()) {
            System.out.print("Error: ingrese un numero valido: ");
            scanner.next();
        }
        int valor = scanner.nextInt();
        scanner.nextLine();
        return valor;
    }

    /**
     * Muestra el contenido de una cola de frente a fin, separado por comas.
     * El recorrido es no destructivo: no extrae elementos de la cola.
     * @param etiqueta El titulo o etiqueta a mostrar antes de la cola
     * @param cola     La cola cuyos elementos se mostraran
     */
    public void mostrarCola(String etiqueta, Cola cola) {
        System.out.print(etiqueta + " ");

        if (cola.estaVacia()) {
            System.out.println("(cola vacia)");
            return;
        }

        Nodo actual = cola.obtenerFrente();
        while (actual != null) {
            System.out.print(actual.obtenerValor().obtenerNombre()
                    + " (" + actual.obtenerValor().obtenerEdad() + ")");
            if (actual.obtenerSiguiente() != null) {
                System.out.print(", ");
            }
            actual = actual.obtenerSiguiente();
        }

        System.out.println();
    }

    /**
     * Muestra un mensaje generico por consola.
     * @param mensaje El mensaje a mostrar
     */
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    /**
     * Muestra una linea divisoria decorativa.
     */
    public void mostrarLinea() {
        System.out.println("----------------------------------------");
    }

}
