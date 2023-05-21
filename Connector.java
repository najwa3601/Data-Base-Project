package DataBase;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Connector {

    private static String dbUsername = "root"; // Database username
    private static String dbPassword = "najwa1120"; // Database password
    private static String URL = "127.0.0.1"; // Server location
    private static String port = "3306"; // Port that mysql uses
    private static String dbName = "ALSini"; // Database on mysql to connect to
    private static Connection con;

    public static DBConn a = new DBConn(URL, port, dbName, dbUsername, dbPassword);

}

class DBConn {

    private Connection con;
    private String dbURL;
    private String dbUsername;
    private String dbPassword;
    private String URL;
    private String port;
    private String dbName;

    DBConn(String URL, String port, String dbName, String dbUsername, String dbPassword) {

        this.URL = URL;
        this.port = port;
        this.dbName = dbName;
        this.dbUsername = dbUsername;
        this.dbPassword = dbPassword;
    }

    public Connection connectDB() throws ClassNotFoundException, SQLException {

        dbURL = "jdbc:mysql://" + URL + ":" + port + "/" + dbName + "?verifyServerCertificate=false";

        Properties p = new Properties();
        p.setProperty("user", dbUsername);
        p.setProperty("password", dbPassword);
        p.setProperty("useSSL", "false");
        p.setProperty("autoReconnect", "true");

        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(dbURL, p);
        // new com.mysql.jdbc.Driver();
        // con = DriverManager.getConnection(dbURL,p);

        return con;
    }

    public void ExecuteStatement(String SQL) throws SQLException {

        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(SQL);
            stmt.close();

        } catch (SQLException s) {
            s.printStackTrace();
        }
    }
}



