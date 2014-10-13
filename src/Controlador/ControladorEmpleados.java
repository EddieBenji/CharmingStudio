package Controlador;

import Modelo.Persona;
import Modelo.Empleado;
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
 * @created 19-sep-2014 02:34:45 p.m.
 */
public class ControladorEmpleados extends GestorBD {

    private Connection Conexion;

    public ControladorEmpleados() {
       try {
            Conexion = BaseDeDatos.getInstanciaConexionDeBaseDatos().getConexionBD();
        } catch (SQLException ex) {
            mostrarMensajeEnPantalla("No hubo conexión" + ex.getLocalizedMessage());
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
        Empleado empleado = (Empleado) persona;
   
        Statement sentencia = Conexion.createStatement();
        boolean seAgregoEmpleado = false;;
      if (!existeEmpleado(empleado)) {
            sentencia.executeUpdate("INSERT INTO charmingstudio.empleado (`Nombre`, "
                    + "`Direccion`, `Telefono`, `Correo`, `Desempeno`, `Sueldo` )" + "VALUES("
                    + "'" + empleado.getNombrePersona() + "',"
                    + "'" + empleado.getDireccionPersona() + "',"
                    + "'" + empleado.getTelefonoPersona() + "',"
                    + "'" + empleado.getCorreoPersona() + "',"
                    + "'" + empleado.getEmpDesempenio() + "',"
                    + "'" + empleado.getEmpSueldo() + "')");
            seAgregoEmpleado = true;
        }
        return seAgregoEmpleado;
    }

    /**
     * Este método eliminará al empleado, pasándole el id único.
     *
     * @param idEmpleado
     * @return
     * @throws java.sql.SQLException
     */
    @Override
    public boolean eliminar(int idEmpleado) throws SQLException {
        boolean seEliminoEmpleado = false;

        Statement sentencia = Conexion.createStatement();

        sentencia.executeUpdate("DELETE FROM charmingstudio.empleado WHERE idEmpleado= '" + idEmpleado + "'");
        seEliminoEmpleado = true;
        return seEliminoEmpleado;

    }

        private boolean existeEmpleado(Empleado empleado) throws SQLException {

        LinkedList<Empleado> listaDeEmpleados = buscarCoincidencias(empleado.getNombrePersona());;
        boolean existeUsuario = false;
        if (listaDeEmpleados != null) {
            for (Empleado empleadoEnBD : listaDeEmpleados) {

                if (compararEmpleados(empleadoEnBD, empleado)) {
                    //si se cumple, entonces encontramos una coincidencia:
                    existeUsuario = true;
                    //rompemos el ciclo en caso de que haya más de un cliente
                    //con los mismos datos:
                    break;
                }
            }
        }/*el else fue considerado, pero no es usado.*/

        return existeUsuario;
    }
    
        private boolean compararEmpleados(Empleado empleadoEncontradoEnBD, Empleado empleadoA_modificar) {
        //primero obtenemos ambos nombres:
        String nombreEmpleadoEncontradoEnBD = empleadoEncontradoEnBD.getNombrePersona();
        String nombreEmpleadoA_modificar = empleadoA_modificar.getNombrePersona();
        //comparamos los nombres:
        if (nombreEmpleadoEncontradoEnBD.equalsIgnoreCase(nombreEmpleadoA_modificar)) {
            return true;
        } else {
            //el else fue considerado, pero no es necesario.
        }

        //obtenemos las direcciones:
        String direccionEmpleadoEncontradoEnBD = empleadoEncontradoEnBD.getDireccionPersona();
        String direccionEmpleadoA_modificar = empleadoA_modificar.getDireccionPersona();
        //comparamos las direcciones:
        if (direccionEmpleadoEncontradoEnBD.equalsIgnoreCase(direccionEmpleadoA_modificar)) {
            return true;
        } else {
            //el else fue considerado, pero no es necesario.
        }

        //obtenemos los teléfonos:
        String telefonoEmpleadoEncontradoEnBD = empleadoEncontradoEnBD.getTelefonoPersona();
        String telefonoEmpleadoA_modificar = empleadoA_modificar.getTelefonoPersona();
        if (telefonoEmpleadoEncontradoEnBD.equalsIgnoreCase(telefonoEmpleadoA_modificar)) {
            return true;
        } else {
            //el else fue considerado, pero no es necesario.
        }

        //obtenemos los correos::
        String correoEmpleadoEncontradoEnBD = empleadoEncontradoEnBD.getCorreoPersona();
        String correoEmpleadoA_modificar = empleadoA_modificar.getCorreoPersona();
        if (correoEmpleadoEncontradoEnBD.equalsIgnoreCase(correoEmpleadoA_modificar)) {
            return true;
        } else {
            //el else fue considerado, pero no es necesario.
        }
        
        //obtenemos los desempenios::
        float desempenioEmpleadoEncontradoEnBD = empleadoEncontradoEnBD.getEmpDesempenio();
        float desempenioEmpleadoA_modificar = empleadoA_modificar.getEmpDesempenio();
        if (desempenioEmpleadoEncontradoEnBD == desempenioEmpleadoA_modificar) {
            return true;
        } else {
            //el else fue considerado, pero no es necesario.
        }
        
        //obtenemos los sueldos::
        float sueldoEmpleadoEncontradoEnBD = empleadoEncontradoEnBD.getEmpSueldo();
        float sueldoEmpleadoA_modificar = empleadoA_modificar.getEmpSueldo();
        if (sueldoEmpleadoEncontradoEnBD == sueldoEmpleadoA_modificar) {
            return true;
        } else {
            //el else fue considerado, pero no es necesario.
        }

        /*Si llega hasta aquí, entonces los clientes son distintos:*/
        return false;
    }
        
    /**
     *
     * @param nombrePersona
     * @return
     * @throws java.sql.SQLException
     */
    @Override
    public LinkedList buscarCoincidencias(String nombrePersona) throws SQLException {
          Statement sentenciaDeBusquedaDeEmpleados = Conexion.createStatement();
        ResultSet BusquedaDeEmpleados = sentenciaDeBusquedaDeEmpleados.executeQuery("SELECT * "
                + "FROM charmingstudio.empleado WHERE Nombre LIKE '%" + nombrePersona + "%'");
        if (!BusquedaDeEmpleados.wasNull()) {
            //creamos la lista:
            LinkedList<Empleado> empleados = new LinkedList<>();

            while (BusquedaDeEmpleados.next()) {
                //agregamos c/empleado a la lista:

                empleados.add(new Empleado(BusquedaDeEmpleados.getInt(1), BusquedaDeEmpleados.getString(2), BusquedaDeEmpleados.getString(3),
                        BusquedaDeEmpleados.getString(4), BusquedaDeEmpleados.getString(5), BusquedaDeEmpleados.getFloat(6), BusquedaDeEmpleados.getFloat(7)));

            }
            return empleados;
        }
        mostrarMensajeEnPantalla("El cliente no se encuentra en la BD");
        return null;

    }

    /**
     * Funcion que se encarga de eliminar algún empleado. NO SE HA IMPLEMENTADO.
     *
     * @return
     * @throws java.sql.SQLException
     */
    @Override
    public boolean modificar(Persona persona) throws SQLException{
                //el parámetro solo es de entrada:
        Empleado empleadoA_modificar = (Empleado) persona;

        //en caso de que no haya el usuario en la BD. 
        Statement sentenciaDeActualizacionDeEmpleados = Conexion.createStatement();
        int actualizaInfoEmpleado
                = sentenciaDeActualizacionDeEmpleados.executeUpdate("UPDATE charmingstudio.empleado "
                        + "SET `Nombre` = '" + empleadoA_modificar.getNombrePersona() + "'"
                        + ",`Direccion` = '" + empleadoA_modificar.getDireccionPersona() + "'"
                        + ",`Telefono` = '" + empleadoA_modificar.getTelefonoPersona() + "'"
                        + ",`Correo`= '" + empleadoA_modificar.getCorreoPersona() + "'"
                        + ",`Desempeno`= '" + empleadoA_modificar.getEmpDesempenio() + "'"
                        + ",`Sueldo`= '" + empleadoA_modificar.getEmpSueldo()
                        + "' WHERE `idEmpleado`='" + empleadoA_modificar.getIdPersona() + "'");

        boolean sePudoModificarInfoEmpleado = false;
        if (actualizaInfoEmpleado != 0) {
            sePudoModificarInfoEmpleado = true;
        }

        //devuelve si se pudo o no, modificar el empleado:
        return sePudoModificarInfoEmpleado;
        
        
    }

   

    private void mostrarMensajeEnPantalla(String mensaje){
        JOptionPane.showMessageDialog(null, mensaje);
    }
    
    
}
