package peajeinteligente.controller;

import peajeinteligente.model.List;
import peajeinteligente.model.Queue;
import peajeinteligente.model.Stack;
import peajeinteligente.model.Vehicle;
import peajeinteligente.view.IOManager;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

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

    /** Pila de deshacer: permite revertir la ultima atencion. */
    private Stack<Vehicle> undoStack;

    /** Historial cronologico de vehiculos atendidos. */
    private List<Vehicle> history;

    /** Generador de valores aleatorios para el registro masivo. */
    private Random random;

    /** Formato de hora utilizado para los timestamps. */
    private static final DateTimeFormatter FORMATO_HORA =
            DateTimeFormatter.ofPattern("HH:mm:ss");

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
        random = new Random();
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
                    registrarAleatorio();
                    break;
                case 3:
                    mostrarEstado();
                    break;
                case 4:
                    atender();
                    break;
                case 5:
                    revertir();
                    break;
                case 6:
                    mostrarHistorial();
                    break;
                case 0:
                    io.showMessage("Hasta luego.");
                    break;
                default:
                    io.showMessage("Opcion no valida.");
            }
        } while (op != 0);
    }

    /**
     * Lee los datos del vehiculo manualmente y lo encola en la caseta mas corta.
     *
     * @throws IOException si ocurre un error de lectura
     */
    private void registrar() throws IOException {
        String plate = io.getString("Placa: ");
        int category = io.getInt("Categoria (1-3): ");
        double toll = calcularPeaje(category);
        String timestamp = LocalTime.now().format(FORMATO_HORA);
        Vehicle vehicle = new Vehicle(plate, category, toll, timestamp);
        findShortestBooth().enqueue(vehicle);
        io.showMessage("Vehiculo registrado: " + vehicle);
    }

    /**
     * Genera N vehiculos con placa y categoria aleatorias y los distribuye
     * automaticamente entre las casetas segun la carga actual.
     *
     * @throws IOException si ocurre un error de lectura
     */
    private void registrarAleatorio() throws IOException {
        int n = io.getInt("Ingrese la cantidad de vehiculos: ");
        LocalTime tiempo = LocalTime.now();
        for (int i = 0; i < n; i++) {
            Vehicle vehicle = generarVehiculo(tiempo.format(FORMATO_HORA));
            findShortestBooth().enqueue(vehicle);
            io.showMessage("Vehiculo registrado: " + vehicle);
            tiempo = tiempo.plusSeconds(500);
        }
    }

    /**
     * Muestra el estado actual de todas las casetas, pila y historial.
     */
    private void mostrarEstado() {
        io.showState(booth1.getSize(), booth2.getSize(),
                booth3.getSize(), booth4.getSize(),
                undoStack.getSize(), history.getSize());
    }

    /**
     * Atiende todos los vehiculos de la caseta seleccionada en orden FIFO.
     * Cada vehiculo atendido se apila en undoStack y se agrega al historial.
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
        io.showMessage("=== Atendiendo caseta " + boothNum + " ===");
        while (!booth.isEmpty()) {
            Vehicle vehicle = booth.dequeue();
            undoStack.push(vehicle);
            history.add(vehicle);
            io.showMessage("  Atendido: " + vehicle);
        }
    }

    /**
     * Revierte la ultima atencion sacando el vehiculo de la cima de la pila.
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
     * @return caseta, o {@code null} si el numero es invalido
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
     * Genera un vehiculo aleatorio con placa (formato LLL000), categoria (1-3)
     * y peaje calculado segun la tarifa vigente.
     *
     * @param timestamp hora de llegada del vehiculo
     * @return vehiculo generado
     */
    private Vehicle generarVehiculo(String timestamp) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            sb.append((char) ('A' + random.nextInt(26)));
        }
        for (int i = 0; i < 3; i++) {
            sb.append(random.nextInt(10));
        }
        String plate = sb.toString();
        int category = random.nextInt(3) + 1;
        double toll = calcularPeaje(category);
        return new Vehicle(plate, category, toll, timestamp);
    }

    /**
     * Calcula el valor del peaje segun la categoria del vehiculo.
     * Tarifas del Peaje T Tolima - La Linea (Tolima).
     *
     * @param category categoria del vehiculo (1, 2 o 3)
     * @return valor del peaje
     */
    private double calcularPeaje(int category) {
        if (category == 1) return 13000;
        if (category == 2) return 14600;
        if (category == 3) return 29800;
        return 0;
    }
}
