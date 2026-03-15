package arbolesbinarios.model;

/**
 * Lista enlazada simple que almacena valores enteros.
 *
 * Nota: esta es una estructura construida a mano. No tiene relación
 * con {@code java.util.List}.
 *
 * Cada elemento se envuelve en un {@link ListNode}. Los nuevos elementos
 * se agregan al final ({@link #insertLast(int)}), preservando el orden de
 * inserción. Para recorrer la lista se usa {@link #getFirst()} y se avanza
 * con {@link ListNode#getNext()}.
 */
public class List {

    private ListNode first;
    private ListNode last;
    private int size;

    /**
     * Crea una lista vacía.
     * {@code first} y {@code last} son null, y {@code size} es 0.
     */
    public List() {
        this.first = null;
        this.last  = null;
        this.size  = 0;
    }

    /**
     * Agrega un nuevo valor entero al final de la lista.
     * Se crea internamente un {@link ListNode} para envolver el valor.
     *
     * @param value el valor entero a agregar
     */
    public void insertLast(int value) {
        ListNode newNode = new ListNode(value);
        if (isEmpty()) {
            first = newNode;
            last  = newNode;
        } else {
            last.setNext(newNode);
            last = newNode;
        }
        size++;
    }

    /**
     * Retorna la primera celda de la lista, o null si está vacía.
     * Usar para iniciar el recorrido de la lista.
     *
     * @return el primer ListNode de la lista
     */
    public ListNode getFirst() {
        return first;
    }

    /**
     * Retorna la cantidad de elementos en la lista.
     *
     * @return el número de elementos
     */
    public int size() {
        return size;
    }

    /**
     * Indica si la lista no contiene elementos.
     *
     * @return true si la lista está vacía, false en caso contrario
     */
    public boolean isEmpty() {
        return first == null;
    }
}
