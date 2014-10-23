package Modelo;

import java.util.LinkedList;

/**
 * @author Lalo
 * @version 1.0
 * @created 19-sep-2014 01:23:47 p.m.
 */
public abstract class Paquete {

    String nombre;
    protected float precio;

    public Paquete() {}

    public abstract float getPrecio();
    
    public abstract String getNombre();

    public abstract void setPrecio(float precio);
    
    public abstract LinkedList<Servicio> getServicios();

}
