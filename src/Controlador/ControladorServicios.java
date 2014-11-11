/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Controlador.DAO.DAOClientes;
import Controlador.DAO.DAOServicios;
import Modelo.Cliente;
import Modelo.Servicio;
import java.sql.SQLException;

/**
 *
 * @author Carlos
 */
public class ControladorServicios {
    
    DAOServicios dao=new DAOServicios();
    
    public Servicio buscarServicio(String nombreServicio) throws SQLException {

        return dao.devuelveServicio(nombreServicio);
        
    }
    
    public Servicio buscarServicio(int idServicio) throws SQLException {

        return dao.encontrarServicio(idServicio);
        
    }
    
    
}
