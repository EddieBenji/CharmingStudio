package Controlador.DAO;

import Modelo.Servicio;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Romario
 */
public class DAOServicios {

    Connection Conexion;
    ConexionBaseDatos BaseDeDatos;

    public DAOServicios() {
        try {
            Conexion = ConexionBaseDatos.getInstancia().getConexionBD();
        } catch (SQLException ex) {
            System.out.println("No hay conexion");
            Logger.getLogger(DAOClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param idServicio
     * @return
     * @throws java.sql.SQLException
     */
    public boolean eliminarServicio(int idServicio) throws SQLException {
        boolean seEliminoServicio = false;
        Statement sentencia = Conexion.createStatement();

        sentencia.executeUpdate("DELETE FROM charmingstudio.servicios WHERE"
                + " idServicios= '" + idServicio + "'");
        seEliminoServicio = true;

        return seEliminoServicio;
    }

    /**
     *
     * @param nombreServicio
     * @return
     * @throws java.sql.SQLException
     */
    public LinkedList buscarServicio(String nombreServicio) throws SQLException {
        LinkedList<Servicio> servicio = new LinkedList<>();
        Statement sentencia = Conexion.createStatement();
        ResultSet Busqueda = sentencia.executeQuery("SELECT * FROM charmingstudio.servicios"
                + " WHERE Nombre LIKE '%" + nombreServicio + "%'");
        if (!Busqueda.wasNull()) {
            while (Busqueda.next()) {
                servicio.add(new Servicio(Busqueda.getInt(1), Busqueda.getString(2)));
            }
            return servicio;
        }
        System.out.println("El Servicio no se encuentra en la BD");
        return null;
    }

    /**
     *
     * @param nombreServicio
     * @return
     */
    public boolean modificarServicio(String nombreServicio) {
        return false;
    }

    /**
     * A partir del nombre que se le pase, se buscará en la BD, para encontrar
     * toda la información asociada a ese servicio.
     *
     * @param serv, es el nombre del servicio a encontrar.
     * @return el servicio con la información completa.
     * @throws java.sql.SQLException, en caso de que haya un error con la
     * conexión de la BD.
     */
    public Servicio encontrarServicioPorNombre(String serv) throws SQLException {
        System.out.println(serv);

        Statement sentencia = Conexion.createStatement();
        ResultSet busqueda = sentencia.executeQuery("SELECT * FROM charmingstudio.servicios"
                + " WHERE Nombre ='" + serv + "'");
        if (busqueda.wasNull()) {
            return null;
        }

        busqueda.next();
        Servicio servicio = new Servicio(busqueda.getInt(1), busqueda.getString(2));

        return servicio;
    }

    public Servicio encontrarServicioPorID(int id) throws SQLException {
        Statement sentencia = Conexion.createStatement();
        ResultSet busqueda = sentencia.executeQuery("SELECT * FROM charmingstudio.servicios"
                + " WHERE IdServicios ='" + id + "'");
        busqueda.next();

        //Empaquetamos el objeto, con todos sus datos:
        Servicio servicio = new Servicio(busqueda.getInt(1), busqueda.getString(2));

        return servicio;
    }
    
     /**
     * Este método se encarga de encontrar y devolver los servicios de un
     * proveedor, a partir de la clave del proveedor.
     *
     * @param claveProveedor, única que se encunetra en la BD.
     * @return
     * @throws SQLException
     */
    public LinkedList encontrarServiciosDelProveedor(int claveProveedor) throws SQLException {

        Statement sentenciaBuscaIdServicios = Conexion.createStatement();
        ResultSet resultadoDeBusqueda = sentenciaBuscaIdServicios.executeQuery("SELECT * FROM"
                + " charmingstudio.provee WHERE idProveedor = '" + claveProveedor + "'");

        if (resultadoDeBusqueda.wasNull()) {
            return null;
        }
       LinkedList<Servicio> servicios = new LinkedList<>();
        Servicio _servicio;
        int idServicio;
        while (resultadoDeBusqueda.next()) {
            idServicio = resultadoDeBusqueda.getInt(2);
            //Ya tenemos el id, ahora necesitamos la info de la bd.
            _servicio = encontrarServicioPorID(idServicio);
            _servicio.setCosto(resultadoDeBusqueda.getFloat(3));
            servicios.add(_servicio);
        }//fin while
        return servicios;

    }
    
    
    
}
