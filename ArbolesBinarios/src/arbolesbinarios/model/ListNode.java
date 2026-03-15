package arbolesbinarios.model;

/**
 * ListNode is a single cell (link) in a singly-linked {@link List}.
 *
 * <p>It stores one integer value and a reference to the next cell.
 * When there is no next cell, {@code next} is null.</p>
 *
 * <p>In this project, integer values stored here are the values of
 * tree nodes ({@link TreeNode#getValue()}), collected during tree
 * traversals and queries.</p>
 */
public class ListNode {

    private int value;
    private ListNode next;

    /**
     * Creates a new list cell with the given integer value.
     * The next reference is initialized to null.
     *
     * @param value the integer value stored in this cell
     */
    public ListNode(int value) {
        this.value = value;
        this.next  = null;
    }

    /**
     * Returns the integer value stored in this cell.
     *
     * @return the cell's value
     */
    public int getValue() {
        return value;
    }

    /**
     * Returns the next cell in the list, or null if this is the last cell.
     *
     * @return the next ListNode
     */
    public ListNode getNext() {
        return next;
    }

    /**
     * Sets the next cell in the list.
     *
     * @param next the ListNode to link as the next cell
     */
    public void setNext(ListNode next) {
        this.next = next;
    }
}
