/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Controlador.DAO.DAOEventos;
import Modelo.Cliente;
import Modelo.MesaDeDulces;
import Modelo.Proveedor;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedList;

/**
 *
 * @author Lalo
 */
public class ControladorEventos {

    DAOEventos dao = new DAOEventos();

    /**
     * Se encarga de guardar un nuevo registro para un evento, actualiza los
     * paquetes ya que para cada evento se arma uno solo.
     *
     * @param claveCliente es el cliente asociado al evento.
     * @param claveEmpleado es el empleado encargado de llevar el evento.
     * @param claveMesaDulces es la mesa de dulces asociada al evento.
     * @param proveedores son los posibles proveedores que darán algún servicio.
     * Podría ser que solo un proveedor de todos los servicios que necesita.
     * @param clavePaquete Es el tipo de paquete que se usará en el evento 
     * 1 - es el básico. 
     * 2 - es el intermedio. 
     * 3 - es el completo.
     * @param precioEvento es el precio que cobrará la empresa por todo.
     * @param fechaEvento es la fecha en que se realizará el evento.
     * @return 
     * @throws java.sql.SQLException
     */
    public boolean agregarEvento(int claveCliente, int claveEmpleado, int claveMesaDulces,
            Object[] proveedores, int clavePaquete, float precioEvento,
            Calendar fechaEvento) throws SQLException {

        //Se actualiza la información de los paquetes en la BD:
        agregarPaquetes(clavePaquete, proveedores);

        int idProveedor = 0, idServicio = 0;
        String nombreServicio = "";

        boolean sePudoAgregarEvento = false;
        for (int indice = 0; indice < proveedores.length; indice += 2) {
            
            //obtenemos la información del arreglo:
            idProveedor = (int) proveedores[indice];
            nombreServicio = (String) proveedores[indice + 1];
            
            //encontramos el ID del servicio, a partir del nombre:
            idServicio = buscarIdServicioPorNombre(nombreServicio);
            System.out.println("Servicio:" +nombreServicio+" ID: "+idServicio);
            //todo eso, lo agregamos a la BD.
            sePudoAgregarEvento = dao.agregarElemento(claveCliente, claveMesaDulces, fechaEvento,
                    precioEvento, claveEmpleado, clavePaquete,
                    idProveedor, idServicio);
                        
            nombreServicio = "";
        }
        return sePudoAgregarEvento;
    }

    /**
     * Se encarga de actualizar la tabla de los paquetes.
     * (arma)
     */
    private void agregarPaquetes(int clavePaquete, Object[] proveedoresConServicios) throws SQLException {
        ControladorPaquetes unControladorPaquetes = new ControladorPaquetes();
        int idProveedor = 0, idServicio = 0;
        String nombreServicio = "";

        for (int indice = 0; indice < proveedoresConServicios.length; indice += 2) {
            //obtenemos la información del arreglo:
            idProveedor = (int) proveedoresConServicios[indice];
            nombreServicio = (String) proveedoresConServicios[indice + 1];

            //encontramos el ID del servicio, a partir del nombre:
            idServicio = buscarIdServicioPorNombre(nombreServicio);

            //Actualizamos la información del paquete en la BD.
            unControladorPaquetes.agregarPaquetes(clavePaquete, idProveedor, idServicio);
        }
    }

    /**
     * Se encarga de retornar el ID del servicio que se busca, a partir del
     * nombre que se le pase.
     */
    private int buscarIdServicioPorNombre(String nombreServicio) throws SQLException {
        ControladorServicios controlador = new ControladorServicios();
        int idServicio = controlador.buscarServicioPorNombre(nombreServicio).getId();

        return idServicio;
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

    /**
     * Este método se encarga de encontrar todas las mesas de dulces registradas
     * en la BD, para que el usuario pueda escoger alguna.
     *
     * @return una lista simple con la información de las mesas.
     * @throws SQLException
     */
    public LinkedList<MesaDeDulces> encontrarMesasDeDulces() throws SQLException {

        ControladorMesaDeDulces ctrlMesa = new ControladorMesaDeDulces();
        LinkedList<MesaDeDulces> mesasEncontradas = ctrlMesa.buscarTodasMD();

        return mesasEncontradas;
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
}
