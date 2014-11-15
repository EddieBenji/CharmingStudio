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
            
            agregarPreciosDeServicioDelProveedor(proveedor.getServiciosQueProvee(), idProveedor);
            
            seAgregoProveedor = true;
        }//fin if

        return seAgregoProveedor;
        
    }
    
    private void agregarProveedorA_suTabla(Proveedor proveedor) throws SQLException {
        /*INSERT INTO charmingstudio.proveedor (`Nombre`, `Direccion`, `Telefono`, `Correo`) 
         VALUES ('Eduardo', 'Vergel 2', '9831170', 'eduardo@hotmail.com')*/
        Statement sentenciaAgregaProveedor = Conexion.createStatement();
        sentenciaAgregaProveedor.executeUpdate("INSERT INTO charmingstudio.proveedor "
                + "(`Nombre`, `Direccion`, `Telefono`, `Correo`)" + "VALUES("
                + "'" + proveedor.getNombrePersona() + "',"
                + "'" + proveedor.getDireccionPersona() + "',"
                + "'" + proveedor.getTelefonoPersona() + "',"
                + "'" + proveedor.getCorreoPersona() + "')");
    }
    
    private void agregarPreciosDeServicioDelProveedor(LinkedList<Servicio> serviciosProveidos, int idProveedor) {
        try {
            int claveServ = 0;
            float costoServ = 0;
            Statement sentenciaAgregaProveedor = Conexion.createStatement();
            for (Servicio servicio : serviciosProveidos) {
                claveServ = servicio.getId();
                costoServ = servicio.getCosto();
                sentenciaAgregaProveedor.executeUpdate("INSERT INTO charmingstudio.provee "
                        + "(`idProveedor`, `idServicios`, `costo`)" + "VALUES("
                        + "'" + idProveedor + "',"
                        + "'" + claveServ + "',"
                        + "'" + costoServ + "')");
            }
        } catch (SQLException ex) {
            
        }
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
    
    public Proveedor obtenerProveedor(Proveedor prov) {
        return null;
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
                proveedores.get(i).setServiciosQueProvee(encontrarServiciosDelProveedor(BusquedaDeProveedores.getInt(1)));
                i++;
            }//fin while

            return proveedores;
        } else {
            //el else fue considerado, pero no es necesario.
        }
        return null;
    }

    /**
     * Este método se encarga de encontrar y devolver los servicios de un
     * proveedor, a partir de la clave del proveedor.
     *
     * @param claveProveedor, única que se encunetra en la BD.
     * @return
     * @throws SQLException
     */
    public LinkedList encontrarServiciosDelProveedor(int claveProveedor) throws SQLException {
        LinkedList<Servicio> servicios = new LinkedList<>();
        
        Statement sentenciaBuscaIdServicios = Conexion.createStatement();
        ResultSet BusquedaIdServicios = sentenciaBuscaIdServicios.executeQuery("SELECT * FROM"
                + " charmingstudio.provee WHERE idProveedor = '" + claveProveedor + "'");
        
        if (!BusquedaIdServicios.wasNull()) {
            Servicio servicio;
            while (BusquedaIdServicios.next()) {
                //Ya tenemos el id, ahora necesitamos la info de la bd.
                servicio = encontrarServicioPorID(BusquedaIdServicios.getInt(2));
                servicio.setCosto(BusquedaIdServicios.getFloat(3));
                servicios.add(servicio);
            }//fin while
            return servicios;
        }//fin if
        return null;
    }

    /**
     * Este método se encargará de buscar en la base de datos, toda la
     * información del servicio que corresponda al id que se le pase.
     *
     * @param idServicio, es el ID del servicio que se quiere buscar en la BD.
     */
    DAOServicios dao = new DAOServicios();
    
    private Servicio encontrarServicioPorID(int idServicio) throws SQLException {
        
        return dao.encontrarServicioPorID(idServicio);
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
        
        actualizarPreciosDeProveedor(proveedorA_modificar.getServiciosQueProvee(),
                proveedorA_modificar.getIdPersona());
        
        int actualizaInfoProveedor
                = sentenciaDeActualizacionDeProveedor.executeUpdate("UPDATE charmingstudio.proveedor "
                        + "SET `Nombre` = '" + proveedorA_modificar.getNombrePersona() + "'"
                        + ",`Direccion` = '" + proveedorA_modificar.getDireccionPersona() + "'"
                        + ",`Telefono` = '" + proveedorA_modificar.getTelefonoPersona() + "'"
                        + ",`Correo`= '" + proveedorA_modificar.getCorreoPersona()
                        + "' WHERE `idProveedor`='" + proveedorA_modificar.getIdPersona() + "'");
        
        boolean sePudoModificarInfoProveedor = false;
        if (actualizaInfoProveedor != 0) {
            sePudoModificarInfoProveedor = true;
        }

        //devuelve si se pudo o no, modificar el cliente:
        return sePudoModificarInfoProveedor;
    }
    
    private void actualizarPreciosDeProveedor(LinkedList<Servicio> servicios, int idProveedor) {
        //Para eso, primero eliminamos los precios que aparecen en la interrelación:
        eliminarPreciosDeProveedor(idProveedor);
        //Posteriormente los agregamos, con los nuevos precios e incluso
        //con los nuevos servicios (si es que se agregaron).
        agregarPreciosDeServicioDelProveedor(servicios, idProveedor);
        
    }
    
    private void eliminarPreciosDeProveedor(int idProveedor) {
        try {
            Statement sentenciaEliminaServicios = Conexion.createStatement();
            sentenciaEliminaServicios.executeUpdate("DELETE FROM "
                    + "charmingstudio.provee WHERE idProveedor = '" + idProveedor + "'");
            
        } catch (SQLException ex) {
            
        }
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
        
        int claveServicio = ctrlServicio.encontrarServicioPorNombre(servicio).getId();
        
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
