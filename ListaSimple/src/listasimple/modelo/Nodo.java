package listasimple.modelo;

/**
 * Clase Nodo representa un elemento de la lista enlazada simple.
 * Contiene un valor entero y una referencia al siguiente nodo.
 */
public class Nodo {
    private int valor;
    private Nodo siguiente;

    /**
     * Constructor con valor.
     * @param valor El valor entero que almacenara el nodo
     */
    public Nodo(int valor) {
        this.valor = valor;
        this.siguiente = null;
    }

    /**
     * Constructor completo.
     * @param valor El valor entero que almacenara el nodo
     * @param siguiente Referencia al siguiente nodo en la lista
     */
    public Nodo(int valor, Nodo siguiente) {
        this.valor = valor;
        this.siguiente = siguiente;
    }

    /**
     * Obtiene el valor almacenado en el nodo.
     * @return El valor del nodo
     */
    public int obtenerValor() {
        return valor;
    }

    /**
     * Obtiene la referencia al siguiente nodo.
     * @return El siguiente nodo en la lista
     */
    public Nodo obtenerSiguiente() {
        return siguiente;
    }

    /**
     * Establece la referencia al siguiente nodo.
     * @param siguiente El nodo que seguira a este en la lista
     */
    public void asignarSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
}
