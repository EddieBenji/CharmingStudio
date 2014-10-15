/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Controlador.DAO.DAOMesaDeDulces;
import Modelo.MesaDeDulces;
import java.sql.SQLException;

/**
 *
 * @author Alejandro
 */
public class ControladorMesaDeDulces {

    public void agregarMD(MesaDeDulces mesaDulces) throws SQLException {
        DAOMesaDeDulces dao = new DAOMesaDeDulces();

        dao.agregarMD(mesaDulces);
    }

    public void eliminarMD(int idMesaDeDulces) throws SQLException {
        DAOMesaDeDulces dao = new DAOMesaDeDulces();

        dao.eliminarMD(idMesaDeDulces);
    }
    
    public void buscarCoincidencias(String nombreMesaDeDulces) throws SQLException {
         DAOMesaDeDulces dao = new DAOMesaDeDulces();

        dao.buscarCoincidencias(nombreMesaDeDulces);
    }
    
    public void modificarMD(MesaDeDulces mesaDeDulcesAModificar) throws SQLException {
        DAOMesaDeDulces dao = new DAOMesaDeDulces();

        dao.modificarMD(mesaDeDulcesAModificar);
    }
}
