package Modelo;

/**
 * @author Lalo
 * @version 1.0
 * @created 19-sep-2014 01:35:48 p.m.
 */
public class MesaDeDulces {

	private String mdNombreDeMesa;
	private float mdPrecio;

	public MesaDeDulces(String nombre, float precio){
            this.mdNombreDeMesa=nombre;
            this.mdPrecio= precio;
	}

        public String getmdNombreDeMesa(){
        return mdNombreDeMesa;
        }
        
        public void setmdNombreDeMesa(){
        this.mdNombreDeMesa = mdNombreDeMesa;
        }
        
	public void setPrecio(float precio){
            this.mdPrecio= precio;
	}

	public float getPrecio(){
		return mdPrecio;
	}

}