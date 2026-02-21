package operacionespilas.modelo;

/**
 * Clase Pila implementa una pila enlazada de caracteres.
 * Sigue el principio LIFO (ultimo en entrar, primero en salir).
 */
public class Pila {

    private Nodo cima;
    private int tamano;

    /**
     * Constructor de la pila.
     * Inicializa la pila vacia.
     */
    public Pila() {
        this.cima = null;
        this.tamano = 0;
    }

    /**
     * Apila un nuevo caracter en la cima de la pila.
     * @param valor El caracter a apilar
     */
    public void apilar(char valor) {
        Nodo nuevo = new Nodo(valor);
        nuevo.asignarSiguiente(cima);
        cima = nuevo;
        tamano++;
    }

    /**
     * Desapila y retorna el caracter en la cima de la pila.
     * @return El caracter de la cima, o '\0' si la pila esta vacia
     */
    public char desapilar() {
        if (estaVacia()) {
            return '\0';
        }
        char valor = cima.obtenerValor();
        cima = cima.obtenerSiguiente();
        tamano--;
        return valor;
    }

    /**
     * Retorna el nodo en la cima sin extraerlo.
     * @return El nodo cima, o null si la pila esta vacia
     */
    public Nodo obtenerCima() {
        return cima;
    }

    /**
     * Indica si la pila esta vacia.
     * @return true si no hay elementos, false en caso contrario
     */
    public boolean estaVacia() {
        return cima == null;
    }

    /**
     * Retorna la cantidad de elementos en la pila.
     * @return El numero de nodos apilados
     */
    public int obtenerTamano() {
        return tamano;
    }

    /**
     * Compara esta pila con otra para determinar si son iguales.
     * Dos pilas son iguales si tienen el mismo tamano y los mismos
     * caracteres en el mismo orden.
     * @param otra La otra pila a comparar
     * @return true si son iguales, false en caso contrario
     */
    public boolean comparteStack(Pila otra) {
        if (this.tamano != otra.tamano) {
            return false;
        }

        Nodo actual = this.cima;
        Nodo actualOtra = otra.cima;

        while (actual != null) {
            if (actual.obtenerValor() != actualOtra.obtenerValor()) {
                return false;
            }
            actual = actual.obtenerSiguiente();
            actualOtra = actualOtra.obtenerSiguiente();
        }

        return true;
    }
}
