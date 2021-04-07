package com.Tatiana_Volkova_PI19_4.Tatiana_Volkova_PI19_4.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Client {

    /*
    Пользователь имеет следующие атрибуты:
    1.Уникальный идентификатор
    2.Логин
    3.Фамилия
    4.Имя
    5.Отчество
    6.Дата рождения
    7.Дата создания
    8.Дата обновления
     */

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String login;

    private String lastName;
    private String firstName;
    private String middleName;
    //сделала строковыми, потому что пока не уверена как работать с датой
    private String birthDate;
    private String createDate;
    private String updateDate;

    //Пользователь – Задание – один ко многим
    @OneToMany(mappedBy = "client")
    private Set<Task> tasks;

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }
}
