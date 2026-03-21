package peajeinteligente.model;

/**
 * Lista enlazada simple generica.
 * Permite agregar elementos al final y mostrarlos por consola.
 * Mantiene un contador de tamano para consulta sin recorrido.
 *
 * @param <T> tipo del elemento almacenado
 */
public class List<T> {

    /** Nodo cabeza de la lista. */
    private Node<T> head;

    /** Cantidad de elementos actualmente en la lista. */
    private int size;

    /**
     * Agrega un elemento al final de la lista.
     *
     * @param data elemento a agregar
     */
    public void add(T data) {
        Node<T> node = new Node<>(data);
        if (head == null) {
            head = node;
        } else {
            Node<T> aux = head;
            while (aux.getNext() != null) {
                aux = aux.getNext();
            }
            aux.setNext(node);
        }
        size++;
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

    /**
     * Retorna la cantidad de elementos en la lista.
     *
     * @return tamano de la lista
     */
    public int getSize() {
        return size;
    }
}
