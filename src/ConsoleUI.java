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
}
