package Controlador;

import Controlador.DAO.DAOEmpleados;
import Modelo.Empleado;
import Modelo.Persona;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Alejandro
 */
public class ControladorEmpleado implements ControladorPersona {

    DAOEmpleados dao = new DAOEmpleados();

    @Override
    public boolean agregar(Persona empleado) throws SQLException {

        return dao.agregar(empleado);

    }

    @Override
    public boolean eliminar(int idEmpleado) throws SQLException {

        return dao.eliminar(idEmpleado);
    }
    
     public Empleado obtenerEmpleadoPorId(int idEmpleado) throws SQLException{
        return dao.buscarEmpleadoPorId(idEmpleado);
    }

    /**
     * Método encargado de devolver un arreglo con la información básica (ID y
     * nombre) de cada empleado.
     *
     * @return arreglo de Strings, donde cada fila es solo la información básica
     * de cada empleado.
     * @throws SQLException
     */
    public String[] obtenerInformacionBasicaEmpleados() throws SQLException {
        LinkedList<Empleado> empleados = dao.obtenerTodosLosEmpleados();
        String[] infoBasicaEmpleados = copiarInformacion(empleados);

        return infoBasicaEmpleados;
    }

    private String[] copiarInformacion(LinkedList<Empleado> listaEmpleados) {
        String[] datosBasicosEmpleados = new String[listaEmpleados.size()];
        int fila = 0;
        for (Empleado unEmpleado : listaEmpleados) {
            datosBasicosEmpleados[fila] = unEmpleado.obtenerInfoBasicoDeEmpleado();
            fila++;
        }

        return datosBasicosEmpleados;
    }

    /**
     *
     * @param nombrePersona
     * @return
     * @throws SQLException
     */
    @Override
    public LinkedList buscarCoincidencias(String nombrePersona) throws SQLException {

        return dao.buscarCoincidencias(nombrePersona);
    }

    @Override
    public boolean modificar(Persona persona) throws SQLException {

        return dao.modificar(persona);
    }

    @Override
    public Empleado buscarPorNombre(String nombrePersona) throws SQLException {

        return dao.buscarPorNombre(nombrePersona);

    }

    public LinkedList buscarTodosLosEmpleados() throws SQLException {

        return dao.obtenerTodosLosEmpleados();

    }
    
    public LinkedList buscarTodosLosEmpleadosConVentas() throws SQLException {

        return dao.obtenerTodosLosEmpleadosConVentas();

    }
    
    
}
