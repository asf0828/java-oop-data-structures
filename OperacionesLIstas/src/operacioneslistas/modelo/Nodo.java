package operacioneslistas.modelo;

/**
 * Clase Nodo representa un elemento de la lista enlazada simple.
 * Contiene un valor de tipo String y una referencia al siguiente nodo.
 */
public class Nodo {

    private String valor;
    private Nodo siguiente;

    /**
     * Constructor con valor.
     * @param valor El valor de tipo String que almacenara el nodo
     */
    public Nodo(String valor) {
        this.valor = valor;
        this.siguiente = null;
    }

    /**
     * Obtiene el valor almacenado en el nodo.
     * @return El valor del nodo
     */
    public String obtenerValor() {
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
