package arbolesbinarios.controller;

import java.io.IOException;

import arbolesbinarios.model.List;
import arbolesbinarios.model.Tree;
import arbolesbinarios.model.TreeNode;
import arbolesbinarios.view.IOManager;

/**
 * Controlador principal de la aplicación.
 *
 * Conecta la vista ({@link IOManager}) con el modelo ({@link Tree}).
 * Siguiendo el patrón MVC, el controlador construye el árbol, invoca
 * las operaciones sobre él y delega la presentación de resultados a
 * la vista. No contiene llamadas a {@code System.out}.
 */
public class TreeController {

    private IOManager io;
    private Tree tree;

    /**
     * Crea el controlador, inyectando la vista y construyendo el árbol.
     *
     * @param io la instancia de IOManager usada para toda la salida por consola
     */
    public TreeController(IOManager io) {
        this.io   = io;
        this.tree = buildTree();
    }

    /**
     * Construye y retorna el árbol binario con la estructura definida.
     *
     * El árbol en notación de lista es:
     * {@code 24 ( 27 ( 32 , 4 ( 3 , 6 ) ), 5 ( 12 , 1 ( 8 ( null , 2 ), null ) ) )}
     *
     * @return el árbol completamente construido listo para consultas
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
     * Ejecuta la secuencia completa de operaciones sobre el árbol
     * y muestra cada resultado a través de la vista.
     *
     * @throws IOException si la vista no puede leer desde la entrada estándar
     */
    public void execute() throws IOException {
        io.getString("Presione ENTER para iniciar...");

        io.showMessage("=== ÁRBOL BINARIO ===");
        io.showMessage("Árbol: 24(27(32, 4(3,6)), 5(12, 1(8(null,2), null)))");
        io.showMessage("");

        // Recorridos
        io.showMessage("--- Recorridos ---");
        io.showList("Preorden:   ", tree.preorder(tree.getRoot(),  new List()));
        io.showList("Inorden:    ", tree.inorder(tree.getRoot(),   new List()));
        io.showList("Postorden:  ", tree.postorder(tree.getRoot(), new List()));
        io.showMessage("");

        // Nodos padre
        io.showMessage("--- Nodos padre ---");
        List parents = tree.getParents(tree.getRoot(), new List());
        io.showList("Padres:", parents);
        io.showMessage("");

        // Nodos hijo
        io.showMessage("--- Nodos hijo ---");
        List children = tree.getChildren(tree.getRoot(), new List());
        io.showList("Hijos:", children);
        io.showMessage("");

        // Nodos hoja
        io.showMessage("--- Nodos hoja ---");
        List leaves = tree.getLeaves(tree.getRoot(), new List());
        io.showList("Hojas:", leaves);
        io.showMessage("");

        // Altura
        io.showMessage("--- Altura del árbol ---");
        io.showInt("Altura:", tree.height(tree.getRoot()));
    }
}
