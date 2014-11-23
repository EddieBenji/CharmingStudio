/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

/**
 *
 * @author Lalo
 */
public class DAOPaquetes {

    Connection Conexion;

    public DAOPaquetes() {
        try {
            Conexion = ConexionBaseDatos.getInstancia().getConexionBD();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Este método se encarga de insertar un nuevo paquete en la BD, la tabla
     * que le corresponde es "arma".
     *
     * @param idPaquete
     * @param idProveedor
     * @param idServicio
     * @return verdadero o falso, dependiendo de si se pudo o no, insertar el
     * elemento.
     * @throws java.sql.SQLException
     */
    public boolean insertarElemento(int idEvento, int idPaquete, 
            int idProveedor, int idServicio, String strFecha) throws SQLException {

        Statement sentenciaDeInsercion = Conexion.createStatement();

        
        /*
        INSERT INTO `arma` (`idPaquetes`, `idProveedor`, `idServicios`, 
        `Eventos_idEvento`, `Eventos_Fecha`) VALUES ('1', '1', '1', '1', '2014-11-23')
        */
        boolean seAgregoElemento = sentenciaDeInsercion.execute("INSERT INTO "
                + "charmingstudio.arma (`idPaquetes`, `idProveedor`, "
                + "`idServicios`,`Eventos_idEvento`)" + "VALUES("
                + "'" + idPaquete + "',"
                + "'" + idProveedor + "',"
                + "'" + idServicio  +"',"
                + "'" + idEvento + "')");

        return seAgregoElemento;
    }

    /**
     * Se encarga de encontrar todos los proveedores con sus servicios a partir
     * del ID del paquete que se le pase.
     *
     * @param idPaquete
     * @return lista simple, con la información de los proveedores (SOLO LOS IDs).
     * Por ejemplo:
     * en la posición 1 estaría: 1 1 1
     * Significa: Paquete 1, Proveedor 1, Servicio 1.
     * @throws SQLException
     */
    public LinkedList obtenerElementosDePaquete(int idPaquete) throws SQLException {
        Statement obtencionProveedores = Conexion.createStatement();
        ResultSet infoPaquete = obtencionProveedores.executeQuery("SELECT FROM"
                + " charmingstudio.arma WHERE idPaquete = '" + idPaquete + "'");

        LinkedList listaProveedores = new LinkedList();
        while (infoPaquete.next()) {
            //obtiene el id del paquete:
            listaProveedores.add(infoPaquete.getInt(1));
            //obtiene el id del proveedor:
            listaProveedores.add(infoPaquete.getInt(2));
            //obtiene el id del servicio:
            listaProveedores.add(infoPaquete.getInt(3));
        }

        return listaProveedores;
    }

}
