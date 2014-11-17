/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Controlador.DAO.DAOEventos;
import Modelo.Cliente;
import Modelo.Empleado;
import Modelo.EventosSociales;
import Modelo.MesaDeDulces;
import Modelo.Proveedor;
import Modelo.Servicio;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlos
 */
public class ControladorEventos {

    DAOEventos dao = new DAOEventos();

    public void agregarEvento(String nombreCliente, String nombreEmpleado, String nombreMesaDulces, String tipoPaquete,
            String nombreProveedor) {

    }

    /**
     * Método encargado de devolver la información completa de un cliente, a
     * partir del nombre que se busque en la BD.
     *
     * @param nombre del cliente a encontrar en la BD.
     * @return lista simple con la información de todos los empleados.
     * @throws SQLException
     */
    public LinkedList<Cliente> obtenerInformacionClientes(String nombre) throws SQLException {

        ControladorCliente controlador = new ControladorCliente();
        LinkedList<Cliente> listaClientes = controlador.buscarCoincidencias(nombre);

        return listaClientes;
    }

    /**
     * Método encargado de obtener la información de todos los empleados
     * registrados en la BD, solo devuelve el ID y nombre de cada empleado.
     *
     * @return un arreglo de String, donde cada fila es la información básica de
     * cada empleado.
     * @throws SQLException
     */
    public String[] obtenerInformacionBasicaEmpleado() throws SQLException {

        ControladorEmpleado controlEmpleado = new ControladorEmpleado();
        String[] datosEmpleados = controlEmpleado.obtenerInformacionBasicaEmpleados();

        return datosEmpleados;
    }

    public void agregarEvento(EventosSociales evento) throws SQLException {

    }

    public LinkedList<MesaDeDulces> encontrarMesasDeDulces() throws SQLException {

        ControladorMesaDeDulces ctrlMesa = new ControladorMesaDeDulces();
        LinkedList<MesaDeDulces> mesasEncontrada = ctrlMesa.buscarTodasMD();

        return mesasEncontrada;
    }

    /**
     * encuentra todos los proveedores que hay en la BD y retorna toda la
     * información asociada a ellos (servicios, costos, etc).
     *
     * @return lista simple con la información completa de los proveedores.
     * @throws SQLException
     */
    public LinkedList encontrarProveedores() throws SQLException {
        LinkedList<Proveedor> proveedores = new LinkedList();
        ControladorProveedores ctrlProv = new ControladorProveedores();

        proveedores = ctrlProv.obtenerTodosLosProveedoresConSusServicios();

        return proveedores;
    }

    /**
     * encuentra todos los proveedores que dan el servicio básico, que hay en la
     * BD y retorna toda la información asociada a ellos (servicios, costos,
     * etc).
     *
     * @return lista simple con la información completa de los proveedores.
     * @throws SQLException
     */
    public LinkedList encontrarProveedoresDeServicioBasico() throws SQLException {
        LinkedList<Proveedor> proveedores = new LinkedList();
        ControladorProveedores ctrlProv = new ControladorProveedores();

        proveedores = ctrlProv.obtenerTodosLosProveedoresDeservicioBasico();
        
        return proveedores;
    }

    private LinkedList encontrarServicios(LinkedList<Servicio> serviciosA_Encontrar) throws SQLException {
        LinkedList<Servicio> servicios = new LinkedList();
        ControladorServicios ctrlServicio = new ControladorServicios();

        for (Servicio servicio : serviciosA_Encontrar) {
            servicios.add(ctrlServicio.buscarServicioPorNombre(servicio.getServNombre()));
        }
        return servicios;
    }

}
