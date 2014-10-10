package Controlador;

import Modelo.MesaDeDulces;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Lalo
 * @version 1.0
 * @created 19-sep-2014 02:36:30 p.m.
 */
public class ControladorMesaDeDulces {

    Connection Conexion;
    ConexionBaseDatos BaseDeDatos;

    public ControladorMesaDeDulces() {
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
     * @param mesaDulces
     * @return 
     * @throws java.sql.SQLException
     */
    public boolean agregarMD(MesaDeDulces mesaDulces) throws SQLException {
        boolean seAgregoMesaDeDulces = false;

        Statement sentAgregaMesaDeDulces = Conexion.createStatement();
        sentAgregaMesaDeDulces.executeUpdate("INSERT INTO charmingstudio.mesadulces (`Nombre`, `Costo`)" + "VALUES("
                + "'" + mesaDulces.getmdNombreDeMesa() + "',"
                + "'" + mesaDulces.getPrecio() + "')");

        seAgregoMesaDeDulces = true;
        return seAgregoMesaDeDulces;

    }

    /**
     *
     * @param idMesaDeDulces
     * @return 
     * @throws java.sql.SQLException
     */
    public boolean eliminarMD(int idMesaDeDulces) throws SQLException {
        boolean seEliminoMesaDeDulces = false;

        Statement sentEliminaMesaDeDulces = Conexion.createStatement();

        sentEliminaMesaDeDulces.executeUpdate("DELETE FROM charmingstudio.mesadulces WHERE idMesaDulces= '" + idMesaDeDulces + "'");
        seEliminoMesaDeDulces = true;

        return seEliminoMesaDeDulces;

    }

    /**
     *
     * @param nombreMdDulces
     * @return 
     */
    public MesaDeDulces buscarMD(String nombreMdDulces) {
        return null;
    }

    /**
     *
     * @param nombreMD
     * @return 
     */
    public boolean modificarMD(String nombreMD) {
        return false;
    }

   
}
