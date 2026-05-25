/**
 * Clase Persona que implementa Comparable para ordenamiento por cédula.
 * Almacena información personal: cédula, nombre y edad.
 */
public class Persona implements Comparable<Persona> {
    private int cedula;
    private String nombre;
    private int edad;

    /**
     * Constructor de la clase Persona.
     *
     * @param cedula Número de cédula único
     * @param nombre Nombre completo de la persona
     * @param edad Edad de la persona
     */
    public Persona(int cedula, String nombre, int edad) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.edad = edad;
    }

    // Getters
    public int getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    /**
     * Compara dos personas por su cédula.
     *
     * @param otra Persona a comparar
     * @return Valor negativo si esta cédula es menor, 0 si es igual, positivo si es mayor
     */
    @Override
    public int compareTo(Persona otra) {
        return Integer.compare(this.cedula, otra.cedula);
    }

    /**
     * Representación en string de la persona.
     *
     * @return String con la información formateada
     */
    @Override
    public String toString() {
        return String.format("Cédula: %d | Nombre: %s | Edad: %d", cedula, nombre, edad);
    }
}
