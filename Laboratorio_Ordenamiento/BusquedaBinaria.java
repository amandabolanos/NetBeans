/**
 * Implementa el algoritmo de Búsqueda Binaria.
 * Solo funciona en arreglos ordenados.
 */
public class BusquedaBinaria {

    /**
     * Busca una persona por su cédula en un arreglo ordenado.
     * Complejidad: O(log n).
     *
     * @param personas Arreglo ordenado de personas
     * @param cedulaBuscada Cédula a buscar
     * @return La Persona encontrada, o null si no existe
     */
    public static Persona buscar(Persona[] personas, int cedulaBuscada) {
        int bajo = 0;
        int alto = personas.length - 1;

        while (bajo <= alto) {
            int medio = bajo + (alto - bajo) / 2;
            int comparacion = Integer.compare(personas[medio].getCedula(), cedulaBuscada);

            if (comparacion == 0) {
                return personas[medio];
            } else if (comparacion < 0) {
                bajo = medio + 1;
            } else {
                alto = medio - 1;
            }
        }

        return null;
    }
}
