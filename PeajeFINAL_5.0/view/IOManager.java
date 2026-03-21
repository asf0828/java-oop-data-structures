
package view;
import java.util.Scanner;

public class IOManager{
    Scanner sc=new Scanner(System.in);

    public int menu(){
        System.out.println("1 Registrar");
        System.out.println("2 Atender");
        System.out.println("3 Revertir");
        System.out.println("4 Historial");
        System.out.println("5 Salir");
        return sc.nextInt();
    }

    public String plate(){
        System.out.print("Placa: ");
        return sc.next();
    }

    public int cat(){
        System.out.print("Categoria: ");
        return sc.nextInt();
    }

    public int booth(){
        System.out.print("Caseta (0-3): ");
        return sc.nextInt();
    }

    public void msg(String m){
        System.out.println(m);
    }
}
