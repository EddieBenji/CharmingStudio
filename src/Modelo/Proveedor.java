package Modelo;

import java.util.LinkedList;

/**
 * @author Lalo
 * @version 1.0
 * @created 19-sep-2014 01:19:26 p.m.
 */
public class Proveedor extends Persona {

    private LinkedList<Servicio> provServicios;

    /**
     * Establece un nuevo objeto de tipo proveedor, con sus respectivos
     * atributos.
     *
     * @param id
     * @param nombre
     * @param direccion
     * @param telefono
     * @param correo
     */
    public Proveedor(int id, String nombre, String direccion, String telefono, String correo) {
        super(id, nombre, direccion, telefono, correo);
        this.provServicios = new LinkedList<>();
    }

    /**
     * @return the provServicios
     */
    public LinkedList<Servicio> getServiciosQueProvee() {
        return provServicios;
    }

    /**
     * @param provServicios the provServicios to set
     */
    public void setServiciosQueProvee(LinkedList<Servicio> provServicios) {
        this.provServicios = provServicios;
    }
    
    /**
     *Método que agrega un servicio en específico, para el proveedor.
     * @param unServicio, que es el nuevo que se agregará.
     */
    public void agregarUnServicio(Servicio unServicio){
        
        provServicios.add(unServicio);
    }
    
    /**
     *Agrega todos los servicios a un proveedor en particular.
     * @param provServicios
     */
    public void agregarServicios(LinkedList<Servicio> provServicios){
        provServicios.addAll(provServicios);
    }
    
    
    
    
    
    @Override
    public String toString() {
        return "Proveedor: " + super.toString() + "Servicios que provee:" + getServiciosQueProvee();
    }

}
