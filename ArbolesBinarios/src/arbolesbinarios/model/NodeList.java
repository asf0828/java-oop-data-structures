
package arbolesbinarios.model;

/**
 * Implementación simple de una lista para almacenar nodos.
 * Se usa para cumplir con el requisito del taller:
 * "El almacenamiento de los nodos se debe hacer en una estructura tipo lista".
 */

public class NodeList {

    private TreeNode[] data;
    private int size;

    public NodeList(){
        data = new TreeNode[50];
        size = 0;
    }

    public void add(TreeNode node){
        data[size] = node;
        size++;
    }

    public TreeNode get(int index){
        return data[index];
    }

    public int size(){
        return size;
    }
}
