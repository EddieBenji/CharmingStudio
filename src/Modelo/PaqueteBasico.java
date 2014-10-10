package Modelo;

/**
 * @author Lalo
 * @version 1.0
 * @created 19-sep-2014 01:26:22 p.m.
 */
public class PaqueteBasico extends Paquete {

    private Servicio ServBanquetera;
    private Servicio servIluminacion;

    /**
     * Inicializa un nuevo paquete básico, con los servicios de banquetera,
     * iluminación y el precio.
     *
     * @param ServBanquetera
     * @param servIluminacion
     * @param precio
     */
    public PaqueteBasico(Servicio ServBanquetera, Servicio servIluminacion, float precio) {
        this.ServBanquetera = ServBanquetera;
        this.servIluminacion = servIluminacion;
        this.precio = precio;
    }

    /**
     *Devuelve el precio del paquete básico.
     * @return precio.
     */
    @Override
    public float getPrecio() {
        return this.precio;
    }

    /**
     *Establece el precio del paquete básico.
     * @param precio
     */
    @Override
    public void setPrecio(float precio) {
        this.precio = precio;
    }

}
