package Controlador.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Romario
 */
public class ConexionBaseDatos {

    //se usará el patrón de diseño singleton:
    private static ConexionBaseDatos instanciaDeConexionBaseDeDatos = new ConexionBaseDatos();

    // El constructor privado no permite que se genere un constructor por defecto.
    // (con mismo modificador de acceso que la definición de la clase) 
    private ConexionBaseDatos() {
    }

    /**
     * Se encarga de usar el patrón de diseño singleton.
     *
     * @return la instancia de la clase.
     */
    public static ConexionBaseDatos getInstancia() {
        return instanciaDeConexionBaseDeDatos;
    }

    /**
     * Este método se encarga de establecer la conexión de la base de datos para
     * poder tomar información de ella.
     *
     * @return la conexión con la base de datos.
     * @throws SQLException si ocurre algún error de que no se haya podido
     * establecer la conexión, lanza una excepción.
     */
    public Connection getConexionBD() throws SQLException {

        DriverManager.registerDriver(new com.mysql.jdbc.Driver());

        String host = "jdbc:mysql://localhost:3306/charmingstudio";
        String nombreUsuario = "root";
        String contraseniaUsuario = "";

        Connection conexion = DriverManager.getConnection(host, nombreUsuario, contraseniaUsuario);

        return conexion;

    }
}
