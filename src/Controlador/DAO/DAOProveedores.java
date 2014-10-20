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
            Conexion = BaseDeDatos.getInstanciaConexionDeBaseDatos().getConexionBD();
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

        //Se crean dos para que no creen conflictos entre ellos.
        Statement sentenciaAgregaProveedor = Conexion.createStatement();
        Statement sentenciaBuscaIdProveedor = Conexion.createStatement();
        
        
        if (!existeUsuario(proveedor)) {
            sentenciaAgregaProveedor.executeUpdate("INSERT INTO charmingstudio.proveedor "+
                "(`Nombre`, `Direccion`, `Telefono`, `Correo`)" + "VALUES("+
                "'" + proveedor.getNombrePersona() + "',"+
                "'" + proveedor.getDireccionPersona() + "',"+
                "'" + proveedor.getTelefonoPersona() + "',"+
                "'" + proveedor.getCorreoPersona() + "')");

            ResultSet idProveedor = sentenciaBuscaIdProveedor.executeQuery("SELECT idProveedor "
                    + "FROM charmingstudio.proveedor WHERE"
                    + " Nombre='" + proveedor.getNombrePersona() + "'");
            idProveedor.next();
        
            for (Servicio provServicio : proveedor.getProvServicios()) {
                int idServicio=devuelveIdServicio(provServicio.getServNombre());
                sentenciaAgregaProveedor.executeUpdate("INSERT INTO charmingstudio.provee "+
                    "(`idProveedor`, `idServicios`, `costo`)" + "VALUES("+
                    "'" + idProveedor.getInt(1) + "',"+
                    "'" + idServicio + "',"+
                    "'" + provServicio.getCosto() + "')");
            }//fin for
            
            seAgregoProveedor = true;
        }//fin if
        
        return seAgregoProveedor;

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
                }else{
                    /*el else fue considerado, pero no es usado.*/
                }
            }//fin for
        }else{
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
        
        LinkedList<Proveedor> proveedores = new LinkedList<>();
        LinkedList<Servicio> servicios = new LinkedList<>();
         
        Statement sentenciaDeBusquedaDeProveedores = Conexion.createStatement();
        ResultSet BusquedaDeProveedores = sentenciaDeBusquedaDeProveedores.executeQuery("SELECT * "
                + "FROM charmingstudio.proveedor WHERE Nombre LIKE '%" + nombrePersona + "%'");
        
        if (!BusquedaDeProveedores.wasNull()) {
            //creamos la lista:
            

            while (BusquedaDeProveedores.next()) {
                //agregamos c/empleado a la lista:
                servicios=serviciosDelProveedor(BusquedaDeProveedores.getInt(1));
                Servicio[] ServiciosProveedor = new Servicio[servicios.size()];
                servicios.toArray(ServiciosProveedor);
                proveedores.add(new Proveedor(BusquedaDeProveedores.getInt(1), 
                        BusquedaDeProveedores.getString(2), 
                        BusquedaDeProveedores.getString(3),
                        BusquedaDeProveedores.getString(4), 
                        BusquedaDeProveedores.getString(5),
                        ServiciosProveedor)) ;

            }//fin while
            return proveedores;
        }else{
            //el else fue considerado, pero no es necesario.
        }
        
        mostrarMensaje("El proveedor no se encuentra en la BD");
        return null;
    }
public LinkedList serviciosDelProveedor(int clave) throws SQLException{
        LinkedList<Servicio> servicio = new LinkedList<>();
        LinkedList<Integer> clavesDeServicios=new LinkedList();
        LinkedList<String> nombresDeServicios=new LinkedList();
        
        Statement sentenciaBuscaIdServicios = Conexion.createStatement();
        Statement sentenciaBuscaServicios = Conexion.createStatement();
        Statement sentenciaBuscaNombresServ = Conexion.createStatement();
        
        ResultSet BusquedaIdServicios = sentenciaBuscaIdServicios.executeQuery("SELECT * FROM" +
                " charmingstudio.provee WHERE idProveedor = '" + clave + "'");
        
        if (!BusquedaIdServicios.wasNull()) {
            while (BusquedaIdServicios.next()) {
                clavesDeServicios.add(BusquedaIdServicios.getInt(2));
            }//fin while
        }//fin if
        
        ResultSet BusquedaNombresDeServicios=null;
        ResultSet BusquedaServicios = sentenciaBuscaServicios.executeQuery("SELECT * FROM "+
                "charmingstudio.provee WHERE idProveedor = '" + clave + "'");
        for(Integer llave : clavesDeServicios){
           BusquedaNombresDeServicios = sentenciaBuscaNombresServ.executeQuery("SELECT * FROM " +
                   "charmingstudio.servicios WHERE idServicios = '" + llave + "'");
           while (BusquedaNombresDeServicios.next()) {
           nombresDeServicios.add(BusquedaNombresDeServicios.getString(2));
           }//fin while
        }//fin for
        if (!BusquedaNombresDeServicios.wasNull()) {
            BusquedaNombresDeServicios.next();
            int i=0;
            while (BusquedaServicios.next()) {
                servicio.add(new Servicio(nombresDeServicios.get(i),BusquedaServicios.getFloat("Costo")));
                i++;
            }//fin while
            
            return servicio;
        }else{
            
        }
        
        //si llega aqui, no tiene servicios
        return null;
    }
    
    /**
     *
     * @param Persona
     * @return 
     */
    @Override
    public boolean modificar( Persona Persona) throws SQLException {
        //el parámetro solo es de entrada:
        Proveedor proveedorA_modificar = (Proveedor) Persona;

        //en caso de que no haya el usuario en la BD. 
        Statement sentenciaDeActualizacionDeProveedor = Conexion.createStatement();
        Statement sentenciaDeActualizacionDePrecios = Conexion.createStatement();
        Statement sent=Conexion.createStatement();
        Statement sent2=Conexion.createStatement();
        int actualizaInfoProveedor
                = sentenciaDeActualizacionDeProveedor.executeUpdate("UPDATE charmingstudio.proveedor "
                        + "SET `Nombre` = '" + proveedorA_modificar.getNombrePersona() + "'"
                        + ",`Direccion` = '" + proveedorA_modificar.getDireccionPersona() + "'"
                        + ",`Telefono` = '" + proveedorA_modificar.getTelefonoPersona() + "'"
                        + ",`Correo`= '" + proveedorA_modificar.getCorreoPersona()
                        + "' WHERE `idProveedor`='" + proveedorA_modificar.getIdPersona() + "'");

        
        sent.executeUpdate("DELETE FROM charmingstudio.provee WHERE idProveedor = '" + 
                proveedorA_modificar.getIdPersona() + "'");
        
        for (int i = 0; i < proveedorA_modificar.getProvServicios().length; i++) {
            
        
        //ResultSet Busqueda3 = sent.executeQuery("SELECT * FROM charmingstudio.provee WHERE idProveedor = '" + proveedorA_modificar.getIdPersona() + "'");
        sent2.executeUpdate("INSERT INTO charmingstudio.provee "+
                    "(`idProveedor`, `idServicios`, `costo`)" + "VALUES("+
                    "'" + proveedorA_modificar.getIdPersona() + "',"+
                    "'" + devuelveIdServicio(proveedorA_modificar.getProvServicios()[i].getServNombre()) + "',"+
                    "'" + proveedorA_modificar.getProvServicios()[i].getCosto() + "')");
        
        }
        
        boolean sePudoModificarInfoProveedor = false;
        if (actualizaInfoProveedor != 0) {
            sePudoModificarInfoProveedor = true;
        }

        //devuelve si se pudo o no, modificar el cliente:
        return sePudoModificarInfoProveedor;
    }
    
    private void mostrarMensaje(String mensaje){
        JOptionPane.showMessageDialog(null, mensaje);
    }

    private int devuelveIdServicio(String serv) throws SQLException{
        Statement sentencia=Conexion.createStatement();
        ResultSet busqueda=sentencia.executeQuery("SELECT * FROM charmingstudio.servicios WHERE Nombre ='"+serv+"'");
        busqueda.next();
        
        return busqueda.getInt(1);
    }
    
    public LinkedList proveedoresDelServicio(String servicio) throws SQLException{
        LinkedList<Proveedor> proveedores=new LinkedList();
         LinkedList<Integer> clavesDEProvs=new LinkedList();
        LinkedList<String> nombresDeProvs=new LinkedList();
        
            int claveServicio=devuelveIdServicio(servicio);
            
            Statement sentenciaBuscaIdServicios = Conexion.createStatement();
        Statement sentenciaBuscaServicios = Conexion.createStatement();
        Statement sentenciaBuscaNombresServ = Conexion.createStatement();
            
        ResultSet BusquedaIDProvs
                = sentenciaBuscaServicios.executeQuery("SELECT * FROM "+
                "charmingstudio.provee WHERE idServicios = '" + claveServicio + "'");
        
        if (!BusquedaIDProvs.wasNull()) {
            while (BusquedaIDProvs.next()) {
                clavesDEProvs.add(BusquedaIDProvs.getInt(1));
            }//fin while
        }//fin if
        for(Integer llave : clavesDEProvs){
           ResultSet BusquedaNombresDeProvs = sentenciaBuscaNombresServ.executeQuery("SELECT * FROM " +
                   "charmingstudio.proveedor WHERE idProveedor = '" + llave + "'");
           while (BusquedaNombresDeProvs.next()) {
           nombresDeProvs.add(BusquedaNombresDeProvs.getString(2));
           }//fin while
        }//fin for
        for(String nom : nombresDeProvs){
            Servicio[]servs=new Servicio[0];
            proveedores.add(new Proveedor(0,nom,"","","",servs));
        }
        
        /*
        ResultSet BusquedaNombresDeProvs=null;
        ResultSet BusquedaServicios = sentenciaBuscaServicios.executeQuery("SELECT * FROM "+
                "charmingstudio.provee WHERE idServicios = '" + claveServicio + "'");
        for(Integer llave : clavesDEProvs){
           BusquedaNombresDeProvs = sentenciaBuscaNombresServ.executeQuery("SELECT * FROM " +
                   "charmingstudio.proveedor WHERE idProveedor = '" + llave + "'");
           while (BusquedaNombresDeProvs.next()) {
           nombresDeProvs.add(BusquedaNombresDeProvs.getString(2));
           }//fin while
        }//fin for
        if (!BusquedaNombresDeProvs.wasNull()) {
            BusquedaNombresDeProvs.next();
            int i=0;
            Servicio[]servs=new Servicio[0];
            while (BusquedaServicios.next()) {
                proveedores.add(new Proveedor(0,BusquedaServicios.getString(2),"","","",servs));
                i++;
            }//fin while
            * */
          return proveedores;  
        }
         
        
    }
    
    



