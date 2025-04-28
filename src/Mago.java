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
            diccionario.loadFromFile("src\\Diccionario.txt");
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

    private void jugarRonda() {
        letrasActuales = generarLetras(10);
        palabrasUsadas.clear();
        boolean[] jugadoresPasaron = new boolean[jugadores.size()];

        ConsoleUI.mostrarLetras(letrasActuales);

        while (true) {
            boolean todosPasaron = true;

            Iterator<String> iteradorJugadores = jugadores.iterator();
            int indice = 0;

            while (iteradorJugadores.hasNext()) {
                String jugador = iteradorJugadores.next();

                if (!jugadoresPasaron[indice]) {
                    if (ConsoleUI.preguntarContinuar(jugador)) {
                        todosPasaron = false;
                        procesarPalabra(jugador);
                    } else {
                        jugadoresPasaron[indice] = true;
                        System.out.println(jugador + " ha pasado turno");
                    }
                }
                indice++;
            }

            if (todosPasaron) {
                System.out.println("\nTerminó la ronda porque todos los jugadores han pasado");
                break;
            }

            boolean algunoActivo = false;
            for (boolean paso : jugadoresPasaron) {
                if (!paso) {
                    algunoActivo = true;
                    break;
                }
            }

            if (!algunoActivo) {
                System.out.println("\nTerminó la ronda porque todos los jugadores han pasado");
                break;
            }
        }
    }


    private void procesarPalabra(String jugador) {
        String palabra = ConsoleUI.ingresarPalabra(jugador, modoDeJuego).toUpperCase();

        if (palabrasUsadas.contains(palabra)) {
            System.out.println("Esta palabra ya se usó en esta ronda");
            return;
        }
        palabrasUsadas.add(palabra);

        if (validarPalabra(palabra)) {
            int puntos = diccionario.obtenerPuntos(palabra);
            puntajeJugador.merge(jugador, puntos, Integer::sum);
            ConsoleUI.mostrarPuntos(palabra, puntos);
        } else {
            int penalizacion = (modoDeJuego == 1) ? -5 : -10;
            puntajeJugador.merge(jugador, penalizacion, Integer::sum);
            ConsoleUI.mostrarPalabraInvalida();
        }
        ConsoleUI.mostrarPuntuaciones(puntajeJugador);
    }

    private boolean validarPalabra(String palabra) {
        return diccionario.contienePalabra(palabra, modoDeJuego) &&
                letrasValidas(palabra);
    }

    private boolean letrasValidas(String palabra) {
        for (char letra : palabra.toCharArray()) {
            if (!letrasActuales.contains(Character.toUpperCase(letra))) {
                return false;
            }
        }
        return true;
    }

    private Set<Character> generarLetras(int cantidad) {
        Random random = new Random();
        Set<Character> letras = new HashSet<>();
        String vocalesBasicas = "AEIOU";
        String vocalesAcentuadas = "ÁÉÍÓ";
        String consonantes = "BCDFGHJKLMNPQRSTVWXYZ";
        String consonantesExpertas = "BCDFGHJKLMNPQRSTVWXYZÑ";

        for (int i = 0; i < 4; i++) {
            letras.add(vocalesBasicas.charAt(random.nextInt(vocalesBasicas.length())));
        }

        if (modoDeJuego == 2) {
            letras.add(vocalesAcentuadas.charAt(random.nextInt(vocalesAcentuadas.length())));
            if (random.nextDouble() <= 0.2) {
                letras.add('Ñ');
            }
        }

        String fuenteConsonantes = (modoDeJuego == 2) ? consonantesExpertas : consonantes;
        while (letras.size() < cantidad) {
            char c = fuenteConsonantes.charAt(random.nextInt(fuenteConsonantes.length()));
            letras.add(c);
        }
        return letras;
    }

}