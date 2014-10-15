/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Controlador.DAO.DAOEmpleados;
import Modelo.Persona;
import java.sql.SQLException;

/**
 *
 * @author Alejandro
 */
public class ControladorEmpleado {

    public void agregar(Persona empleado) throws SQLException {

        DAOEmpleados dao = new DAOEmpleados();

        dao.agregar(empleado);

    }

    public void eliminar(int idEmpleado) throws SQLException {

        DAOEmpleados dao = new DAOEmpleados();

        dao.eliminar(idEmpleado);
    }

    public void buscarCoincidencias(String nombrePersona) throws SQLException {
       
        DAOEmpleados dao = new DAOEmpleados();

        dao.buscarCoincidencias(nombrePersona);
    }
    
    public void modificar(Persona persona) throws SQLException{
        DAOEmpleados dao = new DAOEmpleados();

        dao.modificar(persona);
    }
}
