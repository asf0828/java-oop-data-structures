/**
 * Clase Lista representa una lista enlazada simple.
 * Permite almacenar una secuencia de valores enteros enlazados mediante nodos.
 */
public class Lista {
    private Nodo cabeza;
    private int tamano;

    /**
     * Constructor de la lista.
     * Inicializa una lista vacia sin nodos.
     */
    public Lista() {
        cabeza = null;
        tamano = 0;
    }

    /**
     * Inserta un nuevo nodo al inicio de la lista.
     * @param valor El valor a insertar en el nuevo nodo
     */
    public void insertarAlInicio(int valor) {
        Nodo nuevo = new Nodo(valor);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            nuevo.setSiguiente(cabeza);
            cabeza = nuevo;
        }
        tamano++;
    }

    /**
     * Inserta un nuevo nodo al final de la lista.
     * @param valor El valor a insertar en el nuevo nodo
     */
    public void insertarAlFinal(int valor) {
        Nodo nuevo = new Nodo(valor);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            Nodo ultimo = obtenerUltimoNodo();
            ultimo.setSiguiente(nuevo);
        }
        tamano++;
    }

    /**
     * Obtiene el ultimo nodo de la lista.
     * @return El ultimo nodo de la lista, o null si la lista esta vacia
     */
    private Nodo obtenerUltimoNodo() {
        if (cabeza == null) {
            return null;
        }
        Nodo actual = cabeza;
        while (actual.getSiguiente() != null) {
            actual = actual.getSiguiente();
        }
        return actual;
    }

    /**
     * Busca un nodo por su contenido (valor).
     * @param valor El valor a buscar en la lista
     * @return El primer nodo que contiene el valor buscado, o null si no se encuentra
     */
    public Nodo buscarPorContenido(int valor) {
        Nodo actual = cabeza;
        while (actual != null) {
            if (actual.getValor() == valor) {
                return actual;
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    /**
     * Busca un nodo por su posicion en la lista.
     * @param posicion La posicion del nodo a buscar (inicia en 0)
     * @return El nodo en la posicion especificada, o null si la posicion es invalida
     */
    public Nodo buscarPorPosicion(int posicion) {
        if (posicion < 0 || posicion >= tamano) {
            return null;
        }
        Nodo actual = cabeza;
        for (int i = 0; i < posicion; i++) {
            actual = actual.getSiguiente();
        }
        return actual;
    }

    /**
     * Retorna el tamano de la lista.
     * @return El numero de nodos en la lista
     */
    public int getTamano() {
        return tamano;
    }

    /**
     * Verifica si la lista esta vacia.
     * @return true si la lista no tiene nodos, false en caso contrario
     */
    public boolean estaVacia() {
        return cabeza == null;
    }

    /**
     * Muestra el contenido completo de la lista por consola.
     */
    public void mostrarContenido() {
        Nodo actual = cabeza;
        int i = 0;
        while (actual != null) {
            System.out.println("Posicion " + i + ": " + actual.getValor());
            actual = actual.getSiguiente();
            i++;
        }
    }

    /**
     * Calcula el promedio de todos los valores en la lista.
     * Util para el punto 4 de la actividad (calculo de nota final).
     * @return El promedio de los valores, o 0 si la lista esta vacia
     */
    public double calcularPromedio() {
        if (tamano == 0) return 0;
        int suma = 0;
        Nodo actual = cabeza;
        while (actual != null) {
            suma += actual.getValor();
            actual = actual.getSiguiente();
        }
        return (double) suma / tamano;
    }
}
