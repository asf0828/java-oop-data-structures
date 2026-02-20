package operacionespilas.modelo;

/**
 * Clase Nodo representa un elemento de la pila enlazada.
 * Contiene un valor de tipo char y una referencia al siguiente nodo.
 */
public class Nodo {

    private char valor;
    private Nodo siguiente;

    /**
     * Constructor con valor.
     * @param valor El valor de tipo char que almacenara el nodo
     */
    public Nodo(char valor) {
        this.valor = valor;
        this.siguiente = null;
    }

    /**
     * Obtiene el valor almacenado en el nodo.
     * @return El valor del nodo
     */
    public char obtenerValor() {
        return valor;
    }

    /**
     * Obtiene la referencia al siguiente nodo.
     * @return El siguiente nodo en la pila
     */
    public Nodo obtenerSiguiente() {
        return siguiente;
    }

    /**
     * Establece la referencia al siguiente nodo.
     * @param siguiente El nodo que seguira a este en la pila
     */
    public void asignarSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
}
