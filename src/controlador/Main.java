package controlador;

import java.util.InputMismatchException;
import java.util.Scanner;

import modelo.Torneo;
import modelo.ArbolTorneo;

/**
 * @author Ferrando Carlos
 */
public class Main {

    static Scanner sc;

    ;

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        sc = new Scanner(System.in);
        boolean salirMenuP = false;
        int opcionMenuP;

        while (!salirMenuP) {

            System.out.println("\n\n**************************************************************");
            System.out.println("****************  BIENVENIDO AL MENU ATP ULP  ****************");
            System.out.println("****   ¿Que desea hacer?. Seleccione la opcion deseada:   ****");
            System.out.println("**************************************************************");
            System.out.println("******   1 - Cargar Jugadores                        *********");
            System.out.println("******   2 - Iniciar Torneo                          *********");
            System.out.println("******   3 - Lista Jugadores                         *********");
            System.out.println("******   4 - Mostrar rondas                          *********");
            System.out.println("******   5 - Mostrar Arbol del Torneo                *********");
            System.out.println("******   6 - Salir                                   *********");
            System.out.println("**************************************************************\n\n");

            try {
                System.out.println("Escribe una de las opciones: ");

                opcionMenuP = leerScannerEntero(sc.nextLine(), 3); // cambiado por error al escribir letras

                switch (opcionMenuP) {
                    case 1:
                        limpiarConsola();
                        int opcionMenuS;
                        boolean salirMenuS = false;
                        while (!salirMenuS) {
                            System.out.println("\n\n***************************");
                            System.out.println("*****   MENU CARGAR   *****");
                            System.out.println("*** 1 - Cargar Manual   ***");
                            System.out.println("*** 2 - Cargar Defecto  ***");
                            System.out.println("*** 3 - Volver al Menu  ***");
                            System.out.println("***************************\n\n");

                            try {
                                if (Torneo.totalJugadores > 0) {
                                    System.out.println("==============================================================================");
                                    System.out.println("\tLista del torneo tiene Jugadores. Cantidad actual: "+Torneo.totalJugadores);
                                    System.out.println("==============================================================================\n");
                                }
                                System.out.println("Escribe una de las opciones: ");
                                opcionMenuS = sc.nextInt();

                                switch (opcionMenuS) {
                                    case 1:
                                        modelo.Torneo.inscribirJugadorManual();
                                        salirMenuS = true;
                                        break;
                                    case 2:
                                        modelo.Torneo.inscribirJugadorDefecto();
                                        salirMenuS = true;
                                        break;
                                    case 3:
                                        salirMenuS = true;
                                        break;
                                    default:
                                        System.out.println("Solo numeros entre 1 y 2");
                                }
                            } catch (InputMismatchException e) {
                                salirMenuS = false;
                                sc.nextLine();// para que vuelva a tomar el nextInt
                                System.out.println("Debes insertar un numero!!");
                            }
                        }

                        break;
                    case 2: // iniciar torneo, carga partidos obejtos cavio y los empareja
                        // limpiarConsola();
                        System.out.println("\n\n __Torneo iniciado__ ");
                        //Torneo.anexarRondas();
                    // Torneo.rondasDisponibles();
                           Torneo.configurarTorneo();
                        break;
                    case 3:
                        limpiarConsola();
                        modelo.Torneo.printListadoJugadoresActual();
                        break;
                    case 4:
                  //  int niveles[]=Torneo.indiceNivelesArbol();

                         System.out.println("*************** [Mostrar rondas del juego ] *******************");
                         System.out.println("\tRondas disponibles (R1-inicial):");
                         Torneo.rondasDisponibles();
                         System.out.print("\n\tSeleccione una: ");
                         int opcion=leerScannerEntero(sc.nextLine());
                         Torneo.mostrarRondas(opcion);
                        

                        
                    break;
                    case 5:
                   // modelo.Torneo.prueba();
                     modelo.Torneo.imprimirTorneo();
                        break;
                    case 6:
                        salirMenuP = true;
                        break;
                    default:
                        System.out.println("Solo numeros entre 1 y 6");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un numero!!");
            }

            // sc.close();
        }

    }

    public static int leerScannerEntero(String numero) {
        int opcion;
        try {
            opcion = Integer.parseInt(numero); // Convierte el texto a número entero

        } catch (NumberFormatException e) {
            opcion = 4;
        }
        return opcion;
    }

    public static int leerScannerEntero(String numero, int op) {
        int opcion;
        try {
            opcion = Integer.parseInt(numero); // Convierte el texto a número entero

        } catch (NumberFormatException e) {
            opcion = op;
        }
        return opcion;
    }

    public static void limpiarConsola() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
