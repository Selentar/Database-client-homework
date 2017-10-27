package DBClient;

import DBClient.model.StudentWrapper;
import DBClient.view.StudentsOverviewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {
    private final static String STUDENTS_OVERVIEW = "view/StudentsOverview.fxml";

    private Stage primaryStage;
    private BorderPane rootLayout;

    private StudentWrapper studentWrapper;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("DBClient");

        initRootLayout();

        showOverview(STUDENTS_OVERVIEW);
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
    }

    /**
     * Загружает необходимую таблицу
     * @param path
     * @throws Exception
     */
    public void showOverview(String path) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(path));
        Parent overview = loader.load();
        rootLayout.setCenter(overview);

        if(path.equals(STUDENTS_OVERVIEW)) {
            if (studentWrapper != null);
            studentWrapper = new StudentWrapper();
            StudentsOverviewController controller = loader.getController();
            controller.setStudentWrapper(studentWrapper);
        }
    }



    public static void main(String[] args) {
        launch(args);
    }
}
