package modelo;

import java.util.ArrayList;


/**
 * @author Ferrando Carlos
 */
public class ArbolTorneo {

    private ArrayList<Jugador> partidos;
    private int totalJugadores;

    public ArbolTorneo(int totalJugadores) {
        this.totalJugadores=totalJugadores;
        partidos = new ArrayList<>();
        Jugador jugador = new Jugador("nombre", "apellido", "nacionalidad", 0, 0);
        int niveles = (int) (Math.log(this.totalJugadores) / Math.log(2));
        int maxNodos = (int) Math.pow(2, niveles + 1) - 1;
        inicializarArbol(maxNodos,jugador);
    }
    
    private void inicializarArbol(int maxNodos,Jugador jugador){
        for (int i = 0; i < maxNodos; i++) {
            partidos.add(jugador);// jugadores vacios
        }
    }

    public void ingresarJugadores(int posicion,Jugador jugador1,Jugador jugador2){
        partidos.set(posicion, jugador1);
        partidos.set(posicion + 1, jugador2);
    }

    public void mostrarNiveles(int nivel){
        if(this.totalJugadores>0){//sin jugadores no hay nada, no tiene que ver con los partidos a nivel de recorridos
            for (int i = nivel; i < partidos.size(); i++) {
                //mostrar las parejas
                
            }
        }
      
    }



}
