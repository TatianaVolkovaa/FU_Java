package com.company.tasks_2_3;

public class HourSalaryWorker extends Worker{
    /**
     * Класс работника с почасовой оплатой труда.
     *
     * @param id - уникальный номер работника.
     * @param name - имя работника.
     * @param surname - фамилия работника.
     * @param hourRate - ставка в час.
     */
    private double hourRate;

    public HourSalaryWorker(int id, String name, String surname, double hourRate) {
        super(id, name, surname);
        this.hourRate = hourRate;
    }

    /**
     * Метод для вычисления зарплаты.
     *
     * @return зарплату, которая равна 20.8 * 8 * ставка в час.
     */
    @Override
    public double calculateSalary() {
        return 20.8 * 8 * hourRate;
    }
}