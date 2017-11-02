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
    private final StringProperty email;
    private final StringProperty phone;

    public Student() {
        id = new SimpleIntegerProperty();
        fullName = new SimpleStringProperty();
        birthday = new SimpleObjectProperty<>();
        age = new SimpleIntegerProperty();
        city = new SimpleStringProperty();
        email = new SimpleStringProperty();
        phone = new SimpleStringProperty();
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

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
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

    public void setEmail(String email) {
        this.email.set(email);
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }
}