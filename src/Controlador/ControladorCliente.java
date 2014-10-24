/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Controlador.DAO.DAOClientes;
import Modelo.Cliente;
import Modelo.Persona;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Alejandro
 */
public class ControladorCliente implements ControladorPersona {

    DAOClientes dao = new DAOClientes();
    
 
    public boolean agregar(Persona cliente) throws SQLException {

        return dao.agregar(cliente);


    }

    public boolean eliminar(int IdCliente) throws SQLException {

        return dao.eliminar(IdCliente);


    }

    public LinkedList buscarCoincidencias(String nombrePersona) throws SQLException {

        return dao.buscarCoincidencias(nombrePersona);
    }
    
    public Cliente buscarPorNombre(String nombrePersona) throws SQLException {

        return dao.buscarEspecificamente(nombrePersona);
        
        
    }

    public boolean modificar(Persona persona) throws SQLException {

        return dao.modificar(persona);
    }
    
    public LinkedList buscarTodosLosClientes() throws SQLException {

        return dao.buscarTodosLosClientes();
        
    }
    
}
