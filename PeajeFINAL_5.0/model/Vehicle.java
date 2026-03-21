
package model;
public class Vehicle{
    public String plate;
    public int cat;
    public double toll;
    public Vehicle(String p,int c,double t){
        plate=p;cat=c;toll=t;
    }
    public String toString(){
        return plate+" Cat:"+cat+" $"+toll;
    }
}
