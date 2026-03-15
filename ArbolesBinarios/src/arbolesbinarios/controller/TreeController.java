package arbolesbinarios.controller;

import java.io.IOException;

import arbolesbinarios.model.List;
import arbolesbinarios.model.Tree;
import arbolesbinarios.model.TreeNode;
import arbolesbinarios.view.IOManager;

/**
 * TreeController orchestrates the application flow.
 *
 * <p>It connects the view ({@link IOManager}) with the model ({@link Tree}).
 * Following the MVC pattern, the controller:</p>
 * <ol>
 *   <li>Builds the binary tree (responsibility of the controller, per
 *       the professor's TAD — the model only defines the structure).</li>
 *   <li>Calls tree operations to collect results.</li>
 *   <li>Passes those results to the view for display.</li>
 * </ol>
 *
 * <p>No {@code System.out} calls appear in this class.</p>
 */
public class TreeController {

    private IOManager io;
    private Tree tree;

    /**
     * Creates the controller, injecting the view and building the tree.
     *
     * @param io the IOManager instance used for all console output
     */
    public TreeController(IOManager io) {
        this.io   = io;
        this.tree = buildTree();
    }

    /**
     * Builds and returns the binary tree defined in the workshop statement.
     *
     * <p>The tree represented as a list notation is:
     * {@code 24 ( 27 ( 32 , 4 ( 3 , 6 ) ), 5 ( 12 , 1 ( 8 ( null , 2 ), null ) ) )}</p>
     *
     * <p>This method follows the pattern shown in the professor's TAD (page 8):
     * tree construction is done in the controller, not inside the Tree model.
     * The model's job is to provide operations; the controller's job is to
     * decide what data to load.</p>
     *
     * @return the fully constructed Tree ready for queries
     */
    private Tree buildTree() {
        Tree t = new Tree();

        TreeNode n24 = new TreeNode(24);
        t.setRoot(n24);

        TreeNode n27 = new TreeNode(27);
        TreeNode n5  = new TreeNode(5);
        n24.setLeft(n27);
        n24.setRight(n5);

        TreeNode n32 = new TreeNode(32);
        TreeNode n4  = new TreeNode(4);
        n27.setLeft(n32);
        n27.setRight(n4);

        TreeNode n3 = new TreeNode(3);
        TreeNode n6 = new TreeNode(6);
        n4.setLeft(n3);
        n4.setRight(n6);

        TreeNode n12 = new TreeNode(12);
        TreeNode n1  = new TreeNode(1);
        n5.setLeft(n12);
        n5.setRight(n1);

        TreeNode n8 = new TreeNode(8);
        n1.setLeft(n8);

        TreeNode n2 = new TreeNode(2);
        n8.setRight(n2);

        return t;
    }

    /**
     * Runs the full sequence of tree operations and displays each result.
     *
     * <p>The execution order is:</p>
     * <ol>
     *   <li>Wait for user to press Enter (demonstrates BufferedReader use).</li>
     *   <li>Display preorder, inorder, and postorder traversals.</li>
     *   <li>Display parent nodes with count.</li>
     *   <li>Display child nodes with count.</li>
     *   <li>Display leaf nodes with count.</li>
     *   <li>Display the tree height.</li>
     * </ol>
     *
     * @throws IOException if the view cannot read from standard input
     */
    public void execute() throws IOException {
        io.getString("Press ENTER to start...");

        io.showMessage("=== BINARY TREE ===");
        io.showMessage("Tree: 24(27(32, 4(3,6)), 5(12, 1(8(null,2), null)))");
        io.showMessage("");

        // Traversals
        io.showMessage("--- Traversals ---");
        io.showList("Preorder: ",  tree.preorder(tree.getRoot(),  new List()));
        io.showList("Inorder:  ",  tree.inorder(tree.getRoot(),   new List()));
        io.showList("Postorder:",  tree.postorder(tree.getRoot(), new List()));
        io.showMessage("");

        // Parent nodes
        io.showMessage("--- Parent nodes ---");
        List parents = tree.getParents(tree.getRoot(), new List());
        io.showList("Parents:", parents);
        io.showMessage("");

        // Child nodes
        io.showMessage("--- Child nodes ---");
        List children = tree.getChildren(tree.getRoot(), new List());
        io.showList("Children:", children);
        io.showMessage("");

        // Leaf nodes
        io.showMessage("--- Leaf nodes ---");
        List leaves = tree.getLeaves(tree.getRoot(), new List());
        io.showList("Leaves:", leaves);
        io.showMessage("");

        // Height
        io.showMessage("--- Tree height ---");
        io.showInt("Height:", tree.height(tree.getRoot()));
    }
}
