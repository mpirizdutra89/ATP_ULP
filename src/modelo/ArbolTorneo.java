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
            }else{
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

    public void cargaPostOrden() {
        int niveles = (int) (Math.log(totalJugadores) / Math.log(2));
        while (niveles > 0) {
            postOrden(0);
            niveles--;
        }

    }

    private void postOrden(int posicion) {
        imprimirPartidos = (ArrayList<Jugador>) partidos.clone();
        if (posicion >= imprimirPartidos.size() || imprimirPartidos.get(posicion) == null) {
            return;
        }
        // Recorrer hijo izq
        postOrden(2 * posicion + 1);
        // Recorrer hijo der
        postOrden(2 * posicion + 2);
        // Visita nodo actual " AL iniciar nodo raíz"
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
            imprimirPartidos.set(posicion, jugador);// borro elemento hoja
        }
    }



    //Pruebas martin


    public void postordenIterativo() {
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> resultado = new Stack<>(); // Para almacenar los nodos en el orden postorden
        
        stack.push(0); // Empezamos desde la raíz
        
        while (!stack.isEmpty()) {
            int nodo = stack.pop();
            resultado.push(nodo);
            
            // Empujar el hijo izquierdo primero para que se procese después en el postorden
            int hijoIzquierdo = 2 * nodo + 1;
            int hijoDerecho = 2 * nodo + 2;
            
            if (hijoIzquierdo <  partidos.size() ){//&& arbol[hijoIzquierdo] != -1) {
                stack.push(hijoIzquierdo);
            }
            if (hijoDerecho <  partidos.size() ) {
                stack.push(hijoDerecho);
            }
        }
        
        // Imprimir el contenido de "resultado" que contiene el recorrido postorden
        System.out.println("Recorrido en postorden (de octavos a ganador):");
        while (!resultado.isEmpty()) {
            int nodo = resultado.pop();
            System.out.println("Jugador " + partidos.get(nodo));
        }
    }

 
 public void imprimirArbolDeLado() {
    imprimirDeLado(0, 0);
}


private void imprimirDeLado(int posicion, int nivel) {
    if (posicion >= partidos.size() || partidos.get(posicion) == null) {
        return;
    }

    //  hijo izquierdo
    imprimirDeLado(2 * posicion + 1, nivel + 1);

    // Imprimir el nodo con la sangría adecuada
    for (int i = 0; i < nivel; i++) {
        System.out.print("\t\t");
    }
    
    System.out.println(partidos.get(posicion));

    // hijo derecho
    imprimirDeLado(2 * posicion + 2, nivel + 1);
}

/* 
 * Recorrido inorden 
Hijo izquierdo: Se recorre primero, moviéndose hacia los nodos más a la izquierda.
Raíz: El nodo actual se imprime en su nivel de profundidad.
Hijo derecho: Después se recorre el subárbol derecho, lo que permite que este aparezca alineado hacia la derecha en la impresión.
 * 
 */
   
}
