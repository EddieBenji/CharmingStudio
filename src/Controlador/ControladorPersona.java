/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Cliente;
import Modelo.Persona;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Carlos
 */
public interface ControladorPersona {
    public boolean agregar(Persona persona)throws SQLException;
    public boolean eliminar(int idPersona)throws SQLException;
    public LinkedList buscarCoincidencias(String nombrePersona)throws SQLException;
    public Persona buscarPorNombre(String nombrePersona)throws SQLException;
    public boolean modificar(Persona persona)throws SQLException;
}
