package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Ferrando Carlos pedir cantidad de jugadores 4-8-16-32 etapas 2-3-4--5- array 7-16
 */
public class Torneo {

    private static LinkedHashSet<Jugador> listaJugadores = new LinkedHashSet<>();
    private static ArrayList<Jugador> listaJugadores2;
    private static HashMap<Integer, Integer> rondas = new HashMap<>();
    private static Integer ganadorPos;
    private static ArrayList<Integer> accederRonda;
    public static int totalJugadores = 0;
    private static ArbolTorneo arbol;

    /*
     * Método para cargar una lista de jugadores Manualmente
     */
    public static void inscribirJugadorManual() {
        vaciarListaJugadores();
        boolean insertado = false;

        System.out.println("\n\n ___Inscripcion de jugadores___\n");
        System.out.println("Nota: Cantidad de jugadores posibles 4-8-16-32-64.");
        // String ranker = "";
        int jugadores;
        Scanner leer = new Scanner(System.in);

        System.out.print("Ingrese la cantidad: ");
        // Para evitar perdida de tiempo si el dato ingresado es erroneo se carga el
        // minimo de jugadores
        jugadores = leerScannerEntero(leer.nextLine(), "Por defecto se cargo un torneo para 4 jugadores");
        if (jugadores != 4 && jugadores != 8 && jugadores != 16 && jugadores != 32 && jugadores != 64) {

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
     * Método para cargar jugadores por defecto "rankig no actual"
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

        // listaJugadores.add(new Jugador("Taylor", "Fritz", "EE.UU.", 9, 0));
        // listaJugadores.add(new Jugador("Frances", "Tiafoe", "EE.UU.", 10, 0));
        // listaJugadores.add(new Jugador("Alexander", "Zverev", "Alemania", 11, 0));
        // listaJugadores.add(new Jugador("Cameron", "Norrie", "Reino Unido", 12, 0));
        // listaJugadores.add(new Jugador("Hubert", "Hurkacz", "Polonia", 13, 0));
        // listaJugadores.add(new Jugador("Karen", "Khachanov", "Rusia", 14, 0));
        // listaJugadores.add(new Jugador("Felix", "Auger-Aliassime", "Canadá", 15, 0));
        // listaJugadores.add(new Jugador("Alex", "De Minaur", "Australia", 16, 0));
        totalJugadores = listaJugadores.size();
        // para poder ingresar a posiciones espesificas lo pasamos a
        // una arrayList
        listaJugadores2 = new ArrayList<>(listaJugadores);
        printListadoJugadoresActual();

    }

    /*
     * Emparejamiento e inicio de torneo
     * Se implementa Arbol
     */
    public static void configurarTorneo() {
        if (totalJugadores > 0) {

            arbol = new ArbolTorneo(totalJugadores);
            anexarRondas();
            emparejar();
            System.out.println("\n\n\n*************** [__Inicia el juego__] ***************\n");

            jugarRondas();

        }
    }

    private static void jugarRondas() {
        int ronda = 0;
        int cantJugadores = totalJugadores;
        int niveles = calcularNiveles();

        while (niveles > 0) {
            ronda++;
            definirSets(ronda, (cantJugadores / ronda));
            definirCampeon(ronda);
            niveles--;
        }

    }

    private static int calcularNiveles() {
        int rondas = 0;
        if (totalJugadores > 0) {
            rondas = (int) (Math.log(totalJugadores) / Math.log(2));
        }
        return rondas;
    }

    private static int[] indiceNivelesArbol() {
        int indiceInicio[] = new int[calcularNiveles() + 1];
        int nivel = 0;
        while (Math.pow(2, nivel) - 1 < totalJugadores) {
            indiceInicio[nivel] = (int) Math.pow(2, nivel) - 1;
            nivel++;
        }

        return indiceInicio;
    }

    public static void mostarNiveles() {
        int[] nivel = indiceNivelesArbol();
        for (int i : nivel) {
            System.out.println(i);
        }
    }

    public static void anexarRondas() {
        int niveles[] = indiceNivelesArbol();

        int j = 1;
        for (int i = niveles.length; i > 0; i--) {
            rondas.put((j), niveles[i - 1]);
            ganadorPos = j;
            j++;
        }
    }

    public static void mostrarRondas(int nivel) {
        if (nivel <= ganadorPos) {
            System.out.println("\n\tRonda Nr.: " + nivel + "");
            arbol.mostrarNiveles(navegar(nivel)); // nivel que le paso esta mal el usuario pone 1,2,3,4 pero tengo que
            // buscarlo rondas y que me de el nivel de larray de niveles
            System.out.println("\n\tGanadores pasan a la Ronda Nr: " + (nivel + 1) + "\n\t");
            arbol.mostrarNiveles(navegar(nivel + 1));
        } else {
            System.out.println("\n\n NOTA: ___ingrese una ronda valida___");
        }

    }
    

    private static int nivelSiguiente(int nivel) {
        int siguiente = -1;
        for (int niveles : rondas.keySet()) {
            if ((nivel + 1) == niveles) {
                siguiente = rondas.get(niveles);
            }
        }
        if (siguiente == -1) {
            System.out.println(" No exite nivel ni resultado para la ronda: " + nivel);
        }

        return siguiente;
    }

    public static void rondasDisponibles() {
        int i = 0;
        System.out.print("\t\t*** [");
        for (int ronda : rondas.keySet()) {
            if (ronda != rondas.size()) {

                if (i == rondas.size() - 2) {
                    System.out.print(ronda + " ] ***");

                } else {

                    System.out.print(ronda + ", ");
                }
            }
            i++;
        }

    }

    public static void emparejar() {

        if (totalJugadores > 0) {
            System.out.println("\n*******************************");
            System.out.println("\tEmparejar jugadores");
            System.out.println("*******************************\n");
            int indices[] = indiceNivelesArbol();
            int inicio = indices[indices.length - 1];

            for (int i = 0; i < (totalJugadores / 2); i++) {

                arbol.ingresarJugadores(inicio, listaJugadores2.get(i), listaJugadores2.get(i + (totalJugadores / 2)));
                inicio += 2;

            }

            arbol.mostrarNiveles(navegar(1));

        } else {
            System.out.println("\n\n __No se puede emparejar, sin jugadores__");
        }
    }

    private static int[] navegar(int nivelUsuario) {
        int[] nav = new int[2];
        Integer indice = 1;
        if (nivelUsuario > 0 && nivelUsuario <= ganadorPos) {
            indice = rondas.get(nivelUsuario);

            int niveles[] = indiceNivelesArbol();

            for (int i = 0; i < niveles.length; i++) {
                if (indice == niveles[i]) {
                    if (nivelUsuario != ganadorPos) {
                        if (indice != niveles[niveles.length - 1]) {
                            nav[0] = indice;
                            nav[1] = niveles[i + 1];// fin
                        } else {
                            nav[0] = indice;
                            nav[1] = -1;
                        }
                    } else {
                        nav[0] = indice;
                        nav[1] = -2;
                    }

                }
            }
        } else {
            nav[0] = indice;
            nav[1] = -3;
        }
        return nav;
    }

    /*
     * Definir Ganador Random
     */
    public static void definirSets(int ronda, int jugadores) {
        int indice = 0;
        int largoArray = (jugadores * 2) - 1;

        if (totalJugadores > 0) {

            indice = arbol.jugadoresVacios();

            System.out.println("\n\n*************** [Ronda: " + ronda + "]*******************");

            for (int i = indice; i < largoArray - 1; i += 2) {
                Jugador jugador1 = arbol.verJugador(i);
                Jugador jugador2 = arbol.verJugador(i + 1);

                Jugador ganador = definirGanadorPartido(jugador1, jugador2);

                arbol.ingresarGanador((i / 2), ganador);

            }
        } else {
            System.out.println("\n\n _No se puede definir sets, sin jugadores_");
        }

    }

    private static void definirCampeon(int ronda) {
       
        if (ronda == calcularNiveles()) {
            System.out.println("\n*******************************************");
            System.out.println("   El campeon del torneo ULP es: " + arbol.campeon());
            System.out.println("*******************************************\n");
        }
    }

    //Método para definir el ganador del partido
    private static Jugador definirGanadorPartido(Jugador jugador1, Jugador jugador2) {
        Jugador ganador;
        int cont = 1;
        while (jugador1.getSet() < 3 && jugador2.getSet() < 3) {
            definirGanadorSet(jugador1, jugador2, cont);
            cont++;
        }
        if (jugador1.getSet() == 3) {
            System.out.println("\n*******************************************");
            System.out.println("   El ganador del partido es: " + jugador1);
            System.out.println("*******************************************\n");
            ganador = jugador1;
        } else {
            System.out.println("*******************************************");
            System.out.println("   El ganador del partido es: " + jugador2);
            System.out.println("*******************************************\n");
            ganador = jugador2;
        }

        // reiniciar sets
        jugador1.resetSets();
        jugador2.resetSets();

        return ganador;
    }

    //Método para definir el ganador del set
    private static void definirGanadorSet(Jugador jugador1, Jugador jugador2, int cont) {
        Random random = new Random();

        int resultado1 = random.nextInt(100) + 1;
        int resultado2 = random.nextInt(100) + 1;
        
        System.out.println("=================================================");
        System.out.println("SET " + cont + ": " + jugador1 + " vs " + jugador2);
        System.out.println("-------------------------------------------------");
        System.out.println("Resultado:");
        System.out.println("   " + jugador1 + " " + resultado1 + " - " + resultado2 + " " + jugador2);
        System.out.println("=================================================");

        if (resultado1 > resultado2) {
            jugador1.incrementarSets();
        } else {
            jugador2.incrementarSets();
        }

    }

    //Se deberia cambiar el nombre a imprimirArbol o algo así.
    public static void imprimirArbol() {
        arbol.imprimirArbolDeLado();
    }
    
    public static Jugador devolverCampeon(){
        return arbol.campeon();
    }
    
    public static void encabezadoTorneo(){
        int niveles = calcularNiveles();
        String titulo= "";
        String[] rondas = {"Final","Semifinal","Cuartos","Octavos","Dieciseisavos","Treintaidosavos","Sesentaicuatroavos"};
        
            
        for (int i = 0; i <= niveles; i++) {
            titulo += "|"+rondas[i] + "    \t";
        }
        
        System.out.println(titulo);
    }
}
