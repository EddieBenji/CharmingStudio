package Controlador.DAO;

import Modelo.EventosSociales;
import Modelo.Proveedor;
import Modelo.Servicio;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Lalo
 * @version 1.0
 * @created 19-sep-2014 02:36:23 p.m.
 */
public class DAOEventos  {

    protected ConexionBaseDatos BaseDeDatos;
    Connection Conexion;

    public DAOEventos() {
        try {
            Conexion = BaseDeDatos.getInstanciaConexionDeBaseDatos().getConexionBD();
            System.out.println("Se conecto");
        } catch (SQLException ex) {
            System.out.println("No hay conexion");
            Logger.getLogger(DAOClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     *
     * @param eventoA_Guardar
     */
    public void agregarEvento(EventosSociales eventoA_Guardar) throws SQLException {

        boolean seAgregoEvento = false;
        EventosSociales evento=eventoA_Guardar;

        //Se crean dos para que no creen conflictos entre ellos.
        
        Statement sentenciaAgregaEvento = Conexion.createStatement();
        
        Statement sentenciaBuscaIdPaquete = Conexion.createStatement();
        
        //id del paquete
        ResultSet idPaquete = sentenciaBuscaIdPaquete.executeQuery("SELECT * "
                    + "FROM charmingstudio.paquetes WHERE"
                    + " Nombre='" + evento.getEvtPaquete().getNombre() + "'");
            idPaquete.next();
            
            
        LinkedList idprovs=new LinkedList();
        for(Servicio serv:evento.getEvtPaquete().getServicios()){
            idprovs.add(devuelveIdProveedor(serv.getProvedoresQueBrindanServicio()[0].getNombrePersona()));
        }
        LinkedList idservs=new LinkedList();
        for(Servicio serv:evento.getEvtPaquete().getServicios()){
            idservs.add(devuelveIdServicio(serv.getServNombre()));
        }
        
        agregaArma(idPaquete.getInt(1),idprovs,idservs);
        /*
        for (int i = 0; i < idprovs.size(); i++) {
            sentenciaAgregaArma.executeUpdate("INSERT INTO charmingstudio.arma "+
                "(`Paquetes_idPaquetes`, `Provee_idProveedor`,`Provee_idServicios` )" + "VALUES("+
                "'" + idPaquete.getInt(1) + "',"+
                "'" + idprovs.get(i) + "',"+              
                "'" + idservs.get(i) + "')");
        }
            */
            
        Statement sentenciaBuscaIdCliente=Conexion.createStatement();
        ResultSet busquedaIdCliente=sentenciaBuscaIdCliente.executeQuery("SELECT * FROM "
                + "charmingstudio.cliente WHERE Nombre ='"+evento.getEvtCliente().getNombrePersona()+"'");
        busquedaIdCliente.next();
        
        Statement sentenciaBuscaIdMD=Conexion.createStatement();
        ResultSet busquedaIdMD=sentenciaBuscaIdMD.executeQuery("SELECT * FROM "
                + "charmingstudio.mesadulces WHERE Nombre ='"+evento.getEvtMesaDeDulces().getmdNombreDeMesa()+"'");
        busquedaIdMD.next();
        
        Statement sentenciaBuscaIdEmpleado=Conexion.createStatement();
        ResultSet busquedaIdEmpleado=sentenciaBuscaIdEmpleado.executeQuery("SELECT * FROM "
                + "charmingstudio.empleado WHERE Nombre ='"+evento.getEvtEmpleado().getNombrePersona()+"'");
        busquedaIdEmpleado.next();
        
        
        java.sql.Date sqlDate = new java.sql.Date(evento.getEvtFecha().getTime());

 
        
        for (int i = 0; i < idprovs.size(); i++) {
            sentenciaAgregaEvento.executeUpdate("INSERT INTO charmingstudio.eventos "+
                "(`idCliente`, `idMesaDulces`,`Fecha`,`PrecioTotal`,`idEmpleado`,`idPaquetes`, `idProveedor`,`idServicio` )" +
                    "VALUES("+
                "'" + busquedaIdCliente.getInt(1) + "',"+
                    "'" + busquedaIdMD.getInt(1) + "',"+
                    "'" + sqlDate + "',"+
                    "'" + calculaPrecioTotal(evento,idprovs,idservs) + "',"+
                    "'" + busquedaIdEmpleado.getInt(1) + "',"+
                    "'" + idPaquete.getInt(1)+ "',"+
                    "'" + idprovs.get(i) + "',"+
                    "'" + idservs.get(i) + "')");
                    
        }
        
        
        
        
            
            seAgregoEvento = true;
        //}//fin if
        
        
        
        
        
        
        
        
        
        
        
    }

    /**
     *
     * @param fecha
     * @param nombreEvento
     * @return 
     */
    public boolean eliminarEvento(Date fecha, EventosSociales nombreEvento) {
        return false;
    }

    /**
     *
     * @param fecha
     * @return 
     */
    public EventosSociales buscarEventos(Date fecha) {
        return null;
    }

    /**
     *
     * @param fecha
     * @param nombreCliente
     * @return 
     */
    public boolean modificarEvento(Date fecha, String nombreCliente) {
        return false;
    }

    
    private int devuelveIdServicio(String serv) throws SQLException{
        Statement sentencia=Conexion.createStatement();
        ResultSet busqueda=sentencia.executeQuery("SELECT * FROM charmingstudio.servicios WHERE Nombre ='"+serv+"'");
        busqueda.next();
        
        return busqueda.getInt(1);
    }
    
    private int devuelveIdProveedor(String prov) throws SQLException{
        Statement sentencia=Conexion.createStatement();
        ResultSet busqueda=sentencia.executeQuery("SELECT * FROM charmingstudio.proveedor WHERE Nombre ='"+prov+"'");
        busqueda.next();
        
        return busqueda.getInt(1);
    }
    
    public float calculaPrecioTotal(EventosSociales evento,LinkedList provs, LinkedList servs) throws SQLException{
        float precioTotal=0;
        Statement sentenciaBuscaCostos=Conexion.createStatement();
        for (int i = 0; i < provs.size(); i++) {
            ResultSet busquedaCostos=sentenciaBuscaCostos.executeQuery("SELECT * FROM "
                + "charmingstudio.provee WHERE IdProveedor ='"+provs.get(i)+"' AND IdServicios='"+
                   servs.get(i) + "'");
        busquedaCostos.next();
        precioTotal=precioTotal+busquedaCostos.getFloat(3);
            
        }
        
        ResultSet busquedaMesaDulces=sentenciaBuscaCostos.executeQuery("SELECT * FROM "
                + "charmingstudio.mesadulces WHERE Nombre ='"+ evento.getEvtMesaDeDulces().getmdNombreDeMesa() +"'");
        busquedaMesaDulces.next();
        precioTotal=precioTotal+busquedaMesaDulces.getFloat(3);
        
        precioTotal=(float) (precioTotal*1.15);
        
        return precioTotal;
    }
    
    
    public void agregaArma(int idpaq,LinkedList idprovs,LinkedList idservs) throws SQLException{
        Statement sentenciaAgregaArma = Conexion.createStatement();
        for (int i = 0; i < idprovs.size(); i++) {
            if(!existeCombinacion(idpaq,(Integer)idprovs.get(i),(Integer)idprovs.get(i)))
            sentenciaAgregaArma.executeUpdate("INSERT INTO charmingstudio.arma "+
                "(`Paquetes_idPaquetes`, `Provee_idProveedor`,`Provee_idServicios` )" + "VALUES("+
                "'" + idpaq+ "',"+
                "'" + idprovs.get(i) + "',"+              
                "'" + idservs.get(i) + "')");
        }
        
    }
    
    private boolean existeCombinacion(int a, int b, int c) throws SQLException {

        Statement sentencia=Conexion.createStatement();
        ResultSet busqueda=sentencia.executeQuery("SELECT * FROM charmingstudio.arma WHERE Paquetes_idPaquetes='"
                +a+ "' AND Provee_idProveedor='"+b+"' AND Provee_idServicios='"+c+"'");
        //busqueda.next();
        boolean existeComb = true;
        if (busqueda.wasNull()) {
            existeComb=false;

                
            }
        /*el else fue considerado, pero no es usado.*/

        return existeComb;
    }
}
