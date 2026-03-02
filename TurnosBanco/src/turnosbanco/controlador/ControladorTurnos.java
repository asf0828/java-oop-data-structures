package turnosbanco.controlador;

import turnosbanco.modelo.Cola;
import turnosbanco.vista.Vista;

/**
 * Clase ControladorTurnos orquesta el flujo del programa.
 * Lee clientes, los distribuye en dos filas segun la inicial del nombre,
 * muestra las filas y los atiende alternando con prioridad a la fila 1.
 * Esta clase pertenece al controlador en la arquitectura MVC.
 */
public class ControladorTurnos {

    private Vista vista;
    private Cola fila1;
    private Cola fila2;

    /**
     * Constructor del controlador.
     * @param vista La vista para interaccion con el usuario
     */
    public ControladorTurnos(Vista vista) {
        this.vista = vista;
        this.fila1 = new Cola();
        this.fila2 = new Cola();
    }

    /**
     * Ejecuta el flujo completo del ejercicio.
     */
    public void ejecutar() {
        int cantidad = vista.leerEntero("Digite el numero de clientes: ");
        for (int i = 0; i < cantidad; i++) {
            String nombre = vista.leerTexto("Digite el nombre del cliente: ");
            distribuirCliente(nombre);
        }

        vista.mostrarLinea();
        vista.mostrarFila("Clientes en la fila 1", fila1);
        vista.mostrarFila("Clientes en la fila 2", fila2);
        vista.mostrarLinea();
        vista.mostrarMensaje("Clientes atendidos");
        atenderClientes();
    }

    /**
     * Encola el cliente en fila1 si su nombre empieza con vocal,
     * o en fila2 si empieza con consonante.
     * @param nombre El nombre del cliente
     */
    private void distribuirCliente(String nombre) {
        if (empiezaConVocal(nombre)) {
            fila1.encolar(nombre);
        } else {
            fila2.encolar(nombre);
        }
    }

    /**
     * Indica si el nombre empieza con una vocal (A, E, I, O, U).
     * @param nombre El nombre a evaluar
     * @return true si la primera letra es vocal, false en caso contrario
     */
    private boolean empiezaConVocal(String nombre) {
        if (nombre == null || nombre.isEmpty()) return false;
        char inicial = Character.toUpperCase(nombre.charAt(0));
        return inicial == 'A' || inicial == 'E' || inicial == 'I' || inicial == 'O' || inicial == 'U';
    }

    /**
     * Atiende los clientes alternando entre fila1 y fila2,
     * dando prioridad a fila1. Muestra el estado de las filas
     * al quedar una de ellas vacia.
     */
    private void atenderClientes() {
        boolean turnoFila1 = true;
        while (!fila1.estaVacia() || !fila2.estaVacia()) {
            if (turnoFila1) {
                if (!fila1.estaVacia()) {
                    String cliente = fila1.desencolar();
                    vista.mostrarMensaje("cliente atendido de la fila 1: " + cliente);
                    turnoFila1 = false;
                } else {
                    // fila1 vacia, atender fila2
                    String cliente = fila2.desencolar();
                    vista.mostrarMensaje("cliente atendido de la fila 2: " + cliente);
                }
            } else {
                if (!fila2.estaVacia()) {
                    String cliente = fila2.desencolar();
                    vista.mostrarMensaje("cliente atendido de la fila 2: " + cliente);
                    turnoFila1 = true;
                } else {
                    // fila2 vacia, atender fila1
                    String cliente = fila1.desencolar();
                    vista.mostrarMensaje("cliente atendido de la fila 1: " + cliente);
                }
            }
        }
    }

}
