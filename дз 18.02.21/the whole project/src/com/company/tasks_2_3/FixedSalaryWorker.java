package com.company.tasks_2_3;

public class FixedSalaryWorker extends Worker{
    /**
     * Класс работника с фиксированной оплатой труда.
     *
     * @param id - уникальный номер работника.
     * @param name - имя работника.
     * @param surname - фамилия работника.
     * @param fixedSalary - фиксированная месячная оплата.
     */
    private double fixedSalary;

    public FixedSalaryWorker(int id, String name, String surname, double fixedSalary) {
        super(id, name, surname);
        this.fixedSalary = fixedSalary;
    }

    /**
     * Метод для вычисления зарплаты.
     *
     * @return зарплату, которая равна ставке.
     */
    @Override
    public double calculateSalary() {
        return fixedSalary;
    }
}
