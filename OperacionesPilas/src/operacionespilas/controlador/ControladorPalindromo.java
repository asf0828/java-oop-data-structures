package operacionespilas.controlador;

import operacionespilas.modelo.Pila;
import operacionespilas.vista.Vista;

/**
 * Ejercicio 1:
 * Determina si una frase es palindroma usando una pila.
 */
public class ControladorPalindromo {

    private Vista vista;

    // Constructor del controlador.
     
    public ControladorPalindromo(Vista vista) {
        this.vista = vista;
    }

    /**
     * Ejecuta la logica del ejercicio de palindromo.
     * Lee una frase del usuario, ignora espacios y mayusculas,
     * y determina si es palindroma usando una pila.
     */
    public void ejecutar() {
        vista.mostrarLinea();
        vista.mostrarMensaje("  EJERCICIO 1: PALINDROMO");
        vista.mostrarLinea();

        String frase = vista.leerTexto("Ingrese la frase: ");

        // Limpiar la frase: quitar espacios y convertir a minusculas
        String fraseNormalizada = frase.replaceAll("\\s+", "").toLowerCase();

        // Apilar todos los caracteres de la frase normalizada
        Pila pila = new Pila();
        for (int i = 0; i < fraseNormalizada.length(); i++) {
            pila.apilar(fraseNormalizada.charAt(i));
        }

        // Mostrar la pila original (de cima a base = inverso de la frase)
        vista.mostrarMensaje("\nFrase ingresada: " + frase);
        vista.mostrarPila("Pila (cima -> base):", pila);

        // Comparar cada caracter de la frase con lo desapilado
        // La pila desapila en orden inverso, si coincide es palindromo
        boolean esPalindromo = true;
        for (int i = 0; i < fraseNormalizada.length(); i++) {
            char desapilado = pila.desapilar();
            if (fraseNormalizada.charAt(i) != desapilado) {
                esPalindromo = false;
                break;
            }
        }

        // Mostrar resultado
        vista.mostrarLinea();
        if (esPalindromo) {
            vista.mostrarMensaje("Resultado: \"" + frase + "\" ES un palindromo.");
        } else {
            vista.mostrarMensaje("Resultado: \"" + frase + "\" NO es un palindromo.");
        }
        vista.mostrarLinea();
    }
}
