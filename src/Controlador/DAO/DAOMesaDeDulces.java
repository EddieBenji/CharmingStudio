package Controlador.DAO;

import Modelo.Cliente;
import Modelo.MesaDeDulces;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * @author Lalo
 * @version 1.0
 * @created 19-sep-2014 02:36:30 p.m.
 */
public class DAOMesaDeDulces {

    Connection Conexion;

    public DAOMesaDeDulces() {
        try {
            Conexion = ConexionBaseDatos.getInstancia().getConexionBD();
        } catch (SQLException ex) {
            System.out.println("No hay conexion");
        }
    }

    /**
     *
     * @param mesaDulces
     * @return
     * @throws java.sql.SQLException
     */
    public boolean agregarMD(MesaDeDulces mesaDulces) throws SQLException {

        Statement sentAgregaMesaDeDulces = Conexion.createStatement();
        boolean seAgregoMesaDeDulces = false;
        sentAgregaMesaDeDulces.executeUpdate("INSERT INTO charmingstudio.mesadulces (`Nombre`, `Costo`)" + "VALUES("
                + "'" + mesaDulces.getNombreDeMesa() + "',"
                + "'" + mesaDulces.getPrecio() + "')");

        seAgregoMesaDeDulces = true;
        return seAgregoMesaDeDulces;

    }

    private boolean existeMesaDeDulces(MesaDeDulces mesaDeDulces) throws SQLException {

        LinkedList<MesaDeDulces> listaDeMesaDeDulces = buscarCoincidencias(mesaDeDulces.getNombreDeMesa());;
        boolean existeMesaDeDulces = false;
        if (listaDeMesaDeDulces != null) {
            for (MesaDeDulces mesaDeDulcesEnBD : listaDeMesaDeDulces) {

                if (compararMesaDeDulces(mesaDeDulcesEnBD, mesaDeDulces)) {
                    //si se cumple, entonces encontramos una coincidencia:
                    existeMesaDeDulces = true;
                    //rompemos el ciclo en caso de que haya más de un cliente
                    //con los mismos datos:
                    break;
                }
            }
        }/*el else fue considerado, pero no es usado.*/

        return existeMesaDeDulces;
    }

    private boolean compararMesaDeDulces(MesaDeDulces mesaDeDulcesEncontradoEnBD,
            MesaDeDulces mesaDeDulcesA_modificar) {
        //primero obtenemos ambos nombres:
        String nombreMesaDeDulcesEncontradoEnBD = mesaDeDulcesEncontradoEnBD.getNombreDeMesa();
        String nombreMesaDeDulcesA_modificar = mesaDeDulcesA_modificar.getNombreDeMesa();
        //comparamos los nombres:
        if (nombreMesaDeDulcesEncontradoEnBD.equalsIgnoreCase(nombreMesaDeDulcesA_modificar)) {
            return true;
        } else {
            //el else fue considerado, pero no es necesario.
        }

        //obtenemos los precio:
        float precioMesaDeDulcesEncontradoEnBD = mesaDeDulcesEncontradoEnBD.getPrecio();
        float precioMesaDeDulcesA_modificar = mesaDeDulcesA_modificar.getPrecio();
        if (precioMesaDeDulcesEncontradoEnBD == precioMesaDeDulcesA_modificar) {
            return true;
        } else {
            //el else fue considerado, pero no es necesario.
        }
        /*Si llega hasta aquí, entonces los clientes son distintos:*/
        return false;
    }
    private static final int columnaId = 1;
    private static final int columnaNombre = 2;
    private static final int columnaCosto = 3;

    public MesaDeDulces buscarMDPorId(int idMD) throws SQLException {

        Statement sentenciaBuscaMD = Conexion.createStatement();
        ResultSet busquedaMD = sentenciaBuscaMD.executeQuery("SELECT * FROM "
                + "charmingstudio.mesadulces WHERE idMesaDulces ='" + idMD + "'");
        busquedaMD.next();

        MesaDeDulces unaMD = new MesaDeDulces(busquedaMD.getInt(columnaId),
                busquedaMD.getString(columnaNombre),
                busquedaMD.getFloat(columnaCosto));
        /*
         LinkedList<Servicio> servicios = encontrarServiciosDelProveedor(unProveedor.getIdPersona());
         unProveedor.setServiciosQueProvee(servicios);
         */
        return unaMD;
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

        sentEliminaMesaDeDulces.executeUpdate("DELETE FROM charmingstudio.mesadulces"
                + " WHERE idMesaDulces= '" + idMesaDeDulces + "'");
        seEliminoMesaDeDulces = true;

        return seEliminoMesaDeDulces;

    }


    public LinkedList buscarCoincidencias(String nombreMesaDeDulces) throws SQLException {

        Statement sentenciaDeBusquedaDeMesaDeDulces = Conexion.createStatement();
        ResultSet BusquedaDeMesaDeDulces = sentenciaDeBusquedaDeMesaDeDulces.executeQuery("SELECT * "
                + "FROM charmingstudio.mesadulces WHERE Nombre LIKE '%" + nombreMesaDeDulces + "%'");
        if (BusquedaDeMesaDeDulces.wasNull()) {
            return null;
        }
        //creamos la lista:
        LinkedList<MesaDeDulces> mesaDeDulces = new LinkedList<>();
        MesaDeDulces unaMesa;
        while (BusquedaDeMesaDeDulces.next()) {
            //agregamos c/cliente a la lista:
            unaMesa = new MesaDeDulces(
                    BusquedaDeMesaDeDulces.getInt(columnaId),
                    BusquedaDeMesaDeDulces.getString(columnaNombre),
                    BusquedaDeMesaDeDulces.getFloat(columnaCosto));

            mesaDeDulces.add(unaMesa);

        }
        return mesaDeDulces;

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
     * Modificará la información de la mesa de dulces en la BD.
     *
     * @param mesaDeDulcesAModificar, es el nuevo objeto de mesa de dulces que
     * tiene la información actualizada.
     * @return verdadero o falso, dependiendo de si se pudo o no, actualizar la
     * BD.
     * @throws SQLException
     */
    public boolean modificarMD(MesaDeDulces mesaDeDulcesAModificar) throws SQLException {

        //en caso de que no haya el usuario en la BD. 
        Statement sentenciaDeActualizacionDeMesaDeDulces = Conexion.createStatement();
        int actualizaInfoMesaDeDulces = sentenciaDeActualizacionDeMesaDeDulces.executeUpdate("UPDATE charmingstudio.mesadulces "
                + "SET `Nombre` = '" + mesaDeDulcesAModificar.getNombreDeMesa() + "'"
                + ",`Costo` = '" + mesaDeDulcesAModificar.getPrecio()
                + "' WHERE `idMesaDulces`='" + mesaDeDulcesAModificar.getIdMesaDulces() + "'");

        boolean sePudoModificarInfoMesaDulces = false;
        if (actualizaInfoMesaDeDulces != 0) {
            sePudoModificarInfoMesaDulces = true;
        }

        //devuelve si se pudo o no, modificar el cliente:
        return sePudoModificarInfoMesaDulces;
    }

    public LinkedList buscarTodasMD() throws SQLException {

        Statement sentenciaDeBusquedaDeMD = Conexion.createStatement();
        ResultSet BusquedaDeMD = sentenciaDeBusquedaDeMD.
                executeQuery("SELECT * FROM charmingstudio.mesadulces");

        if (BusquedaDeMD.wasNull()) {
            return null;
        }

        LinkedList<MesaDeDulces> mesas = new LinkedList<>();

        while (BusquedaDeMD.next()) {

            //agregamos c/mesa a la lista:
            mesas.add(new MesaDeDulces(BusquedaDeMD.getInt(columnaId),
                    BusquedaDeMD.getString(columnaNombre),
                    BusquedaDeMD.getFloat(columnaCosto)));

        }
        return mesas;

    }

    /**
     * Método que se encarga de buscar una mesa en la BD, que coincida con el
     * nombre que se especifica. Si hay 2 mesas con el nombre igual (pero
     * diferente ID), se devuelve la primera que se haya registrado en la BD.
     *
     * @param nombreMesa, es el nombre de la mesa a buscar en formato de String.
     * @return la mesa que se encontró en la BD. Si no hay coincidencias,
     * devuelve nulo.
     * @throws SQLException
     */
    public MesaDeDulces buscarMesaPorNombre(String nombreMesa) throws SQLException {
        Statement sentenciaBuscaMD = Conexion.createStatement();
        ResultSet busquedaMD = sentenciaBuscaMD.executeQuery("SELECT * FROM "
                + "charmingstudio.mesadulces WHERE Nombre ='" + nombreMesa + "'");
        if (busquedaMD.wasNull()) {
            return null;
        }

        busquedaMD.next();

        MesaDeDulces mesa = new MesaDeDulces(busquedaMD.getInt(columnaId),
                busquedaMD.getString(columnaNombre),
                busquedaMD.getFloat(columnaCosto));

        return mesa;
    }
}
