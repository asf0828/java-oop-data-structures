package listasimple.vista;

import listasimple.modelo.Lista;
import listasimple.modelo.Nodo;
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
     * Muestra el contenido completo de una lista por consola.
     * @param lista La lista a mostrar
     */
    public void mostrarContenido(Lista lista) {
        if (lista.estaVacia()) {
            mostrarMensaje("La lista esta vacia.");
            return;
        }

        Nodo actual = lista.obtenerCabeza();
        int i = 0;
        while (actual != null) {
            System.out.println("Posicion " + i + ": " + actual.obtenerValor());
            actual = actual.obtenerSiguiente();
            i++;
        }
    }

    /**
     * Muestra un mensaje generico por consola.
     * @param mensaje El mensaje a mostrar
     */
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    /**
     * Muestra el resultado de un promedio calculado.
     * @param promedio El valor del promedio a mostrar
     */
    public void mostrarPromedio(double promedio) {
        System.out.println("\nNota final del estudiante (promedio): " + promedio);
    }

    /**
     * Muestra la informacion de un nodo encontrado.
     * @param nodo El nodo a mostrar
     */
    public void mostrarNodoEncontrado(Nodo nodo) {
        if (nodo != null) {
            System.out.println("Valor encontrado: " + nodo.obtenerValor());
        } else {
            System.out.println("Nodo no encontrado.");
        }
    }

    /**
     * Lee un numero entero ingresado por el usuario.
     * Incluye validacion para evitar errores si se ingresa texto.
     * @param mensaje El mensaje a mostrar antes de leer
     * @return El numero entero ingresado
     */
    public int leerEntero(String mensaje) {
        System.out.print(mensaje);
        while (!scanner.hasNextInt()) {
            System.out.print("Error: Debe ingresar un numero entero. Intente de nuevo: ");
            scanner.next(); // Limpiar entrada invalida
        }
        int valor = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        return valor;
    }

    /**
     * Cierra el scanner.
     */
    public void cerrar() {
        scanner.close();
    }
}
