
package runner;
import controller.Control;
import view.IOManager;
import model.Vehicle;

public class Run{
    public static void main(String[] args){
        Control c=new Control();
        IOManager io=new IOManager();
        int op;
        do{
            op=io.menu();
            switch(op){
                case 1:
                    c.register(io.plate(),io.cat());
                    break;
                case 2:
                    Vehicle v=c.attend(io.booth());
                    io.msg(v!=null? "Atendido "+v:"Vacio");
                    break;
                case 3:
                    io.msg("Revertido "+c.undo());
                    break;
                case 4:
                    c.showHistory();
                    break;
            }
        }while(op!=5);
    }
}
