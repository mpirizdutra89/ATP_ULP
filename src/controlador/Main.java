package controlador;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Ferrando Carlos
 */
public class Main {

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        Scanner sc = new Scanner(System.in);
        boolean salir = false;
        int opcion;

        while (!salir) {
            System.out.println("**************************************************************");
            System.out.println("****************  BIENVENIDO AL MENU ATP ULP  ****************");
            System.out.println("****   ¿Que desea hacer?. Seleccione la opción deseada:   ****");
            System.out.println("**************************************************************");
            System.out.println("******   1 - Cargar Jugadores                        *********");
            System.out.println("******   2 -                                         *********");
            System.out.println("******   3 -                                         *********");
            System.out.println("******   4 -                                         *********");
            System.out.println("******   5 -                                         *********");
            System.out.println("******   6 - Salir                                   *********");
            System.out.println("**************************************************************");
            
            try {
                System.out.println("Escribe una de las opciones: "); 
                opcion = sc.nextInt();
                
                switch (opcion) {
                    case 1:
                        
                        break;
                    case 2: 
                        break;
                    case 3: 
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        salir = true;
                        break;
                    default:
                        System.out.println("Solo numeros entre 1 y 6");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un numero!!");
            }
        }
    }
}
