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
        System.out.print("Selecciona el modo de juego (1: Regular, 2: Experto): ");
        return scanner.nextInt();
    }

    public static void mostrarReglas(int mode) {
        if (mode == 1) {
            System.out.println("\nREGLAS DEL MODO REGULAR:");
            System.out.println("* Sin acentos ni Ñ");
            System.out.println("* 5 pts por vocal, 3 pts por consonante");
            System.out.println("* -5 pts por palabra inválida");
        } else {
            System.out.println("\nREGLAS DEL MODO EXPERTO:");
            System.out.println("* Acentos obligatorios donde corresponda");
            System.out.println("* Incluye letra Ñ (20% de probabilidad)");
            System.out.println("* -10 pts por palabra inválida");
        }
    }

}
