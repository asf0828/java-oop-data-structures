package controlador;

import modelo.Lista;
import modelo.Nodo;
import vista.Vista;

/**
 * Clase Controlador coordina el modelo y la vista.
 * Contiene la logica de negocio de la aplicación.
 */
public class Controlador {
    private Vista vista;

    /**
     * Constructor del controlador.
     * @param vista La vista a utilizar para interactuar con el usuario
     */
    public Controlador(Vista vista) {
        this.vista = vista;
    }

    /**
     * Calcula el promedio de todos los valores en una lista.
     * @param lista La lista de la cual calcular el promedio
     * @return El promedio de los valores, o 0 si la lista esta vacia
     */
    public double calcularPromedio(Lista lista) {
        if (lista.estaVacia()) {
            return 0;
        }

        int suma = 0;
        Nodo actual = lista.obtenerCabeza();
        while (actual != null) {
            suma += actual.obtenerValor();
            actual = actual.obtenerSiguiente();
        }

        return (double) suma / lista.obtenerTamano();
    }

    /**
     * Ejecuta el menu principal de la aplicacion.
     * Implementa el programa de calculo de promedio de notas.
     */
    public void ejecutar() {
        Lista lista = new Lista();

        int n = vista.leerEntero("Ingrese la cantidad de notas: ");

        for (int i = 0; i < n; i++) {
            int nota = vista.leerEntero("Ingrese la nota " + (i + 1) + ": ");
            lista.insertarAlFinal(nota);
        }

        vista.mostrarMensaje("\nContenido de la lista:");
        vista.mostrarContenido(lista);

        int pos = vista.leerEntero("\nIngrese la posicion a buscar: ");
        Nodo encontrado = lista.buscarPorPosicion(pos);

        vista.mostrarNodoEncontrado(encontrado);

        double promedio = calcularPromedio(lista);
        vista.mostrarPromedio(promedio);

        vista.cerrar();
    }
}
