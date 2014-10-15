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

    public void agregar(Persona cliente) throws SQLException {

        DAOClientes dao = new DAOClientes();

        dao.agregar(cliente);


    }

    public void eliminar(int IdCliente) throws SQLException {

        DAOClientes dao = new DAOClientes();

        dao.eliminar(IdCliente);


    }

    public void buscarCoincidencia(String nombrePersona) throws SQLException {

        DAOClientes dao = new DAOClientes();

        dao.buscarCoincidencias(nombrePersona);
    }

    public void modificar(Persona persona) throws SQLException {

        DAOClientes dao = new DAOClientes();

        dao.modificar(persona);
    }
}
