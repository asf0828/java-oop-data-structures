package arbolesbinarios.model;

/**
 * TreeNode represents a single node in a binary tree.
 *
 * <p>Each node holds an integer value and references to its
 * left and right children. When a child does not exist,
 * its reference is null.</p>
 *
 * <p>This class is the building block of the {@link Tree} structure.
 * It follows the node design shown in the professor's TAD (NodeT),
 * adapted to store integer values instead of strings.</p>
 */
public class TreeNode {

    private int value;
    private TreeNode left;
    private TreeNode right;

    /**
     * Creates a new tree node with the given integer value.
     * Both child references are initialized to null.
     *
     * @param value the integer value stored in this node
     */
    public TreeNode(int value) {
        this.value = value;
        this.left  = null;
        this.right = null;
    }

    /**
     * Returns the integer value stored in this node.
     *
     * @return the node's value
     */
    public int getValue() {
        return value;
    }

    /**
     * Returns the left child of this node, or null if none.
     *
     * @return the left child TreeNode
     */
    public TreeNode getLeft() {
        return left;
    }

    /**
     * Returns the right child of this node, or null if none.
     *
     * @return the right child TreeNode
     */
    public TreeNode getRight() {
        return right;
    }

    /**
     * Sets the left child of this node.
     *
     * @param left the TreeNode to assign as the left child
     */
    public void setLeft(TreeNode left) {
        this.left = left;
    }

    /**
     * Sets the right child of this node.
     *
     * @param right the TreeNode to assign as the right child
     */
    public void setRight(TreeNode right) {
        this.right = right;
    }
}
