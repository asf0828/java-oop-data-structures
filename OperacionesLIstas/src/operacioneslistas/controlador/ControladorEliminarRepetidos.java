package operacioneslistas.controlador;

import operacioneslistas.modelo.Lista;
import operacioneslistas.modelo.Nodo;
import operacioneslistas.vista.Vista;

/**
 * Clase ControladorEliminarRepetidos gestiona la logica del ejercicio 3.
 * Lee una lista ingresada por el usuario, muestra la lista original
 * y genera una nueva lista sin elementos repetidos.
 */
public class ControladorEliminarRepetidos {

    private Vista vista;

    /**
     * Constructor del controlador.
     * @param vista La vista a utilizar para interactuar con el usuario
     */
    public ControladorEliminarRepetidos(Vista vista) {
        this.vista = vista;
    }

    /**
     * Lee los elementos de una lista hasta que el usuario escriba 'end',
     * muestra la lista original y luego la lista sin elementos repetidos.
     */
    public void ejecutar() {
        Lista lista = new Lista();

        // Leer elementos de la lista
        vista.mostrarMensaje("Ingrese los elementos de la lista uno por uno, presione Enter despues de cada elemento. Escriba 'end' y presione Enter para terminar:");
        String elemento;
        while (!(elemento = vista.leerTexto("  Elemento: ")).equals("end")) {
            lista.insertarAlFinal(elemento);
        }

        // Mostrar lista original
        vista.mostrarLista("Lista original:", lista);

        // Construir lista sin repetidos recorriendo nodo a nodo
        Lista sinRepetidos = new Lista();
        Nodo actual = lista.obtenerCabeza();
        while (actual != null) {
            if (!sinRepetidos.contiene(actual.obtenerValor())) {
                sinRepetidos.insertarAlFinal(actual.obtenerValor());
            }
            actual = actual.obtenerSiguiente();
        }

        // Mostrar lista sin repetidos
        vista.mostrarLista("Lista sin repetidos:", sinRepetidos);
    }
}
