package com.company.tasks_2_3;

import java.io.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        //чтение - true или запись - false
        boolean flag = true;

        ArrayList<Worker> workers = new ArrayList<>();

        if (flag) {
            workers = Main.read_file("C:\\Users\\Tatiana\\IdeaProjects\\helpme_h1\\src\\com\\company\\tasks_2_3\\workers.txt");
        } else {
            workers.add(new FixedSalaryWorker(1, "Tatiana", "Volkova", 50000));
            workers.add(new FixedSalaryWorker(2, "Ekaterina", "Prishepa", 100000));
            workers.add(new FixedSalaryWorker(3, "Maks", "Kasyanov", 80000));
            workers.add(new HourSalaryWorker(4, "Vanya", "Bedak", 250.5));
            workers.add(new HourSalaryWorker(5, "Veronika", "Sokolova", 260));
            workers.add(new HourSalaryWorker(6, "Bair", "Badmaev", 200.1));
        }

        if(workers.size() != 0) {

            workers.sort((o1, o2) -> {
                if (o1.calculateSalary() == o2.calculateSalary())
                    return o1.getName().compareTo(o2.getName());
                return (int) (o2.calculateSalary() - o1.calculateSalary());
            });

            System.out.println("ID -- NAME -- SURNAME -- SALARY");
            for (Worker worker : workers) {
                System.out.println(worker.getId() + "  " + worker.getName() + "  " + worker.getSurname() + "  " + worker.calculateSalary());
            }
            System.out.println();

            System.out.println("------ TOP 5 ------");
            for (int i = 0; i < 5; i++) {
                Worker worker = workers.get(i);
                System.out.println(worker.getName());
            }
            System.out.println();

            System.out.println("------ Last 3 ID ------");
            for (int i = workers.size() - 3; i < workers.size(); i++) {
                Worker worker = workers.get(i);
                System.out.println(worker.getId());
            }
            Main.write_file(workers);
        }

        else {
            System.out.println("ФАЙЛ НЕ ПРОЧТЁН!");
        }
    }

    private static ArrayList<Worker> read_file(String filePath) {
        // чтение из файла
        ArrayList<Worker> workers = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String worker = bufferedReader.readLine();
            while (worker != null) {
                int id = Integer.parseInt(worker.split(" ")[0]);
                String name = worker.split(" ")[1];
                String surname = worker.split(" ")[2];
                double rateOrSal = Double.parseDouble(worker.split(" ")[3]);
                if (rateOrSal < 9000) {
                    workers.add(new HourSalaryWorker(id, name, surname, rateOrSal));
                } else {
                    workers.add(new FixedSalaryWorker(id, name, surname, rateOrSal));
                }
                worker = bufferedReader.readLine();
            }
            return workers;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return workers;
        } catch (IOException e) {
            e.printStackTrace();
            return workers;
        }
    }

    private static void write_file(ArrayList<Worker> workers) throws IOException {
        // запись в файл
        FileWriter writer = new FileWriter("workers_new.txt");
        for (Worker w : workers) {
            writer.write(w.getId() + " " + w.getName() + " " + w.getSurname() + " " + (int)w.calculateSalary() + "\n");
        }
        writer.flush();
    }
}
