/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Cliente;
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
public class ControladorServicios {

    Connection Conexion;
    ConexionBaseDatos BaseDeDatos;

    public ControladorServicios() {
        try {
            Conexion = ConexionBaseDatos.getInstanciaConexionDeBaseDatos().getConexionBD();
            System.out.println("Se conecto");
        } catch (SQLException ex) {
            System.out.println("No hay conexion");
            Logger.getLogger(ControladorClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     *
     * @param servicio
     * @throws java.sql.SQLException
     * @return
     * 
     */
    public boolean agregarServicio(Servicio servicio) throws SQLException {
        boolean seAgregoServicio = false;

        Statement sentencia = Conexion.createStatement();
        sentencia.executeUpdate("INSERT INTO charmingstudio.servicios (`Nombre`)" + "VALUES("
                + "'" + servicio.getServNombre() + "')");
        seAgregoServicio = true;

        return seAgregoServicio;
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

        sentencia.executeUpdate("DELETE FROM charmingstudio.servicios WHERE idServicios= '" + idServicio + "'");
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
        ResultSet Busqueda = sentencia.executeQuery("SELECT * FROM charmingstudio.cliente WHERE Nombre LIKE '%" + nombreServicio + "%'");
        if (!Busqueda.wasNull()) {
            while (Busqueda.next()) {
                servicio.add(new Servicio(Busqueda.getString(2),0));
            }
            return servicio;
        }
        System.out.println("El cliente no se encuentra en la BD");
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

    
}
