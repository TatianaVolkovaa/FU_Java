package com.Tatiana_Volkova_PI19_4.Tatiana_Volkova_PI19_4.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Category {

    /*
    Категория имеет следующие атрибуты:
    1.Уникальный идентификатор
    2.Наименование
    3.Дата создания
    4.Дата обновления
     */

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    //сделала строковыми, потому что пока не уверена как работать с датой
    private String createDate;
    private String updateDate;

    //Задание – Категория – многие ко многим
    @ManyToMany
    private Set<Task> tasks;

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

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

}

