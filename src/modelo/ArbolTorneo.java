package modelo;

import java.util.ArrayList;

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
        if(nav[1]==-1){
            nav[1]=partidos.size()-1;
        }
     
        if (this.totalJugadores > 0) {//sin jugadores no hay nada, no tiene que ver con los partidos a nivel de recorridos
            for (int i = nav[0]; i <= nav[1];) {
                //mostrar las parejas
                System.out.print(
                        "__Partido nr." + cant + ":  [" + partidos.get(i) + "] & ["
                        + partidos.get(i + 1)
                        + "]   ");
                cant++;
                i = i + 2;
            }
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

    public void cargaPostOrden() {
        int niveles = (int) (Math.log(totalJugadores) / Math.log(2));
        while (niveles > 0) {
            postOrden(0);
            niveles --;
        }

    }

    private void postOrden(int posicion) {
        imprimirPartidos = (ArrayList<Jugador>) partidos.clone();
        if (posicion >= imprimirPartidos.size() || imprimirPartidos.get(posicion) == null) {
            return;
        }
        //Recorrer hijo izq
        postOrden(2 * posicion + 1);
        //Recorrer hijo der
        postOrden(2 * posicion + 2);
        //Visita nodo actual " AL iniciar nodo raíz"
        // System.out.print("[" + partidos.get(posicion) + "]");
        if (esHoja(posicion)) {
            System.out.print("\n[" + imprimirPartidos.get(posicion) + "]");
            if (!imprimirPartidos.get(posicion).getNombre().equalsIgnoreCase("")) {
                borrarHoja(posicion);
            }
        }
        System.out.println("");
    }

    private boolean esHoja(int posicion) {
        return (2 * posicion + 1 >= imprimirPartidos.size() || imprimirPartidos.get(2 * posicion + 1) == null)
                && (2 * posicion + 2 >= imprimirPartidos.size() || imprimirPartidos.get(2 * posicion + 2) == null);

    }

    private void borrarHoja(int posicion) {
        Jugador jugador = new Jugador("", "", "", 0, 0);
        if (posicion >= imprimirPartidos.size() || imprimirPartidos.get(posicion) == null) {
            return;
        }

        if (esHoja(posicion)) {
            imprimirPartidos.set(posicion, jugador);//borro elemento hoja
        }
    }
}
