
package modelo;

/**
 * @author Ferrando Carlos
 */
public class Jugador {
    private String nombre;
    private String apellido;
    private String nacionalidad;
    private int ranking;
    private int set;
    /*CONSTRUCTORES*/
    public Jugador() {
    }

    public Jugador(String nombre, String apellido, String nacionalidad, int ranking) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nacionalidad = nacionalidad;
        this.ranking = ranking;
    }
    public Jugador(String nombre, String apellido, String nacionalidad, int ranking,int set) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nacionalidad = nacionalidad;
        this.ranking = ranking;
        this.set = set;
    }
    /*GETTERS & SETTERS*/

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
    /*toString*/
    @Override
    public String toString() {
        return apellido + ", " + ranking;
    }
    //TODO
    //método carga "Interface"
}
