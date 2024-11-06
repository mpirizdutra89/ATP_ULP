
package modelo;

import java.util.Objects;

/**
 * @author Ferrando Carlos
 */
public class Jugador {
    private String nombre;
    private String apellido;
    private String nacionalidad;
    private int ranking;
    private int set;

    /* CONSTRUCTORES */
    public Jugador() {
    }

    public Jugador(String nombre, String apellido, String nacionalidad, int ranking) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nacionalidad = nacionalidad;
        this.ranking = ranking;
    }

    public Jugador(String nombre, String apellido, String nacionalidad, int ranking, int set) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nacionalidad = nacionalidad;
        this.ranking = ranking;
        this.set = set;
    }
    /* GETTERS & SETTERS */

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public int getSet() {
        return set;
    }

    public void setSet(int set) {
        this.set = set;
    }

    /* Método para incrementar set y resetear */
    public void incrementarSets() {
        this.set++;
    }

    public void resetSets() {
        this.set = 0;
    }

    /* toString */
    @Override
    public String toString() {
        return apellido + " (R-" + ranking + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Jugador jugador = (Jugador) o;
        return ranking == jugador.ranking; // Comparar solo por ranking
    }

    @Override
    public int hashCode() {
        return Objects.hash(ranking); // Hash solo por ranking
    }

    // TODO
    // método carga "Interface"
}

