package arbolesbinarios.model;

/**
 * Árbol binario con operaciones de recorrido y consulta.
 *
 * Esta clase define únicamente la estructura del árbol ({@code root}) y
 * los algoritmos que operan sobre él. No construye el árbol ni realiza
 * ninguna salida por consola.
 *
 * Los métodos que recopilan resultados reciben una {@link List} vacía
 * del llamador, la llenan de forma recursiva y la retornan.
 */
public class Tree {

    private TreeNode root;

    /**
     * Crea un árbol vacío sin nodo raíz.
     * La raíz debe asignarse con {@link #setRoot(TreeNode)} antes de
     * invocar cualquier operación.
     */
    public Tree() {
        this.root = null;
    }

    /**
     * Asigna el nodo raíz de este árbol.
     *
     * @param root el TreeNode a usar como raíz
     */
    public void setRoot(TreeNode root) {
        this.root = root;
    }

    /**
     * Retorna el nodo raíz del árbol, o null si el árbol está vacío.
     *
     * @return el TreeNode raíz
     */
    public TreeNode getRoot() {
        return root;
    }

    /**
     * Indica si el árbol no tiene nodo raíz.
     *
     * @return true si la raíz es null
     */
    public boolean isEmpty() {
        return root == null;
    }

    // ============================================================
    // RECORRIDOS
    // ============================================================

    /**
     * Recorrido preorden (raíz → izquierda → derecha).
     * Agrega el valor de cada nodo visitado a la lista dada.
     *
     * @param node el nodo raíz del subárbol a recorrer (null es caso base)
     * @param list la lista que acumula los valores del recorrido
     * @return la misma lista con el resultado del recorrido
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
     * Recorrido inorden (izquierda → raíz → derecha).
     * Agrega el valor de cada nodo visitado a la lista dada.
     *
     * @param node el nodo raíz del subárbol a recorrer (null es caso base)
     * @param list la lista que acumula los valores del recorrido
     * @return la misma lista con el resultado del recorrido
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
     * Recorrido postorden (izquierda → derecha → raíz).
     * Agrega el valor de cada nodo visitado a la lista dada.
     *
     * @param node el nodo raíz del subárbol a recorrer (null es caso base)
     * @param list la lista que acumula los valores del recorrido
     * @return la misma lista con el resultado del recorrido
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
    // MÉTODOS DE CONSULTA
    // ============================================================

    /**
     * Recopila los valores de todos los nodos padre en una lista.
     * Un nodo padre es aquel que tiene al menos un hijo (izquierdo,
     * derecho o ambos).
     *
     * @param node el nodo raíz del subárbol a examinar (null es caso base)
     * @param list la lista que acumula los valores de los nodos padre
     * @return la misma lista con todos los nodos padre
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
     * Recopila los valores de todos los nodos hijo en una lista.
     * Un nodo hijo es aquel referenciado como hijo izquierdo o derecho
     * de otro nodo. Cada nodo no raíz queda capturado exactamente una vez.
     *
     * @param node el nodo raíz del subárbol a examinar (null es caso base)
     * @param list la lista que acumula los valores de los nodos hijo
     * @return la misma lista con todos los nodos hijo
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
     * Recopila los valores de todos los nodos hoja en una lista.
     * Un nodo hoja no tiene hijos: tanto su referencia izquierda como
     * derecha son null.
     *
     * @param node el nodo raíz del subárbol a examinar (null es caso base)
     * @param list la lista que acumula los valores de los nodos hoja
     * @return la misma lista con todos los nodos hoja
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
     * Calcula la altura del subárbol con raíz en el nodo dado.
     * La altura es el número de niveles desde el nodo hasta la hoja más
     * lejana. Un subárbol vacío tiene altura 0.
     *
     * @param node la raíz del subárbol (null retorna 0)
     * @return la altura del subárbol
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
