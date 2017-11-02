package DBClient.view;

import DBClient.MainApp;
import DBClient.enums.Tables;
import javafx.fxml.FXML;

public class MainViewController {

    private MainApp mainApp;

    @FXML
    public void handleTableStudents() {
        try {
            mainApp.showOverview(Tables.Students);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
