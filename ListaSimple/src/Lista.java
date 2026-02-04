public class Lista {
    private Nodo cabeza;
    private int tamano;

    public Lista() {
        cabeza = null;
        tamano = 0;
    }

    public void insertar(int valor) {
        Nodo nuevo = new Nodo(valor);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            Nodo actual = cabeza;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
        }
        tamano++;
    }

    public Nodo buscarPorPosicion(int posicion) {
        if (posicion < 0 || posicion >= tamano) {
            return null;
        }
        Nodo actual = cabeza;
        for (int i = 0; i < posicion; i++) {
            actual = actual.getSiguiente();
        }
        return actual;
    }

    public int getTamano() {
        return tamano;
    }

    public void mostrarContenido() {
        Nodo actual = cabeza;
        int i = 0;
        while (actual != null) {
            System.out.println("Posicion " + i + ": " + actual.getValor());
            actual = actual.getSiguiente();
            i++;
        }
    }

    public double calcularPromedio() {
        if (tamano == 0) return 0;
        int suma = 0;
        Nodo actual = cabeza;
        while (actual != null) {
            suma += actual.getValor();
            actual = actual.getSiguiente();
        }
        return (double) suma / tamano;
    }
}

