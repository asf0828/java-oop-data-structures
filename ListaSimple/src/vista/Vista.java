package vista;

import modelo.Lista;
import modelo.Nodo;
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

        Nodo actual = lista.getCabeza();
        int i = 0;
        while (actual != null) {
            System.out.println("Posicion " + i + ": " + actual.getValor());
            actual = actual.getSiguiente();
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
     * Muestra un mensaje sin salto de linea.
     * @param mensaje El mensaje a mostrar
     */
    public void mostrarSinSalto(String mensaje) {
        System.out.print(mensaje);
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
            System.out.println("Valor encontrado: " + nodo.getValor());
        } else {
            System.out.println("Nodo no encontrado.");
        }
    }

    /**
     * Muestra el tamano de una lista.
     * @param tamano El tamano de la lista
     */
    public void mostrarTamano(int tamano) {
        System.out.println("Tamano de la lista: " + tamano);
    }

    /**
     * Lee un numero entero ingresado por el usuario.
     * @param mensaje El mensaje a mostrar antes de leer
     * @return El numero entero ingresado
     */
    public int leerEntero(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextInt();
    }

    /**
     * Cierra el scanner.
     */
    public void cerrar() {
        scanner.close();
    }
}
