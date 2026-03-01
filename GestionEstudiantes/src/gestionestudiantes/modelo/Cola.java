package gestionestudiantes.modelo;

/**
 * Clase Cola implementa una cola enlazada de estudiantes.
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
     * Agrega un estudiante al final de la cola.
     * @param estudiante El estudiante a encolar
     */
    public void encolar(Estudiante estudiante) {
        Nodo nuevo = new Nodo(estudiante);
        if (estaVacia()) {
            frente = nuevo;
            fin = nuevo;
        } else {
            fin.asignarSiguiente(nuevo);
            fin = nuevo;
        }
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
