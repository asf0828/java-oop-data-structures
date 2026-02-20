package operacionespilas.controlador;

import operacionespilas.modelo.Pila;
import operacionespilas.vista.Vista;

/**
 * Clase ControladorParentesis gestiona el ejercicio 3.
 * Verifica si una expresion aritmetica tiene los parentesis equilibrados,
 * es decir, si cada parentesis abierto tiene su correspondiente cierre.
 */
public class ControladorParentesis {

    private Vista vista;

    /**
     * Constructor del controlador.
     * @param vista La vista a utilizar para interactuar con el usuario
     */
    public ControladorParentesis(Vista vista) {
        this.vista = vista;
    }

    /**
     * Ejecuta el ejercicio 3.
     * Lee una expresion aritmetica del usuario y determina si los
     * parentesis estan equilibrados usando una pila.
     */
    public void ejecutar() {
        vista.mostrarLinea();
        String expresion = vista.leerTexto("Ingrese la expresion aritmetica: ");

        Pila pila = new Pila();
        boolean correcta = true;

        for (int i = 0; i < expresion.length(); i++) {
            char c = expresion.charAt(i);
            if (c == '(') {
                pila.apilar(c);
            } else if (c == ')') {
                if (pila.estaVacia()) {
                    correcta = false;
                    break;
                }
                pila.desapilar();
            }
        }

        if (!pila.estaVacia()) {
            correcta = false;
        }

        vista.mostrarMensaje("Expresion: " + expresion);
        if (correcta) {
            vista.mostrarMensaje("Resultado: la expresion es correcta.");
        } else {
            vista.mostrarMensaje("Resultado: la expresion es incorrecta.");
        }
        vista.mostrarLinea();
    }
}
