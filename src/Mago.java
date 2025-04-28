import java.io.*;
import java.util.*;

public class Mago {
    private Diccionario diccionario;
    private HashMap<String, Integer> puntajeJugador;
    private int modoDeJuego;
    private List<String> jugadores;
    private Set<Character> letrasActuales;
    private Set<String> palabrasUsadas = new HashSet<>();

    public Mago(List<String> jugadores, int modoDeJuego) {
        this.jugadores = new ArrayList<>(jugadores);
        this.modoDeJuego = modoDeJuego;
        this.puntajeJugador = new HashMap<>();
        this.diccionario = new Diccionario(modoDeJuego);

        try {
            diccionario.loadFromFile("src\\diccionariopalabras.txt");
        } catch (IOException e) {
            System.err.println("Error al cargar el diccionario: " + e.getMessage());
            System.exit(1);
        }

        jugadores.forEach(p -> puntajeJugador.put(p, 0));
    }

    public void iniciarJuego() {
        for (int round = 1; round <= 3; round++) {
            System.out.println("\n============ RONDA " + round + " ============");
            jugarRonda();
        }
        ConsoleUI.mostrarPuntajeFinal(puntajeJugador);
    }