package peajeinteligente.model;

/**
 * Representa un vehiculo que pasa por el peaje.
 * Almacena la placa, la categoria, el valor del peaje y la hora de ingreso.
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
     * Crea un vehiculo con todos sus datos, incluida la hora de ingreso.
     * El timestamp es responsabilidad del llamador para permitir
     * tiempos reales (registro manual) o simulados (registro masivo).
     *
     * @param plate     placa del vehiculo
     * @param category  categoria del vehiculo
     * @param toll      valor del peaje
     * @param timestamp hora de ingreso en formato HH:mm:ss
     */
    public Vehicle(String plate, int category, double toll, String timestamp) {
        this.plate = plate;
        this.category = category;
        this.toll = toll;
        this.timestamp = timestamp;
    }

    /**
     * Retorna la placa del vehiculo.
     *
     * @return placa
     */
    public String getPlate() {
        return plate;
    }

    /**
     * Retorna la categoria del vehiculo.
     *
     * @return categoria
     */
    public int getCategory() {
        return category;
    }

    /**
     * Retorna el valor del peaje.
     *
     * @return valor del peaje
     */
    public double getToll() {
        return toll;
    }

    /**
     * Retorna la hora de ingreso al peaje.
     *
     * @return timestamp en formato HH:mm:ss
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * Representacion legible del vehiculo para mostrar en consola.
     *
     * @return cadena con placa, categoria, peaje y hora
     */
    @Override
    public String toString() {
        return plate + " Cat:" + category + " $" + (int) toll + " [" + timestamp + "]";
    }
}
