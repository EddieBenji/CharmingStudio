/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Controlador.DAO.DAOClientes;
import Modelo.Persona;
import java.sql.SQLException;

/**
 *
 * @author Alejandro
 */
public class ControladorCliente {
    
    
    public void agregar(Persona cliente) throws SQLException{
        
        DAOClientes dao = new DAOClientes();
        
        dao.agregar(cliente);
       
        
    }
    
    
}
