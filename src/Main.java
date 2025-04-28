import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("EL MAGO DE LAS PALABRAS");

        System.out.print("Número de jugadores [2-4]: ");
        int numPlayers = scanner.nextInt();
        scanner.nextLine();

        if (numPlayers < 2 || numPlayers > 4) {
            System.out.println("Deben ser 2-4 jugadores.");
            return;
        }

        List<String> players = ConsoleUI.getJugadores(numPlayers);
        int mode = ConsoleUI.modoSeleccionado();
        ConsoleUI.mostrarReglas(mode);

        Mago game = new Mago(players, mode);
        game.iniciarJuego();
    }
}