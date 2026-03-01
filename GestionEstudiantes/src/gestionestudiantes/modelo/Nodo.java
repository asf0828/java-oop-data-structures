package gestionestudiantes.modelo;

/**
 * Clase Nodo representa un elemento de la cola enlazada.
 * Contiene un valor de tipo Estudiante y una referencia al siguiente nodo.
 */
public class Nodo {

    private Estudiante valor;
    private Nodo siguiente;

    /**
     * Constructor con valor.
     * @param valor El estudiante que almacenara el nodo
     */
    public Nodo(Estudiante valor) {
        this.valor = valor;
        this.siguiente = null;
    }

    /**
     * Obtiene el estudiante almacenado en el nodo.
     * @return El estudiante del nodo
     */
    public Estudiante obtenerValor() {
        return valor;
    }

    /**
     * Obtiene la referencia al siguiente nodo.
     * @return El siguiente nodo en la cola
     */
    public Nodo obtenerSiguiente() {
        return siguiente;
    }

    /**
     * Establece la referencia al siguiente nodo.
     * @param siguiente El nodo que seguira a este en la cola
     */
    public void asignarSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
}
