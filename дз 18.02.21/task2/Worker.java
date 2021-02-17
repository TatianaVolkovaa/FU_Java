package com.company.task2;

public abstract class Worker {
    private int id;
    private String Name;

    public Worker(int id, String name) {
        this.id = id;
        this.Name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return Name;
    }

    public abstract double calculateSalary();
}
