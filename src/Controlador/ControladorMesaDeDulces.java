/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Controlador.DAO.DAOMesaDeDulces;
import Modelo.MesaDeDulces;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Alejandro
 */
public class ControladorMesaDeDulces {
    DAOMesaDeDulces dao = new DAOMesaDeDulces();
    
    public boolean agregarMD(MesaDeDulces mesaDulces) throws SQLException {
        
        return dao.agregarMD(mesaDulces);
    }

    public boolean eliminarMD(int idMesaDeDulces) throws SQLException {
        

        return dao.eliminarMD(idMesaDeDulces);
    }
    
    public LinkedList buscarCoincidencias(String nombreMesaDeDulces) throws SQLException {
         
        return dao.buscarCoincidencias(nombreMesaDeDulces);
    }
    
    public boolean modificarMD(MesaDeDulces mesaDeDulcesAModificar) throws SQLException {
      
        return dao.modificarMD(mesaDeDulcesAModificar);
    }
    public MesaDeDulces buscarEspecifico(String nombreMesa) throws SQLException {

        DAOMesaDeDulces dao = new DAOMesaDeDulces();

        return dao.buscarEspecificamente(nombreMesa);
        
        
    }
}
