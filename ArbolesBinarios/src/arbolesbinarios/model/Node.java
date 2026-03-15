package arbolesbinarios.model;

/**
 * Clase Node
 * Representa cada nodo del árbol.
 * Guarda el valor y las referencias a hijo izquierdo y derecho.
 */
public class Node {

    public int value;
    public Node left;
    public Node right;

    public Node(int value){
        this.value = value;
        this.left = null;
        this.right = null;
    }
}
