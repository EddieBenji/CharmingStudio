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
    ConexionBaseDatos BaseDeDatos;

    public DAOMesaDeDulces() {
        try {
            Conexion = ConexionBaseDatos.getInstanciaConexionDeBaseDatos().getConexionBD();
            System.out.println("Se conecto");
        } catch (SQLException ex) {
            System.out.println("No hay conexion");
            Logger.getLogger(DAOClientes.class.getName()).log(Level.SEVERE, null, ex);
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
                + "'" + mesaDulces.getmdNombreDeMesa() + "',"
                + "'" + mesaDulces.getPrecio() + "')");

        seAgregoMesaDeDulces = true;
        return seAgregoMesaDeDulces;

    }

    private boolean existeMesaDeDulces(MesaDeDulces mesaDeDulces) throws SQLException {

        LinkedList<MesaDeDulces> listaDeMesaDeDulces = buscarCoincidencias(mesaDeDulces.getmdNombreDeMesa());;
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
        String nombreMesaDeDulcesEncontradoEnBD = mesaDeDulcesEncontradoEnBD.getmdNombreDeMesa();
        String nombreMesaDeDulcesA_modificar = mesaDeDulcesA_modificar.getmdNombreDeMesa();
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

    /**
     *
     * @param idMesaDeDulces
     * @return
     * @throws java.sql.SQLException
     */
    public boolean eliminarMD(int idMesaDeDulces) throws SQLException {
        boolean seEliminoMesaDeDulces = false;

        Statement sentEliminaMesaDeDulces = Conexion.createStatement();

        sentEliminaMesaDeDulces.executeUpdate("DELETE FROM charmingstudio.mesadulces"+
                " WHERE idMesaDulces= '" + idMesaDeDulces + "'");
        seEliminoMesaDeDulces = true;

        return seEliminoMesaDeDulces;

    }

    public LinkedList buscarCoincidencias(String nombreMesaDeDulces) throws SQLException {

        Statement sentenciaDeBusquedaDeMesaDeDulces = Conexion.createStatement();
        ResultSet BusquedaDeMesaDeDulces = sentenciaDeBusquedaDeMesaDeDulces.executeQuery("SELECT * "+
                "FROM charmingstudio.mesadulces WHERE Nombre LIKE '%" + nombreMesaDeDulces + "%'");
        if (!BusquedaDeMesaDeDulces.wasNull()) {
            //creamos la lista:
            LinkedList<MesaDeDulces> mesaDeDulces = new LinkedList<>();

            while (BusquedaDeMesaDeDulces.next()) {
                //agregamos c/cliente a la lista:

                mesaDeDulces.add(new MesaDeDulces(BusquedaDeMesaDeDulces.getInt(1),
                        BusquedaDeMesaDeDulces.getString(2), 
                        BusquedaDeMesaDeDulces.getFloat(3)));

            }
            return mesaDeDulces;
        }
        mostrarMensaje("El cliente no se encuentra en la BD");
        return null;
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
    public boolean modificarMD(MesaDeDulces mesaDeDulcesAModificar) throws SQLException {

        //en caso de que no haya el usuario en la BD. 
        Statement sentenciaDeActualizacionDeMesaDeDulces = Conexion.createStatement();
        int actualizaInfoMesaDeDulces = sentenciaDeActualizacionDeMesaDeDulces.executeUpdate("UPDATE charmingstudio.mesadulces "
                + "SET `Nombre` = '" + mesaDeDulcesAModificar.getmdNombreDeMesa() + "'"
                + ",`Costo` = '" + mesaDeDulcesAModificar.getPrecio()
                + "' WHERE `idMesaDulces`='" + mesaDeDulcesAModificar.getIdMesaDulces() + "'");

        boolean sePudoModificarInfoMesaDulces = false;
        if (actualizaInfoMesaDeDulces != 0) {
            sePudoModificarInfoMesaDulces = true;
        }

        //devuelve si se pudo o no, modificar el cliente:
        return sePudoModificarInfoMesaDulces;
    }

    private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }
    
    public LinkedList buscarTodasMD() throws SQLException {

        Statement sentenciaDeBusquedaDeMD = Conexion.createStatement();
        ResultSet BusquedaDeMD = sentenciaDeBusquedaDeMD.
                executeQuery("SELECT * FROM charmingstudio.mesadulces");
        
        /*En este caso, se espera que la búsqueda no siempre sea nula, por
        lo que nos interesa el negativo de las sentencia:*/
        if (!BusquedaDeMD.wasNull()) {
            
            LinkedList<MesaDeDulces> mesas = new LinkedList<>();

            while (BusquedaDeMD.next()) {
                
                //agregamos c/cliente a la lista:
                mesas.add(new MesaDeDulces(BusquedaDeMD.getInt(1), 
                        BusquedaDeMD.getString(2), 
                        BusquedaDeMD.getFloat(3)));

            }
            return mesas;
        }
        mostrarMensaje("El cliente no se encuentra en la BD");
        return null;
    }

    public MesaDeDulces buscarEspecificamente(String nombreMesa) throws SQLException {
        Statement sentenciaBuscaMD=Conexion.createStatement();
        ResultSet busquedaMD=sentenciaBuscaMD.executeQuery("SELECT * FROM "+
                "charmingstudio.mesadulces WHERE Nombre ='"+nombreMesa+"'");
        busquedaMD.next();
        
        MesaDeDulces mesa=new MesaDeDulces(busquedaMD.getInt(1),
                busquedaMD.getString(2),
                busquedaMD.getFloat(3));
        
        
        return mesa;
    }
}
