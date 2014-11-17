package Controlador.DAO;

import Modelo.EventosSociales;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
    public boolean agregarElemento(int idCliente, int idMesasDulces, Calendar Fecha,
            float PrecioTotal, int idEmpleado, int idPaquetes,
            int idProveedor, int idServicios) throws SQLException {

        Statement sentenciaDeInsercion = Conexion.createStatement();
        String strFecha = obtenerFecha(Fecha);
        
        boolean seAgregoElemento = sentenciaDeInsercion.execute("INSERT INTO charmingstudio.eventos "
                + "(`idCliente`,`idMesaDulces`,`Fecha`,`PrecioTotal`,`idEmpleado`, "
                + "`idPaquetes`,`idProveedor`,`idServicios`)"
                + "VALUES("
                + "'" + idCliente + "',"
                + "'" + idMesasDulces + "',"
                + "'" + strFecha + "',"
                + "'" + PrecioTotal + "',"
                + "'" + idEmpleado + "',"
                + "'" + idPaquetes + "',"
                + "'" + idProveedor + "',"
                + "'" + idServicios + "')");

        return seAgregoElemento;

    }

    private String obtenerFecha(Calendar fecha) {

        String dia = String.valueOf(fecha.get(Calendar.DAY_OF_MONTH));
        String mes = String.valueOf(fecha.get(Calendar.MONTH));
        String anio = String.valueOf(fecha.get(Calendar.YEAR));

        return anio + "-" + mes + "-" + dia;
    }

    public static void main(String[] args) {
        DAOEventos d = new DAOEventos();
        Calendar fecha = new GregorianCalendar();
        d.obtenerFecha(fecha);
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
