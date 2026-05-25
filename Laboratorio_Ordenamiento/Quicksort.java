/**
 * Implementa el algoritmo de ordenamiento Quicksort.
 * Algoritmo de divide y conquista basado en partición.
 */
public class Quicksort {

    /**
     * Ordena un arreglo de Personas usando el algoritmo Quicksort.
     * Complejidad: O(n log n) promedio, O(n²) peor caso.
     *
     * @param personas Arreglo de personas a ordenar (copia del original)
     */
    public static void ordenar(Persona[] personas) {
        if (personas.length == 0) return;
        quicksort(personas, 0, personas.length - 1);
    }

    /**
     * Método recursivo que implementa el algoritmo quicksort.
     *
     * @param personas Arreglo a ordenar
     * @param bajo Índice inicial
     * @param alto Índice final
     */
    private static void quicksort(Persona[] personas, int bajo, int alto) {
        if (bajo < alto) {
            int indiceParticion = particion(personas, bajo, alto);
            quicksort(personas, bajo, indiceParticion - 1);
            quicksort(personas, indiceParticion + 1, alto);
        }
    }

    /**
     * Particiona el arreglo usando el último elemento como pivote.
     *
     * @param personas Arreglo a particionar
     * @param bajo Índice inicial
     * @param alto Índice final
     * @return Índice del pivote en su posición correcta
     */
    private static int particion(Persona[] personas, int bajo, int alto) {
        Persona pivote = personas[alto];
        int i = bajo - 1;

        for (int j = bajo; j < alto; j++) {
            if (personas[j].compareTo(pivote) < 0) {
                i++;
                // Intercambiar
                Persona temp = personas[i];
                personas[i] = personas[j];
                personas[j] = temp;
            }
        }

        // Colocar el pivote en su posición correcta
        Persona temp = personas[i + 1];
        personas[i + 1] = personas[alto];
        personas[alto] = temp;

        return i + 1;
    }
}
