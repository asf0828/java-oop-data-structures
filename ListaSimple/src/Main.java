import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Lista lista = new Lista();

        System.out.print("Ingrese la cantidad de notas: ");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.print("Ingrese la nota " + (i + 1) + ": ");
            int nota = sc.nextInt();
            lista.insertar(nota);
        }

        System.out.println("\nContenido de la lista:");
        lista.mostrarContenido();

        System.out.print("\nIngrese la posición a buscar: ");
        int pos = sc.nextInt();
        Nodo encontrado = lista.buscarPorPosicion(pos);

        if (encontrado != null) {
            System.out.println("Valor encontrado: " + encontrado.getValor());
        } else {
            System.out.println("Posicion no valida.");
        }

        double promedio = lista.calcularPromedio();
        System.out.println("\nNota final del estudiante (promedio): " + promedio);
    }
}


