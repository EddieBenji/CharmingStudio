package Controlador.DAO;

import Modelo.Persona;
import Modelo.Cliente;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 * @author Lalo
 * @version 1.0
 * @created 19-sep-2014 02:34:05 p.m.
 */
public class DAOClientes extends GestorBD {

    private Connection Conexion;

    @SuppressWarnings({"static-access", "static-access"})
    public DAOClientes() {
        try {
            Conexion = BaseDeDatos.getInstancia().getConexionBD();
        } catch (SQLException ex) {
        }
    }

    /**
     * Este método se encarga de agregar un cliente específicamente a la base de
     * datos.
     *
     * @return verdadero o falso, dependiendo de si se pudo agregar o no a la
     * base de datos.
     * @throws java.sql.SQLException, en caso de que la conexión con la base de
     * datos no se logre o cualquier otra excepción relacionada con la base de
     * datos.
     */
    @Override
    public boolean agregar(Persona persona) throws SQLException {

        Cliente cliente = (Cliente) persona;
        Statement sentencia = Conexion.createStatement();
        boolean seAgregoCliente = false;
        if (!existeUsuario(cliente)) {
            sentencia.executeUpdate("INSERT INTO charmingstudio.cliente (`Nombre`, "
                    + "`Direccion`, `Telefono`, `Correo`)" + "VALUES("
                    + "'" + cliente.getNombrePersona() + "',"
                    + "'" + cliente.getDireccionPersona() + "',"
                    + "'" + cliente.getTelefonoPersona() + "',"
                    + "'" + cliente.getCorreoPersona() + "')");
            seAgregoCliente = true;
        }
        return seAgregoCliente;
    }

    /**
     * Método que se encarga de verificar si una persona está en la base de
     * datos,regresa un booleano que está en verdadero cuando dicho cliente está
     * en la BD o un falso cuando no se encuentra en la BD.
     *
     * @param idCliente
     * @return
     * @throws java.sql.SQLException, en caso de que la conexión con la base de
     * datos no se logre o cualquier otra excepción relacionada con la base de
     */
    private boolean existeUsuario(Cliente cliente) throws SQLException {

        LinkedList<Cliente> listaDeClientes = buscarCoincidencias(cliente.getNombrePersona());;
        boolean existeUsuario = false;
        if (listaDeClientes != null) {
            for (Cliente clienteEnBD : listaDeClientes) {

                if (compararClientes(clienteEnBD, cliente)) {
                    //si se cumple, entonces encontramos una coincidencia:
                    existeUsuario = true;
                    //rompemos el ciclo cuando encontramos coincidencia
                    //pues no tiene caso de que siga recorriendo la lista de clientes.
                    break;
                }
            }
        }/*el else fue considerado, pero no es usado.*/

        return existeUsuario;
    }

    /**
     * Esta función se encarga de comparar todos los campos de 2 clientes.
     *
     * @return boolean, true si son iguales en almenos un campo, falso de otro
     * modo.
     */
    private boolean compararClientes(Cliente clienteEncontradoEnBD, Cliente clienteA_modificar) {
        //primero obtenemos ambos nombres:
        String nombrePrimerCliente = clienteEncontradoEnBD.getNombrePersona();
        String nombreSegundoCliente = clienteA_modificar.getNombrePersona();

        if (nombrePrimerCliente.equalsIgnoreCase(nombreSegundoCliente)) {
            return true;
        } //el else fue considerado, pero no es necesario.

        //obtenemos las direcciones:
        String direccionPrimerCliente = clienteEncontradoEnBD.getDireccionPersona();
        String direccionSegundoCliente = clienteA_modificar.getDireccionPersona();

        if (direccionPrimerCliente.equalsIgnoreCase(direccionSegundoCliente)) {
            return true;
        } //el else fue considerado, pero no es necesario.

        //obtenemos los teléfonos:
        String telefonoPrimerCliente = clienteEncontradoEnBD.getTelefonoPersona();
        String telefonoSegundoCliente = clienteA_modificar.getTelefonoPersona();

        if (telefonoPrimerCliente.equalsIgnoreCase(telefonoSegundoCliente)) {
            return true;
        } //el else fue considerado, pero no es necesario.

        //obtenemos los correos::
        String correoPrimerCliente = clienteEncontradoEnBD.getCorreoPersona();
        String correoSegundoCliente = clienteA_modificar.getCorreoPersona();

        if (correoPrimerCliente.equalsIgnoreCase(correoSegundoCliente)) {
            return true;
        } //el else fue considerado, pero no es necesario.

        /*Si llega hasta aquí, entonces los clientes son distintos:*/
        return false;
    }

    /**
     * Método que se encarga de eliminar a una persona desde la base de datos.
     *
     * @param idCliente
     * @return
     * @throws java.sql.SQLException, en caso de que la conexión con la base de
     * datos no se logre o cualquier otra excepción relacionada con la base de
     */
    @Override
    public boolean eliminar(int idCliente) throws SQLException {
        boolean seEliminoCliente = false;
        Statement sentencia = Conexion.createStatement();
        sentencia.executeUpdate("DELETE FROM charmingstudio.cliente WHERE idCliente= '" + idCliente + "'");
        seEliminoCliente = true;

        return seEliminoCliente;
    }

    /**
     * Función que se encarga de buscar Coincidencias a uno o varios clientes,
     * dependiendo de los nombres, de la base de datos.
     *
     * @param nombrePersona que es el nombre de la persona a
     * buscarCoincidencias.
     * @return la lista de personas que coincidan con el nombre, si solo es una
     * regresa la lista con un solo cliente.
     * @throws java.sql.SQLException, en caso de que la conexión con la base de
     * datos no se logre o cualquier otra excepción relacionada con la base de
     */
    @Override
    public LinkedList buscarCoincidencias(String nombrePersona) throws SQLException {

        Statement sentenciaDeBusquedaDeClientes = Conexion.createStatement();
        ResultSet BusquedaDeClientes = sentenciaDeBusquedaDeClientes.
                executeQuery("SELECT * FROM charmingstudio.cliente WHERE "
                        + "Nombre LIKE '%" + nombrePersona + "%'");

        /*En este caso, se espera que la búsqueda no siempre sea nula, por
         lo que nos interesa el negativo de las sentencia:*/
        if (!BusquedaDeClientes.wasNull()) {

            LinkedList<Cliente> clientes = new LinkedList<>();

            while (BusquedaDeClientes.next()) {

                //agregamos c/cliente a la lista:
                clientes.add(new Cliente(BusquedaDeClientes.getInt(1),
                        BusquedaDeClientes.getString(2),
                        BusquedaDeClientes.getString(3),
                        BusquedaDeClientes.getString(4),
                        BusquedaDeClientes.getString(5)));

            }
            return clientes;
        }
        return null;
    }

    public Cliente buscarEspecificamente(String nombreCliente) throws SQLException {
        Statement sentenciaBuscaIdCliente = Conexion.createStatement();
        ResultSet busquedaIdCliente = sentenciaBuscaIdCliente.executeQuery("SELECT * FROM "
                + "charmingstudio.cliente WHERE Nombre ='" + nombreCliente + "'");
        busquedaIdCliente.next();

        Cliente cliente = new Cliente(busquedaIdCliente.getInt(1),
                busquedaIdCliente.getString(2),
                busquedaIdCliente.getString(3),
                busquedaIdCliente.getString(4),
                busquedaIdCliente.getString(5));

        return cliente;
    }

    /**
     * Función que actualiza la información de un cliente en la base de datos,
     * primero encuentra la persona a modificar para saber que exista en la base
     * de datos, posteriormente realiza las actualizaciones de la información.
     *
     * @param persona que es el objeto cliente con las modificaciones ya hechas.
     * @return verdadero o falso, dependiendo de si se pudo modificar la
     * información o no.
     * @throws java.sql.SQLException, en caso de que la conexión con la base de
     * datos no se logre o cualquier otra excepción relacionada con la base de
     */
    @Override
    public boolean modificar(Persona persona) throws SQLException {
        //el parámetro solo es de entrada:
        Cliente clienteA_modificar = (Cliente) persona;

        //en caso de que no haya el usuario en la BD. 
        Statement sentenciaDeActualizacionDeClientes = Conexion.createStatement();
        int actualizaInfoEmpleado
                = sentenciaDeActualizacionDeClientes.executeUpdate("UPDATE charmingstudio.cliente "
                        + "SET `Nombre` = '" + clienteA_modificar.getNombrePersona() + "'"
                        + ",`Direccion` = '" + clienteA_modificar.getDireccionPersona() + "'"
                        + ",`Telefono` = '" + clienteA_modificar.getTelefonoPersona() + "'"
                        + ",`Correo`= '" + clienteA_modificar.getCorreoPersona()
                        + "' WHERE `idCliente`='" + clienteA_modificar.getIdPersona() + "'");

        boolean sePudoModificarInfoCliente = false;
        if (actualizaInfoEmpleado != 0) {
            sePudoModificarInfoCliente = true;
        }

        //devuelve si se pudo o no, modificar el cliente:
        return sePudoModificarInfoCliente;
    }

    public LinkedList buscarTodosLosClientes() throws SQLException {

        Statement sentenciaDeBusquedaDeClientes = Conexion.createStatement();
        ResultSet BusquedaDeClientes = sentenciaDeBusquedaDeClientes.
                executeQuery("SELECT * FROM charmingstudio.cliente");

        /*En este caso, se espera que la búsqueda no siempre sea nula, por
         lo que nos interesa el negativo de las sentencia:*/
        if (!BusquedaDeClientes.wasNull()) {

            LinkedList<Cliente> clientes = new LinkedList<>();

            while (BusquedaDeClientes.next()) {

                //agregamos c/cliente a la lista:
                clientes.add(new Cliente(BusquedaDeClientes.getInt(1),
                        BusquedaDeClientes.getString(2),
                        BusquedaDeClientes.getString(3),
                        BusquedaDeClientes.getString(4),
                        BusquedaDeClientes.getString(5)));

            }
            return clientes;
        }

        return null;
    }
    
        
    private static final int columnaIdCliente= 1;
    private static final int columnaNombre = 2;
    private static final int columnaDireccion = 3;
    private static final int columnaTelefono = 4;
    private static final int columnaCorreo = 5;

    public Cliente buscarClientePorId(int idCliente) throws SQLException {

        Statement sentenciaBuscaliente = Conexion.createStatement();
        ResultSet busquedaCliente = sentenciaBuscaliente.executeQuery("SELECT * FROM "
                + "charmingstudio.cliente WHERE idCliente ='" + idCliente + "'");
        busquedaCliente.next();

        Cliente unCliente = new Cliente(busquedaCliente.getInt(columnaIdCliente),
                busquedaCliente.getString(columnaNombre),
                busquedaCliente.getString(columnaDireccion),
                busquedaCliente.getString(columnaTelefono),
                busquedaCliente.getString(columnaCorreo));
        /*
         LinkedList<Servicio> servicios = encontrarServiciosDelProveedor(unProveedor.getIdPersona());
         unProveedor.setServiciosQueProvee(servicios);
         */
        return unCliente;
    }

}//fin de la clase.
