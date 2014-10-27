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
public class ControladorEmpleado implements ControladorPersona {
    
    DAOEmpleados dao = new DAOEmpleados();
    
    @Override
    public boolean agregar(Persona empleado) throws SQLException {

       return dao.agregar(empleado);

    }

    @Override
    public boolean eliminar(int idEmpleado) throws SQLException {

        return dao.eliminar(idEmpleado);
    }

    /**
     *
     * @param nombrePersona
     * @return
     * @throws SQLException
     */
    @Override
    public LinkedList buscarCoincidencias(String nombrePersona) throws SQLException {
       
        return dao.buscarCoincidencias(nombrePersona);
    }
    
    @Override
    public boolean modificar(Persona persona) throws SQLException{
       
        return dao.modificar(persona);
    }
    
    @Override
    public Empleado buscarPorNombre(String nombrePersona) throws SQLException {

        return dao.buscarPorNombre(nombrePersona);
      
    }
    
    public LinkedList buscarTodosLosEmpleados() throws SQLException {

        return dao.obtenerTodosLosEmpleados();
        
    }
}
