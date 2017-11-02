package DBClient.view;

import DBClient.MainApp;
import DBClient.enums.Table;
import javafx.fxml.FXML;

public class CRUDPanelViewController {

    private MainApp mainApp;

    @FXML
    private void handleRefresh() throws Exception {
        Table currentTable = mainApp.getCurrentTable().getTable();
        currentTable.getWrapper().refresh();
    }

    @FXML
    private void handleInsert() throws Exception {
        Table currentTable = mainApp.getCurrentTable().getTable();
        currentTable.getWrapper().insert();
    }

    @FXML
    private void handleUpdate() throws Exception {
        Table currentTable = mainApp.getCurrentTable().getTable();
        currentTable.getWrapper().update();
    }

    @FXML
    private void handleDelete() {
        Table currentTable = mainApp.getCurrentTable().getTable();
        currentTable.getWrapper().delete();
    }

    @FXML
    private void handleSearch() {
        Table currentTable = mainApp.getCurrentTable().getTable();
        currentTable.getWrapper().search();
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
