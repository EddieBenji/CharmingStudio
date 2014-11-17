package Modelo;

/**
 * @author Lalo
 * @version 1.0
 * @created 19-sep-2014 01:26:22 p.m.
 */
public class PaqueteBasico extends Paquete {

    
    private Proveedor proveedorBanquetera;
    private Proveedor proveedorIluminacion;

    public PaqueteBasico() {
    }
    
    
    public PaqueteBasico(int id, String nombre, float precio, 
            Proveedor proveedorBanquetera, Proveedor proveedorIluminacion) {
        super(id, nombre, precio);
        this.proveedorBanquetera = proveedorBanquetera;
        this.proveedorIluminacion = proveedorIluminacion;
    }

    @Override
    public int getIdPaquete() {
        return this.id;
    }

    @Override
    public void setIdPaquete(int id) {
        this.id = id;
    }

    @Override
    public float getPrecio() {
        return this.precio;
    }

    @Override
    public void setPrecio(float precio) {
        this.precio = precio;
    }

    @Override
    public String getNombre() {
        return this.nombre;
    }
    
    @Override
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Paquete Básico:" +"\n"+
                super.toString()+"\n"+
                "Proveedor de la Banquetera: " + proveedorBanquetera + "\n"+
                "Proveedor de la Iluminacion: " + proveedorIluminacion;
    }
    
    
    
}
