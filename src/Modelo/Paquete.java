package Modelo;

import java.util.LinkedList;

/**
 * @author Lalo
 * @version 1.0
 * @created 19-sep-2014 01:23:47 p.m.
 */
public abstract class Paquete {
    int id;
    String nombre;
    protected float precio;

    public Paquete() {}

    public abstract int getIdPaquete();
    public abstract void setIdPaquete(int id);
    public abstract float getPrecio();
    
    public abstract String getNombre();

    public abstract void setPrecio(float precio);
    public abstract void setProveedores(LinkedList<Proveedor> proveedores);
    public abstract void setServicios(LinkedList<Servicio> servicios);
    public abstract LinkedList<Servicio> getServicios();
    public abstract LinkedList<Proveedor> getProveedores();

}
