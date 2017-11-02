package DBClient;

import DBClient.enums.Tables;
import DBClient.model.StudentWrapper;
import DBClient.view.CRUDPanelViewController;
import DBClient.view.DBConnectorViewController;
import DBClient.view.MainViewController;
import DBClient.view.overviews.StudentsOverviewController;
import DBClient.view.edit.StudentsEditViewController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.Connection;

public class MainApp extends Application {
    private final static String CRUD_PANEL = "view/CRUDPanelView.fxml";

    private Stage primaryStage;
    private BorderPane rootLayout;

    private Connection dbConnection;

    private Tables currentTable;


    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("DBClient");

        showDBConnector();
        Tables.Students.getTable().setWrapper(new StudentWrapper(dbConnection, this));

        initRootLayout();
    }

    /**
     * Инициализирует корневой макет
     * @throws Exception
     */
    public void initRootLayout() throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("view/MainView.fxml"));
        rootLayout = (BorderPane) loader.load();
        primaryStage.setScene(new Scene(rootLayout));
        primaryStage.show();

        MainViewController controller = loader.getController();
        controller.setMainApp(this);
    }

    /**
     * Загружает необходимую таблицу и панель CRUD для нее
     * @param table
     * @throws Exception
     */
    public void showOverview(Tables table) throws Exception {
        currentTable = table;

        FXMLLoader overviewLoader = new FXMLLoader();
        overviewLoader.setLocation(getClass().getResource(table.getTable().getOverviewPath()));
        Parent overview = overviewLoader.load();
        rootLayout.setCenter(overview);

        if(table == Tables.Students) {
            table.getTable().getWrapper().refresh();
            StudentsOverviewController controller = overviewLoader.getController();
            Tables.Students.getTable().setOverviewController(controller);
            controller.setWrapper(table.getTable().getWrapper());
        }

        FXMLLoader panelLoader = new FXMLLoader();
        panelLoader.setLocation(getClass().getResource(CRUD_PANEL));
        Parent panel = panelLoader.load();
        ((BorderPane) rootLayout.getRight()).setCenter(panel);
        ((CRUDPanelViewController) panelLoader.getController()).setMainApp(this);
    }

    /**
     * Вызывает окно логина в БД.
     * @throws Exception
     */
    public void showDBConnector() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("view/DBConnectorView.fxml"));
        Parent connectorView = loader.load();

        Stage stage = new Stage();
        stage.setTitle("Авторизация");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(primaryStage);
        Scene scene = new Scene(connectorView);
        stage.setScene(scene);

        DBConnectorViewController controller = loader.getController();

        stage.showAndWait();

        dbConnection = controller.getDBConnection();
        if (dbConnection == null) {
            Platform.exit();
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Возвращает текущую рабочую таблицу
     * @return
     */
    public Tables getCurrentTable() {
        return currentTable;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
