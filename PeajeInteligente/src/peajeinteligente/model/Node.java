package peajeinteligente.model;

/**
 * Nodo generico utilizado por las estructuras de datos (Cola, Pila, Lista).
 * Almacena un dato de tipo T y una referencia al siguiente nodo.
 *
 * @param <T> tipo del dato almacenado
 */
public class Node<T> {

    /** Dato almacenado en el nodo. */
    private T data;

    /** Referencia al siguiente nodo en la estructura. */
    private Node<T> next;

    /**
     * Crea un nodo con el dato indicado y sin sucesor.
     *
     * @param data dato a almacenar
     */
    public Node(T data) {
        this.data = data;
    }

    /**
     * Retorna el dato almacenado en el nodo.
     *
     * @return dato del nodo
     */
    public T getData() {
        return data;
    }

    /**
     * Retorna el siguiente nodo en la estructura.
     *
     * @return nodo siguiente, o {@code null} si no hay sucesor
     */
    public Node<T> getNext() {
        return next;
    }

    /**
     * Asigna el siguiente nodo en la estructura.
     *
     * @param next nodo sucesor
     */
    public void setNext(Node<T> next) {
        this.next = next;
    }
}
