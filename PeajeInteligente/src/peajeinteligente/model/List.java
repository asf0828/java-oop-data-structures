package peajeinteligente.model;

/**
 * Lista enlazada simple generica.
 * Permite agregar elementos al final y recorrerlos por indice.
 * Mantiene un contador de tamano para consulta sin recorrido destructivo.
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
     * Retorna el elemento en la posicion indicada (base 0).
     * Permite al Controller recorrer la lista sin destruirla
     * y sin delegar impresion al modelo.
     *
     * @param index posicion del elemento
     * @return elemento en esa posicion, o {@code null} si el indice es invalido
     */
    public T get(int index) {
        if (index < 0 || index >= size) return null;
        Node<T> aux = head;
        for (int i = 0; i < index; i++) {
            aux = aux.getNext();
        }
        return aux.getData();
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
