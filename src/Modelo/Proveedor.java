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
        //this.provServicios = new LinkedList<>();
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
    @Override
    public String toString() {
        return "Proveedor: " + super.toString() + "Servicios que provee:" + getServiciosQueProvee();
    }

}
