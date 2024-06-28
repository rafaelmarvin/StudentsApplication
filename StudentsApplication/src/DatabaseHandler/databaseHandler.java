package DatabaseHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class databaseHandler {

    private static final String USERNAME = "root";
    public static final String PASSWORD = "";
    public static final String HOSTNAME = "localhost";
    public static final int PORT = 3306;
    public static final String DATABASE = "rekapMahasiswa";

    private static Connection connection;

    public static Connection getConnection() {
        return connection;
    }

    public static void connect() {
        // Create the url
        String url = "jdbc:mariadb://" + HOSTNAME + ":" + PORT + "/" + DATABASE;
        try {
            connection = DriverManager.getConnection(url, USERNAME, PASSWORD);
            System.out.println("Successfully connected to the MySQL database!");
        } catch (SQLException ex) {
            System.out.println("Failed to connect to the database!");
            ex.printStackTrace();
        }
    }

    public static void close() {
        try {
            if (connection != null) connection.close();
        } catch (SQLException ex) {
            System.out.println("Error while trying to close connection!");
            ex.printStackTrace();
        }
    }

}