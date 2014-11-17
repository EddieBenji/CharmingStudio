package Modelo;

/**
 * @author Lalo
 * @version 1.0
 * @created 19-sep-2014 01:35:48 p.m.
 */
public class MesaDeDulces {

    private String mdNombreDeMesa;
    private float mdPrecio;
    private int idMesaDulces;

    public MesaDeDulces(int idMesaDulces, String nombre, float precio) {
        this.idMesaDulces = idMesaDulces;
        this.mdNombreDeMesa = nombre;
        this.mdPrecio = precio;
    }

    public String getNombreDeMesa() {
        return mdNombreDeMesa;
    }

    public void setmdNombreDeMesa() {
        this.mdNombreDeMesa = mdNombreDeMesa;
    }

    public void setPrecio(float precio) {
        this.mdPrecio = precio;
    }

    public float getPrecio() {
        return mdPrecio;
    }

    public int getIdMesaDulces() {
        return idMesaDulces;
    }

    public void setIdMesaDulces(int idMesaDulces) {
        this.idMesaDulces = idMesaDulces;
    }
}