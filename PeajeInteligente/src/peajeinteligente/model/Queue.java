package peajeinteligente.model;

/**
 * Cola enlazada FIFO generica.
 * Mantiene un contador de tamano para evitar recorridos destructivos.
 *
 * @param <T> tipo del elemento almacenado
 */
public class Queue<T> {

    /** Nodo al frente de la cola (primer elemento en salir). */
    private Node<T> front;

    /** Nodo al final de la cola (ultimo elemento en entrar). */
    private Node<T> rear;

    /** Cantidad de elementos actualmente en la cola. */
    private int size;

    /**
     * Agrega un elemento al final de la cola.
     *
     * @param data elemento a encolar
     */
    public void enqueue(T data) {
        Node<T> node = new Node<>(data);
        if (rear == null) {
            front = rear = node;
        } else {
            rear.setNext(node);
            rear = node;
        }
        size++;
    }

    /**
     * Extrae y retorna el elemento del frente de la cola.
     * El nodo queda sin referencia y sera recolectado por el GC.
     *
     * @return elemento del frente, o {@code null} si la cola esta vacia
     */
    public T dequeue() {
        if (front == null) {
            return null;
        }
        T data = front.getData();
        front = front.getNext();
        if (front == null) {
            rear = null;
        }
        size--;
        return data;
    }

    /**
     * Indica si la cola esta vacia.
     *
     * @return {@code true} si no hay elementos, {@code false} en caso contrario
     */
    public boolean isEmpty() {
        return front == null;
    }

    /**
     * Retorna la cantidad de elementos en la cola sin recorrido destructivo.
     *
     * @return tamano de la cola
     */
    public int getSize() {
        return size;
    }
}
