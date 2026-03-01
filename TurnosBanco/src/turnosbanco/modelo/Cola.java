package turnosbanco.modelo;

/**
 * Clase Cola implementa una cola enlazada de clientes.
 * Sigue el principio FIFO (primero en entrar, primero en salir).
 */
public class Cola {

    private Nodo frente;
    private Nodo fin;

    /**
     * Constructor de la cola.
     * Inicializa la cola vacia.
     */
    public Cola() {
        this.frente = null;
        this.fin = null;
    }

    /**
     * Agrega el nombre de un cliente al final de la cola.
     * @param nombre El nombre del cliente a encolar
     */
    public void encolar(String nombre) { }

    /**
     * Retira y retorna el nombre del cliente al frente de la cola.
     * @return El nombre del cliente al frente, o null si la cola esta vacia
     */
    public String desencolar() { return null; }

    /**
     * Retorna el nodo al frente sin extraerlo.
     * Permite recorrer la cola sin modificarla.
     * @return El nodo frente, o null si la cola esta vacia
     */
    public Nodo obtenerFrente() { return null; }

    /**
     * Indica si la cola esta vacia.
     * @return true si no hay elementos, false en caso contrario
     */
    public boolean estaVacia() { return false; }

}
