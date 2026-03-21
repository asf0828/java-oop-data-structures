
package model;
public class List<T>{
    private Node<T> head;
    public void add(T d){
        Node<T> n=new Node<>(d);
        if(head==null){head=n;return;}
        Node<T> aux=head;
        while(aux.next!=null) aux=aux.next;
        aux.next=n;
    }
    public void show(){
        Node<T> aux=head;
        while(aux!=null){
            System.out.println(aux.data);
            aux=aux.next;
        }
    }
}
