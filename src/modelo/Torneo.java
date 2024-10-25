package modelo;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Ferrando Carlos pedir cantidad de jugadores 4-8-16-32 etapas 2-3-4--5- array 7-16
 */
public class Torneo {

    public static ArrayList<Jugador> listaJugadores = new ArrayList<>();

    public static void inscribirJugadorManual() {
        Jugador jugadorNuevo = null;
        int jugadores;
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingresar la cantidad de jugadores a inscribir: ");
        System.out.println("Puede inscribir 4-8-16-32-64 jugadores");

        jugadores = sc.nextInt();

        if (jugadores != 4 && jugadores != 8 && jugadores != 16 && jugadores != 32 && jugadores != 64) {
            System.out.println("Solo puede inscribir 4-8-16-32-64 jugadores");
            return;
        }
        
        sc.nextLine();//limpiar buffer
        //Cargar jugadores Manualmente
        for (int i = 0; i < jugadores; i++) {
            System.out.println("Datos jugador "+ i+1 +":");
            
            System.out.println("Apellido: ");
            String apellido = sc.nextLine();
            
            System.out.println("Nombre: ");
            String nombre = sc.nextLine();
  
            System.out.println("Nacionalidad: ");
            String nacionalidad = sc.nextLine();
            
            System.out.println("Ranking: ");
            int ranking = sc.nextInt();
            sc.nextLine();//limpiar buffer
            
            listaJugadores.add(new Jugador(nombre, apellido, nacionalidad, ranking));
        }
      
    }

}
