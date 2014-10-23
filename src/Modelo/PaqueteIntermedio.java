package Modelo;

import java.util.LinkedList;

/**
 * @author Lalo
 * @version 1.0
 * @created 19-sep-2014 01:28:40 p.m.
 */
public class PaqueteIntermedio extends PaqueteBasico {

    private Servicio servCarpa;

    /**
     * Inicializa un nuevo paquete intermedio, con los servicios de banquetera,
     * iluminación, carpa y el precio.
     * @param ServBanquetera
     * @param servIluminacion
     * @param servCarpa
     * @param precio
     */
    public PaqueteIntermedio(Servicio ServBanquetera, Servicio servIluminacion, Servicio servCarpa, float precio ) {
        super(ServBanquetera, servIluminacion, precio);
        this.nombre="Intermedio";
        this.servCarpa = servCarpa;
    }
    /**
     *Devuelve el precio del paquete intermedio.
     * @return precio.
     */
    @Override
    public float getPrecio() {
        return this.precio;
    }
     /**
     *Establece el precio del paquete intermedio.
     * @param precio
     */
    @Override
    public void setPrecio(float precio) {
        this.precio = precio;
    }
    
    public LinkedList getServicios(){
        LinkedList<Servicio> servicios=super.getServicios();
        servicios.add(servCarpa);
        return servicios;
    }
    
    @Override
    public String getNombre(){
        return this.nombre;
    }

}
