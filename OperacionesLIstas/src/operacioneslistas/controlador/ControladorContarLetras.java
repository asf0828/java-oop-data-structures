package operacioneslistas.controlador;

import operacioneslistas.modelo.Lista;
import operacioneslistas.modelo.Nodo;
import operacioneslistas.vista.Vista;

/**
 * Clase ControladorContarLetras gestiona la logica para contar letras.
 * Lee una frase caracter a caracter, la almacena en una lista
 * y cuenta cuantas veces aparece la letra 'a'.
 */
public class ControladorContarLetras {

    private Vista vista;

    /**
     * Constructor del controlador.
     * @param vista La vista a utilizar para interactuar con el usuario
     */
    public ControladorContarLetras(Vista vista) {
        this.vista = vista;
    }

    /**
     * Ejecuta la logica para contar letras.
     * Lee una frase ingresada por el usuario, la almacena caracter a caracter
     * en una lista y muestra cuantas veces aparece la letra 'a'.
     */
    public void ejecutar() {
        Lista lista = new Lista();

        // Leer la frase del usuario
        String frase = vista.leerTexto("Ingrese una frase: ");

        // Cargar la frase caracter a caracter en la lista
        for (int i = 0; i < frase.length(); i++) {
            lista.insertarAlFinal(String.valueOf(frase.charAt(i)));
        }

        // Contar las letras 'a' recorriendo la lista nodo a nodo
        int contador = 0;
        Nodo actual = lista.obtenerCabeza();
        while (actual != null) {
            if (actual.obtenerValor().equalsIgnoreCase("a")) {
                contador++;
            }
            actual = actual.obtenerSiguiente();
        }

        // Mostrar el resultado
        vista.mostrarMensaje("La frase tiene " + contador + " letra(s) 'a'.");
    }
}
