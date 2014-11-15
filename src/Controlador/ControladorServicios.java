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
    
    public Servicio buscarServicioPorNombre(String nombreServicio) throws SQLException {

        return dao.encontrarServicioPorNombre(nombreServicio);
        
    }
    
    public Servicio buscarServicioPorID(int idServicio) throws SQLException {

        return dao.encontrarServicioPorID(idServicio);
        
    }
    
    
}
