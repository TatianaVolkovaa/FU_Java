package com.company.task2;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        ArrayList<Worker> workers = new ArrayList<>() {{
            add(new FixedSalaryWorker(1, "Tatiana", 50));
            add(new FixedSalaryWorker(2, "Ekaterina", 100));
            add(new FixedSalaryWorker(3, "Maks", 80));
            add(new HourSalaryWorker(4, "Vanya", 25.5));
            add(new HourSalaryWorker(5, "Vanya", 25.5));

        }};
    }
}
