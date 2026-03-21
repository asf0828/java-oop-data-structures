package peajeinteligente.model;

/**
 * Representa un vehiculo que pasa por el peaje.
 * Almacena la placa, la categoria y el valor del peaje calculado.
 */
public class Vehicle {

    /** Placa del vehiculo. */
    private String plate;

    /** Categoria del vehiculo (1, 2 o 3). */
    private int category;

    /** Valor del peaje cobrado. */
    private double toll;

    /**
     * Crea un vehiculo con los datos indicados.
     *
     * @param plate    placa del vehiculo
     * @param category categoria del vehiculo
     * @param toll     valor del peaje
     */
    public Vehicle(String plate, int category, double toll) {
        this.plate = plate;
        this.category = category;
        this.toll = toll;
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
     * Representacion legible del vehiculo para mostrar en consola.
     *
     * @return cadena con placa, categoria y peaje
     */
    @Override
    public String toString() {
        return plate + " Cat:" + category + " $" + toll;
    }
}
