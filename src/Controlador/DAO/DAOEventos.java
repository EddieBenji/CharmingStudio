package Controlador.DAO;

import Modelo.EventosSociales;
import java.util.Date;

/**
 * @author Lalo
 * @version 1.0
 * @created 19-sep-2014 02:36:23 p.m.
 */
public class DAOEventos {

    public DAOEventos() {

    }

    /**
     *
     * @param eventoA_Guardar
     */
    public void agregarEvento(EventosSociales eventoA_Guardar) {

    }

    /**
     *
     * @param fecha
     * @param nombreEvento
     * @return 
     */
    public boolean eliminarEvento(Date fecha, EventosSociales nombreEvento) {
        return false;
    }

    /**
     *
     * @param fecha
     * @return 
     */
    public EventosSociales buscarEventos(Date fecha) {
        return null;
    }

    /**
     *
     * @param fecha
     * @param nombreCliente
     * @return 
     */
    public boolean modificarEvento(Date fecha, String nombreCliente) {
        return false;
    }

}
