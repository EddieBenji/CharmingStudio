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

    public Paquete(int id, String nombre, float precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }

    
    
    
    public Paquete() {}

    public abstract int getIdPaquete();
    public abstract void setIdPaquete(int id);
    
    public abstract float getPrecio();
    public abstract void setPrecio(float precio);
    
    public abstract String getNombre();
    public abstract void setNombre(String nombre);

    @Override
    public String toString() {
        return  "Identificador: " + id + "\n"+
                "Nombre: " + nombre +"\n"+
                "Precio: " + precio ;
    }

    

}
