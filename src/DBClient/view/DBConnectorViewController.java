package DBClient.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.*;

import static DBClient.util.ErrorMessage.showErrorMessage;
import static DBClient.util.ErrorMessage.showInformationMessage;

public class DBConnectorViewController {
    @FXML
    private TextField ipAddressField;
    @FXML
    private TextField databaseField;
    @FXML
    private TextField userNameField;
    @FXML
    private TextField passwordField;

    private Connection dbConnection;

    @FXML
    private void handleConnectToDB() {
        if (ipAddressField.getCharacters().length() == 0 ||
                databaseField.getCharacters().length() == 0 ||
                userNameField.getCharacters().length() == 0 ||
                passwordField.getCharacters().length() == 0)
        {
            showInformationMessage("Все поля должны быть заолненны!");
            return;
        }

        String url = "jdbc:mysql://" + ipAddressField.getCharacters() + "/" +
                databaseField.getCharacters() + "?autoReconnect=true&useSSL=false";
        String user = userNameField.getCharacters().toString();
        String password = passwordField.getCharacters().toString();

        try {
            dbConnection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            showErrorMessage("Все поля должны быть заолненны!");
            e.printStackTrace();
        }

        if (dbConnection != null) handleClose();
    }

    @FXML
    private void fastDataInsert() {
        ipAddressField.setText("localhost:3306");
        databaseField.setText("Lab1");
        userNameField.setText("root");
        passwordField.setText("password");
    }

    @FXML
    private void handleClose() {
        Stage stage = (Stage) ipAddressField.getScene().getWindow();
        stage.close();
    }

    public Connection getDBConnection() {
        return dbConnection;
    }
}
