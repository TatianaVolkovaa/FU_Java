package project.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Employee {

    @SerializedName("employeeId")
    @Expose
    private Integer employeeId;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("surname")
    @Expose
    private String surname;

    @SerializedName("gender")
    @Expose
    private String gender;

    @SerializedName("birthdate")
    @Expose
    private String birthdate;

    @SerializedName("patronymic")
    @Expose
    private String patronymic;

    @SerializedName("phoneNumber")
    @Expose
    private String phoneNumber;

    @SerializedName("phoneNumber2")
    @Expose
    private String phoneNumber2;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("pass")
    @Expose
    private String pass;

    @SerializedName("pos")
    @Expose
    private String position;

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getPhoneNumber2() {
        return phoneNumber2;
    }

    public void setPhoneNumber2(String phoneNumber2) {
        this.phoneNumber2 = phoneNumber2;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return name + " " + surname + " " + patronymic;
    }
}
