package Controlador.DAO;

import Modelo.Persona;
import Modelo.Proveedor;
import Modelo.Servicio;
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
 * @created 19-sep-2014 02:34:26 p.m.
 */
public class DAOProveedores extends GestorBD {

    Connection Conexion;

    public DAOProveedores() {
        try {
            Conexion = ConexionBaseDatos.getInstancia().getConexionBD();
            System.out.println("Se conecto");
        } catch (SQLException ex) {
            System.out.println("No hay conexion");
            Logger.getLogger(DAOClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param persona
     * @return
     * @throws java.sql.SQLException
     */
    @Override
    public boolean agregar(Persona persona) throws SQLException {
        
        boolean seAgregoProveedor = false;
        Proveedor proveedor = (Proveedor) persona;

        if (!existeUsuario(proveedor)) {

            agregarProveedorA_suTabla(proveedor);

            int idProveedor = encontrarIdDeProveedor(proveedor.getNombrePersona());

            LinkedList<Servicio> serviciosProveidos = proveedor.getServiciosQueProvee();
            int claveServ = 0;
            Statement sentenciaAgregaProveedor = Conexion.createStatement();
            for (Servicio servicio : serviciosProveidos) {
                claveServ = servicio.getId();
                sentenciaAgregaProveedor.executeUpdate("INSERT INTO charmingstudio.provee "
                        + "(`idProveedor`, `idServicios`)" + "VALUES("
                        + "'" + idProveedor + "',"
                        + "'" + claveServ + "')");
            }
            seAgregoProveedor = true;
        }//fin if

        return seAgregoProveedor;

    }

    private void agregarProveedorA_suTabla(Proveedor proveedor) throws SQLException {
        Statement sentenciaAgregaProveedor = Conexion.createStatement();
        sentenciaAgregaProveedor.executeUpdate("INSERT INTO charmingstudio.proveedor "
                + "(`Nombre`, `Direccion`, `Telefono`, `Correo`)" + "VALUES("
                + "'" + proveedor.getNombrePersona() + "',"
                + "'" + proveedor.getDireccionPersona() + "',"
                + "'" + proveedor.getTelefonoPersona() + "',"
                + "'" + proveedor.getCorreoPersona() + "')");
    }

    private int encontrarIdDeProveedor(String nombreProveedor) throws SQLException {
        //Ahora encontramos el id del proveedor, que acabamos de insertar en la BD.
        Statement sentenciaBuscaIdProveedor = Conexion.createStatement();
        ResultSet idProveedor = sentenciaBuscaIdProveedor.executeQuery("SELECT idProveedor "
                + "FROM charmingstudio.proveedor WHERE"
                + " Nombre='" + nombreProveedor + "'");
        idProveedor.next();
        return idProveedor.getInt(1);
    }

    private boolean existeUsuario(Proveedor proveedor) throws SQLException {

        LinkedList<Proveedor> listaDeProveedores = buscarCoincidencias(proveedor.getNombrePersona());
        boolean existeUsuario = false;
        if (listaDeProveedores != null) {
            for (Proveedor proveedorEnBD : listaDeProveedores) {
                if (compararProveedores(proveedorEnBD, proveedor)) {
                    //si se cumple, entonces encontramos una coincidencia:
                    existeUsuario = true;
                    //rompemos el ciclo en caso de que haya más de un cliente
                    //con los mismos datos:
                    break;
                } else {
                    /*el else fue considerado, pero no es usado.*/
                }
            }//fin for
        } else {
            /*el else fue considerado, pero no es usado.*/
        }

        return existeUsuario;
    }

    private boolean compararProveedores(Proveedor proveedorEncontradoEnBD, Proveedor proveedorA_modificar) {
        //primero obtenemos ambos nombres:
        String nombreProveedorEncontradoEnBD = proveedorEncontradoEnBD.getNombrePersona();
        String nombreProveedorA_modificar = proveedorA_modificar.getNombrePersona();
        //comparamos los nombres:
        if (nombreProveedorEncontradoEnBD.equalsIgnoreCase(nombreProveedorA_modificar)) {
            return true;
        } else {
            //el else fue considerado, pero no es necesario.
        }

        //obtenemos las direcciones:
        String direccionProveedorEncontradoEnBD = proveedorEncontradoEnBD.getDireccionPersona();
        String direccionProveedorA_modificar = proveedorA_modificar.getDireccionPersona();
        //comparamos las direcciones:
        if (direccionProveedorEncontradoEnBD.equalsIgnoreCase(direccionProveedorA_modificar)) {
            return true;
        } else {
            //el else fue considerado, pero no es necesario.
        }

        //obtenemos los teléfonos:
        String telefonoProveedorEncontradoEnBD = proveedorEncontradoEnBD.getTelefonoPersona();
        String telefonoProveedorA_modificar = proveedorA_modificar.getTelefonoPersona();
        if (telefonoProveedorEncontradoEnBD.equalsIgnoreCase(telefonoProveedorA_modificar)) {
            return true;
        } else {
            //el else fue considerado, pero no es necesario.
        }

        //obtenemos los correos::
        String correoProveedorEncontradoEnBD = proveedorEncontradoEnBD.getCorreoPersona();
        String correoProveedorA_modificar = proveedorA_modificar.getCorreoPersona();
        if (correoProveedorEncontradoEnBD.equalsIgnoreCase(correoProveedorA_modificar)) {
            return true;
        } else {
            //el else fue considerado, pero no es necesario.
        }

        /*Si llega hasta aquí, entonces los clientes son distintos:*/
        return false;
    }

    /**
     *
     * @param idProveedor
     * @return
     */
    @Override
    public boolean eliminar(int idProveedor) throws SQLException {
        boolean seEliminoProveedor = false;
        Statement sentenciaEliminaProveedor = Conexion.createStatement();
        Statement sentenciaEliminaServicios = Conexion.createStatement();

        sentenciaEliminaServicios.executeUpdate("DELETE FROM "
                + "charmingstudio.provee WHERE idProveedor = '" + idProveedor + "'");
        sentenciaEliminaProveedor.executeUpdate("DELETE FROM "
                + "charmingstudio.proveedor WHERE idProveedor= '" + idProveedor + "'");

        seEliminoProveedor = true;

        return seEliminoProveedor;
    }

    /**
     *
     * @param nombrePersona
     * @return
     * @throws java.sql.SQLException
     */
    @Override
    public LinkedList buscarCoincidencias(String nombrePersona) throws SQLException {

        Statement sentenciaDeBusquedaDeProveedores = Conexion.createStatement();
        ResultSet BusquedaDeProveedores = sentenciaDeBusquedaDeProveedores.executeQuery("SELECT * "
                + "FROM charmingstudio.proveedor WHERE Nombre LIKE '%" + nombrePersona + "%'");
        if (!BusquedaDeProveedores.wasNull()) {
            //creamos la lista:
            LinkedList<Proveedor> proveedores = new LinkedList<>();
            int i = 0;
            while (BusquedaDeProveedores.next()) {
                //agregamos c/empleado a la lista:
                proveedores.add(new Proveedor(BusquedaDeProveedores.getInt(1),
                        BusquedaDeProveedores.getString(2),
                        BusquedaDeProveedores.getString(3),
                        BusquedaDeProveedores.getString(4),
                        BusquedaDeProveedores.getString(5)));
                proveedores.get(i).setServiciosQueProvee(serviciosDelProveedor(BusquedaDeProveedores.getInt(1)));
                i++;
            }//fin while

            return proveedores;
        } else {
            //el else fue considerado, pero no es necesario.
        }
        mostrarMensaje("El proveedor no se encuentra en la BD");
        return null;
    }

    public LinkedList serviciosDelProveedor(int claveProveedor) throws SQLException {
        LinkedList<Servicio> servicios = new LinkedList<>();

        Statement sentenciaBuscaIdServicios = Conexion.createStatement();
        ResultSet BusquedaIdServicios = sentenciaBuscaIdServicios.executeQuery("SELECT * FROM"
                + " charmingstudio.provee WHERE idProveedor = '" + claveProveedor + "'");

        if (!BusquedaIdServicios.wasNull()) {
            Servicio servTemporal;
            while (BusquedaIdServicios.next()) {
                servTemporal = actualizarInfoServicios(BusquedaIdServicios.getInt(2));
                servicios.add(servTemporal);
            }//fin while
            return servicios;
        }//fin if
        return null;
    }

    private Servicio actualizarInfoServicios(int idServicio) throws SQLException {
        DAOServicios dao = new DAOServicios();
        return dao.encontrarServicio(idServicio);
    }

    /**
     *
     * @param Persona
     * @return
     */
    @Override
    public boolean modificar(Persona Persona) throws SQLException {
        //el parámetro solo es de entrada:
        Proveedor proveedorA_modificar = (Proveedor) Persona;

        //en caso de que no haya el usuario en la BD. 
        Statement sentenciaDeActualizacionDeProveedor = Conexion.createStatement();
        Statement sentenciaBorraPrecios = Conexion.createStatement();
        Statement sentenciaActualizaPrecios = Conexion.createStatement();
        int actualizaInfoProveedor
                = sentenciaDeActualizacionDeProveedor.executeUpdate("UPDATE charmingstudio.proveedor "
                        + "SET `Nombre` = '" + proveedorA_modificar.getNombrePersona() + "'"
                        + ",`Direccion` = '" + proveedorA_modificar.getDireccionPersona() + "'"
                        + ",`Telefono` = '" + proveedorA_modificar.getTelefonoPersona() + "'"
                        + ",`Correo`= '" + proveedorA_modificar.getCorreoPersona()
                        + "' WHERE `idProveedor`='" + proveedorA_modificar.getIdPersona() + "'");

        sentenciaBorraPrecios.executeUpdate("DELETE FROM charmingstudio.provee WHERE idProveedor = '"
                + proveedorA_modificar.getIdPersona() + "'");

        /*
         for (int i = 0; i < proveedorA_modificar.getServiciosQueProvee().length; i++) {
         int claveServicio=proveedorA_modificar.getServiciosQueProvee()[i].getId();
         sentenciaActualizaPrecios.executeUpdate("INSERT INTO charmingstudio.provee "+
         "(`idProveedor`, `idServicios`, `Costo`)" + "VALUES("+
         "'" + proveedorA_modificar.getIdPersona() + "',"+
         "'" + claveServicio + "',"+
         "'" + proveedorA_modificar.getServiciosQueProvee()[i].getCosto() + "')");
        
         }
         */
        boolean sePudoModificarInfoProveedor = false;
        if (actualizaInfoProveedor != 0) {
            sePudoModificarInfoProveedor = true;
        }

        //devuelve si se pudo o no, modificar el cliente:
        return sePudoModificarInfoProveedor;
    }

    private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }

    public Proveedor buscarEspecificamente(String nombreProveedor) throws SQLException {
        Statement sentenciaBuscaProveedor = Conexion.createStatement();
        ResultSet busquedaProveedor = sentenciaBuscaProveedor.executeQuery("SELECT * FROM "
                + "charmingstudio.proveedor WHERE Nombre ='" + nombreProveedor + "'");
        busquedaProveedor.next();

        Proveedor proveedor = new Proveedor(busquedaProveedor.getInt(1),
                busquedaProveedor.getString(2),
                busquedaProveedor.getString(3),
                busquedaProveedor.getString(4),
                busquedaProveedor.getString(5));

        return proveedor;
    }

    public LinkedList proveedoresDelServicio(String servicio) throws SQLException {
        DAOServicios ctrlServicio = new DAOServicios();
        LinkedList<Proveedor> proveedores = new LinkedList();
        LinkedList<Integer> clavesDeProveedores = new LinkedList();
        LinkedList<String> nombresDeProvs = new LinkedList();

        int claveServicio = ctrlServicio.devuelveServicio(servicio).getId();

        Statement sentenciaBuscaServicios = Conexion.createStatement();
        Statement sentenciaBuscaNombresServ = Conexion.createStatement();

        ResultSet BusquedaIdProvs
                = sentenciaBuscaServicios.executeQuery("SELECT * FROM "
                        + "charmingstudio.provee WHERE idServicios = '" + claveServicio + "'");

        if (!BusquedaIdProvs.wasNull()) {
            while (BusquedaIdProvs.next()) {
                clavesDeProveedores.add(BusquedaIdProvs.getInt(1));
            }//fin while
        }//fin if

        for (Integer llave : clavesDeProveedores) {
            ResultSet BusquedaNombresDeProvs = sentenciaBuscaNombresServ.executeQuery("SELECT * FROM "
                    + "charmingstudio.proveedor WHERE idProveedor = '" + llave + "'");
            while (BusquedaNombresDeProvs.next()) {
                nombresDeProvs.add(BusquedaNombresDeProvs.getString(2));
            }//fin while
        }//fin for

        for (String nombreProveedor : nombresDeProvs) {
            proveedores.add(new Proveedor(0, nombreProveedor, "", "", ""));
        }

        return proveedores;
    }
}
