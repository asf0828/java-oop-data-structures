package operacioneslistas.controlador;

import operacioneslistas.modelo.Lista;
import operacioneslistas.vista.Vista;

/**
 * Controlador para el Ejercicio 1: Contar letras 'a' en una frase
 * Lee una frase, la almacena caracter a caracter en una lista
 * y cuenta cuantas letras 'a' contiene.
 */
public class ControladorContarLetras {
    private Vista vista;
    private Lista lista;
    
    /**
     * Constructor del controlador
     * @param vista Instancia de la vista para interactuar con el usuario
     */
    public ControladorContarLetras(Vista vista) {
        this.vista = vista;
        this.lista = new Lista();
    }
    
    /**
     * Ejecuta el flujo del ejercicio 1
     */
    public void ejecutar() {
        vista.mostrarMensaje("\n========== EJERCICIO 1: CONTAR LETRAS 'a' ==========");
        vista.mostrarLinea();
        
        // Leer la frase del usuario
        String frase = vista.leerTexto("Ingrese una frase: ");
        
        // Cargar la frase caracter a caracter en la lista
        cargarFraseEnLista(frase);
        
        // Contar las letras 'a' en la lista
        int cantidadA = lista.contarLetrasA();
        
        // Mostrar el resultado
        vista.mostrarLinea();
        vista.mostrarMensaje("RESULTADO:");
        vista.mostrarMensaje("Frase ingresada: " + frase);
        vista.mostrarMensaje("Cantidad de letras 'a' (minusculas): " + cantidadA);
        vista.mostrarLinea();
    }
    
    /**
     * Carga cada caracter de la frase en la lista
     * Cada caracter se almacena como un nodo individual
     * @param frase La frase a cargar en la lista
     */
    private void cargarFraseEnLista(String frase) {
        lista = new Lista();
        for (int i = 0; i < frase.length(); i++) {
            char caracter = frase.charAt(i);
            lista.insertarCaracter(caracter);
        }
    }
}
