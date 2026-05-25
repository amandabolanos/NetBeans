/**
 * Implementa el algoritmo de ordenamiento Shellsort.
 * Generalización del ordenamiento por inserción con brechas decrecientes.
 */
public class Shellsort {

    /**
     * Ordena un arreglo de Personas usando el algoritmo Shellsort.
     * Complejidad: O(n log n) a O(n²) dependiendo de la secuencia de brechas.
     *
     * @param personas Arreglo de personas a ordenar (copia del original)
     */
    public static void ordenar(Persona[] personas) {
        int n = personas.length;

        // Usar la secuencia de Knuth: h = 3*h + 1
        int h = 1;
        while (h < n / 3) {
            h = 3 * h + 1;
        }

        while (h >= 1) {
            // Ordenamiento por inserción con brecha h
            for (int i = h; i < n; i++) {
                Persona clave = personas[i];
                int j = i;

                while (j >= h && personas[j - h].compareTo(clave) > 0) {
                    personas[j] = personas[j - h];
                    j -= h;
                }

                personas[j] = clave;
            }

            h = h / 3;
        }
    }
}
