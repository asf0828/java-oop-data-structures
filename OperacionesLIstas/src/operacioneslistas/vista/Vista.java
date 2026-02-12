package operacioneslistas.vista;

import operacioneslistas.modelo.Lista;
import operacioneslistas.modelo.Nodo;
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
     * Muestra el menu principal y lee la opcion ingresada por el usuario.
     * Incluye validacion para evitar errores si se ingresa texto.
     * @return El numero entero correspondiente a la opcion seleccionada
     */
    public int leerOpcionMenu() {
        System.out.println("\n==============================");
        System.out.println("  Seleccione un ejercicio:");
        System.out.println("  1. Contar letra 'a' en una frase");
        System.out.println("  2. Comparar dos listas");
        System.out.println("  3. Eliminar repetidos de una lista");
        System.out.println("  0. Salir");
        System.out.println("==============================");
        System.out.print("Ingrese su opcion: ");
        while (!scanner.hasNextInt()) {
            System.out.print("Error: ingrese un numero valido: ");
            scanner.next();
        }
        int opcion = scanner.nextInt();
        scanner.nextLine();
        return opcion;
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
     * Muestra el contenido de una lista en una sola linea, separado por comas.
     * @param nombre El titulo o etiqueta a mostrar antes de la lista
     * @param lista La lista cuyos elementos se mostraran
     */
    public void mostrarLista(String nombre, Lista lista) {
        System.out.print(nombre + " ");
        if (lista.estaVacia()) {
            System.out.println("(lista vacia)");
        } else {
            Nodo actual = lista.obtenerCabeza();
            while (actual != null) {
                System.out.print(actual.obtenerValor());
                if (actual.obtenerSiguiente() != null) {
                    System.out.print(", ");
                }
                actual = actual.obtenerSiguiente();
            }
            System.out.println();
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
     * Cierra el scanner.
     */
    public void cerrar() {
        scanner.close();
    }
}
