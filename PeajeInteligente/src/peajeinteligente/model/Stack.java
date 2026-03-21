package peajeinteligente.model;

/**
 * Pila enlazada LIFO generica.
 * Mantiene un contador de tamano para consulta sin recorrido destructivo.
 *
 * @param <T> tipo del elemento almacenado
 */
public class Stack<T> {

    /** Nodo en la cima de la pila. */
    private Node<T> top;

    /** Cantidad de elementos actualmente en la pila. */
    private int size;

    /**
     * Apila un elemento en la cima.
     *
     * @param data elemento a apilar
     */
    public void push(T data) {
        Node<T> node = new Node<>(data);
        node.setNext(top);
        top = node;
        size++;
    }

    /**
     * Desapila y retorna el elemento de la cima.
     * El nodo queda sin referencia y sera recolectado por el GC.
     *
     * @return elemento de la cima, o {@code null} si la pila esta vacia
     */
    public T pop() {
        if (top == null) {
            return null;
        }
        T data = top.getData();
        top = top.getNext();
        size--;
        return data;
    }

    /**
     * Indica si la pila esta vacia.
     *
     * @return {@code true} si no hay elementos, {@code false} en caso contrario
     */
    public boolean isEmpty() {
        return top == null;
    }

    /**
     * Retorna la cantidad de elementos en la pila sin recorrido destructivo.
     *
     * @return tamano de la pila
     */
    public int getSize() {
        return size;
    }
}
