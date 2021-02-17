package com.company.tasks_2_3;

public abstract class Worker {
    /**
     * Абстрактный класс работника.
     */
    private int id;
    private String name;
    private String surname;

    public Worker(int id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    /**
     * @return id работника.
     */
    public int getId() {
        return id;
    }

    /**
     * @return имя работника.
     */
    public String getName() {
        return name;
    }

    /**
     * @return фамилию работника.
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Метод для вычисления зарплаты.
     * Переопределяется в {@link HourSalaryWorker} и {@link FixedSalaryWorker}.
     *
     * @return заработную плату сотрулника.
     */
    public abstract double calculateSalary();
}