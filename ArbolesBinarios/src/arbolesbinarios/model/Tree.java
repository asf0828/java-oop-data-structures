package arbolesbinarios.model;

/**
 * Tree represents a binary tree and provides operations on it.
 *
 * <p>This class only defines the tree structure ({@code root}) and the
 * algorithms that operate on it. It does <em>not</em> build the tree itself —
 * that responsibility belongs to the controller, following the professor's
 * TAD design (see {@code TreeController#buildTree()}).</p>
 *
 * <p>Each method that collects results receives an empty {@link List} from
 * the caller, fills it recursively, and returns it. This pattern is taken
 * directly from the professor's TAD:
 * <pre>
 *   public List preorder(TreeNode root, List list) { ... return list; }
 * </pre>
 * </p>
 *
 * <p>No {@code System.out} calls appear in this class. All output is
 * handled by the view layer ({@code IOManager}).</p>
 */
public class Tree {

    private TreeNode root;

    /**
     * Creates an empty tree with no root node.
     * The root must be set via {@link #setRoot(TreeNode)} before any
     * operation is called.
     */
    public Tree() {
        this.root = null;
    }

    /**
     * Sets the root node of this tree.
     *
     * @param root the TreeNode to use as the root
     */
    public void setRoot(TreeNode root) {
        this.root = root;
    }

    /**
     * Returns the root node of this tree, or null if the tree is empty.
     *
     * @return the root TreeNode
     */
    public TreeNode getRoot() {
        return root;
    }

    /**
     * Returns true if this tree has no root node.
     *
     * @return true when root is null
     */
    public boolean isEmpty() {
        return root == null;
    }

    // ============================================================
    // TRAVERSALS
    // ============================================================

    /**
     * Performs a preorder traversal (root → left → right) and appends
     * each visited node's value to the given list.
     *
     * <p>Preorder visits the current node first, then recurses into the
     * left subtree, then the right subtree.</p>
     *
     * @param node the subtree root to traverse (null is a safe base case)
     * @param list the list that collects the traversal values
     * @return the same list, now containing the traversal result
     */
    public List preorder(TreeNode node, List list) {
        if (node != null) {
            list.insertLast(node.getValue());
            preorder(node.getLeft(), list);
            preorder(node.getRight(), list);
        }
        return list;
    }

    /**
     * Performs an inorder traversal (left → root → right) and appends
     * each visited node's value to the given list.
     *
     * <p>Inorder visits the left subtree first, then the current node,
     * then the right subtree. For a binary search tree this produces
     * values in sorted order.</p>
     *
     * @param node the subtree root to traverse (null is a safe base case)
     * @param list the list that collects the traversal values
     * @return the same list, now containing the traversal result
     */
    public List inorder(TreeNode node, List list) {
        if (node != null) {
            inorder(node.getLeft(), list);
            list.insertLast(node.getValue());
            inorder(node.getRight(), list);
        }
        return list;
    }

    /**
     * Performs a postorder traversal (left → right → root) and appends
     * each visited node's value to the given list.
     *
     * <p>Postorder visits both subtrees before the current node.
     * It is commonly used to safely delete or process all children
     * before their parent.</p>
     *
     * @param node the subtree root to traverse (null is a safe base case)
     * @param list the list that collects the traversal values
     * @return the same list, now containing the traversal result
     */
    public List postorder(TreeNode node, List list) {
        if (node != null) {
            postorder(node.getLeft(), list);
            postorder(node.getRight(), list);
            list.insertLast(node.getValue());
        }
        return list;
    }

    // ============================================================
    // QUERY METHODS
    // ============================================================

    /**
     * Collects the values of all parent nodes into a list.
     *
     * <p>A parent node is any node that has at least one child
     * (left child, right child, or both).</p>
     *
     * @param node the subtree root to examine (null is a safe base case)
     * @param list the list that collects the parent node values
     * @return the same list, now containing all parent node values
     */
    public List getParents(TreeNode node, List list) {
        if (node != null) {
            if (node.getLeft() != null || node.getRight() != null) {
                list.insertLast(node.getValue());
            }
            getParents(node.getLeft(), list);
            getParents(node.getRight(), list);
        }
        return list;
    }

    /**
     * Collects the values of all child nodes into a list.
     *
     * <p>A child node is any node that is referenced as the left or
     * right child of another node. This method adds both children of
     * each visited node, which means every non-root node is captured
     * exactly once.</p>
     *
     * @param node the subtree root to examine (null is a safe base case)
     * @param list the list that collects the child node values
     * @return the same list, now containing all child node values
     */
    public List getChildren(TreeNode node, List list) {
        if (node != null) {
            if (node.getLeft() != null) {
                list.insertLast(node.getLeft().getValue());
            }
            if (node.getRight() != null) {
                list.insertLast(node.getRight().getValue());
            }
            getChildren(node.getLeft(), list);
            getChildren(node.getRight(), list);
        }
        return list;
    }

    /**
     * Collects the values of all leaf nodes into a list.
     *
     * <p>A leaf node has no children: both its left and right
     * references are null.</p>
     *
     * @param node the subtree root to examine (null is a safe base case)
     * @param list the list that collects the leaf node values
     * @return the same list, now containing all leaf node values
     */
    public List getLeaves(TreeNode node, List list) {
        if (node != null) {
            if (node.getLeft() == null && node.getRight() == null) {
                list.insertLast(node.getValue());
            }
            getLeaves(node.getLeft(), list);
            getLeaves(node.getRight(), list);
        }
        return list;
    }

    /**
     * Calculates the height of the subtree rooted at the given node.
     *
     * <p>The height is defined as the number of edges on the longest
     * path from the given node down to a leaf. An empty subtree has
     * height 0.</p>
     *
     * @param node the subtree root (null returns 0)
     * @return the height of the subtree
     */
    public int height(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftHeight  = height(node.getLeft());
        int rightHeight = height(node.getRight());
        return Math.max(leftHeight, rightHeight) + 1;
    }
}
