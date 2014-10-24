package Modelo;

import java.util.LinkedList;

/**
 * @author Lalo
 * @version 1.0
 * @created 19-sep-2014 01:26:22 p.m.
 */
public class PaqueteBasico extends Paquete {

    private Servicio ServBanquetera;
    private Proveedor proveedorBanquetera;
    private Servicio servIluminacion;
    private Proveedor proveedorIluminacion;
    LinkedList<Proveedor> proveedores=new LinkedList();
    LinkedList<Servicio> servicios=new LinkedList();
    
    /**
     * Inicializa un nuevo paquete básico, con los servicios de banquetera,
     * iluminación y el precio.
     *
     * @param ServBanquetera
     * @param servIluminacion
     * @param precio
     */
    public PaqueteBasico(Servicio ServBanquetera, Servicio servIluminacion, float precio) {
        this.nombre="Basico";
        this.ServBanquetera = ServBanquetera;
        this.servIluminacion = servIluminacion;
        this.precio = precio;
    }

    public PaqueteBasico(Servicio ServBanquetera, Proveedor proveedorBanquetera, Servicio servIluminacion, Proveedor proveedorIluminacion) {
        this.nombre="Basico";
        this.ServBanquetera = ServBanquetera;
        this.proveedorBanquetera = proveedorBanquetera;
        this.servIluminacion = servIluminacion;
        this.proveedorIluminacion = proveedorIluminacion;
        proveedores.add(proveedorBanquetera);
        proveedores.add(proveedorIluminacion);
        servicios.add(ServBanquetera);
        servicios.add(servIluminacion);
    }

  

    /**
     *Devuelve el precio del paquete básico.
     * @return precio.
     */
    @Override
    public float getPrecio() {
        return this.precio;
    }

    /**
     *Establece el precio del paquete básico.
     * @param precio
     */
    @Override
    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getIdPaquete() {
        return id;
    }

    public void setIdPaquete(int id) {
        this.id = id;
    }
    
    public LinkedList getServicios(){
        
        
        return servicios;
    }
    
    public LinkedList getProveedores(){
        
        
        return proveedores;
    }
    
    @Override
    public String getNombre(){
        return this.nombre;
    }

    public Servicio getServBanquetera() {
        return ServBanquetera;
    }

    public void setServBanquetera(Servicio ServBanquetera) {
        this.ServBanquetera = ServBanquetera;
    }

    public Proveedor getProveedorBanquetera() {
        return proveedorBanquetera;
    }

    public void setProveedorBanquetera(Proveedor proveedorBanquetera) {
        this.proveedorBanquetera = proveedorBanquetera;
    }

    public Servicio getServIluminacion() {
        return servIluminacion;
    }

    public void setServIluminacion(Servicio servIluminacion) {
        this.servIluminacion = servIluminacion;
    }

    public Proveedor getProveedorIluminacion() {
        return proveedorIluminacion;
    }

    public void setProveedorIluminacion(Proveedor proveedorIluminacion) {
        this.proveedorIluminacion = proveedorIluminacion;
    }

    

    public void setProveedores(LinkedList<Proveedor> proveedores) {
        this.proveedores = proveedores;
    }

    

    public void setServicios(LinkedList<Servicio> servicios) {
        this.servicios = servicios;
    }

    
}
