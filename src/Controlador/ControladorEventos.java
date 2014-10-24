/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Controlador.DAO.ConexionBaseDatos;
import Controlador.DAO.DAOEmpleados;
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
    ConexionBaseDatos conexion;
    DAOEventos dao=new DAOEventos();
    
    public void agregarEvento(EventosSociales evento) throws SQLException{
        
        ControladorCliente ctrlCliente=new ControladorCliente();
        Cliente cliente=ctrlCliente.buscarEspecifico(evento.getEvtCliente().getNombrePersona());
        evento.setEvtCliente(cliente);
        
        ControladorEmpleado ctrlEmpleado=new ControladorEmpleado();
        Empleado empleado=ctrlEmpleado.buscarEspecifico(evento.getEvtEmpleado().getNombrePersona());
        evento.setEvtEmpleado(empleado);
        
        ControladorMesaDeDulces ctrlMesa=new ControladorMesaDeDulces();
        MesaDeDulces mesa=ctrlMesa.buscarEspecifico(evento.getEvtMesaDeDulces().getmdNombreDeMesa());
        evento.setEvtMesaDeDulces(mesa);
        
        LinkedList<Proveedor> proveedores=new LinkedList();
        ControladorProveedores ctrlProv=new ControladorProveedores();
        System.out.println("Llego hasta proveedor");
        
        for(Proveedor prov:evento.getEvtPaquete().getProveedores()){
            proveedores.add(ctrlProv.buscarEspecifico(prov.getNombrePersona()));
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
    
    
    
}
