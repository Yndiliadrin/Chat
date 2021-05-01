package Model;

import javafx.beans.property.*;

public class User {
    /**
     *  username: String
     *  password: String
     *  age: Int
     *  gender: String
     *  interest: String
     */

    private IntegerProperty ID = new SimpleIntegerProperty(this, "ID");
    private StringProperty username = new SimpleStringProperty(this, "username");
    private StringProperty password = new SimpleStringProperty(this, "password");
    private IntegerProperty age = new SimpleIntegerProperty(this, "age");
    private StringProperty gender = new SimpleStringProperty(this, "gender");
    private StringProperty interest = new SimpleStringProperty(this, "interest");
    private IntegerProperty role = new SimpleIntegerProperty(this, "role");

    public int getID() {
        return ID.get();
    }

    public IntegerProperty IDProperty() {
        return ID;
    }

    public void setID(int ID) {
        this.ID.set(ID);
    }

    public String getUsername() {
        return username.get();
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public int getAge() {
        return age.get();
    }

    public IntegerProperty ageProperty() {
        return age;
    }

    public void setAge(int age) {
        this.age.set(age);
    }

    public String getGender() {
        return gender.get();
    }

    public StringProperty genderProperty() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender.set(gender);
    }

    public String getInterest() {
        return interest.get();
    }

    public StringProperty interestProperty() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest.set(interest);
    }

    public int getRole() {
        return role.get();
    }

    public IntegerProperty roleProperty() {
        return role;
    }

    public void setRole(int role) {
        this.role.set(role);
    }

}
