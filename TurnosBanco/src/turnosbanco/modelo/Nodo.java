package turnosbanco.modelo;

/**
 * Clase Nodo representa un elemento de la cola enlazada.
 * Contiene el nombre de un cliente y una referencia al siguiente nodo.
 */
public class Nodo {

    private String nombre;
    private Nodo siguiente;

    /**
     * Constructor con nombre.
     * @param nombre El nombre del cliente que almacenara el nodo
     */
    public Nodo(String nombre) {
        this.nombre = nombre;
        this.siguiente = null;
    }

    /**
     * Obtiene el nombre almacenado en el nodo.
     * @return El nombre del cliente
     */
    public String obtenerNombre() {
        return nombre;
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
