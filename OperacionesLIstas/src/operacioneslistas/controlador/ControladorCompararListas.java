package operacioneslistas.controlador;

import operacioneslistas.modelo.Lista;
import operacioneslistas.vista.Vista;

/**
 * Clase ControladorCompararListas gestiona la logica del ejercicio 2.
 * Lee dos listas ingresadas por el usuario y determina si son identicas.
 * Dos listas son identicas si tienen la misma cantidad de nodos
 * y el mismo contenido en cada posicion.
 */
public class ControladorCompararListas {

    private Vista vista;

    /**
     * Constructor del controlador.
     * @param vista La vista a utilizar para interactuar con el usuario
     */
    public ControladorCompararListas(Vista vista) {
        this.vista = vista;
    }

    /**
     * Lee los elementos de dos listas hasta que el usuario escriba 'end',
     * muestra ambas listas y determina si son identicas usando compareList.
     */
    public void ejecutar() {
        Lista lista1 = new Lista();
        Lista lista2 = new Lista();

        // Leer elementos de la primera lista
        vista.mostrarMensaje("Ingrese los elementos de la Lista 1 uno por uno, presione Enter despues de cada elemento. Escriba 'end' y presione Enter para terminar:");
        String elemento;
        while (!(elemento = vista.leerTexto("  Elemento: ")).equals("end")) {
            lista1.insertarAlFinal(elemento);
        }

        // Leer elementos de la segunda lista
        vista.mostrarMensaje("Ingrese los elementos de la Lista 2 uno por uno, presione Enter despues de cada elemento. Escriba 'end' y presione Enter para terminar:");
        while (!(elemento = vista.leerTexto("  Elemento: ")).equals("end")) {
            lista2.insertarAlFinal(elemento);
        }

        // Mostrar ambas listas
        vista.mostrarLista("Lista 1:", lista1);
        vista.mostrarLista("Lista 2:", lista2);

        // Comparar y mostrar resultado
        boolean igual = lista1.compareList(lista2);
        if (igual) {
            vista.mostrarMensaje("Las listas son identicas.");
        } else {
            vista.mostrarMensaje("Las listas NO son identicas.");
        }
    }
}
