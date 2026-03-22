package peajeinteligente.model;

/**
 * Registro de un dia de operacion para una caseta.
 * Almacena el numero de dia, el total recaudado y una pila con los
 * vehiculos atendidos. La pila permite mostrar el historico empezando
 * por el ultimo vehiculo que cruzo (orden LIFO), tal como lo requiere
 * el supervisor semanal.
 */
public class DailyRecord {

    /** Numero de dia dentro de la semana (1-7). */
    private int dayNumber;

    /** Numero de la caseta a la que pertenece este registro (1-4). */
    private int boothNumber;

    /** Total recaudado en este dia por esta caseta. */
    private double total;

    /**
     * Pila de vehiculos atendidos en este dia.
     * El ultimo en entrar es el primero en mostrarse (LIFO),
     * cumpliendo el requisito: "empezando por el ultimo vehiculo que cruzo".
     */
    private Stack<Vehicle> vehicles;

    /**
     * Crea un registro diario para la caseta y dia indicados.
     *
     * @param boothNumber numero de caseta (1-4)
     * @param dayNumber   numero de dia en la semana (1-7)
     */
    public DailyRecord(int boothNumber, int dayNumber) {
        this.boothNumber = boothNumber;
        this.dayNumber   = dayNumber;
        this.total       = 0;
        this.vehicles    = new Stack<>();
    }

    /**
     * Agrega un vehiculo al registro: lo apila y acumula su tarifa al total.
     *
     * @param v vehiculo atendido
     */
    public void addVehicle(Vehicle v) {
        vehicles.push(v);
        total += v.getToll();
    }

    /** @return numero de dia en la semana */
    public int getDayNumber()   { return dayNumber; }

    /** @return numero de caseta */
    public int getBoothNumber() { return boothNumber; }

    /** @return total recaudado en este dia por esta caseta */
    public double getTotal()    { return total; }

    /** @return pila de vehiculos (LIFO: cima = ultimo vehiculo atendido) */
    public Stack<Vehicle> getVehicles() { return vehicles; }

    /** @return cantidad de vehiculos atendidos */
    public int getVehicleCount() { return vehicles.getSize(); }
}
