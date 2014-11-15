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

/**
 *
 * @author Carlos
 */
public class ControladorEventos {
    
    DAOEventos dao=new DAOEventos();
    
    public void agregarEvento(EventosSociales evento) throws SQLException{
        
        Cliente cliente = encontrarCliente( evento.getEvtCliente() );
        evento.setEvtCliente(cliente);
        
        Empleado empleado=encontrarEmpleado( evento.getEvtEmpleado() );
        evento.setEvtEmpleado(empleado);

        MesaDeDulces mesa=encontrarMesaDeDulces( evento.getEvtMesaDeDulces() );
        evento.setEvtMesaDeDulces(mesa);
        
        LinkedList<Proveedor> proveedores=encontrarProveedores( evento.getEvtPaquete().getProveedores() );
        evento.getEvtPaquete().setProveedores(proveedores);
        
              
        LinkedList<Servicio> servicios=encontrarServicios( evento.getEvtPaquete().getServicios());
        evento.getEvtPaquete().setServicios(servicios);
        
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
        
    private MesaDeDulces encontrarMesaDeDulces(MesaDeDulces mesaA_Encontrar) throws SQLException{
        
        ControladorMesaDeDulces ctrlMesa= new ControladorMesaDeDulces();
        MesaDeDulces mesaEncontrada = ctrlMesa.buscarPorNombre(mesaA_Encontrar.getmdNombreDeMesa());
        
        return mesaEncontrada;
    }
    
    private LinkedList encontrarProveedores(LinkedList<Proveedor> proveedoresA_Encontrar) throws SQLException{
        LinkedList<Proveedor> proveedores=new LinkedList();
        ControladorProveedores ctrlProv=new ControladorProveedores();
        
        for(Proveedor prov:proveedoresA_Encontrar){
            proveedores.add(ctrlProv.buscarPorNombre(prov.getNombrePersona()));
        }
        return proveedores;
    }
    
    private LinkedList encontrarServicios(LinkedList<Servicio> serviciosA_Encontrar) throws SQLException{
        LinkedList<Servicio> servicios=new LinkedList();
        ControladorServicios ctrlServicio=new ControladorServicios();
        
        for(Servicio servicio:serviciosA_Encontrar){
            servicios.add(ctrlServicio.buscarServicioPorNombre(servicio.getServNombre()));
        }
        return servicios;
    }
    
    
    
    
    
}
