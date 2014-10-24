/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Controlador.DAO.DAOEmpleados;
import Modelo.Empleado;
import Modelo.Persona;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Alejandro
 */
public class ControladorEmpleado {
    DAOEmpleados dao = new DAOEmpleados();
    public boolean agregar(Persona empleado) throws SQLException {

       return dao.agregar(empleado);

    }

    public boolean eliminar(int idEmpleado) throws SQLException {

        return dao.eliminar(idEmpleado);
    }

    public LinkedList buscarCoincidencias(String nombrePersona) throws SQLException {
       
        return dao.buscarCoincidencias(nombrePersona);
    }
    
    public boolean modificar(Persona persona) throws SQLException{
       
        return dao.modificar(persona);
    }
    
    public Empleado buscarEspecifico(String nombrePersona) throws SQLException {

        return dao.buscarEspecificamente(nombrePersona);
      
    }
    
    public LinkedList buscarTodosLosEmpleados() throws SQLException {

        return dao.buscarTodosLosEmpleados();
        
    }
}
