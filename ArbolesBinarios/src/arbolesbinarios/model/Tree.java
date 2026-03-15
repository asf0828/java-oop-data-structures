package arbolesbinarios.model;

/**
 * Clase Tree
 * Implementa las operaciones solicitadas en el taller.
 * - Recorridos
 * - Padres
 * - Hijos
 * - Hojas
 * - Altura
 */

public class Tree {

    private TreeNode root;
    private NodeList nodes;

    public Tree(){
        nodes = new NodeList();
        buildTree();
    }

    /**
     * Construcción del árbol dado en el enunciado:
     * 24 (27 (32, 4 (3,6)), 5 (12, 1 (8 (null,2), null)))
     */
    private void buildTree(){

        TreeNode n24 = new TreeNode(24);
        TreeNode n27 = new TreeNode(27);
        TreeNode n5 = new TreeNode(5);
        TreeNode n32 = new TreeNode(32);
        TreeNode n4 = new TreeNode(4);
        TreeNode n3 = new TreeNode(3);
        TreeNode n6 = new TreeNode(6);
        TreeNode n12 = new TreeNode(12);
        TreeNode n1 = new TreeNode(1);
        TreeNode n8 = new TreeNode(8);
        TreeNode n2 = new TreeNode(2);

        root = n24;

        n24.setLeft(n27);
        n24.setRight(n5);

        n27.setLeft(n32);
        n27.setRight(n4);

        n4.setLeft(n3);
        n4.setRight(n6);

        n5.setLeft(n12);
        n5.setRight(n1);

        n1.setLeft(n8);

        n8.setRight(n2);

        nodes.add(n24);
        nodes.add(n27);
        nodes.add(n5);
        nodes.add(n32);
        nodes.add(n4);
        nodes.add(n3);
        nodes.add(n6);
        nodes.add(n12);
        nodes.add(n1);
        nodes.add(n8);
        nodes.add(n2);
    }

    // ================= RECORRIDOS =================

    public void preorder(TreeNode node){
        if(node != null){
            System.out.print(node.getValue() + " ");
            preorder(node.getLeft());
            preorder(node.getRight());
        }
    }

    public void inorder(TreeNode node){
        if(node != null){
            inorder(node.getLeft());
            System.out.print(node.getValue() + " ");
            inorder(node.getRight());
        }
    }

    public void postorder(TreeNode node){
        if(node != null){
            postorder(node.getLeft());
            postorder(node.getRight());
            System.out.print(node.getValue() + " ");
        }
    }

    public void showTraversals(){
        System.out.println("PREORDEN:");
        preorder(root);
        System.out.println();

        System.out.println("INORDEN:");
        inorder(root);
        System.out.println();

        System.out.println("POSTORDEN:");
        postorder(root);
        System.out.println();
    }

    // ================= PADRES =================

    public void showParents(){

        int count = 0;

        for(int i=0;i<nodes.size();i++){
            TreeNode n = nodes.get(i);

            if(n.getLeft() != null || n.getRight() != null){
                System.out.print(n.getValue() + " ");
                count++;
            }
        }

        System.out.println("\nTotal nodos padre: " + count);
    }

    // ================= HIJOS =================

    public void showChildren(){

        int count = 0;

        for(int i=0;i<nodes.size();i++){
            TreeNode n = nodes.get(i);

            if(n != root){
                System.out.print(n.getValue() + " ");
                count++;
            }
        }

        System.out.println("\nTotal nodos hijo: " + count);
    }

    // ================= HOJAS =================

    public void showLeaves(){

        int count = 0;

        for(int i=0;i<nodes.size();i++){
            TreeNode n = nodes.get(i);

            if(n.getLeft() == null && n.getRight() == null){
                System.out.print(n.getValue() + " ");
                count++;
            }
        }

        System.out.println("\nTotal nodos hoja: " + count);
    }

    // ================= ALTURA =================

    public int height(TreeNode node){

        if(node == null)
            return 0;

        int leftHeight = height(node.getLeft());
        int rightHeight = height(node.getRight());

        return Math.max(leftHeight, rightHeight) + 1;
    }

    public void showHeight(){
        System.out.println("Altura del arbol: " + height(root));
    }
}
