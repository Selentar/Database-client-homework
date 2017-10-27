package DBClient.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectorViewController {
    @FXML
    private TextField ipAddressField;
    @FXML
    private TextField databaseField;
    @FXML
    private TextField userNameField;
    @FXML
    private TextField passwordField;

    static final String DB_URL = "jdbc:mysql://localhost:3306/Lab1?autoReconnect=true&useSSL=false";
    static final String USER = "root";
    static final String PASS = "password";


    @FXML
    public void initialize() {

    }




    public static void main(String[] argv) {

        System.out.println("Testing connection to PostgreSQL JDBC");

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
            return;
        }

        System.out.println("PostgreSQL JDBC Driver successfully connected");
        Connection connection = null;

        try {
            connection = DriverManager
                    .getConnection(DB_URL, USER, PASS);

        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return;
        }

        if (connection != null) {
            System.out.println("You successfully connected to database now");
        } else {
            System.out.println("Failed to make connection to database");
        }
    }
}
