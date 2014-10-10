package Controlador;

/**
 * @author Lalo
 * @version 1.0
 * @created 19-sep-2014 02:35:44 p.m.
 */
public interface AdministraReportes {

    public Object generaReportes();

    /**
     *
     * @param vector
     * @return verdadero o falso, dependiendo de si se pudo exportar el reporte
     * o no.
     */
    public boolean exportaReportes(Object vector);

}
