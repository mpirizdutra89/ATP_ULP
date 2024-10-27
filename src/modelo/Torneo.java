package modelo;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Ferrando Carlos pedir cantidad de jugadores 4-8-16-32 etapas 2-3-4--5- array 7-16
 */
public class Torneo {

    private static LinkedHashSet<Jugador> listaJugadores = new LinkedHashSet<>();
    private static ArrayList<Jugador> listaJugadores2;
    private static ArrayList<Jugador> partidos = new ArrayList<>();
    private static int totalJugadores = 0;

    /*
        Método para cargar una lista de jugadores Manualmente
     */
    public static void inscribirJugadorManual() {
        vaciarListaJugadores();
        boolean insertado = false;

        System.out.println("\n\n ___Inscripcion de jugadores___\n");
        System.out.println("Nota: Cantidad de jugadores posibles 4-8-16-32-64.");
        String ranker = "";
        int jugadores;
        Scanner leer = new Scanner(System.in);

        System.out.print("Ingrese la cantidad: ");
        //Para evitar perdida de tiempo si el dato ingresado es erroneo se carga el minimo de jugadores
        jugadores = leerScannerEntero(leer.nextLine(), "Por defecto se cargo un torneo para 4 jugadores");
        if (jugadores != 4 && jugadores != 8 && jugadores != 16 && jugadores != 32 && jugadores != 64) {
            // leer.close();
            System.out.println("\n\n Nota:Cantidad de jugadores posibles 4-8-16-32-64. (se carga por default 4)\n\n");
            jugadores = 4;
        } else {
            System.out.println("\n\n __Cantidad de jugadores del torneo: " + jugadores + "\n");
        }

        // leer.nextLine();//limpiar buffer
        // Cargar jugadores Manualmente
        for (int i = 0; i < jugadores; i++) {
            System.out.println("\n__Datos jugador " + (i + 1) + ":");

            System.out.print("Apellido: ");
            String apellido = leer.nextLine();

            System.out.print("Nombre: ");
            String nombre = leer.nextLine();

            System.out.print("Nacionalidad: ");
            String nacionalidad = leer.nextLine();

            System.out.print("Ranking: ");
            int ranking = leerScannerEntero(leer.nextLine(), "\n__Debe ingresar un ranker valido y sin repetir.");

            int set = 0;

            // leer.nextLine();//limpiar buffer
            insertado = listaJugadores.add(new Jugador(nombre, apellido, nacionalidad, ranking));
            while (!insertado) {
                printListadoJugadoresActual();
                System.out.print("Ingrese ranking nuevamente para :(" + apellido + " " + nombre + "): ");
                ranking = leerScannerEntero(leer.nextLine(),
                        "Debe ingresar un numero valido (que no exita en la lista de rankers),por defecto se le asigna el nr. 4");
                insertado = listaJugadores.add(new Jugador(nombre, apellido, nacionalidad, ranking, set));
            }
            totalJugadores = listaJugadores.size();

        }
        System.out.println("\n\n Jugadores Ingresados Correctamente.");
        listaJugadores2 = new ArrayList<>(listaJugadores);

    }

    private static void vaciarListaJugadores() {

        listaJugadores.clear();
    }

    public static void printListadoJugadoresActual() {

        if (listaJugadores.size() > 0) {
            System.out.println("\n\n___Lista de jugadores del torneo___\n");
            System.out.println("[Apellido, Ranking]\n");
            for (Jugador jugador : listaJugadores) {
                System.out.println("[" + jugador + "]");
            }
        } else {
            System.out.println("  [ LISTA  JUGADORES VACIA ]");
        }

        System.out.println("\n");
    }

    private static int leerScannerEntero(String numero, String msjError) {
        int opcion;
        try {
            opcion = Integer.parseInt(numero); // Convierte el texto a número entero

        } catch (NumberFormatException e) {
            opcion = 4;
            System.out.println(msjError);
        }
        return opcion;
    }

    /*
        Método para cargar jugadores por defecto "rankig no actual"
     */
    public static void inscribirJugadorDefecto() {
        vaciarListaJugadores();
        listaJugadores.add(new Jugador("Novak", "Djokovic", "Serbia", 1, 0));
        listaJugadores.add(new Jugador("Carlos", "Alcaraz", "España", 2, 0));
        listaJugadores.add(new Jugador("Daniil", "Medvedev", "Rusia", 3, 0));
        listaJugadores.add(new Jugador("Stefanos", "Tsitsipas", "Grecia", 4, 0));
        listaJugadores.add(new Jugador("Andrey", "Rublev", "Rusia", 5, 0));
        listaJugadores.add(new Jugador("Holger", "Rune", "Dinamarca", 6, 0));
        listaJugadores.add(new Jugador("Casper", "Ruud", "Noruega", 7, 0));
        listaJugadores.add(new Jugador("Jannik", "Sinner", "Italia", 8, 0));
        /*
         * listaJugadores.add(new Jugador("Taylor", "Fritz", "EE.UU.", 9,0));
         * listaJugadores.add(new Jugador("Frances", "Tiafoe", "EE.UU.", 10,0));
         * listaJugadores.add(new Jugador("Alexander", "Zverev", "Alemania", 11,0));
         * listaJugadores.add(new Jugador("Cameron", "Norrie", "Reino Unido", 12,0));
         * listaJugadores.add(new Jugador("Hubert", "Hurkacz", "Polonia", 13,0));
         * listaJugadores.add(new Jugador("Karen", "Khachanov", "Rusia", 14,0));
         * listaJugadores.add(new Jugador("Felix", "Auger-Aliassime", "Canadá", 15,0));
         * listaJugadores.add(new Jugador("Alex", "De Minaur", "Australia", 16,0));
         */
        totalJugadores = listaJugadores.size();
        // para poder ingresar a posiciones espesificas lo pasamos a
        // una arrayList
        listaJugadores2 = new ArrayList<>(listaJugadores);
        printListadoJugadoresActual();

    }

    /* Emparejamiento */
    public static void configurarTorneo() {
        if (totalJugadores > 0) {
            Jugador jugador = new Jugador("nombre", "apellido", "nacionalidad", 0, 0);
            int niveles = calcularNivele();
            int maxNodos = (int) Math.pow(2, niveles + 1) - 1;

            // System.out.println("Niveles:" + niveles + " max nodos posibles:" + maxNodos);
            for (int i = 0; i < maxNodos; i++) {
                partidos.add(jugador);// jugadores vacios
            }

            emparejar();

        }
    }

    private static int calcularNivele() {
        int rondas = 0;
        if (totalJugadores > 0) {
            rondas = (int) (Math.log(totalJugadores) / Math.log(2));

        }

        return rondas;
    }

    private static int[] indiceNivelesArbol() {
        int indiceInicio[] = new int[calcularNivele() + 1];
        int nivel = 0;
        while (Math.pow(2, nivel) - 1 < totalJugadores) {
            indiceInicio[nivel] = (int) Math.pow(2, nivel) - 1;
            nivel++;
        }

        return indiceInicio;
    }

    public static void emparejar() {

        if (totalJugadores > 0) {
            System.out.println("\n  __Emparejar jugadores__\n");
            int indices[] = indiceNivelesArbol();
            int inicio = indices[indices.length - 1];

            for (int i = 0; i < (totalJugadores / 2); i++) {

                partidos.set(inicio, listaJugadores2.get(i));

                partidos.set(inicio + 1, listaJugadores2.get(i + (totalJugadores / 2)));
                System.out.println(
                        "     __Partido nr." + (i + 1) + ":  (" + partidos.get(inicio) + ") & ("
                        + partidos.get(inicio + 1)
                        + ")");
                inicio += 2;
            }

        } else {
            System.out.println("\n\n __No se puede emparejar, sin jugadores__");
        }
    }
    /*
        Definir Ganador Random
    */
    public static void definirSets() {
        if (totalJugadores > 0) {
            for (int i = 0; i < partidos.size(); i += 2) {
                Jugador jugador1 = partidos.get(i);
                Jugador jugador2 = partidos.get(i + 1);

                if (jugador1 != null && jugador2 != null) {
                    definirGanadorPartido(jugador1, jugador2);
                }
            }
        } else {
            System.out.println("\n\n _No se puede definir sets, sin jugadores_");
        }     
    }

    private static Jugador definirGanadorPartido(Jugador jugador1, Jugador jugador2) {
        while (jugador1.getSet() < 3 && jugador2.getSet() < 3) {
            definirGanadorSet(jugador1, jugador2);
        }
        if (jugador1.getSet() == 3) {
            return jugador1;
        } else {
            return jugador2;
        }
    }

    private static void definirGanadorSet(Jugador jugador1, Jugador jugador2) {
        Random random = new Random();

        int resultado1 = random.nextInt(100) + 1;
        int resultado2 = random.nextInt(100) + 1;

        System.out.println("Partido: " + jugador1 + " vs " + jugador2);
        System.out.println("Resultado: " + resultado1 + " - " + resultado2);

        if (resultado1 > resultado2) {
            jugador1.incrementarSet();
            System.out.println("Ganador del set: " + jugador1);
        } else if (resultado1 < resultado2) {
            jugador2.incrementarSet();
            System.out.println("Ganador del set: " + jugador2);
        } else {
            System.out.println("Empate");
        }
    }

}
