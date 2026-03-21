package peajeinteligente.model;

/**
 * Lista enlazada simple generica.
 * Permite agregar elementos al final y mostrarlos por consola.
 *
 * @param <T> tipo del elemento almacenado
 */
public class List<T> {

    /** Nodo cabeza de la lista. */
    private Node<T> head;

    /**
     * Agrega un elemento al final de la lista.
     *
     * @param data elemento a agregar
     */
    public void add(T data) {
        Node<T> node = new Node<>(data);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> aux = head;
        while (aux.getNext() != null) {
            aux = aux.getNext();
        }
        aux.setNext(node);
    }

    /**
     * Muestra todos los elementos de la lista por consola.
     */
    public void show() {
        Node<T> aux = head;
        while (aux != null) {
            System.out.println(aux.getData());
            aux = aux.getNext();
        }
    }
}
