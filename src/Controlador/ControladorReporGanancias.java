package Controlador;

import Controlador.AdministraReportes;
import java.util.Date;

/**
 * @author Lalo
 * @version 1.0
 * @created 19-sep-2014 02:35:55 p.m.
 */
public class ControladorReporGanancias implements AdministraReportes {

    private Date fecha;

    public ControladorReporGanancias() {

    }

    @Override
    public Object generaReportes() {
        return null;
    }

    /**
     *
     * @param vector
     * @return 
     */
    @Override
    public boolean exportaReportes(Object vector) {
        return false;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

}
