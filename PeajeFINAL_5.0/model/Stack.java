
package model;
public class Stack<T>{
    private Node<T> top;
    public void push(T d){
        Node<T> n=new Node<>(d);
        n.next=top;
        top=n;
    }
    public T pop(){
        if(top==null) return null;
        T d=top.data;
        top=top.next;
        return d;
    }
}
