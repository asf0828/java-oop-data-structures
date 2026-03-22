package peajeinteligente.model;

/**
 * Representa un vehiculo que pasa por el peaje.
 * Almacena la placa, la categoria, el valor del peaje, la hora de ingreso
 * y la caseta por la que fue atendido.
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

    /** Numero de caseta por la que fue atendido (1-4). Asignado en atencion. */
    private int booth;

    /**
     * Crea un vehiculo con todos sus datos, incluida la hora de ingreso.
     *
     * @param plate     placa del vehiculo
     * @param category  categoria del vehiculo
     * @param toll      valor del peaje
     * @param timestamp hora de ingreso en formato HH:mm:ss
     */
    public Vehicle(String plate, int category, double toll, String timestamp) {
        this.plate     = plate;
        this.category  = category;
        this.toll      = toll;
        this.timestamp = timestamp;
        this.booth     = 0;
    }

    public String getPlate()     { return plate; }
    public int    getCategory()  { return category; }
    public double getToll()      { return toll; }
    public String getTimestamp() { return timestamp; }
    public int    getBooth()     { return booth; }

    /**
     * Asigna la caseta por la que fue atendido el vehiculo.
     *
     * @param booth numero de caseta (1-4)
     */
    public void setBooth(int booth) { this.booth = booth; }

    @Override
    public String toString() {
        return plate + " Cat:" + category + " $" + (int) toll + " [" + timestamp + "]";
    }
}
