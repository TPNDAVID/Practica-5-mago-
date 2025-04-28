import java.util.*;

public class ConsoleUI {
    private static Scanner scanner = new Scanner(System.in);

    public static List<String> getJugadores(int cantidad) {
        List<String> jugadores = new ArrayList<>();
        for (int i = 1; i <= cantidad; i++) {
            System.out.print("Nombre del jugador " + i + ": ");
            jugadores.add(scanner.nextLine().trim());
        }
        return jugadores;
    }

    public static int modoSeleccionado() {
        System.out.print("Selecciona el modo de juego ([1] MODO REGULAR, [2] MODO EXPERTO): ");
        return scanner.nextInt();
    }

    public static void mostrarReglas(int mode) {
        if (mode == 1) {
            System.out.println("\n======== REGLAS DEL MODO REGULAR ========");
            System.out.println("* Sin acentos ni Ñ");
            System.out.println("* 5 pts por vocal, 3 pts por consonante");
            System.out.println("* -5 pts por palabra inválida");
        } else {
            System.out.println("\n======== REGLAS DEL MODO EXPERTO ========");
            System.out.println("* Acentos obligatorios donde corresponda");
            System.out.println("* Incluye letra Ñ (20% de probabilidad)");
            System.out.println("* -10 pts por palabra inválida");
        }
    }
    public static void mostrarLetras(Set<Character> letras) {
        System.out.println("\nLAS 10 LETRAS GENERADAS SON: " + letras);
    }

    public static boolean preguntarContinuar(String jugador) {
        System.out.print("Jugador [" + jugador + "], ¿vas a escribir una palabra? [s] ó [n]: ");
        return scanner.next().equalsIgnoreCase("s");
    }

    public static String ingresarPalabra(String jugador, int mode) {
        scanner.nextLine();
        System.out.print(jugador + ", escribe tu palabra: ");
        return scanner.nextLine().trim();
    }

    public static void mostrarPuntos(String palabra, int puntos) {
        System.out.println("PALABRA VÁLIDA '" + palabra + "' +" + puntos + " puntos");
    }

    public static void mostrarPalabraInvalida() {
        System.out.println("PALABRA INVÁLIDA. Penalización aplicada.");
    }

    public static void mostrarPuntuaciones(HashMap<String, Integer> puntuaciones) {
        System.out.println("\nPuntuaciones de los jugadores:");
        puntuaciones.forEach((jugador, puntos) ->
                System.out.println(jugador + ": " + puntos + " puntos"));
    }

    public static void mostrarPuntajeFinal(HashMap<String, Integer> scores) {
        System.out.println("\nPuntuaciones finales:");
        scores.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .forEach(e -> System.out.println(e.getKey() + ": " + e.getValue() + " puntos"));

        String ganador = Collections.max(scores.entrySet(), Map.Entry.comparingByValue()).getKey();
        System.out.println("\nEl ganador del juego es " + ganador);
    }
}
