package Modelo;

/**
 * @author Lalo
 * @version 1.0
 * @created 19-sep-2014 01:30:27 p.m.
 */
public class PaqueteCompleto extends PaqueteIntermedio {

    private Proveedor proveedorLugar;
    private Proveedor proveedorMusica;

    public PaqueteCompleto() {
    }

    
    
    public PaqueteCompleto(int id, String nombre, float precio, Proveedor proveedorBanquetera, 
            Proveedor proveedorIluminacion, Proveedor provCarpa,Proveedor proveedorLugar,
            Proveedor proveedorMusica) {
        super(id, nombre, precio, proveedorBanquetera, proveedorIluminacion, provCarpa);
        this.proveedorLugar = proveedorLugar;
        this.proveedorMusica = proveedorMusica;
    }
  

    @Override
    public float getPrecio() {
        return this.precio;
    }
    /**
     *Establece el precio del paquete completo.
     * @param precio
     */
    @Override
    public void setPrecio(float precio) {
        this.precio = precio;
    }

    @Override
    public String getNombre(){
        return this.nombre;
    }

     @Override
    public void setNombre(String nombre) {
        super.setNombre(nombre); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void setProvCarpa(Proveedor provCarpa) {
        super.setProvCarpa(provCarpa); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Proveedor getProvCarpa() {
        return super.getProvCarpa(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getIdPaquete() {
        return super.getIdPaquete(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setIdPaquete(int id) {
        super.setIdPaquete(id); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the proveedorLugar
     */
    public Proveedor getProveedorLugar() {
        return proveedorLugar;
    }

    /**
     * @return the proveedorMusica
     */
    public Proveedor getProveedorMusica() {
        return proveedorMusica;
    }

    /**
     * @param proveedorLugar the proveedorLugar to set
     */
    public void setProveedorLugar(Proveedor proveedorLugar) {
        this.proveedorLugar = proveedorLugar;
    }

    /**
     * @param proveedorMusica the proveedorMusica to set
     */
    public void setProveedorMusica(Proveedor proveedorMusica) {
        this.proveedorMusica = proveedorMusica;
    }

    @Override
    public String toString() {
        return "Paquete Completo: "+"\n" +
                super.toString()+
                "Proveedor de Lugar: " + proveedorLugar + 
                "Proveedor de Música: " + proveedorMusica;
    }

   

}
