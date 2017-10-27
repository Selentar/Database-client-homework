package DBClient.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Класс для связи класса Student с базой данных
 */
public class StudentWrapper {
    private ObservableList<Student> students;

    public StudentWrapper() {
        //Тестовые студенты ;)
        students = FXCollections.observableArrayList();
        students.add(new Student(1, "Один"));
        students.add(new Student(2, "Два"));
        students.add(new Student(3, "Три"));
        students.add(new Student(4, "Четыре"));
    }

    public ObservableList<Student> getStudents() {
        return students;
    }
}
