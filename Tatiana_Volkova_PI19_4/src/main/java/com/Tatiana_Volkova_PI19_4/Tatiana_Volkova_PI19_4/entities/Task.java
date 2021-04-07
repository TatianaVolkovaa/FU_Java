package com.Tatiana_Volkova_PI19_4.Tatiana_Volkova_PI19_4.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Task {

    /*
    Задание имеет следующие атрибуты:
    1.Уникальный идентификатор
    2.Наименование
    3.Описание
    4.Дата выполнения
    5.Выполнено (значение Да/Нет)
    6.Категория
    7.Пользователь
    8.Дата создания
    9.Дата обновления
     */

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String daeadline;
    private Boolean readiness;

    //private String category;
    //Задание – Категория – многие ко многим
    @ManyToMany
    private Set<Category> categories;

    //private String client
    //Пользователь – Задание – один ко многим
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    //сделала строковыми, потому что пока не уверена как работать с датой
    private String createDate;
    private String updateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDaeadline() {
        return daeadline;
    }

    public void setDaeadline(String daeadline) {
        this.daeadline = daeadline;
    }

    public Boolean getReadiness() {
        return readiness;
    }

    public void setReadiness(Boolean readiness) {
        this.readiness = readiness;
    }

    public Set<Category> getCategory() {
        return categories;
    }

    public void setCategory(Set<Category> category) {
        this.categories = category;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
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

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

}
