package arbolesbinarios.model;

/**
 * Representa un nodo de un árbol binario.
 *
 * Cada nodo almacena un valor entero y referencias a su hijo
 * izquierdo y derecho. Cuando un hijo no existe, su referencia es null.
 */
public class TreeNode {

    private int value;
    private TreeNode left;
    private TreeNode right;

    /**
     * Crea un nuevo nodo con el valor entero dado.
     * Ambas referencias de hijo se inicializan en null.
     *
     * @param value el valor entero almacenado en este nodo
     */
    public TreeNode(int value) {
        this.value = value;
        this.left  = null;
        this.right = null;
    }

    /**
     * Retorna el valor entero almacenado en este nodo.
     *
     * @return el valor del nodo
     */
    public int getValue() {
        return value;
    }

    /**
     * Retorna el hijo izquierdo de este nodo, o null si no tiene.
     *
     * @return el TreeNode hijo izquierdo
     */
    public TreeNode getLeft() {
        return left;
    }

    /**
     * Retorna el hijo derecho de este nodo, o null si no tiene.
     *
     * @return el TreeNode hijo derecho
     */
    public TreeNode getRight() {
        return right;
    }

    /**
     * Asigna el hijo izquierdo de este nodo.
     *
     * @param left el TreeNode a asignar como hijo izquierdo
     */
    public void setLeft(TreeNode left) {
        this.left = left;
    }

    /**
     * Asigna el hijo derecho de este nodo.
     *
     * @param right el TreeNode a asignar como hijo derecho
     */
    public void setRight(TreeNode right) {
        this.right = right;
    }
}
