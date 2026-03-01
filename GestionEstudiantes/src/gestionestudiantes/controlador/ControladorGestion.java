package gestionestudiantes.controlador;

import gestionestudiantes.modelo.Cola;
import gestionestudiantes.modelo.Estudiante;
import gestionestudiantes.modelo.Nodo;
import gestionestudiantes.vista.Vista;

/**
 * Clase ControladorGestion orquesta el flujo del programa.
 * Lee estudiantes, solicita el nombre a buscar y la nueva edad,
 * y al final muestra la cola original y la cola con el reemplazo aplicado.
 * Esta clase pertenece al controlador en la arquitectura MVC.
 */
public class ControladorGestion {

    private Vista vista;
    private Cola cola;

    /**
     * Constructor del controlador.
     * @param vista La vista para interaccion con el usuario
     */
    public ControladorGestion(Vista vista) {
        this.vista = vista;
        this.cola = new Cola();
    }

    /**
     * Ejecuta el flujo completo del ejercicio.
     * Primero se recolecta toda la entrada (estudiantes, nombre a buscar y nueva edad)
     * y al final se muestra la salida: Queue Original y Queue reemplazada.
     */
    public void ejecutar() {
        cargarEstudiantes();

        vista.mostrarMensaje("");
        String nombre = vista.leerTexto("Ingrese el nombre a buscar: ");
        int nuevaEdad = vista.leerEntero("Ingrese la nueva edad: ");

        vista.mostrarMensaje("");
        vista.mostrarLinea();
        vista.mostrarCola("Queue Original:", cola);

        reemplazarEdad(nombre, nuevaEdad);

        vista.mostrarCola("Queue reemplazada:", cola);
        vista.mostrarLinea();
    }

    /**
     * Lee pares nombre/edad del usuario y los encola.
     * El ingreso termina cuando el nombre sea "end".
     */
    private void cargarEstudiantes() {
        vista.mostrarMensaje("Ingrese estudiantes (escriba 'end' en el nombre para terminar):");
        while (true) {
            String nombre = vista.leerTexto("Nombre: ");
            if (nombre.equalsIgnoreCase("end")) {
                break; // El usuario indicó que no hay mas estudiantes: se termina el ciclo.
            }
            int edad = vista.leerEntero("Edad: ");
            cola.encolar(new Estudiante(nombre, edad));
        }
    }

    /**
     * Busca el primer estudiante con el nombre dado y actualiza su edad.
     * El recorrido se realiza directamente sobre los nodos de la cola.
     * @param nombre    El nombre del estudiante a buscar
     * @param nuevaEdad La nueva edad a asignar
     */
    private void reemplazarEdad(String nombre, int nuevaEdad) {
        Nodo actual = cola.obtenerFrente();
        while (actual != null) {
            if (actual.obtenerValor().obtenerNombre().equalsIgnoreCase(nombre)) {
                actual.obtenerValor().asignarEdad(nuevaEdad);
                return; // Se encontró el estudiante: se actualizó la edad y se termina el ciclo y el metodo.
            }
            actual = actual.obtenerSiguiente();
        }
        // Si llegamos aquí, el ciclo terminó sin encontrar ningun estudiante con ese nombre.
        vista.mostrarMensaje("Estudiante '" + nombre + "' no encontrado.");
    }
}
