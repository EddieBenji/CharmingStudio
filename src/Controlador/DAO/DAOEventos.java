package Controlador.DAO;

import Modelo.EventosSociales;
import Modelo.Proveedor;
import Modelo.Servicio;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Lalo
 * @version 1.0
 * @created 19-sep-2014 02:36:23 p.m.
 */
public class DAOEventos {

    protected ConexionBaseDatos BaseDeDatos;
    Connection Conexion;

    public DAOEventos() {
        try {
            Conexion = BaseDeDatos.getInstanciaConexionDeBaseDatos().getConexionBD();
            System.out.println("Se conecto");
        } catch (SQLException ex) {
            System.out.println("No hay conexion");
            Logger.getLogger(DAOClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param eventoA_Guardar
     */
    public void agregarEvento(EventosSociales eventoA_Guardar) throws SQLException {

        boolean seAgregoEvento = false;
        EventosSociales evento = eventoA_Guardar;

        //Se crean dos para que no creen conflictos entre ellos.

        Statement sentenciaAgregaEvento = Conexion.createStatement();


        Statement sentenciaAgregaArma = Conexion.createStatement();


        java.sql.Date sqlDate = new java.sql.Date(evento.getEvtFecha().getTime());

        for (int i = 0; i < evento.getEvtPaquete().getProveedores().size(); i++) {
            sentenciaAgregaArma.executeUpdate("INSERT INTO charmingstudio.arma "
                        + "(`idPaquete`, `idProveedor`,`idServicio` )" + "VALUES("
                        + "'" + evento.getEvtPaquete().getIdPaquete() + "',"
                        + "'" + evento.getEvtPaquete().getProveedores().get(i).getIdPersona() + "',"
                    + "'" + evento.getEvtPaquete().getServicios().get(i).getId() + "')");
        }

        for (int i = 0; i < evento.getEvtPaquete().getProveedores().size(); i++) {
            sentenciaAgregaEvento.executeUpdate("INSERT INTO charmingstudio.eventos "
                    + "(`idCliente`, `idMesaDulces`,`Fecha`,`PrecioTotal`,`idEmpleado`,`idPaquetes`, `idProveedor`,`idServicio` )"
                    + "VALUES("
                    + "'" + evento.getEvtCliente().getIdPersona() + "',"
                    + "'" + evento.getEvtMesaDeDulces().getIdMesaDulces() + "',"
                    + "'" + sqlDate + "',"
                    + "'" + calculaPrecioTotal(evento) + "',"
                    + "'" + evento.getEvtEmpleado().getIdPersona() + "',"
                    + "'" + evento.getEvtPaquete().getIdPaquete() + "',"
                    + "'" + evento.getEvtPaquete().getProveedores().get(i).getIdPersona() + "',"
                    + "'" + evento.getEvtPaquete().getServicios().get(i).getId() + "')");

        }






        seAgregoEvento = true;
        //}//fin if











    }

    /**
     *
     * @param fecha
     * @param nombreEvento
     * @return
     */
    public boolean eliminarEvento(Date fecha, EventosSociales nombreEvento) {
        return false;
    }

    /**
     *
     * @param fecha
     * @return
     */
    public EventosSociales buscarEventos(Date fecha) {
        return null;
    }

    /**
     *
     * @param fecha
     * @param nombreCliente
     * @return
     */
    public boolean modificarEvento(Date fecha, String nombreCliente) {
        return false;
    }

    private int devuelveIdServicio(String serv) throws SQLException {
        Statement sentencia = Conexion.createStatement();
        ResultSet busqueda = sentencia.executeQuery("SELECT * FROM charmingstudio.servicios WHERE Nombre ='" + serv + "'");
        busqueda.next();

        return busqueda.getInt(1);
    }

    private int devuelveIdProveedor(String prov) throws SQLException {
        Statement sentencia = Conexion.createStatement();
        ResultSet busqueda = sentencia.executeQuery("SELECT * FROM charmingstudio.proveedor WHERE Nombre ='" + prov + "'");
        busqueda.next();

        return busqueda.getInt(1);
    }

    public float calculaPrecioTotal(EventosSociales evento) throws SQLException {
        float precioTotal = 0;

        precioTotal = precioTotal + evento.getPrecioTotal();



        return precioTotal;
    }
/*
    public void agregaArma(int idpaq, LinkedList idprovs, LinkedList idservs) throws SQLException {
        Statement sentenciaAgregaArma = Conexion.createStatement();
        for (int i = 0; i < idprovs.size(); i++) {
            if (!existeCombinacion(idpaq, (Integer) idprovs.get(i), (Integer) idprovs.get(i))) {
                sentenciaAgregaArma.executeUpdate("INSERT INTO charmingstudio.arma "
                        + "(`Paquetes_idPaquetes`, `Provee_idProveedor`,`Provee_idServicios` )" + "VALUES("
                        + "'" + idpaq + "',"
                        + "'" + idprovs.get(i) + "',"
                        + "'" + idservs.get(i) + "')");
            }
        }

    }
    * */
/*
    private boolean existeCombinacion(int a, int b, int c) throws SQLException {

        Statement sentencia = Conexion.createStatement();
        ResultSet busqueda = sentencia.executeQuery("SELECT * FROM charmingstudio.arma WHERE Paquetes_idPaquetes='"
                + a + "' AND Provee_idProveedor='" + b + "' AND Provee_idServicios='" + c + "'");
        //busqueda.next();
        boolean existeComb = true;
        if (busqueda.wasNull()) {
            existeComb = false;


        }
        /*el else fue considerado, pero no es usado.

        return existeComb;
    }
*/
}
