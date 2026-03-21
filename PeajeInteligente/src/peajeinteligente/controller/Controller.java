package peajeinteligente.controller;

import peajeinteligente.model.List;
import peajeinteligente.model.Queue;
import peajeinteligente.model.Stack;
import peajeinteligente.model.Vehicle;
import peajeinteligente.view.IOManager;

import java.io.IOException;

/**
 * Controlador principal del sistema de peaje.
 * Coordina las operaciones de registro, atencion, reversion e historial.
 * Contiene el bucle de menu y el switch de despacho.
 */
public class Controller {

    /** Gestor de entrada/salida. */
    private IOManager io;

    /** Caseta 1. */
    private Queue<Vehicle> booth1;

    /** Caseta 2. */
    private Queue<Vehicle> booth2;

    /** Caseta 3. */
    private Queue<Vehicle> booth3;

    /** Caseta 4. */
    private Queue<Vehicle> booth4;

    /** Pila de deshacer: almacena el ultimo vehiculo atendido por caseta. */
    private Stack<Vehicle> undoStack;

    /** Historial cronologico de vehiculos atendidos. */
    private List<Vehicle> history;

    /**
     * Crea el controlador e inicializa todas las estructuras.
     *
     * @param io gestor de entrada/salida
     */
    public Controller(IOManager io) {
        this.io = io;
        booth1 = new Queue<>();
        booth2 = new Queue<>();
        booth3 = new Queue<>();
        booth4 = new Queue<>();
        undoStack = new Stack<>();
        history = new List<>();
    }

    /**
     * Ejecuta el bucle principal del programa.
     * Muestra el menu, lee la opcion y despacha al metodo correspondiente.
     *
     * @throws IOException si ocurre un error de lectura
     */
    public void ejecutar() throws IOException {
        int op;
        do {
            op = io.showMenu();
            switch (op) {
                case 1:
                    registrar();
                    break;
                case 2:
                    atender();
                    break;
                case 3:
                    revertir();
                    break;
                case 4:
                    mostrarHistorial();
                    break;
                case 5:
                    io.showMessage("Hasta luego.");
                    break;
                default:
                    io.showMessage("Opcion no valida.");
            }
        } while (op != 5);
    }

    /**
     * Lee los datos del vehiculo y lo encola en la caseta con menos vehiculos.
     *
     * @throws IOException si ocurre un error de lectura
     */
    private void registrar() throws IOException {
        String plate = io.getString("Placa: ");
        int category = io.getInt("Categoria (1-3): ");
        double toll = calcularPeaje(category);
        Vehicle vehicle = new Vehicle(plate, category, toll);
        findShortestBooth().enqueue(vehicle);
        io.showMessage("Vehiculo registrado: " + vehicle);
    }

    /**
     * Lee el numero de caseta, desencola el vehiculo del frente y lo registra.
     * El vehiculo atendido queda en la pila de deshacer y en el historial.
     *
     * @throws IOException si ocurre un error de lectura
     */
    private void atender() throws IOException {
        int boothNum = io.getInt("Caseta (1-4): ");
        Queue<Vehicle> booth = boothByNumber(boothNum);
        if (booth == null) {
            io.showMessage("Caseta invalida.");
            return;
        }
        if (booth.isEmpty()) {
            io.showMessage("La caseta " + boothNum + " esta vacia.");
            return;
        }
        Vehicle vehicle = booth.dequeue();
        undoStack.push(vehicle);
        history.add(vehicle);
        io.showMessage("Atendido: " + vehicle);
    }

    /**
     * Revierte la ultima atencion sacando el vehiculo de la pila de deshacer.
     */
    private void revertir() {
        if (undoStack.isEmpty()) {
            io.showMessage("No hay operaciones para revertir.");
            return;
        }
        Vehicle vehicle = undoStack.pop();
        io.showMessage("Revertido: " + vehicle);
    }

    /**
     * Muestra el historial completo de vehiculos atendidos.
     */
    private void mostrarHistorial() {
        io.showMessage("=== Historial ===");
        history.show();
    }

    /**
     * Retorna la caseta con menor cantidad de vehiculos en espera.
     * Compara los tamanos sin recorrido destructivo gracias a {@link Queue#getSize()}.
     *
     * @return caseta con menor carga
     */
    private Queue<Vehicle> findShortestBooth() {
        Queue<Vehicle> shortest = booth1;
        if (booth2.getSize() < shortest.getSize()) shortest = booth2;
        if (booth3.getSize() < shortest.getSize()) shortest = booth3;
        if (booth4.getSize() < shortest.getSize()) shortest = booth4;
        return shortest;
    }

    /**
     * Retorna la caseta correspondiente al numero indicado.
     *
     * @param number numero de caseta (1-4)
     * @return caseta o {@code null} si el numero es invalido
     */
    private Queue<Vehicle> boothByNumber(int number) {
        switch (number) {
            case 1: return booth1;
            case 2: return booth2;
            case 3: return booth3;
            case 4: return booth4;
            default: return null;
        }
    }

    /**
     * Calcula el valor del peaje segun la categoria del vehiculo.
     *
     * @param category categoria del vehiculo (1, 2 o 3)
     * @return valor del peaje
     */
    private double calcularPeaje(int category) {
        if (category == 1) return 10000;
        if (category == 2) return 15000;
        if (category == 3) return 20000;
        return 0;
    }
}
