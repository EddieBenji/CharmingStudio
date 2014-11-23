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
    private Empleado evtEmpleado;
    
    private int idEvento;
    private int idCliente;
    private int idMD;

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdMD() {
        return idMD;
    }

    public void setIdMD(int idMD) {
        this.idMD = idMD;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
    private Date fecha;
    private float PrecioTotal;
    private int idEmpleado;
            
             /**
             * Este constructor inicializa un nuevo objeto de tipo
             * EventosSociales, pero con todos sus atributos nulos.
             */

    public EventosSociales() {
        evtMesaDeDulces = null;
        evtPaquete = null;
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
     * @param evtEmpleado
     */
    public EventosSociales(Cliente evtCliente, Date evtFecha,
            MesaDeDulces evtMesaDeDulces, Paquete evtPaquete,
            float evtPrecioTotal, float evtComision, Empleado evtEmpleado) {

        this.evtCliente = evtCliente;
        this.evtFecha = evtFecha;
        this.evtMesaDeDulces = evtMesaDeDulces;
        this.evtPaquete = evtPaquete;
        this.evtPrecioTotal = evtPrecioTotal;
        this.evtComision = evtComision;
        this.evtEmpleado = evtEmpleado;
    }

    public EventosSociales(int idEvento, int idCliente,int idMD, Date fecha,
            float evtPrecioTotal, int idEmpleado) {
        this.idCliente = idCliente;
        this.idEvento = idEvento;
        this.idMD = idMD;
        this.fecha = fecha;
        this.PrecioTotal = evtPrecioTotal;
        this.idEmpleado = idEmpleado;
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

    /**
     * puede que esta no sirva, no hace algo
     *
     * @param precio.
     */
    public void setPrecioTotal(float precio) {
    }

    public Cliente getEvtCliente() {
        return evtCliente;
    }

    public Date getEvtFecha() {
        return evtFecha;
    }

    public MesaDeDulces getEvtMesaDeDulces() {
        return evtMesaDeDulces;
    }

    public Paquete getEvtPaquete() {
        return evtPaquete;
    }

    public float getEvtPrecioTotal() {
        return evtPrecioTotal;
    }

    public float getEvtComision() {
        return evtComision;
    }

    public Empleado getEvtEmpleado() {
        return evtEmpleado;
    }

    /**
     * @param evtCliente the evtCliente to set
     */
    public void setEvtCliente(Cliente evtCliente) {
        this.evtCliente = evtCliente;
    }

    /**
     * @param evtFecha the evtFecha to set
     */
    public void setEvtFecha(Date evtFecha) {
        this.evtFecha = evtFecha;
    }

    /**
     * @param evtMesaDeDulces the evtMesaDeDulces to set
     */
    public void setEvtMesaDeDulces(MesaDeDulces evtMesaDeDulces) {
        this.evtMesaDeDulces = evtMesaDeDulces;
    }

    /**
     * @param evtPaquete the evtPaquete to set
     */
    public void setEvtPaquete(Paquete evtPaquete) {
        this.evtPaquete = evtPaquete;
    }

    /**
     * @param evtPrecioTotal the evtPrecioTotal to set
     */
    public void setEvtPrecioTotal(float evtPrecioTotal) {
        this.evtPrecioTotal = evtPrecioTotal;
    }

    /**
     * @param evtComision the evtComision to set
     */
    public void setEvtComision(float evtComision) {
        this.evtComision = evtComision;
    }

    /**
     * @param evtEmpleado the evtEmpleado to set
     */
    public void setEvtEmpleado(Empleado evtEmpleado) {
        this.evtEmpleado = evtEmpleado;
    }

    @Override
    public String toString() {
        return "EventosSociales{" + "evtCliente=" + evtCliente + ", evtFecha=" + evtFecha
                + ", evtMesaDeDulces=" + evtMesaDeDulces + ", evtPaquete=" + evtPaquete
                + ", evtPrecioTotal=" + evtPrecioTotal + ", evtComision=" + evtComision
                + ", evtEmpleado=" + evtEmpleado + '}';
    }
}
