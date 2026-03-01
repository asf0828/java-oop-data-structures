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
    public void ejecutar() { }

    /**
     * Encola el cliente en fila1 si su nombre empieza con vocal,
     * o en fila2 si empieza con consonante.
     * @param nombre El nombre del cliente
     */
    private void distribuirCliente(String nombre) { }

    /**
     * Indica si el nombre empieza con una vocal (A, E, I, O, U).
     * @param nombre El nombre a evaluar
     * @return true si la primera letra es vocal, false en caso contrario
     */
    private boolean empiezaConVocal(String nombre) { return false; }

    /**
     * Atiende los clientes alternando entre fila1 y fila2,
     * dando prioridad a fila1. Muestra el estado de las filas
     * al quedar una de ellas vacia.
     */
    private void atenderClientes() { }

}
