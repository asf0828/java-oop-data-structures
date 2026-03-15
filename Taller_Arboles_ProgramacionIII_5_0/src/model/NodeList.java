
package model;

/**
 * Implementación simple de una lista para almacenar nodos.
 * Se usa para cumplir con el requisito del taller:
 * "El almacenamiento de los nodos se debe hacer en una estructura tipo lista".
 */

public class NodeList {

    private Node[] data;
    private int size;

    public NodeList(){
        data = new Node[50];
        size = 0;
    }

    public void add(Node node){
        data[size] = node;
        size++;
    }

    public Node get(int index){
        return data[index];
    }

    public int size(){
        return size;
    }
}
