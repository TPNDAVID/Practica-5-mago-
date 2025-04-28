import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("EL MAGO DE LAS PALABRAS");

        int numJugadores;
        while (true) {
            System.out.print("Número de jugadores [2-4]: ");
            try {
                numJugadores = scanner.nextInt();
                scanner.nextLine();

                if (numJugadores >= 2 && numJugadores <= 4) {
                    break;
                } else {
                    System.out.println("Deben ser entre 2 y 4 jugadores");
                }
            } catch (InputMismatchException e) {
                System.out.println("Ingresa un número válido");
                scanner.nextLine();
            }
        }

        List<String> players = ConsoleUI.getJugadores(numJugadores);
        int mode = ConsoleUI.modoSeleccionado();
        ConsoleUI.mostrarReglas(mode);

        Mago game = new Mago(players, mode);
        game.iniciarJuego();
    }
}