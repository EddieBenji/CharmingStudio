package Modelo;

/**
 * @author Lalo
 * @version 1.0
 * @created 19-sep-2014 01:13:33 p.m.
 */
public class Persona {

    private int idPersona;
    private String nombrePersona;
    private String direccionPersona;
    private String telefonoPersona;
    private String correoPersona;

    /**
     * Establece un nuevo objeto de tipo persona, con sus respectivos atributos.
     *
     * @param id
     * @param nombre
     * @param direccion
     * @param telefono
     * @param correo
     */
    public Persona(int id, String nombre, String direccion, String telefono, String correo) {
        this.idPersona = id;
        this.nombrePersona = nombre;
        this.direccionPersona = direccion;
        this.telefonoPersona = telefono;
        this.correoPersona = correo;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public String getDireccionPersona() {
        return direccionPersona;
    }

    public String getTelefonoPersona() {
        return telefonoPersona;
    }

    public String getCorreoPersona() {
        return correoPersona;
    }

    public void setIdPersona(int IdPersona) {
        this.idPersona = IdPersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public void setDireccionPersona(String direccionPersona) {
        this.direccionPersona = direccionPersona;
    }

    public void setTelefonoPersona(String telefonoPersona) {
        this.telefonoPersona = telefonoPersona;
    }

    public void setCorreoPersona(String correoPersona) {
        this.correoPersona = correoPersona;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombrePersona + "Direccion:" + direccionPersona + telefonoPersona + correoPersona;
    }

}
