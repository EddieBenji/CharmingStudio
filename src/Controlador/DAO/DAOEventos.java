package Controlador.DAO;

import Modelo.EventosSociales;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;

/**
 * @author Lalo
 * @version 1.0
 * @created 19-sep-2014 02:36:23 p.m.
 */
public class DAOEventos {

    Connection Conexion;

    public DAOEventos() {
        try {
            Conexion = ConexionBaseDatos.getInstancia().getConexionBD();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     *
     * @param eventoA_Guardar
     * @throws java.sql.SQLException
     */
    public void agregarEvento(EventosSociales eventoA_Guardar) throws SQLException {

    }

    /**
     * Agrega un nuevo evento a la tabla de eventos.
     *
     * @param idCliente
     * @param idMesasDulces
     * @param Fecha
     * @param PrecioTotal
     * @param idEmpleado
     * @param idPaquetes
     * @param idProveedor
     * @param idServicios
     * @return
     * @throws SQLException
     */
    //claveCliente, claveMesaDulces, fechaEvento,precioEvento, claveEmpleado
    public int agregarElementoA_TablaEventoSocial(int idCliente, int idMesasDulces, String Fecha,
            float PrecioTotal, int idEmpleado) throws SQLException {

        Statement sentenciaDeInsercion = Conexion.createStatement();

        //boolean seAgregoElemento = 
        sentenciaDeInsercion.execute("INSERT INTO charmingstudio.eventos "
                + "(`idCliente`,`idMesaDulces`,`Fecha`,`PrecioTotal`,`idEmpleado`)"
                + "VALUES("
                + "'" + idCliente + "',"
                + "'" + idMesasDulces + "',"
                + "'" + Fecha + "',"
                + "'" + PrecioTotal + "',"
                + "'" + idEmpleado + "')");

        int id = obtenerIdEvento(idCliente, idMesasDulces, PrecioTotal, idEmpleado, Fecha);

        return id;
    }
    private static final int columnaId = 1;
    private static final int columnaIdCliente = 2;
    private static final int columnaIdMD = 3;
    private static final int columnaFecha = 4;
    private static final int columnaPrecioTotal = 5;
    private static final int columnaIdEmpleado = 6;

    public LinkedList buscarTodosLosEventos() throws SQLException {

        Statement sentenciaDeBusquedaDeEventos = Conexion.createStatement();
        ResultSet BusquedaDeEventos = sentenciaDeBusquedaDeEventos.
                executeQuery("SELECT *  FROM charmingstudio.eventos");

        if (BusquedaDeEventos.wasNull()) {
            return null;
        }

        LinkedList<EventosSociales> eventos = new LinkedList<>();

        while (BusquedaDeEventos.next()) {

            //agregamos c/evento a la lista:
            eventos.add(new EventosSociales(BusquedaDeEventos.getInt(columnaId),
                    BusquedaDeEventos.getInt(columnaIdCliente),
                    BusquedaDeEventos.getInt(columnaIdMD),
                    BusquedaDeEventos.getDate(columnaFecha),
                    BusquedaDeEventos.getFloat(columnaPrecioTotal),
                    BusquedaDeEventos.getInt(columnaIdEmpleado)));

        }
        return eventos;

    }

    private int obtenerIdEvento(int idCliente, int idMesasDulces,
            float PrecioTotal, int idEmpleado, String strFecha) throws SQLException {

        Statement sentenciaDeInsercion = Conexion.createStatement();

        ResultSet id = sentenciaDeInsercion.executeQuery("SELECT * FROM charmingstudio.eventos WHERE "
                + "`idCliente` = " + idCliente
                + " AND `idMesaDulces` = " + idMesasDulces
                + " AND `PrecioTotal`= " + PrecioTotal
                + " AND `idEmpleado` = " + idEmpleado
                + " AND `Fecha`= '" + strFecha + "'");
        /**
         * SELECT * FROM charmingstudio.eventos WHERE `idCliente` = 1 AND
         * `idMesaDulces` = 1 AND `PrecioTotal`= 5700 AND `idEmpleado` = 1 AND
         * `Fecha` = '2014-11-27'
         *
         */

        id.next();
        System.out.println("El id es: "+id.getInt("idEvento"));

        return id.getInt("idEvento");
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
