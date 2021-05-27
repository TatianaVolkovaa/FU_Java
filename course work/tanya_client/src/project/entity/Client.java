package project.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Client {


    @SerializedName("clientId")
    @Expose
    private Integer clientId;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("surname")
    @Expose
    private String surname;

    @SerializedName("patronymic")
    @Expose
    private String patronymic;

    @SerializedName("birthdate")
    @Expose
    private String birthdate;

    @SerializedName("gender")
    @Expose
    private String gender;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("phoneNumber")
    @Expose
    private String phoneNumber;

    @SerializedName("phoneNumber2")
    @Expose
    private String phoneNumber2;

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
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

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber2() {
        return phoneNumber2;
    }

    public void setPhoneNumber2(String phoneNumber2) {
        this.phoneNumber2 = phoneNumber2;
    }

    @Override
    public String toString() {
        return name + " " + surname + " " + patronymic;
    }
}
