package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ferrando Carlos
 */
public class ArbolTorneo {

    private ArrayList<Jugador> partidos;
    private ArrayList<Jugador> imprimirPartidos;
    private int totalJugadores;
    private Map<Integer, ArrayList<Jugador>> niveles;

    public ArbolTorneo(int totalJugadores) {
        this.totalJugadores = totalJugadores;
        partidos = new ArrayList<>();
        Jugador jugador = new Jugador("nombre", "apellido", "nacionalidad", 0, 0);
        int niveles = (int) (Math.log(totalJugadores) / Math.log(2));
        int maxNodos = (int) Math.pow(2, niveles + 1) - 1;
        inicializarArbol(maxNodos, jugador);
        this.niveles = new HashMap<>();
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
        if (this.totalJugadores > 0 && nav[1]!=-3) {// sin jugadores no hay nada, no tiene que ver con los partidos a nivel de
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
            }else
            nav[1] = partidos.size() - 1;
        }

        if (this.totalJugadores > 0) {//sin jugadores no hay nada, no tiene que ver con los partidos a nivel de recorridos
            for (int i = nav[0]; i <= nav[1];) {
                //mostrar las parejas

                System.out.print(
                    "\t\t_Ganador de la final: [" + partidos.get(0) + "]" );
            }
        }else{
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


    public  void pruebaMuestraArrayCompleto(){
        for (Jugador j : partidos) {
            System.out.println(j);
        }
    }

//    public void cargaPostOrden() {
//        int niveles = (int) (Math.log(totalJugadores) / Math.log(2));
//        while (niveles > 0) {
//            postOrden(0);
//            niveles--;
   
    public void imprimirTorneoNiveles() {
        niveles.clear();
        postOrden(0, 0);


        imprimirArbolBinario();
    }

    private void postOrden(int posicion, int nivel) {
        imprimirPartidos = (ArrayList<Jugador>) partidos.clone();
        if (posicion >= imprimirPartidos.size() || imprimirPartidos.get(posicion) == null) {
            return;
        }

//        // Recorrer hijo izq
//        postOrden(2 * posicion + 1);
//        // Recorrer hijo der
//        postOrden(2 * posicion + 2);
//        // Visita nodo actual " AL iniciar nodo raíz"

        //Recorrer hijo izq
        postOrden(2 * posicion + 1, nivel + 1);
        //Recorrer hijo der
        postOrden(2 * posicion + 2, nivel + 1);
        //Visita nodo actual " AL iniciar nodo raíz"

        // System.out.print("[" + partidos.get(posicion) + "]");

        niveles.computeIfAbsent(nivel, k -> new ArrayList<>()).add(partidos.get(posicion));
    }
    
    public void imprimirArbolBinario() {
    int altura = (int) (Math.log(partidos.size() + 1) / Math.log(2));
    int nivelActual = 0;
    int cantidadNodos = 1;

    for (int i = 0; i <= altura; i++) {
        int espacioEntre = (int) Math.pow(2, altura - i + 1) - 1; // Espaciado entre nodos
        int espacioInicial = (int) Math.pow(2, altura - i) - 1;  // Espacio inicial para el primer nodo

        // Imprimir el espacio inicial
        for (int j = 0; j < espacioInicial; j++) {
            System.out.print("     ");
        }

        // Imprimir los nodos de este nivel
        for (int j = 0; j < cantidadNodos && nivelActual < partidos.size(); j++, nivelActual++) {
            if (partidos.get(nivelActual) != null) {
                System.out.print("[" + partidos.get(nivelActual) + "]");
            } else {
                System.out.print("                              ");
            }

            // Espacio entre nodos del mismo nivel
            for (int k = 0; k < espacioEntre; k++) {
                System.out.print("");
            }
            
        }

        System.out.println("");
    }

//    private boolean esHoja(int posicion) {
//        return (2 * posicion + 1 >= imprimirPartidos.size() || imprimirPartidos.get(2 * posicion + 1) == null)
//                && (2 * posicion + 2 >= imprimirPartidos.size() || imprimirPartidos.get(2 * posicion + 2) == null);
//
//    }

//    private void borrarHoja(int posicion) {
//        Jugador jugador = new Jugador("", "", "", 0, 0);
//        if (posicion >= imprimirPartidos.size() || imprimirPartidos.get(posicion) == null) {
//            return;
//        }
//
//        if (esHoja(posicion)) {
//            imprimirPartidos.set(posicion, jugador);// borro elemento hoja
//        }
//
//        
//        System.out.println();
//        System.out.println();
//        cantidadNodos *= 2; // Aumenta la cantidad de nodos en el siguiente nivel
//
//    }
}

//    private boolean esHoja(int posicion) {
//        return (2 * posicion + 1 >= imprimirPartidos.size() || imprimirPartidos.get(2 * posicion + 1) == null)
//                && (2 * posicion + 2 >= imprimirPartidos.size() || imprimirPartidos.get(2 * posicion + 2) == null);
//
//    }
//
//    private void borrarHoja(int posicion) {
//        Jugador jugador = new Jugador("", "", "", 0, 0);
//        if (posicion >= imprimirPartidos.size() || imprimirPartidos.get(posicion) == null) {
//            return;
//        }
//
//        if (esHoja(posicion)) {
//            imprimirPartidos.set(posicion, jugador);//borro elemento hoja
//        }
//    }
}
