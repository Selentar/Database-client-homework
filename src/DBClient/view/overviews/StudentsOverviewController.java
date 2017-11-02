package DBClient.view.overviews;

import DBClient.model.Student;
import DBClient.model.StudentWrapper;
import DBClient.model.WrapperInterface;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

public class StudentsOverviewController implements OverviewInterface {
    @FXML
    private TableView<Student> studentTable;
    @FXML
    private TableColumn<Student, Integer> idColumn;
    @FXML
    private TableColumn<Student, String> fullNameColumn;
    @FXML
    private TableColumn<Student, LocalDate> birthdayColumn;
    @FXML
    private TableColumn<Student, Integer> ageColumn;
    @FXML
    private TableColumn<Student, String> cityColumn;
    @FXML
    private TableColumn<Student, String> emailColumn;
    @FXML
    private TableColumn<Student, String> phoneColumn;

    //Ссылка на массив студентов
    private StudentWrapper studentWrapper;

    /**
     * Вызываетс главным приложением, которое дает ссылка на массив студентов
     * @param wrapper
     */
    @Override
    public void setWrapper(WrapperInterface wrapper) {
        if (wrapper instanceof StudentWrapper)
            this.studentWrapper = (StudentWrapper) wrapper;
        studentTable.setItems(studentWrapper.getStudents());
    }

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        fullNameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        birthdayColumn.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
    }

    public Student getSelectedStudent() {
        return studentTable.getSelectionModel().getSelectedItem();
    }
}
