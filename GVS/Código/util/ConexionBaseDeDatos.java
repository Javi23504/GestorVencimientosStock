package gvs.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Javi
 */

public class ConexionBaseDeDatos {

    private static final String URL = "jdbc:mysql://localhost:3306/gvsdb";
    private static final String USER = "root";
    private static final String PASSWORD = "Janais235!!!";

    public static Connection getConexion() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
        }
        return conn;
    }

    public static void main(String[] args) {
        Connection conn = getConexion();
        if (conn != null) {
            System.out.println("Conexión establecida correctamente");
            try {
                conn.close();
            } catch (SQLException ex) {
            }
        } else {
            System.out.println("Conexión fallida");
        }
    }

    public static void cerrarConexion(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Conexión cerrada");
            } catch (SQLException e) {
                System.err.println("Error al cerrar: " + e.getMessage());
            }
        }
    }
}
