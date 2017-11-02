package DBClient.model;

import DBClient.MainApp;
import DBClient.enums.Table;
import DBClient.enums.Tables;
import DBClient.view.edit.StudentsEditViewController;
import DBClient.view.overviews.StudentsOverviewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import static DBClient.util.ErrorMessage.showInformationMessage;

/**
 * Класс для связи класса Student с базой данных
 */
public class StudentWrapper implements WrapperInterface {
    private ObservableList<Student> students;

    private Connection dbConnection;
    private MainApp mainApp;

    private Table thisTable = Tables.Students.getTable();

    public StudentWrapper(Connection dbConnection, MainApp mainApp) {
        this.dbConnection = dbConnection;
        this.mainApp = mainApp;
    }

    /**
     * Заполняет список студентов данными из БД
     */
    @Override
    public void refresh() {
        final String query = "SELECT * FROM Students";
        if (students == null) students = FXCollections.observableArrayList();
        students.removeAll(students);

        try (
                Statement statement = dbConnection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        )
        {
            while (resultSet.next()) {
                Student buf = new Student();
                buf.setId(resultSet.getInt(1));
                buf.setFullName(resultSet.getString(2));
                if (resultSet.getDate(3) != null)
                    buf.setBirthday((LocalDate) resultSet.getDate(3).toLocalDate());
                buf.setAge(resultSet.getInt(4));
                buf.setCity(resultSet.getString(5));
                buf.setEmail(resultSet.getString(6));
                buf.setPhone(resultSet.getString(7));
                students.add(buf);
            }
            System.out.println("Выполнено: " + query);
        } catch (SQLException sqlEx) {
            System.out.println("Ошибка при выполнении запроса: " + query);
            showInformationMessage("Ошибка при выполнении запроса: " + query);
            sqlEx.printStackTrace();
        }
    }

    /**
     * Вставляет запись в БД
     */
    @Override
    public void insert() {
        Student student = null;
        try {
            student = showStudentInsertDialog();
            if (student == null) return;
        }
        catch (Exception e) {
            System.out.println("Не удалось вызвать диалог редактирования!");
            e.printStackTrace();
            return;
        }

        String query = "INSERT INTO " + thisTable.getName() + " (id, name";
        String values = "VALUES (" + student.getId() + ", '" + student.getFullName() + "'";

        if (student.getBirthday() != null) {
            query += ", birthDate";
            values += ", '" + student.getBirthday() + "'";
        }
        if (student.getAge() != 0) {
            query += ", age";
            values += ", " + student.getAge();
        }
        if (student.getCity() != null) {
            query += ", city";
            values += ", '" + student.getCity() + "'";
        }
        if (student.getEmail() != null) {
            query += ", EMail";
            values += ", '" + student.getEmail() + "'";
        }
        if (student.getPhone() != null) {
            query += ", Phone";
            values += ", '" + student.getPhone() + "'";
        }

        query += ") ";
        values += ");";
        query += values;

        try ( Statement statement = dbConnection.createStatement())
        {
            statement.executeUpdate(query);
            students.add(student);
            System.out.println("Выполнено: " + query);
        } catch (SQLException sqlEx) {
            System.out.println("Ошибка при выполнении запроса: " + query);
            showInformationMessage("Ошибка при выполнении запроса: " + query);
            sqlEx.printStackTrace();
        }
    }

    /**
     * Обновляет запись в БД
     */
    @Override
    public void update() {
        StudentsOverviewController controller = (StudentsOverviewController) thisTable.getOverviewController();
        Student currentStudent = controller.getSelectedStudent();
        if (currentStudent == null) {
            showInformationMessage("Не выбранны данные для удаления!");
            return;
        }

        Student student = null;
        try {
            student = showStudentUpdateDialog(currentStudent);
            if (student == null) return;
        }
        catch (Exception e) {
            System.out.println("Не удалось вызвать диалог редактирования!");
            e.printStackTrace();
            return;
        }

        String query = "UPDATE " + thisTable.getName() + " \n" +
                "SET ";

        if (!student.getFullName().equals(currentStudent.getFullName())) {
            query += "name = '" + student.getFullName() + "',";
        }
        if (student.getBirthday() != currentStudent.getBirthday()) {
            query += "birthDate = '" + student.getBirthday() + "',";
        }
        if (student.getAge() != currentStudent.getAge()) {
            query += "age = " + student.getAge() + ",";
        }
        if (!student.getCity().equals(currentStudent.getCity())) {
            query += "city = '" + student.getCity() + "',";
        }
        if (!student.getEmail().equals(currentStudent.getEmail())) {
            query += "EMail = '" + student.getEmail() + "',";
        }
        if (!student.getPhone().equals(currentStudent.getPhone())) {
            query += "Phone = '" + student.getPhone() + "',";
        }
        query = query.substring(0, query.length()-1);

        query += "\nWHERE id = " + student.getId();

        try ( Statement statement = dbConnection.createStatement())
        {
            statement.executeUpdate(query);
            students.remove(currentStudent);
            students.add(student);
            System.out.println("Выполнено: " + query);
        } catch (SQLException sqlEx) {
            System.out.println("Ошибка при выполнении запроса: " + query);
            showInformationMessage("Ошибка при выполнении запроса: " + query);
            sqlEx.printStackTrace();
        }
    }

    /**
     * Удаляет запись из БД
     */
    @Override
    public void delete() {
        StudentsOverviewController controller = (StudentsOverviewController) thisTable.getOverviewController();
        Student currentStudent = controller.getSelectedStudent();
        if (currentStudent == null) {
            showInformationMessage("Не выбранны данные для удаления!");
            return;
        }

        String query = "DELETE FROM " + thisTable.getName() +
                " WHERE id=" + currentStudent.getId();

        try ( Statement statement = dbConnection.createStatement())
        {
            statement.executeUpdate(query);
            students.remove(currentStudent);
            System.out.println("Выполнено: " + query);
        } catch (SQLException sqlEx) {
            System.out.println("Ошибка при выполнении запроса: " + query);
            showInformationMessage("Ошибка при выполнении запроса: " + query);
            sqlEx.printStackTrace();
        }
    }

    /**
     * Поиск записей в БД
     */
    @Override
    public void search() {
        Student searchStudent = null;
        try {
            searchStudent = showStudentInsertDialog();
            if (searchStudent == null) return;
        }
        catch (Exception e) {
            System.out.println("Не удалось вызвать диалог поиска!");
            e.printStackTrace();
            return;
        }

        String query = "SELECT * FROM " + thisTable.getName() + " \n" +
                "WHERE ";
        if (searchStudent.getId() != 0)
            query += "id = " + searchStudent.getId() + " AND ";
        if (!searchStudent.getFullName().equals(""))
            query += "name = '" + searchStudent.getFullName() + "' AND ";
        if (searchStudent.getBirthday() != null)
            query += "birthDate = '" + searchStudent.getBirthday() + "' AND";
        if (searchStudent.getAge() != 0)
            query += "age = " + searchStudent.getAge() + " AND ";
        if (!searchStudent.getCity().equals(""))
            query += "city = '" + searchStudent.getCity() + "' AND ";
        if (!searchStudent.getEmail().equals(""))
            query += "EMail = '" + searchStudent.getEmail() + "' AND ";
        if (!searchStudent.getPhone().equals(""))
            query += "Phone = '" + searchStudent.getPhone() + "' AND ";

        query = query.substring(0, query.length()-4);

        students.removeAll(students);

        try (
                Statement statement = dbConnection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        )
        {
            while (resultSet.next()) {
                Student buf = new Student();
                buf.setId(resultSet.getInt(1));
                buf.setFullName(resultSet.getString(2));
                if (resultSet.getDate(3) != null)
                    buf.setBirthday((LocalDate) resultSet.getDate(3).toLocalDate());
                buf.setAge(resultSet.getInt(4));
                buf.setCity(resultSet.getString(5));
                buf.setEmail(resultSet.getString(6));
                buf.setPhone(resultSet.getString(7));
                students.add(buf);
            }
            System.out.println("Выполнено: " + query);
        } catch (SQLException sqlEx) {
            System.out.println("Ошибка при выполнении запроса: " + query);
            showInformationMessage("Ошибка при выполнении запроса: " + query);
            sqlEx.printStackTrace();
        }
    }

    /**
     * Показывает диалог вставки пользователя в БД
     * @return
     * @throws Exception
     */
    private Student showStudentInsertDialog() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(thisTable.getEditViewPath()));
        Parent parent = loader.load();

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Edit");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(mainApp.getPrimaryStage());

        Scene scene = new Scene(parent);
        dialogStage.setScene(scene);

        StudentsEditViewController controller = loader.getController();
        dialogStage.showAndWait();

        return controller.getStudent();
    }

    /**
     * Показывает диалог редактирования пользователя в БД
     * @param currentStudent
     * @return
     * @throws Exception
     */
    private Student showStudentUpdateDialog(Student currentStudent) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(thisTable.getEditViewPath()));
        Parent parent = loader.load();

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Edit");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(mainApp.getPrimaryStage());

        Scene scene = new Scene(parent);
        dialogStage.setScene(scene);

        StudentsEditViewController controller = loader.getController();
        controller.showSelectedData(currentStudent);
        dialogStage.showAndWait();

        return controller.getStudent();
    }

    public ObservableList<Student> getStudents() {
        return students;
    }
}
