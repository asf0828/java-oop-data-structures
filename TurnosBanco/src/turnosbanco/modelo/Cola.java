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
    public void encolar(String nombre) {
        Nodo nuevo = new Nodo(nombre);
        if (fin == null) {
            frente = nuevo;
            fin = nuevo;
        } else {
            fin.asignarSiguiente(nuevo);
            fin = nuevo;
        }
    }

    /**
     * Retira y retorna el nombre del cliente al frente de la cola.
     * @return El nombre del cliente al frente, o null si la cola esta vacia
     */
    public String desencolar() {
        if (frente == null) {
            return null;
        }
        String nombre = frente.obtenerNombre();
        frente = frente.obtenerSiguiente();
        if (frente == null) {
            fin = null;
        }
        return nombre;
    }

    /**
     * Retorna el nodo al frente sin extraerlo.
     * Permite recorrer la cola sin modificarla.
     * @return El nodo frente, o null si la cola esta vacia
     */
    public Nodo obtenerFrente() {
        return frente;
    }

    /**
     * Indica si la cola esta vacia.
     * @return true si no hay elementos, false en caso contrario
     */
    public boolean estaVacia() {
        return frente == null;
    }

}
