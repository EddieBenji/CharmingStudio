/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Controlador.DAO.DAOPaquetes;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Lalo
 */
public class ControladorPaquetes {

    DAOPaquetes dao;

    public ControladorPaquetes() {
        dao = new DAOPaquetes();
    }

    /**
     * Agrega un nuevo paquete a la BD con la información del proveedor y
     * servicio que va a proveer.
     *
     * @param idPaquete, identifica si es básico, intermedio o completo. 
     * 1 - si es básico.
     * 2 - si es intermedio. 
     * 3 - si es completo.
     * @param idProveedor es el proveedor que va a dar el servicio.
     * @param idServicio es el servicio que va a dar el proveedor.
     * @return verdadero o falso dependiendo de si se pudo o no, agregar a la
     * BD.
     * @throws SQLException
     */
    public boolean agregarPaquetes(int idEvento,int idPaquete, 
            int idProveedor, int idServicio, String strFecha) throws SQLException {
        
        return dao.insertarElemento(idEvento,idPaquete, idProveedor, idServicio, strFecha);
    }

    /**
     *Devuelve todos los IDs de los proveedores y servicios que servirán para
     * un evento.
     * 
     * @param idPaquete
     * @return
     * @throws SQLException
     */
    public LinkedList encontrarProveedoresDePaquete(int idPaquete) throws SQLException {
        return dao.obtenerElementosDePaquete(idPaquete);
    }
    
    
}
