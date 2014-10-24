/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Controlador.DAO.DAOClientes;
import Controlador.DAO.DAOEmpleados;
import Controlador.DAO.DAOProveedores;
import Modelo.Persona;
import Modelo.Proveedor;
import Modelo.Servicio;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Carlos
 */
public class ControladorProveedores implements ControladorPersona {
    
    DAOProveedores dao=new DAOProveedores();
    
    public Proveedor buscarEspecifico(String nombrePersona) throws SQLException {

        return dao.buscarEspecificamente(nombrePersona);
  
    }
    
    public boolean agregar(Persona proveedor) throws SQLException {

        ControladorServicios ctrlServ=new ControladorServicios();
        Proveedor prov=(Proveedor)proveedor;
        for(Servicio serv:prov.getProvServicios()){
            serv.setId(ctrlServ.buscarServicio(serv.getServNombre()).getId());
            
        }
        return dao.agregar(prov);

    }

    public boolean eliminar(int Proveedor) throws SQLException {

        return dao.eliminar(Proveedor);

    }
    
    public boolean modificar(Persona persona) throws SQLException {

        ControladorServicios ctrlServ=new ControladorServicios();
        Proveedor proveedor=(Proveedor)persona;
        for(Servicio serv:proveedor.getProvServicios()){
            serv.setId(ctrlServ.buscarServicio(serv.getServNombre()).getId());
            
        }
        return dao.modificar(persona);
    }
    
    public LinkedList buscarCoincidencias(String nombrePersona) throws SQLException {
       
        return dao.buscarCoincidencias(nombrePersona);
    }
    
    public LinkedList proveedoresDelServicio(String servicio) throws SQLException {
       
        return dao.proveedoresDelServicio(servicio);
    }

}
