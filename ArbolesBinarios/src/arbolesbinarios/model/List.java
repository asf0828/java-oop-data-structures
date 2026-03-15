package arbolesbinarios.model;

/**
 * List is a custom singly-linked list that stores integer values.
 *
 * <p><strong>Note:</strong> this is a hand-built data structure for the course.
 * It is not related to {@code java.util.List}.</p>
 *
 * <p>Each element is wrapped in a {@link ListNode}. New elements are
 * appended at the end ({@link #insertLast(int)}), preserving insertion order.
 * Iteration is done by walking the chain from {@link #getFirst()} through
 * each node's {@link ListNode#getNext()}.</p>
 *
 * <p>This class fulfills the workshop requirement:<br>
 * <em>"El almacenamiento de los nodos se debe hacer en una estructura tipo lista."</em></p>
 */
public class List {

    private ListNode first;
    private ListNode last;
    private int size;

    /**
     * Creates an empty list.
     * Both {@code first} and {@code last} are null, and {@code size} is 0.
     */
    public List() {
        this.first = null;
        this.last  = null;
        this.size  = 0;
    }

    /**
     * Appends a new integer value at the end of the list.
     * A new {@link ListNode} is created internally to wrap the value.
     *
     * @param value the integer value to add
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
     * Returns the first cell of the list, or null if the list is empty.
     * Use this to begin iterating:
     * <pre>
     *   ListNode current = list.getFirst();
     *   while (current != null) {
     *       // use current.getValue()
     *       current = current.getNext();
     *   }
     * </pre>
     *
     * @return the first ListNode in the list
     */
    public ListNode getFirst() {
        return first;
    }

    /**
     * Returns the number of elements currently in the list.
     *
     * @return the element count
     */
    public int size() {
        return size;
    }

    /**
     * Returns true if the list contains no elements.
     *
     * @return true when size is 0
     */
    public boolean isEmpty() {
        return first == null;
    }
}
