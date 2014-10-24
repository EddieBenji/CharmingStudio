package Modelo;

import java.util.Arrays;

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
     * @param nombre
     * @param costo
     */
    public Servicio(String nombre, float costo) {
        this.servNombre = nombre;
        this.costo = costo;
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

    

  

}
