package Modelo;

/**
 * @author Lalo
 * @version 1.0
 * @created 19-sep-2014 01:18:06 p.m.
 */
public class Cliente extends Persona {

    /**
     *Este constructor inicializa un nuevo objeto de tipo cliente,
     * con sus respectivos atributos.
     * @param id
     * @param nombre
     * @param direccion
     * @param telefono
     * @param correo
     */
    public Cliente(int id, String nombre, String direccion, String telefono, String correo) {
        super(id, nombre, direccion, telefono, correo);
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
