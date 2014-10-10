package Modelo;

import java.util.Date;

/**
 * @author Lalo
 * @version 1.0
 * @created 19-sep-2014 01:34:14 p.m.
 */
public class EventosSociales {

    private Cliente evtCliente;
    private Date evtFecha;
    private MesaDeDulces evtMesaDeDulces;
    private Paquete evtPaquete;
    private float evtPrecioTotal;
    private float evtComision;

    /**
     * Este constructor inicializa un nuevo objeto de tipo EventosSociales, pero
     * con todos sus atributos nulos.
     */
    public EventosSociales() {
        evtCliente = null;
        evtFecha = null;
        evtMesaDeDulces = null;
        evtPaquete = null;
        evtPrecioTotal = 0;
        evtComision = 0;
    }

    /**
     * Este constructor inicializa un nuevo objeto de tipo "EventosSociales" con
     * sus respectivos atributos.
     *
     * @param evtCliente
     * @param evtFecha
     * @param evtMesaDeDulces
     * @param evtPaquete
     * @param evtPrecioTotal
     * @param evtComision
     */
    public EventosSociales(Cliente evtCliente, Date evtFecha,
            MesaDeDulces evtMesaDeDulces, Paquete evtPaquete,
            float evtPrecioTotal, float evtComision) {

        this.evtCliente = evtCliente;
        this.evtFecha = evtFecha;
        this.evtMesaDeDulces = evtMesaDeDulces;
        this.evtPaquete = evtPaquete;
        this.evtPrecioTotal = evtPrecioTotal;
        this.evtComision = evtComision;
    }

    /**
     * Devuelve el precio total, del evento actual; Ya cobra la comisión del
     * 15%.
     *
     * @return precio total del evento.
     */
    public float getPrecioTotal() {
        float precioEvt = evtPaquete.getPrecio() + evtMesaDeDulces.getPrecio();
        float comisionEvt = (float) 0.15 * precioEvt;
        return (float) comisionEvt + precioEvt;
    }

    /**puede que esta no sirva, no hace algo
     * @param precio.*/
    public void setPrecioTotal(float precio) {

    }

}
