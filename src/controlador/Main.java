package controlador;

import java.util.InputMismatchException;
import java.util.Scanner;
import modelo.Torneo;

/**
 * @author Ferrando Carlos
 */
public class Main {
    static Scanner sc;;
    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        sc = new Scanner(System.in);
        boolean salirMenuP = false;
        int opcionMenuP;
        String menu;

        while (!salirMenuP) {
           
            System.out.println("\n\n**************************************************************");
            System.out.println("****************  BIENVENIDO AL MENU ATP ULP  ****************");
            System.out.println("****   ¿Que desea hacer?. Seleccione la opción deseada:   ****");
            System.out.println("**************************************************************");
            System.out.println("******   1 - Cargar Jugadores                        *********");
            System.out.println("******   2 -                                         *********");
            System.out.println("******   3 -                                         *********");
            System.out.println("******   4 -                                         *********");
            System.out.println("******   5 - Lista Jugadores                                        *********");
            System.out.println("******   6 - Salir                                   *********");
            System.out.println("**************************************************************");

            try {
                System.out.println("Escribe una de las opciones: ");
              
                 opcionMenuP=sc.nextInt();

                switch (opcionMenuP) {
                    case 1:
                    limpiarConsola();
                        int opcionMenuS;
                        boolean salirMenuS = false;
                        while (!salirMenuS) {
                            System.out.println("***************************");
                            System.out.println("*****   MENU CARGAR   *****");
                            System.out.println("*** 1 - Cargar Manual   ***");
                            System.out.println("*** 2 - Cargar Defecto  ***");
                            System.out.println("***************************");

                            try {
                                System.out.println("Escribe una de las opciones: ");
                                opcionMenuS = sc.nextInt();
                                

                                switch (opcionMenuS) {
                                    case 1:
                                        modelo.Torneo.inleerribirJugadorManual();
                                        salirMenuS = true;
                                        break;
                                    case 2:
                                        modelo.Torneo.ileerribirJugadorDefecto();
                                        salirMenuS = true;
                                        break;
                                    default:
                                        System.out.println("Solo numeros entre 1 y 2");
                                }
                            } catch (InputMismatchException e) {
                                salirMenuS=false;
                                sc.nextLine();//para que vuelva a tomar el nextInt
                                System.out.println("Debes insertar un numero!!");
                            }
                        }

                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        limpiarConsola();
                        modelo.Torneo.printListadoJugadoresActual();
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

    public static int leerScannerEntero(String numero){
        int opcion;
        try {
             opcion = Integer.parseInt(numero); // Convierte el texto a número entero
           
        } catch (NumberFormatException e) {
            opcion=4;
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
