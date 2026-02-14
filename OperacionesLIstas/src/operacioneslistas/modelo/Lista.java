package operacioneslistas.modelo;

/**
 * Clase Lista representa una lista enlazada simple.
 * Permite almacenar una secuencia de valores de tipo String enlazados mediante nodos.
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
     * Inserta un nuevo nodo al final de la lista.
     * @param valor El valor de tipo String a insertar en el nuevo nodo
     */
    public void insertarAlFinal(String valor) {
        Nodo nuevo = new Nodo(valor);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            Nodo actual = cabeza;
            while (actual.obtenerSiguiente() != null) {
                actual = actual.obtenerSiguiente();
            }
            actual.asignarSiguiente(nuevo);
        }
        tamano++;
    }

    /**
     * Verifica si la lista contiene un valor dado.
     * @param valor El valor a buscar en la lista
     * @return true si el valor existe en la lista, false en caso contrario
     */
    public boolean contiene(String valor) {
        Nodo actual = cabeza;
        while (actual != null) {
            if (actual.obtenerValor().equals(valor)) {
                return true;
            }
            actual = actual.obtenerSiguiente();
        }
        return false;
    }

    /**
     * Compara esta lista con otra para determinar si son identicas.
     * Dos listas son identicas si tienen la misma cantidad de nodos
     * y el mismo contenido en cada posicion.
     * @param otra La lista con la que se comparara
     * @return true si las listas son identicas, false en caso contrario
     */
    public boolean compareList(Lista otra) {
        if (this.obtenerTamano() != otra.obtenerTamano()) {
            return false;
        }
        Nodo a = this.cabeza;
        Nodo b = otra.cabeza;
        while (a != null) {
            if (!a.obtenerValor().equals(b.obtenerValor())) {
                return false;
            }
            a = a.obtenerSiguiente();
            b = b.obtenerSiguiente();
        }
        return true;
    }

    /**
     * Obtiene la referencia al primer nodo (cabeza) de la lista.
     * @return El nodo cabeza de la lista
     */
    public Nodo obtenerCabeza() {
        return cabeza;
    }

    /**
     * Retorna el tamano de la lista.
     * @return El numero de nodos en la lista
     */
    public int obtenerTamano() {
        return tamano;
    }

    /**
     * Verifica si la lista esta vacia.
     * @return true si la lista no tiene nodos, false en caso contrario
     */
    public boolean estaVacia() {
        return cabeza == null;
    }

}
