package Modelo;

/**
 * @author Lalo
 * @version 1.0
 * @created 19-sep-2014 01:15:46 p.m.
 */
public class Empleado extends Persona {

    private float empDesempenio;
    private float empSueldo;

    /**
     *Crea un nuevo objeto de tipo Empleado, pero todo está en nulo.
     */
    public Empleado() {
        super(0, "", "","","");
        this.empDesempenio=0;
        this.empSueldo=0;
    }

    
    
    /**
     *Este constructor inicializa un nuevo objeto de tipo empleado,
     * con sus respectivos atributos.
     * @param id
     * @param nombre
     * @param direccion
     * @param telefono
     * @param correo
     * @param desempenio
     * @param sueldo
     */
    public Empleado(int id, String nombre, String direccion, String telefono, String correo, float desempenio, float sueldo) {
        super(id, nombre, direccion, telefono, correo);
        this.empDesempenio = desempenio;
        this.empSueldo = sueldo;

    }

    /**
     *Devuelve el desempeño actual del cliente.
     * @return desempeño.
     */
    public float getEmpDesempenio() {
        return empDesempenio;
    }

    /**
     *Devuelve el sueldo actual del empleado.
     * @return sueldo.
     */
    public float getEmpSueldo() {
        return empSueldo;
    }

    /**
     *Establece el desempeño del empleado actual.
     * @param empDesempenio
     */
    public void setEmpDesempenio(float empDesempenio) {
        this.empDesempenio = empDesempenio;
    }

    /**
     *Establece el sueldo del empleado actual.
     * @param empSueldo
     */
    public void setEmpSueldo(float empSueldo) {
        this.empSueldo = empSueldo;
    }

    @Override
    public void setCorreoPersona(String correoPersona) {
        super.setCorreoPersona(correoPersona); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setTelefonoPersona(String telefonoPersona) {
        super.setTelefonoPersona(telefonoPersona); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setDireccionPersona(String direccionPersona) {
        super.setDireccionPersona(direccionPersona); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setNombrePersona(String nombrePersona) {
        super.setNombrePersona(nombrePersona); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setIdPersona(int IdPersona) {
        super.setIdPersona(IdPersona); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getCorreoPersona() {
        return super.getCorreoPersona(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getTelefonoPersona() {
        return super.getTelefonoPersona(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDireccionPersona() {
        return super.getDireccionPersona(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getNombrePersona() {
        return super.getNombrePersona(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getIdPersona() {
        return super.getIdPersona(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *Regresa el ID y nombre del empleado.
     * @return
     */
    public String obtenerInfoBasicoDeEmpleado(){
        
        return ""+getIdPersona()+" "+getNombrePersona();
    }
    
    
    
    @Override
    public String toString() {
        return "Empleado{" + super.toString() + " empDesempenio=" + empDesempenio + ", empSueldo=" + empSueldo + '}';
    }

}
