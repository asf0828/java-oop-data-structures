
package model;

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

    private Node root;
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

        Node n24 = new Node(24);
        Node n27 = new Node(27);
        Node n5 = new Node(5);
        Node n32 = new Node(32);
        Node n4 = new Node(4);
        Node n3 = new Node(3);
        Node n6 = new Node(6);
        Node n12 = new Node(12);
        Node n1 = new Node(1);
        Node n8 = new Node(8);
        Node n2 = new Node(2);

        root = n24;

        n24.left = n27;
        n24.right = n5;

        n27.left = n32;
        n27.right = n4;

        n4.left = n3;
        n4.right = n6;

        n5.left = n12;
        n5.right = n1;

        n1.left = n8;

        n8.right = n2;

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

    public void preorder(Node node){
        if(node != null){
            System.out.print(node.value + " ");
            preorder(node.left);
            preorder(node.right);
        }
    }

    public void inorder(Node node){
        if(node != null){
            inorder(node.left);
            System.out.print(node.value + " ");
            inorder(node.right);
        }
    }

    public void postorder(Node node){
        if(node != null){
            postorder(node.left);
            postorder(node.right);
            System.out.print(node.value + " ");
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
            Node n = nodes.get(i);

            if(n.left != null || n.right != null){
                System.out.print(n.value + " ");
                count++;
            }
        }

        System.out.println("\nTotal nodos padre: " + count);
    }

    // ================= HIJOS =================

    public void showChildren(){

        int count = 0;

        for(int i=0;i<nodes.size();i++){
            Node n = nodes.get(i);

            if(n != root){
                System.out.print(n.value + " ");
                count++;
            }
        }

        System.out.println("\nTotal nodos hijo: " + count);
    }

    // ================= HOJAS =================

    public void showLeaves(){

        int count = 0;

        for(int i=0;i<nodes.size();i++){
            Node n = nodes.get(i);

            if(n.left == null && n.right == null){
                System.out.print(n.value + " ");
                count++;
            }
        }

        System.out.println("\nTotal nodos hoja: " + count);
    }

    // ================= ALTURA =================

    public int height(Node node){

        if(node == null)
            return 0;

        int leftHeight = height(node.left);
        int rightHeight = height(node.right);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    public void showHeight(){
        System.out.println("Altura del arbol: " + height(root));
    }
}
