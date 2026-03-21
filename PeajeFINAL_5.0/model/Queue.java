
package model;
public class Queue<T>{
    private Node<T> front,rear;
    public void enqueue(T d){
        Node<T> n=new Node<>(d);
        if(rear==null){front=rear=n;return;}
        rear.next=n; rear=n;
    }
    public T dequeue(){
        if(front==null) return null;
        T d=front.data;
        front=front.next;
        if(front==null) rear=null;
        return d;
    }
    public boolean isEmpty(){return front==null;}
}
