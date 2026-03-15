
package controller;

import model.Tree;

/**
 * Controlador del patrón MVC.
 * Se encarga de conectar la vista con el modelo.
 */

public class TreeController {

    private Tree tree;

    public TreeController(){
        tree = new Tree();
    }

    public void ejecutar(){

        System.out.println("=========== RECORRIDOS ===========");
        tree.showTraversals();

        System.out.println("\n=========== NODOS PADRE ===========");
        tree.showParents();

        System.out.println("\n=========== NODOS HIJO ===========");
        tree.showChildren();

        System.out.println("\n=========== NODOS HOJA ===========");
        tree.showLeaves();

        System.out.println("\n=========== ALTURA DEL ARBOL ===========");
        tree.showHeight();
    }
}
