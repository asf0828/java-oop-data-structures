package peajeinteligente.model;

/**
 * Representa un vehiculo que pasa por el peaje.
 * Almacena la placa, la categoria, el valor del peaje y la hora de ingreso.
 * El vehiculo no conoce por cual caseta fue atendido; esa responsabilidad
 * pertenece al Controller mediante listas separadas por caseta.
 */
public class Vehicle {

    /** Placa del vehiculo. */
    private String plate;

    /** Categoria del vehiculo (1, 2 o 3). */
    private int category;

    /** Valor del peaje cobrado. */
    private double toll;

    /** Hora de ingreso al peaje en formato HH:mm:ss. */
    private String timestamp;

    /**
     * Crea un vehiculo con todos sus datos.
     *
     * @param plate     placa del vehiculo
     * @param category  categoria del vehiculo (1, 2 o 3)
     * @param toll      valor del peaje
     * @param timestamp hora de ingreso en formato HH:mm:ss
     */
    public Vehicle(String plate, int category, double toll, String timestamp) {
        this.plate     = plate;
        this.category  = category;
        this.toll      = toll;
        this.timestamp = timestamp;
    }

    public String getPlate()     { return plate; }
    public int    getCategory()  { return category; }
    public double getToll()      { return toll; }
    public String getTimestamp() { return timestamp; }

    @Override
    public String toString() {
        return plate + " Cat:" + category + " $" + (int) toll + " [" + timestamp + "]";
    }
}
