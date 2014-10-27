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

        return dao.buscarEspecificamente(nombrePersona);

    }

    @Override
    public boolean agregar(Persona proveedor) throws SQLException {
        //El parámetro es en específico de tipo proveedor, por lo que
        //creamos un objeto de tipo proveedor:
        Proveedor prov = (Proveedor) proveedor;

        /*Agregamos los servicios a su respetiva tabla, ya que no se puede
         agregar un servicio. (No hay interfaz Gráfica para ésto);
         Para mayor información, checar el documento de ERS.
         */
        agregarServicios(prov.getServiciosQueProvee());

        /*Como en la sentencia anterior se agregaron a la BD,
         ahora necesitamos los IDs que el SMBD les asignó,
         para mantener la relación entre el proveedor
         y el servicio que provee*/
        prov.setServiciosQueProvee(actualizarInfoServicios(prov.getServiciosQueProvee()));

        return dao.agregar(prov);

    }

    /**
     * Este método agrega a la BD, los servicios que el usuario escogió para el proveedor.
     */
    private void agregarServicios(LinkedList<Servicio> serviciosAGuardar) throws SQLException {

        ControladorServicios ctrlServ = new ControladorServicios();
        int indice = 0;

        while (indice < serviciosAGuardar.size()) {
            ctrlServ.agregarServicio(serviciosAGuardar.get(indice));
            indice++;
        }
    }

    private LinkedList<Servicio> actualizarInfoServicios(LinkedList<Servicio> listaServicios) throws SQLException {

        int idServicio;
        Servicio servicioTemp = null;
        ControladorServicios ctrlServ = new ControladorServicios();
        
        for (Servicio cadaServicio : listaServicios) {
            servicioTemp = ctrlServ.buscarServicio(  cadaServicio.getServNombre()  );
            idServicio = servicioTemp.getId();
            cadaServicio.setId(idServicio);
        }
        

        return listaServicios;
    }

    @Override
    public boolean eliminar(int Proveedor) throws SQLException {

        return dao.eliminar(Proveedor);

    }

    @Override
    public boolean modificar(Persona persona) throws SQLException {

        ControladorServicios ctrlServ = new ControladorServicios();
        Proveedor proveedor = (Proveedor) persona;
        for (Servicio serv : proveedor.getServiciosQueProvee()) {
            serv.setId(ctrlServ.buscarServicio(serv.getServNombre()).getId());

        }
        return dao.modificar(persona);
    }

    @Override
    public LinkedList buscarCoincidencias(String nombrePersona) throws SQLException {

        return dao.buscarCoincidencias(nombrePersona);
    }

    public LinkedList proveedoresDelServicio(String servicio) throws SQLException {

        return dao.proveedoresDelServicio(servicio);
    }

}
