package modelo;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @author Ferrando Carlos
 */
public class ArbolTorneo {

    private ArrayList<Jugador> partidos;
    private ArrayList<Jugador> imprimirPartidos;
    private int totalJugadores;

    public ArbolTorneo(int totalJugadores) {
        this.totalJugadores = totalJugadores;
        partidos = new ArrayList<>();
        Jugador jugador = new Jugador("nombre", "apellido", "nacionalidad", 0, 0);
        int niveles = (int) (Math.log(totalJugadores) / Math.log(2));
        int maxNodos = (int) Math.pow(2, niveles + 1) - 1;
        inicializarArbol(maxNodos, jugador);
    }

    private void inicializarArbol(int maxNodos, Jugador jugador) {
        for (int i = 0; i < maxNodos; i++) {
            partidos.add(jugador);// jugadores vacios
        }
    }

    public void ingresarJugadores(int posicion, Jugador jugador1, Jugador jugador2) {
        partidos.set(posicion, jugador1);

        partidos.set(posicion + 1, jugador2);
    }

    public void ingresarGanador(int posicion, Jugador jugador1) {
        partidos.set(posicion, jugador1);

    }

    public void mostrarNiveles(int[] nav) {
        int cant = 1;
        if (nav[1] == -1) {
            nav[1] = partidos.size();
        }

        System.out.println("\n");
        if (this.totalJugadores > 0 && nav[1] != -3) {// sin jugadores no hay nada, no tiene que ver con los partidos a nivel de
            // recorridos
            if (nav[1] != -2) {
                for (int i = nav[0]; i < nav[1];) {
                    // mostrar las parejas
                    System.out.print(
                            "\t\t_Partido Nr." + cant + ":  [" + partidos.get(i) + "] & ["
                            + partidos.get(i + 1)
                            + "]   \n");
                    cant++;
                    i = i + 2;
                }
            } else {
                System.out.print(
                        "\t\t_Ganador de la final: [" + partidos.get(0) + "]");
            }
        } else {
            System.out.println("Ingrese una ronda valida");
        }
        System.out.println("\n\n");

    }

    public Jugador verJugador(int posicion) {
        return partidos.get(posicion);
    }

    public int jugadoresVacios() {
        int indice = 0;
        for (Jugador partido : partidos) {
            if (partido.getApellido().equals("apellido")) {
                indice++;
            } else {
                break;
            }
        }
        return indice;
    }

    public Jugador campeon(){
        Jugador jugador = null;
        if(partidos.size() > 0){
            jugador = partidos.get(0);
        }
        return jugador;
    }

    
    /* Print de arbol binario usando el recorrido inorde INORDEN, pero modificado.*/


    public void imprimirArbol() {
        imprimirDeLadoInorden(0, 0);
    }

    /* 
        * Recorrido inorden 
        Hijo izquierdo: Se recorre primero, moviéndose hacia los nodos más a la izquierda.
        Raíz: El nodo actual se imprime en su nivel de profundidad.
        Hijo derecho: Después se recorre el subárbol derecho, lo que permite que este aparezca alineado hacia la derecha en la impresión.
        * 
    */
    private void imprimirDeLadoInorden(int posicion, int nivel) {
        if (posicion >= partidos.size() || partidos.get(posicion) == null) {
            return;
        }

        //  hijo izquierdo
        imprimirDeLadoInorden(2 * posicion + 1, nivel + 1);

        // Imprimir el nodo con la sangría adecuada
        for (int i = 0; i < nivel; i++) {
            System.out.print("\t\t");
        }

        System.out.println(partidos.get(posicion));

        // hijo derecho
        imprimirDeLadoInorden(2 * posicion + 2, nivel + 1);
    }
    
  
   
}
