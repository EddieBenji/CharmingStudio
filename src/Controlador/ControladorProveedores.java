/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Controlador.DAO.DAOClientes;
import Controlador.DAO.DAOEmpleados;
import Controlador.DAO.DAOProveedores;
import Modelo.Persona;
import Modelo.Proveedor;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Carlos
 */
public class ControladorProveedores {
    DAOProveedores dao=new DAOProveedores();
    
    public Proveedor buscarEspecifico(String nombrePersona) throws SQLException {

        return dao.buscarEspecificamente(nombrePersona);
  
    }
    
    public boolean agregar(Persona proveedor) throws SQLException {

        return dao.agregar(proveedor);

    }

    public boolean eliminar(int Proveedor) throws SQLException {

        return dao.eliminar(Proveedor);

    }
    
    public boolean modificar(Persona persona) throws SQLException {

        return dao.modificar(persona);
    }
    
    public LinkedList buscarCoincidencias(String nombrePersona) throws SQLException {
       
        

        return dao.buscarCoincidencias(nombrePersona);
    }

}
