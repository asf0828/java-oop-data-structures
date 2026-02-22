package operacionespilas.controlador;

import operacionespilas.modelo.Pila;
import operacionespilas.vista.Vista;

/**
 * Clase ControladorCompararPilas gestiona el ejercicio 2.
 * Determina si dos pilas son iguales usando el metodo compararPila.
 */
public class ControladorCompararPilas {

    private Vista vista;

    /**
     * Constructor del controlador.
     * @param vista La vista a utilizar para interactuar con el usuario
     */
    public ControladorCompararPilas(Vista vista) {
        this.vista = vista;
    }

    public void ejecutar() {

        // Crear pila 1
        Pila pila1 = new Pila();

        // Llenar pila 1
        vista.mostrarMensaje("Ingrese los elementos de la Pila 1 uno por uno (solo 1 caracter por elemento), presione Enter despues de cada elemento. Escriba 'end' y presione Enter para terminar:");
        String elemento1;
        while (!(elemento1 = vista.leerTexto("  Elemento: ")).equals("end")) {
            if (!elemento1.isEmpty()) {
                pila1.apilar(elemento1.charAt(0));
            } else {
                vista.mostrarMensaje("  Elemento vacio, no se inserto. Ingrese un caracter valido.");
            }
        }

        // Crear pila 2
        Pila pila2 = new Pila();

        // Llenar pila 2
        vista.mostrarMensaje("Ingrese los elementos de la Pila 2 uno por uno (solo 1 caracter por elemento), presione Enter despues de cada elemento. Escriba 'end' y presione Enter para terminar:");
        String elemento2;
        while (!(elemento2 = vista.leerTexto("  Elemento: ")).equals("end")) {
            if (!elemento2.isEmpty()) {
                pila2.apilar(elemento2.charAt(0));
            } else {
                vista.mostrarMensaje("  Elemento vacio, no se inserto. Ingrese un caracter valido.");
            }
        }

        vista.mostrarLinea();
        vista.mostrarPila("Pila 1:", pila1);
        vista.mostrarPila("Pila 2:", pila2);
        vista.mostrarLinea();

        // Comparar
        if (pila1.comparteStack(pila2)) {
            vista.mostrarMensaje("Las pilas son iguales.");
        } else {
            vista.mostrarMensaje("Las pilas NO son iguales.");
        }
    }
}