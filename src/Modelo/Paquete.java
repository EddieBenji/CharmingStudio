package Modelo;

/**
 * @author Lalo
 * @version 1.0
 * @created 19-sep-2014 01:23:47 p.m.
 */
public abstract class Paquete {

    protected float precio;

    public Paquete() {}

    public abstract float getPrecio();

    public abstract void setPrecio(float precio);

}
