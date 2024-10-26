package modelo;

import java.util.LinkedHashSet;
import java.util.Scanner;

/**
 * @author Ferrando Carlos pedir cantidad de jugadores 4-8-16-32 etapas
 *         2-3-4--5- array 7-16
 */
public class Torneo {

    private static LinkedHashSet<Jugador> listaJugadores = new LinkedHashSet<>();

    public static void inleerribirJugadorManual() {
        vaciarListaJugadores();
        boolean insertado = false;

        System.out.println("\n\n ___Inscripcion de jugadores___\n");
        System.out.println("Nota: Cantidad de jugadores posibles 4-8-16-32-64.");
        String ranker = "";
        int jugadores;
        Scanner leer = new Scanner(System.in);

        System.out.print("\n\n___Ingrese la cantidad: ");
        jugadores = leerScannerEntero(leer.nextLine(), "\n___Por defecto se cargo un torneo para 4 jugadores___");// para
                                                                                                                  // evitar
                                                                                                                  // perdida
                                                                                                                  // ded
                                                                                                                  // tiempo

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
            int ranking = leerScannerEntero(leer.nextLine(), "\n__Deve ingresar un ranker valido y sin repetir.");

            // leer.nextLine();//limpiar buffer

            insertado = listaJugadores.add(new Jugador(nombre, apellido, nacionalidad, ranking));
            while (!insertado) {
                printListadoJugadoresActual();
                System.out.print("Ingrese ranking nuevamente para :(" + apellido + " " + nombre + "): ");
                ranking = leerScannerEntero(leer.nextLine(),
                        "Deve ingresar un numero valido (que no exita en la lista de rankers),por defecto se le asigna el nr. 4");
                insertado = listaJugadores.add(new Jugador(nombre, apellido, nacionalidad, ranking));
            }

        }
        System.out.println("\n\n Jugadores Ingresados Correctamente.");
        printListadoJugadoresActual();

    }

    private static void vaciarListaJugadores() {

        listaJugadores.clear();
    }

    public static void printListadoJugadoresActual() {
        
        if(listaJugadores.size()>0){
            System.out.println("\n\n___Lista de jugadores del torneo___\n");
            for (Jugador jugador : listaJugadores) {
                System.out.println("[" + jugador + "]");
            }
        }else{
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

    public static void ileerribirJugadorDefecto() {
        vaciarListaJugadores();
        listaJugadores.add(new Jugador("Novak", "Djokovic", "Serbia", 1));
        listaJugadores.add(new Jugador("Carlos", "Alcaraz", "España", 2));
        listaJugadores.add(new Jugador("Daniil", "Medvedev", "Rusia", 3));
        listaJugadores.add(new Jugador("Stefanos", "Tsitsipas", "Grecia", 4));
        listaJugadores.add(new Jugador("Andrey", "Rublev", "Rusia", 5));
        listaJugadores.add(new Jugador("Holger", "Rune", "Dinamarca", 6));
        listaJugadores.add(new Jugador("Casper", "Ruud", "Noruega", 7));
        listaJugadores.add(new Jugador("Jannik", "Sinner", "Italia", 8));
        listaJugadores.add(new Jugador("Taylor", "Fritz", "EE.UU.", 9));
        listaJugadores.add(new Jugador("Frances", "Tiafoe", "EE.UU.", 10));
        listaJugadores.add(new Jugador("Alexander", "Zverev", "Alemania", 11));
        listaJugadores.add(new Jugador("Cameron", "Norrie", "Reino Unido", 12));
        listaJugadores.add(new Jugador("Hubert", "Hurkacz", "Polonia", 13));
        listaJugadores.add(new Jugador("Karen", "Khachanov", "Rusia", 14));
        listaJugadores.add(new Jugador("Felix", "Auger-Aliassime", "Canadá", 15));
        listaJugadores.add(new Jugador("Alex", "De Minaur", "Australia", 16));

    }

}
