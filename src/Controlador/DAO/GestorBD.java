package Controlador.DAO;

import Modelo.Persona;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * @author Lalo
 * @version 1.0
 * @created 19-sep-2014 02:33:52 p.m.
 */
public abstract class GestorBD {

    protected ConexionBaseDatos BaseDeDatos;

    /**
     *
     * @param persona
     * @return
     * @throws java.sql.SQLException
     */
    public abstract boolean agregar(Persona persona) throws SQLException;

    /**
     *
     * @param idPersona
     * @return
     * @throws java.sql.SQLException
     */
    public abstract boolean eliminar(int idPersona) throws SQLException;

    /**
     *
     * @param nombrePersona
     * @return
     * @throws java.sql.SQLException
     */
    public abstract LinkedList buscarCoincidencias(String nombrePersona) throws SQLException;


    /**
     *
     * @param persona
     * @return
     * @throws java.sql.SQLException
     */
    public abstract boolean modificar(Persona persona) throws SQLException;

}
