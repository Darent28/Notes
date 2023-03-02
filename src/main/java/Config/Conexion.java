package Config;
import java.sql.*;

public class Conexion {

    private Connection con;
    private String serverName = "localhost";
    private String portNumber = "3306";
    private String databaseName = "DB_Web_Programing";
    private String url = "jdbc:mysql://" + serverName + ":" + portNumber + "/" + databaseName;
    private String userName = "root";
    private String password = "1234";

    public Conexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, userName, password);
        } catch (Exception ex) {
            System.out.println("Error" + ex);
        }
    }

    public Connection getConnection() {
        return con;
    }
}
