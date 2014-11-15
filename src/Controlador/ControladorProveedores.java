package Controlador;

import Controlador.DAO.DAOProveedores;
import Modelo.Persona;
import Modelo.Proveedor;
import Modelo.Servicio;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Lalo
 */
public class ControladorProveedores implements ControladorPersona {

    DAOProveedores dao = new DAOProveedores();

    @Override
    public Proveedor buscarPorNombre(String nombrePersona) throws SQLException {

        return dao.buscarPorNombre(nombrePersona);

    }

    @Override
    public boolean agregar(Persona proveedor) throws SQLException {
        //El parámetro es en específico de tipo proveedor, por lo que
        //creamos un objeto de tipo proveedor:
        Proveedor prov = (Proveedor) proveedor;

        return dao.agregar(prov);

    }

    @Override
    public boolean eliminar(int Proveedor) throws SQLException {

        return dao.eliminar(Proveedor);

    }

    @Override
    public boolean modificar(Persona persona) throws SQLException {

        return dao.modificar(persona);
    }

    @Override
    public LinkedList buscarCoincidencias(String nombrePersona) throws SQLException {
        
        return dao.buscarCoincidencias(nombrePersona);
        
    }

    /**
     * Encuentra todos los proveedores que proveen algún servicio, en primera
     * instancia están los proveedores que dan más barato, dicho servicio.
     * (TODAVÍA NO ESTÁ IMPLEMENTADO.)
     *
     * @param servicio, es el nombre del servicio.
     * @return
     * @throws SQLException
     */
    public LinkedList proveedoresDelServicio(String servicio) throws SQLException {

        return dao.proveedoresDelServicio(servicio);
    }

    /**
     * Método que se encarga de encontrar los servicios que un proveedor en
     * específico está ofreciendo; Los encuentra en la BD, a partir de la clave
     * del proveedor (ID).
     *
     * @param cveProveedor, único para cada proveedor.
     * @return la lista de servicios que ofrece dicho proveedor.
     * @throws SQLException, en caso de algún error con la conexión de la BD.
     */
    public LinkedList<Servicio> encontrarServiciosDelProveedor(int cveProveedor) throws SQLException {
        DAOProveedores daoProv = new DAOProveedores();

        LinkedList<Servicio> serviciosDeProveedor = daoProv.encontrarServiciosDelProveedor(cveProveedor);

        if (serviciosDeProveedor != null) {
            return serviciosDeProveedor;
        }

        return null;

    }

}
