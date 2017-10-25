package DBClient.model;

import javafx.beans.property.*;
import java.time.LocalDate;

/**
 * Класс модель для студента
 */
public class Student {
    private final IntegerProperty id;
    private final StringProperty fullName;
    private final ObjectProperty<LocalDate> birthday;
    private final IntegerProperty age;
    private final StringProperty city;
    private final StringProperty eMail;
    private final StringProperty phone;

    /**
     * Тестовый конструктор
     * @param id
     * @param fullName
     */
    public Student(int id, String fullName) {
        this(id, fullName, LocalDate.of(1999, 4, 5),
                0, "DefaultCity", "dog@gmail.com", "9998887766");
    }

    public Student(int id, String fullName, LocalDate birthday,
                   int age, String city, String eMail, String phone)
    {
        this.id = new SimpleIntegerProperty(id);
        this.fullName = new SimpleStringProperty(fullName);
        this.birthday = new SimpleObjectProperty<>(birthday);
        this.age = new SimpleIntegerProperty(age);
        this.city = new SimpleStringProperty(city);
        this.eMail = new SimpleStringProperty(eMail);
        this.phone = new SimpleStringProperty(phone);
    }

    /*
    Дальше идут только геттеры и сеттеры
     */

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public String getFullName() {
        return fullName.get();
    }

    public StringProperty fullNameProperty() {
        return fullName;
    }

    public LocalDate getBirthday() {
        return birthday.get();
    }

    public ObjectProperty<LocalDate> birthdayProperty() {
        return birthday;
    }

    public int getAge() {
        return age.get();
    }

    public IntegerProperty ageProperty() {
        return age;
    }

    public String getCity() {
        return city.get();
    }

    public StringProperty cityProperty() {
        return city;
    }

    public String geteMail() {
        return eMail.get();
    }

    public StringProperty eMailProperty() {
        return eMail;
    }

    public String getPhone() {
        return phone.get();
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public void setFullName(String fullName) {
        this.fullName.set(fullName);
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday.set(birthday);
    }

    public void setAge(int age) {
        this.age.set(age);
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public void seteMail(String eMail) {
        this.eMail.set(eMail);
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }
}