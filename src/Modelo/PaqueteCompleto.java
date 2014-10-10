package Modelo;

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
    
    

}
