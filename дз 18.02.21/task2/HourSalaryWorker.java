package com.company.task2;

public class HourSalaryWorker extends Worker{

    private double hourRate;

    public HourSalaryWorker(int id, String name, double hourRate) {
        super(id, name);
        this.hourRate = hourRate;
    }

    @Override
    public double calculateSalary() {
        return 20.8 * 8 * hourRate;
    }
}
