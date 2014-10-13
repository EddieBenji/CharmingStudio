package Controlador;

import Controlador.AdministraReportes;

/**
 * @author Lalo
 * @version 1.0
 * @created 19-sep-2014 02:36:04 p.m.
 */
public class ControladorReporEmpleado implements AdministraReportes {

    public ControladorReporEmpleado() {

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

}
