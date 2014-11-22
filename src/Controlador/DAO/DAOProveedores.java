package Controlador.DAO;

import Modelo.Persona;
import Modelo.Proveedor;
import Modelo.Servicio;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

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
            /*En la sentencia anterior, el proveedor se agregó a la BD
             y el SMBD le asignó un ID, por lo que ahora necesitamos 
             encontrar dicho ID:                        */
            int idProveedor = encontrarIdDeProveedor(proveedor.getNombrePersona());
            agregarPreciosDeServicioDelProveedor(proveedor.getServiciosQueProvee(), idProveedor);

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

    private static final int columnaIdProveedor = 1;

    private int encontrarIdDeProveedor(String nombreProveedor) throws SQLException {
        //Ahora encontramos el id del proveedor, que acabamos de insertar en la BD.
        Statement sentenciaBuscaIdProveedor = Conexion.createStatement();
        ResultSet idProveedor = sentenciaBuscaIdProveedor.executeQuery("SELECT idProveedor "
                + "FROM charmingstudio.proveedor WHERE"
                + " Nombre='" + nombreProveedor + "'");
        idProveedor.next();
        return idProveedor.getInt(columnaIdProveedor);
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
        }  //el else fue considerado, pero no es necesario.

        //obtenemos las direcciones:
        String direccionProveedorEncontradoEnBD = proveedorEncontradoEnBD.getDireccionPersona();
        String direccionProveedorA_modificar = proveedorA_modificar.getDireccionPersona();

        //comparamos las direcciones:
        if (direccionProveedorEncontradoEnBD.equalsIgnoreCase(direccionProveedorA_modificar)) {
            return true;
        }  //el else fue considerado, pero no es necesario.

        //obtenemos los teléfonos:
        String telefonoProveedorEncontradoEnBD = proveedorEncontradoEnBD.getTelefonoPersona();
        String telefonoProveedorA_modificar = proveedorA_modificar.getTelefonoPersona();

        if (telefonoProveedorEncontradoEnBD.equalsIgnoreCase(telefonoProveedorA_modificar)) {
            return true;
        }  //el else fue considerado, pero no es necesario.

        //obtenemos los correos::
        String correoProveedorEncontradoEnBD = proveedorEncontradoEnBD.getCorreoPersona();
        String correoProveedorA_modificar = proveedorA_modificar.getCorreoPersona();

        if (correoProveedorEncontradoEnBD.equalsIgnoreCase(correoProveedorA_modificar)) {
            return true;
        } //el else fue considerado, pero no es necesario.

        /*Si llega hasta aquí, entonces los clientes son distintos:*/
        return false;
    }

    /**
     * Elimina a un proveedor de la BD, a partir del ID del proveedor.
     *
     * @param idProveedor
     * @return
     * @throws java.sql.SQLException
     */
    @Override
    public boolean eliminar(int idProveedor) throws SQLException {
        boolean seEliminoProveedor = false;
        Statement sentenciaEliminaProveedor = Conexion.createStatement();

        eliminarPreciosDeProveedor(idProveedor);

        sentenciaEliminaProveedor.executeUpdate("DELETE FROM "
                + "charmingstudio.proveedor WHERE idProveedor= '" + idProveedor + "'");

        seEliminoProveedor = true;

        return seEliminoProveedor;
    }

    private static final int columnaNombre = 2;
    private static final int columnaDireccion = 3;
    private static final int columnaTelefono = 4;
    private static final int columnaCorreo = 5;

    /**
     * Este método devuelve a todos los proveedores que coincidan con el nombre,
     * con sus propios servicios y costos.
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

        if (BusquedaDeProveedores.wasNull()) {
            return null;
        }

        //Como son coincidencias, entonces podrá haber una lista de
        //proveedores:
        LinkedList<Proveedor> proveedores = new LinkedList<>();
        Proveedor unProveedor;
        int idProveedor;
        while (BusquedaDeProveedores.next()) {
            //Creamos el nuevo empleado.
            unProveedor = new Proveedor(BusquedaDeProveedores.getInt(columnaIdProveedor),
                    BusquedaDeProveedores.getString(columnaNombre),
                    BusquedaDeProveedores.getString(columnaDireccion),
                    BusquedaDeProveedores.getString(columnaTelefono),
                    BusquedaDeProveedores.getString(columnaCorreo));

            idProveedor = BusquedaDeProveedores.getInt(columnaIdProveedor);
            unProveedor.setServiciosQueProvee(encontrarServiciosDelProveedor(idProveedor));

            //agregamos c/empleado a la lista:
            proveedores.add(unProveedor);

        }//fin while

        return proveedores;

    }

    /**
     * Este método se encarga de encontrar y devolver los servicios de un
     * proveedor, a partir de la clave del proveedor.
     *
     * @param claveProveedor, única que se encunetra en la BD.
     * @return
     * @throws SQLException
     */
    DAOServicios dao = new DAOServicios();

    public LinkedList encontrarServiciosDelProveedor(int claveProveedor) throws SQLException {

        return dao.encontrarServiciosDelProveedor(claveProveedor);

    }

    /**
     *
     * @param Persona
     * @return
     * @throws java.sql.SQLException
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
        //Para actualizar los precios, primero eliminamos los que aparecen en la interrelación:
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
            ex.printStackTrace();
        }
    }

    public Proveedor buscarPorNombre(String nombreProveedor) throws SQLException {
        Statement sentenciaBuscaProveedor = Conexion.createStatement();
        ResultSet busquedaProveedor = sentenciaBuscaProveedor.executeQuery("SELECT * FROM "
                + "charmingstudio.proveedor WHERE Nombre ='" + nombreProveedor + "'");
        busquedaProveedor.next();

        Proveedor unProveedor = new Proveedor(busquedaProveedor.getInt(columnaIdProveedor),
                busquedaProveedor.getString(columnaNombre),
                busquedaProveedor.getString(columnaDireccion),
                busquedaProveedor.getString(columnaTelefono),
                busquedaProveedor.getString(columnaCorreo));

        LinkedList<Servicio> servicios = encontrarServiciosDelProveedor(unProveedor.getIdPersona());
        unProveedor.setServiciosQueProvee(servicios);

        return unProveedor;
    }

    /**
     * encuentra toda la información asociada a un proveedor, a partir del id
     * que se le pase.
     *
     * @param idProveedor
     * @return
     * @throws SQLException
     */
    public Proveedor buscarProveedorPorId(int idProveedor) throws SQLException {

        Statement sentenciaBuscaProveedor = Conexion.createStatement();
        ResultSet busquedaProveedor = sentenciaBuscaProveedor.executeQuery("SELECT * FROM "
                + "charmingstudio.proveedor WHERE idProveedor ='" + idProveedor + "'");
        busquedaProveedor.next();

        Proveedor unProveedor = new Proveedor(busquedaProveedor.getInt(columnaIdProveedor),
                busquedaProveedor.getString(columnaNombre),
                busquedaProveedor.getString(columnaDireccion),
                busquedaProveedor.getString(columnaTelefono),
                busquedaProveedor.getString(columnaCorreo));
        /*
        LinkedList<Servicio> servicios = encontrarServiciosDelProveedor(unProveedor.getIdPersona());
        unProveedor.setServiciosQueProvee(servicios);
        */
        return unProveedor;
    }

    /**
     * No estoy seguro quien lo usa.
     *
     * @param nombreServicio
     * @return
     * @throws SQLException
     */
    
    private static final int columnaCostoServicio = 3;
    
    public LinkedList obtenerProveedoresDelServicio(String nombreServicio) throws SQLException {
        DAOServicios ctrlServicio = new DAOServicios();
        LinkedList<Proveedor> proveedores = new LinkedList();

        //busco el servicio:
        Servicio servicio = ctrlServicio.encontrarServicioPorNombre(nombreServicio);

        //voy a buscar a los proveedores del servicio:
        Statement sentenciaBuscaServicios = Conexion.createStatement();
        ResultSet BusquedaIdProvs
                = sentenciaBuscaServicios.executeQuery("SELECT * FROM "
                        + "charmingstudio.provee WHERE idServicios = '" + servicio.getId() + "' ORDER BY costo");

        if (BusquedaIdProvs.wasNull()) {
            return null;
        }

        Proveedor provTemporal,proveedor;
        Servicio unServicio;
        while (BusquedaIdProvs.next()) {
            
            provTemporal = buscarProveedorPorId(BusquedaIdProvs.getInt(columnaIdProveedor));
            
            /*necesitamos nuevos objetos, debido a que puede haber
            varios proveedores que den el mismo servicio:*/
            proveedor = new Proveedor(provTemporal.getIdPersona(),
                    provTemporal.getNombrePersona(),
                    provTemporal.getDireccionPersona(),
                    provTemporal.getTelefonoPersona(),
                    provTemporal.getCorreoPersona());
            
            unServicio = new Servicio(servicio.getId(), 
                    servicio.getServNombre(),
                    BusquedaIdProvs.getFloat(columnaCostoServicio));
            
            proveedor.agregarUnServicio(unServicio);
            proveedores.add(proveedor);
        }//fin while

        return proveedores;
    }

    public static void main(String[] args) {
        try {
            DAOProveedores d = new DAOProveedores();
            System.out.println(d.obtenerProveedoresDelServicio("Banquetera"));
        } catch (SQLException ex) {
            System.out.println("error");
            ex.printStackTrace();
            
        }
    }
    
}
