package com.company.task2;

public class FixedSalaryWorker extends Worker{

    private double fixedSalary;

    public FixedSalaryWorker(int id, String name, double fixedSalary) {
        super(id, name);
        this.fixedSalary = fixedSalary;
    }

    @Override
    public double calculateSalary() {
        return fixedSalary;
    }
}
