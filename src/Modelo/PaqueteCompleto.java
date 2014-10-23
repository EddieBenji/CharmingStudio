package Modelo;

import java.util.LinkedList;

/**
 * @author Lalo
 * @version 1.0
 * @created 19-sep-2014 01:30:27 p.m.
 */
public class PaqueteCompleto extends PaqueteIntermedio {

    private Servicio servLugar;
    private Servicio servMusica;

    public PaqueteCompleto(Servicio ServBanquetera, Servicio servIluminacion, Servicio servCarpa,
            Servicio servLugar, Servicio servMusica, float precio) {
        super(ServBanquetera, servIluminacion, servCarpa,precio);
        this.nombre="Completo";
        this.servLugar = servLugar;
        this.servMusica = servMusica;
    }

    @Override
    public float getPrecio() {
        return this.precio;
    }
    /**
     *Establece el precio del paquete completo.
     * @param precio
     */
    @Override
    public void setPrecio(float precio) {
        this.precio = precio;
    }
    
    public LinkedList getServicios(){
        LinkedList<Servicio> servicios=super.getServicios();
        servicios.add(servLugar);
        servicios.add(servMusica);
        return servicios;
    }
    
    @Override
    public String getNombre(){
        return this.nombre;
    }

}
