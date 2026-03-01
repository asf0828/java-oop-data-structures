package gestionestudiantes.modelo;

/**
 * Clase Estudiante representa el dominio del negocio.
 * Encapsula el nombre y la edad de un estudiante.
 */
public class Estudiante {

    private String nombre;
    private int edad;

    /**
     * Constructor con nombre y edad.
     * @param nombre El nombre del estudiante
     * @param edad   La edad del estudiante
     */
    public Estudiante(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    /**
     * Obtiene el nombre del estudiante.
     * @return El nombre del estudiante
     */
    public String obtenerNombre() {
        return nombre;
    }

    /**
     * Obtiene la edad del estudiante.
     * @return La edad del estudiante
     */
    public int obtenerEdad() {
        return edad;
    }

    /**
     * Actualiza la edad del estudiante.
     * @param edad La nueva edad a asignar
     */
    public void asignarEdad(int edad) {
        this.edad = edad;
    }
}
