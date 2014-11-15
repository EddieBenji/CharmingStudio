package Modelo;

/**
 * @author Lalo
 * @version 1.0
 * @created 19-sep-2014 01:21:37 p.m.
 */
public class Servicio {

    private int id;
    private String servNombre;
    private float costo;

    /**
     * Establece un nuevo objeto de tipo servicio, con sus respectivos
     * atributos.
     *
     * @param id
     * @param nombre
     */
    public Servicio(int id, String nombre) {
        this.id = id;
        this.servNombre = nombre;
        this.costo = 0;
    }

    /**
     * Crea un nuevo objeto de tipo servicio, con sus siguientes parámetros:
     *
     * @param id
     * @param servNombre
     * @param costo
     */
    public Servicio(int id, String servNombre, float costo) {
        this.id = id;
        this.servNombre = servNombre;
        this.costo = costo;
    }

    public Servicio(String nombre, float costo) {
        this.id = 0;
        this.servNombre = nombre;
        this.costo = costo;
    }

    public Servicio() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public String getServNombre() {
        return servNombre;
    }

    public void setServNombre(String servNombre) {
        this.servNombre = servNombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Servicio: " + "Id: " + id + " Nombre de servicio: " + servNombre
                + " Costo: " + costo;
    }

}
