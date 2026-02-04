public class Lista {
    private Nodo cabeza;
    private int tamaño;

    public Lista() {
        cabeza = null;
        tamaño = 0;
    }

    public void insertar(int valor) {
        Nodo nuevo = new Nodo(valor);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            Nodo actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }
        tamaño++;
    }

    public Nodo buscarPorPosicion(int posicion) {
        if (posicion < 0 || posicion >= tamaño) {
            return null;
        }
        Nodo actual = cabeza;
        for (int i = 0; i < posicion; i++) {
            actual = actual.siguiente;
        }
        return actual;
    }

    public int getTamaño() {
        return tamaño;
    }

    public void mostrarContenido() {
        Nodo actual = cabeza;
        int i = 0;
        while (actual != null) {
            System.out.println("Posición " + i + ": " + actual.valor);
            actual = actual.siguiente;
            i++;
        }
    }

    public double calcularPromedio() {
        if (tamaño == 0) return 0;
        int suma = 0;
        Nodo actual = cabeza;
        while (actual != null) {
            suma += actual.valor;
            actual = actual.siguiente;
        }
        return (double) suma / tamaño;
    }
}

