
package controller;
import model.*;

public class Control{
    private Queue<Vehicle>[] booths;
    private Stack<Vehicle> stack=new Stack<>();
    private List<Vehicle> history=new List<>();

    public Control(){
        booths=new Queue[4];
        for(int i=0;i<4;i++) booths[i]=new Queue<>();
    }

    public void register(String p,int c){
        double t=calc(c);
        Vehicle v=new Vehicle(p,c,t);
        int min=0;
        for(int i=1;i<4;i++){
            if(size(booths[i])<size(booths[min])) min=i;
        }
        booths[min].enqueue(v);
    }

    private int size(Queue<Vehicle> q){
        int count=0;
        Queue<Vehicle> temp=new Queue<>();
        while(!q.isEmpty()){
            Vehicle v=q.dequeue();
            temp.enqueue(v);
            count++;
        }
        while(!temp.isEmpty()) q.enqueue(temp.dequeue());
        return count;
    }

    public Vehicle attend(int booth){
        Vehicle v=booths[booth].dequeue();
        if(v!=null){
            stack.push(v);
            history.add(v);
        }
        return v;
    }

    public Vehicle undo(){
        return stack.pop();
    }

    public void showHistory(){
        history.show();
    }

    private double calc(int c){
        if(c==1) return 10000;
        if(c==2) return 15000;
        if(c==3) return 20000;
        return 0;
    }
}
