import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase principal que implementa el menú de la aplicación.
 * Coordina la carga de archivos, ordenamiento y búsqueda de personas.
 */
public class Main {
    private static ArrayList<Persona> personas = null;
    private static Persona[] personasOrdenadas = null;
    private static boolean fueOrdenado = false;
    private static String ultimoAlgoritmo = "";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║        SISTEMA DE ALGORITMOS DE ORDENAMIENTO EN JAVA       ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");

        while (!salir) {
            mostrarMenu();
            System.out.print("Seleccione una opción: ");

            try {
                int opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        cargarArchivo();
                        break;
                    case 2:
                        ordenarBurbuja();
                        break;
                    case 3:
                        ordenarInsercion();
                        break;
                    case 4:
                        ordenarSeleccion();
                        break;
                    case 5:
                        ordenarQuicksort();
                        break;
                    case 6:
                        ordenarMergesort();
                        break;
                    case 7:
                        ordenarShellsort();
                        break;
                    case 8:
                        buscarPersona(scanner);
                        break;
                    case 9:
                        imprimirVector();
                        break;
                    case 10:
                        salir = true;
                        System.out.println("\n✓ ¡Hasta luego!");
                        break;
                    default:
                        System.out.println("✗ Opción no válida. Intente de nuevo.\n");
                }
            } catch (Exception e) {
                System.out.println("✗ Error: Entrada inválida. Intente de nuevo.\n");
                scanner.nextLine();
            }
        }

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n┌────────────────── MENÚ PRINCIPAL ──────────────────┐");
        System.out.println("│ 1. Cargar archivo                                  │");
        System.out.println("│ 2. Ordenar mediante Burbuja Mejorado               │");
        System.out.println("│ 3. Ordenar mediante Inserción Directa              │");
        System.out.println("│ 4. Ordenar mediante Selección                      │");
        System.out.println("│ 5. Ordenar mediante Quicksort                      │");
        System.out.println("│ 6. Ordenar mediante Mergesort                      │");
        System.out.println("│ 7. Ordenar mediante Shellsort                      │");
        System.out.println("│ 8. Buscar en el vector ordenado                    │");
        System.out.println("│ 9. Imprimir vector ordenado                        │");
        System.out.println("│ 10. Salir                                          │");
        System.out.println("└────────────────────────────────────────────────────┘");
    }

    private static void cargarArchivo() {
        System.out.println("\n--- Cargando archivo ---");
        ArrayList<Persona> personasCargadas = CargadorArchivos.cargarArchivo();

        if (personasCargadas != null && !personasCargadas.isEmpty()) {
            personas = personasCargadas;
            personasOrdenadas = null;
            fueOrdenado = false;
            ultimoAlgoritmo = "";
            System.out.println("✓ Archivo cargado. Registros: " + personas.size() + "\n");
        } else if (personasCargadas != null) {
            System.out.println("✗ El archivo está vacío.\n");
        }
    }

    private static Persona[] crearCopia() {
        Persona[] copia = new Persona[personas.size()];
        for (int i = 0; i < personas.size(); i++) {
            copia[i] = personas.get(i);
        }
        return copia;
    }

    private static boolean validarArchivoCargado() {
        if (personas == null || personas.isEmpty()) {
            System.out.println("✗ Error: Primero debe cargar un archivo.\n");
            return false;
        }
        return true;
    }

    private static long medirTiempo(Persona[] personas, AlgoritmoOrdenamiento algoritmo) {
        long inicio = System.nanoTime();
        algoritmo.ordenar(personas);
        long fin = System.nanoTime();
        return fin - inicio;
    }

    private static void ordenarBurbuja() {
        if (!validarArchivoCargado()) return;

        System.out.println("\n--- Ordenando con Burbuja Mejorado ---");
        System.out.println("Ejecutando 3 mediciones...\n");

        long[] tiempos = new long[3];

        for (int i = 0; i < 3; i++) {
            Persona[] copia = crearCopia();
            tiempos[i] = medirTiempo(copia, BurbujaImproveado::ordenar);
            System.out.printf("Medición %d: %,d nanosegundos (%.6f segundos)\n", 
                            i + 1, tiempos[i], tiempos[i] / 1_000_000_000.0);
        }

        personasOrdenadas = crearCopia();
        BurbujaImproveado.ordenar(personasOrdenadas);
        fueOrdenado = true;
        ultimoAlgoritmo = "Burbuja Mejorado";

        mostrarEstadisticas(tiempos);
    }

    private static void ordenarInsercion() {
        if (!validarArchivoCargado()) return;

        System.out.println("\n--- Ordenando con Inserción Directa ---");
        System.out.println("Ejecutando 3 mediciones...\n");

        long[] tiempos = new long[3];

        for (int i = 0; i < 3; i++) {
            Persona[] copia = crearCopia();
            tiempos[i] = medirTiempo(copia, InsercionDirecta::ordenar);
            System.out.printf("Medición %d: %,d nanosegundos (%.6f segundos)\n", 
                            i + 1, tiempos[i], tiempos[i] / 1_000_000_000.0);
        }

        personasOrdenadas = crearCopia();
        InsercionDirecta.ordenar(personasOrdenadas);
        fueOrdenado = true;
        ultimoAlgoritmo = "Inserción Directa";

        mostrarEstadisticas(tiempos);
    }

    private static void ordenarSeleccion() {
        if (!validarArchivoCargado()) return;

        System.out.println("\n--- Ordenando con Selección ---");
        System.out.println("Ejecutando 3 mediciones...\n");

        long[] tiempos = new long[3];

        for (int i = 0; i < 3; i++) {
            Persona[] copia = crearCopia();
            tiempos[i] = medirTiempo(copia, Seleccion::ordenar);
            System.out.printf("Medición %d: %,d nanosegundos (%.6f segundos)\n", 
                            i + 1, tiempos[i], tiempos[i] / 1_000_000_000.0);
        }

        personasOrdenadas = crearCopia();
        Seleccion.ordenar(personasOrdenadas);
        fueOrdenado = true;
        ultimoAlgoritmo = "Selección";

        mostrarEstadisticas(tiempos);
    }

    private static void ordenarQuicksort() {
        if (!validarArchivoCargado()) return;

        System.out.println("\n--- Ordenando con Quicksort ---");
        System.out.println("Ejecutando 3 mediciones...\n");

        long[] tiempos = new long[3];

        for (int i = 0; i < 3; i++) {
            Persona[] copia = crearCopia();
            tiempos[i] = medirTiempo(copia, Quicksort::ordenar);
            System.out.printf("Medición %d: %,d nanosegundos (%.6f segundos)\n", 
                            i + 1, tiempos[i], tiempos[i] / 1_000_000_000.0);
        }

        personasOrdenadas = crearCopia();
        Quicksort.ordenar(personasOrdenadas);
        fueOrdenado = true;
        ultimoAlgoritmo = "Quicksort";

        mostrarEstadisticas(tiempos);
    }

    private static void ordenarMergesort() {
        if (!validarArchivoCargado()) return;

        System.out.println("\n--- Ordenando con Mergesort ---");
        System.out.println("Ejecutando 3 mediciones...\n");

        long[] tiempos = new long[3];

        for (int i = 0; i < 3; i++) {
            Persona[] copia = crearCopia();
            tiempos[i] = medirTiempo(copia, Mergesort::ordenar);
            System.out.printf("Medición %d: %,d nanosegundos (%.6f segundos)\n", 
                            i + 1, tiempos[i], tiempos[i] / 1_000_000_000.0);
        }

        personasOrdenadas = crearCopia();
        Mergesort.ordenar(personasOrdenadas);
        fueOrdenado = true;
        ultimoAlgoritmo = "Mergesort";

        mostrarEstadisticas(tiempos);
    }

    private static void ordenarShellsort() {
        if (!validarArchivoCargado()) return;

        System.out.println("\n--- Ordenando con Shellsort ---");
        System.out.println("Ejecutando 3 mediciones...\n");

        long[] tiempos = new long[3];

        for (int i = 0; i < 3; i++) {
            Persona[] copia = crearCopia();
            tiempos[i] = medirTiempo(copia, Shellsort::ordenar);
            System.out.printf("Medición %d: %,d nanosegundos (%.6f segundos)\n", 
                            i + 1, tiempos[i], tiempos[i] / 1_000_000_000.0);
        }

        personasOrdenadas = crearCopia();
        Shellsort.ordenar(personasOrdenadas);
        fueOrdenado = true;
        ultimoAlgoritmo = "Shellsort";

        mostrarEstadisticas(tiempos);
    }

    private static void mostrarEstadisticas(long[] tiempos) {
        long suma = tiempos[0] + tiempos[1] + tiempos[2];
        double promedio = suma / 3.0;

        System.out.println("\n┌──────────────────────────────────────────┐");
        System.out.printf("│ Promedio: %,d ns (%.6f s)         │\n", (long) promedio, promedio / 1_000_000_000.0);
        System.out.printf("│ Mínimo:   %,d ns                    │\n", Math.min(Math.min(tiempos[0], tiempos[1]), tiempos[2]));
        System.out.printf("│ Máximo:   %,d ns                    │\n", Math.max(Math.max(tiempos[0], tiempos[1]), tiempos[2]));
        System.out.println("└──────────────────────────────────────────┘\n");
    }

    private static void buscarPersona(Scanner scanner) {
        if (!fueOrdenado) {
            System.out.println("✗ Error: Primero debe cargar un archivo y ordenarlo.\n");
            return;
        }

        System.out.println("\n--- Búsqueda Binaria ---");
        System.out.print("Ingrese la cédula a buscar: ");

        try {
            int cedula = scanner.nextInt();
            scanner.nextLine();

            long inicio = System.nanoTime();
            Persona encontrada = BusquedaBinaria.buscar(personasOrdenadas, cedula);
            long fin = System.nanoTime();
            long tiempoB = fin - inicio;

            if (encontrada != null) {
                System.out.println("\n✓ Persona encontrada:");
                System.out.println("  " + encontrada);
                System.out.printf("  Tiempo de búsqueda: %,d nanosegundos (%.6f ms)\n", 
                                tiempoB, tiempoB / 1_000_000.0);
            } else {
                System.out.println("\n✗ Persona con cédula " + cedula + " no encontrada.");
                System.out.printf("  Tiempo de búsqueda: %,d nanosegundos (%.6f ms)\n", 
                                tiempoB, tiempoB / 1_000_000.0);
            }

            System.out.println("  (Algoritmo utilizado: " + ultimoAlgoritmo + ")\n");

        } catch (Exception e) {
            System.out.println("✗ Error: Entrada inválida.\n");
            scanner.nextLine();
        }
    }

    private static void imprimirVector() {
        if (!fueOrdenado) {
            System.out.println("✗ Error: Primero debe cargar un archivo y ordenarlo.\n");
            return;
        }

        System.out.println("\n--- Vector Ordenado (Algoritmo: " + ultimoAlgoritmo + ") ---");
        System.out.println("Registros: " + personasOrdenadas.length + "\n");

        System.out.println("┌─────────────┬────────────────────────────────┬───────┐");
        System.out.println("│   CÉDULA    │         NOMBRE COMPLETO         │ EDAD  │");
        System.out.println("├─────────────┼────────────────────────────────┼───────┤");

        for (int i = 0; i < personasOrdenadas.length; i++) {
            Persona p = personasOrdenadas[i];
            String nombre = p.getNombre();
            if (nombre.length() > 30) {
                nombre = nombre.substring(0, 30);
            }
            System.out.printf("│ %,11d │ %-30s │ %5d │\n", 
                            p.getCedula(), 
                            nombre,
                            p.getEdad());
        }

        System.out.println("└─────────────┴────────────────────────────────┴───────┘\n");
    }

    @FunctionalInterface
    interface AlgoritmoOrdenamiento {
        void ordenar(Persona[] personas);
    }
}
