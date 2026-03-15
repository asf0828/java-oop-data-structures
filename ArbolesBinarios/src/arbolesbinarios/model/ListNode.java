package arbolesbinarios.model;

/**
 * Celda de una lista enlazada simple {@link List}.
 *
 * Almacena un valor entero y una referencia a la siguiente celda.
 * Cuando no hay celda siguiente, {@code next} es null.
 */
public class ListNode {

    private int value;
    private ListNode next;

    /**
     * Crea una nueva celda con el valor entero dado.
     * La referencia siguiente se inicializa en null.
     *
     * @param value el valor entero almacenado en esta celda
     */
    public ListNode(int value) {
        this.value = value;
        this.next  = null;
    }

    /**
     * Retorna el valor entero almacenado en esta celda.
     *
     * @return el valor de la celda
     */
    public int getValue() {
        return value;
    }

    /**
     * Retorna la siguiente celda de la lista, o null si es la última.
     *
     * @return el siguiente ListNode
     */
    public ListNode getNext() {
        return next;
    }

    /**
     * Asigna la siguiente celda de la lista.
     *
     * @param next el ListNode a enlazar como celda siguiente
     */
    public void setNext(ListNode next) {
        this.next = next;
    }
}
