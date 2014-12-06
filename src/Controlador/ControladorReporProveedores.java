package Controlador;

import Controlador.AdministraReportes;
import Modelo.Proveedor;
import Modelo.Servicio;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Lalo
 * @version 1.0
 * @created 19-sep-2014 02:36:13 p.m.
 */
public class ControladorReporProveedores implements AdministraReportes {

    public ControladorReporProveedores() {

    }
    @Override
    public Object generaReportes() {
        return null;
    }

    /**
     *
     * @param vector
     * @return 
     */
    @Override
    public boolean exportaReportes(Object vector) {
        return false;
    }
    
    public void generaPDF() {
        //ControladorProveedores ctrlProv=new ControladorProveedores();
        Document document = new Document() ; 
        ControladorProveedores ctrlProv=new ControladorProveedores();
        
        
        try {
            LinkedList<Proveedor> proveedoresBD=ctrlProv.obtenerTodosLosProveedoresConSusServicios();
            //LinkedList proveedores=ctrlProv.obtenerTodosLosProveedoresConSusServicios();
            Proveedor mejorBanquetera=buscaMenorPrecio(proveedoresBD,"Banquetera");
            Proveedor mejorCarpa=buscaMenorPrecio(proveedoresBD,"Carpa");
            Proveedor mejorIluminacion=buscaMenorPrecio(proveedoresBD,"Iluminacion");
            Proveedor mejorLugar=buscaMenorPrecio(proveedoresBD,"Lugar");
            Proveedor mejorMusica=buscaMenorPrecio(proveedoresBD,"Musica");
            
            PdfWriter.getInstance(document, new FileOutputStream("tablas.pdf"));
            document.open();
            
            // Este codigo genera una tabla de 3 columnas
            PdfPTable table = new PdfPTable(6);                
            
            // addCell() agrega una celda a la tabla, el cambio de fila
            // ocurre automaticamente al llenar la fila
            table.addCell("Servicio");
            table.addCell("Proveedor");
            table.addCell("Telefono");
            table.addCell("Direccion");
            table.addCell("Correo");
            table.addCell("Costo");
            
            agregaProveedorATabla(table,mejorBanquetera,"Banquetera");
            agregaProveedorATabla(table,mejorCarpa,"Carpa");
            agregaProveedorATabla(table,mejorIluminacion,"Iluminacion");
            agregaProveedorATabla(table,mejorLugar,"Lugar");
            agregaProveedorATabla(table,mejorMusica,"Musica");
            
            // Agregamos la tabla al documento            
            document.add(table);
            
            document.close();
            
        } catch ( DocumentException | FileNotFoundException ex) {
            Logger.getLogger(ControladorReporProveedores.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorReporProveedores.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
    public void agregaProveedorATabla(PdfPTable tabla, Proveedor proveedor, String servicio){
        tabla.addCell(servicio);
            tabla.addCell(proveedor.getNombrePersona());
            tabla.addCell(proveedor.getTelefonoPersona());
            tabla.addCell(proveedor.getDireccionPersona());
            tabla.addCell(proveedor.getCorreoPersona());
            float costo=devuelveServicio(proveedor,servicio).getCosto();
            tabla.addCell(Float.toString(costo));
    }
    
    
    public void creapdf(){
   
    }
    
    public Proveedor buscaMenorPrecio(LinkedList<Proveedor> proveedores, String servicio){
        Proveedor masBarato=null;
        
        
         for(Proveedor prov:proveedores){
             if(tieneServicio(prov,servicio)){
                 masBarato=prov;
                 break;
             }
         }
        Servicio buscado=devuelveServicio(masBarato,servicio);
        
        for(Proveedor prov2:proveedores){
            if(tieneServicio(prov2,servicio)){
                Servicio temp=devuelveServicio(prov2,servicio);
                System.out.println(temp.getCosto());
                System.out.println(buscado.getCosto());
                if(temp.getCosto()<buscado.getCosto()){
                    masBarato=prov2;
                    buscado=temp;
                }
            
            }
        }
        
        return masBarato;
    }
    
    public boolean tieneServicio(Proveedor prov, String servicio){
        for(Servicio serv:prov.getServiciosQueProvee()){
            if(serv.getServNombre().equals(servicio)){
                return true;
            }
        }
        return false;
    }
    
    public Servicio devuelveServicio(Proveedor prov, String servicio){
        for(Servicio serv:prov.getServiciosQueProvee()){
            if(serv.getServNombre().equals(servicio)){
                return serv;
            }
        }
        return null;
    }

}
