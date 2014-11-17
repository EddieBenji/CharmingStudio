package Modelo;

/**
 * @author Lalo
 * @version 2.0
 * @created 16-nov-2014 11:43:40 a.m.
 */
public class PaqueteIntermedio extends PaqueteBasico {
    private Proveedor provCarpa;

    public PaqueteIntermedio() {
    }

    
    
    public PaqueteIntermedio(int id, String nombre, float precio,Proveedor proveedorBanquetera, 
            Proveedor proveedorIluminacion,Proveedor provCarpa) {
        super(id, nombre, precio, proveedorBanquetera, proveedorIluminacion);
        this.provCarpa = provCarpa;
    }
 
    /**
     *Devuelve el precio del paquete intermedio.
     * @return precio.
     */
    @Override
    public float getPrecio() {
        return super.getPrecio();
    }
     /**
     *Establece el precio del paquete intermedio.
     * @param precio
     */
    @Override
    public void setPrecio(float precio) {
        super.setPrecio(precio);
    }
    
    
    @Override
    public String getNombre(){
        return super.getNombre();
    }

    @Override
    public void setNombre(String nombre) {
        super.setNombre(nombre);
    }

    @Override
    public void setIdPaquete(int id) {
        super.setIdPaquete(id); 
    }

    @Override
    public int getIdPaquete() {
        return super.getIdPaquete();
    }
    
    public Proveedor getProvCarpa() {
        return provCarpa;
    }

    public void setProvCarpa(Proveedor provCarpa) {
        this.provCarpa = provCarpa;
    }

    @Override
    public String toString() {
        return "Paquete Intermedio:" +"\n"+
                super.toString()+"\n"+
                "Proveedor de la Carpa: " + provCarpa ;
    }    

    
}
