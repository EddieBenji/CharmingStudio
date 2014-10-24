/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Controlador.DAO.ConexionBaseDatos;
import Controlador.DAO.DAOEventos;
import Modelo.Cliente;
import Modelo.Empleado;
import Modelo.EventosSociales;
import Modelo.MesaDeDulces;
import Modelo.Proveedor;
import Modelo.Servicio;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Carlos
 */
public class ControladorEventos {
    
    DAOEventos dao=new DAOEventos();
    
    public void agregarEvento(EventosSociales evento) throws SQLException{
        
        /*Mira carlos, checa las pequeñas funciones que hice;*/
        Cliente cliente = encontrarCliente( evento.getEvtCliente() );
        evento.setEvtCliente(cliente);
        /*Así como esta primera, que hagas todas. 
        Como dice el libro de refactorización, con funciones pequeñas.*/
        
        Empleado empleado=encontrarEmpleado( evento.getEvtEmpleado() );
        evento.setEvtEmpleado(empleado);
        
        ControladorMesaDeDulces ctrlMesa=new ControladorMesaDeDulces();
        MesaDeDulces mesa=ctrlMesa.buscarPorNombre(evento.getEvtMesaDeDulces().getmdNombreDeMesa());
        evento.setEvtMesaDeDulces(mesa);
        
        LinkedList<Proveedor> proveedores=new LinkedList();
        ControladorProveedores ctrlProv=new ControladorProveedores();
        
        for(Proveedor prov:evento.getEvtPaquete().getProveedores()){
            proveedores.add(ctrlProv.buscarPorNombre(prov.getNombrePersona()));
        }
       evento.getEvtPaquete().setProveedores(proveedores);
        System.out.println(evento.getEvtPaquete().getProveedores()); 
        
        LinkedList<Servicio> servicios=new LinkedList();
        ControladorServicios ctrlServ=new ControladorServicios();
            System.out.println("Llego hasta servicio");
            
        for(Servicio serv:evento.getEvtPaquete().getServicios()){
            //serv.setId(ctrlServ.buscarId(serv.getServNombre()));
            servicios.add(ctrlServ.buscarServicio(serv.getServNombre()));
        }evento.getEvtPaquete().setServicios(servicios);
        
        
        //evento.getEvtPaquete().setServicios(servicios);
        System.out.println(evento.getEvtPaquete().getServicios()); 
        int idPaquete=getIdPaquete(evento.getEvtPaquete().getNombre());
        evento.getEvtPaquete().setIdPaquete(idPaquete);
        
        dao.agregarEvento(evento);
        
        
    }
    
    private int getIdPaquete(String nombrePaquete){
        if(nombrePaquete.equalsIgnoreCase("Basico")){
            return 1;
        }
        if(nombrePaquete.equalsIgnoreCase("Intermedio")){
            return 2;
        }
        if(nombrePaquete.equalsIgnoreCase("Completo")){
            return 3;
        }
        return 0;
    }
    
    private Cliente encontrarCliente(Cliente clienteA_Encontrar) throws SQLException{
        
        ControladorCliente ctrlCliente = new ControladorCliente();
        Cliente clienteEncontrado = ctrlCliente.buscarPorNombre(clienteA_Encontrar.getNombrePersona());
        
        return clienteEncontrado;
    }
    
        private Empleado encontrarEmpleado(Empleado empleadoA_Encontrar) throws SQLException{
        
        ControladorEmpleado ctrlEmpleado = new ControladorEmpleado();
        Empleado empleadoEncontrado = ctrlEmpleado.buscarPorNombre(empleadoA_Encontrar.getNombrePersona());
        
        return empleadoEncontrado;
    }
    
    
    
    
    
    
}
