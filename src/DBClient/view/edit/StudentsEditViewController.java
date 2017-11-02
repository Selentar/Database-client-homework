package DBClient.view.edit;

import DBClient.model.Student;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;

import static DBClient.util.ErrorMessage.showErrorMessage;
import static DBClient.util.ErrorMessage.showInformationMessage;


public class StudentsEditViewController {
    @FXML
    private TextField idField;
    @FXML
    private TextField fullNameField;
    @FXML
    private TextField birthdayField;
    @FXML
    private TextField ageField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneField;

    private Student student;

    /**
     * Вызывается перед показом окна для задания значений по умолчанию
     * @param data
     */
    public void showSelectedData(Student data) {
        if (data == null) return;

        idField.setText(Integer.toString(data.getId()));
        if (data.getFullName() != null) fullNameField.setText(data.getFullName());
        if (data.getBirthday() != null) birthdayField.setText(data.getBirthday().toString());
        if (data.getAge() != 0) ageField.setText(Integer.toString(data.getAge()));
        if (data.getCity() != null) cityField.setText(data.getCity());
        if (data.getEmail() != null) emailField.setText(data.getEmail());
        if (data.getPhone() != null) phoneField.setText(data.getPhone());
    }

    /**
     * Действия при нажатии на кнопку OK
     */
    @FXML
    private void handleOK() {
        try {
            Student buf = new Student();
            if (idField.getText().trim().length() > 0)
                buf.setId(Integer.parseInt(idField.getText()));
            buf.setFullName(fullNameField.getText());
            if (birthdayField.getText().trim().length() > 0)
                buf.setBirthday(LocalDate.parse(birthdayField.getText()));
            if (ageField.getText().trim().length() > 0)
                buf.setAge(Integer.parseInt(ageField.getText()));
            buf.setCity(cityField.getText());
            buf.setEmail(emailField.getText());
            buf.setPhone(phoneField.getText());
            student = buf;
            windowsClose();
        } catch (Exception e) {
            showErrorMessage("Ошибка при распознавании данных!");
            e.printStackTrace();
        }
    }

    /**
     * Действия при нажатии на кнопку Cancel
     */
    @FXML
    private void handleCancel() {
        student = null;
        windowsClose();
    }

    /**
     * Данный метод закрывает окно
     */
    private void windowsClose() {
        Stage stage = (Stage) idField.getScene().getWindow();
        stage.close();
    }

    public Student getStudent() {
        return student;
    }
}
