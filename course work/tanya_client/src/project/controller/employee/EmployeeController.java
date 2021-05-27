package project.controller.employee;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import project.Main;
import project.entity.Booking;
import project.entity.Employee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class EmployeeController {

    private Main main;
    private ObservableList<Employee> employees;

    public void setMainApp(Main mainApp) {
        this.main = mainApp;

        String pos = mainApp.getEmployee().getPosition();
        //администратор
        if (!pos.equals("admin")) {
            create.setDisable(true);
            edit.setDisable(true);
        }
    }

    public void setEmployees(ObservableList<Employee> employees) {
        this.employees = employees;
        employeeTable.setItems(employees);
    }

    @FXML
    private TableView<Employee> employeeTable;
    @FXML
    private TableColumn<Employee, String> nameColumn;
    @FXML
    private TableColumn<Employee, String> surnameColumn;
    @FXML
    private TableColumn<Employee, String> patronymicColumn;
    @FXML
    private TableColumn<Employee, String> posColumn;
    @FXML
    private Label birthdate;
    @FXML
    private Label gender;
    @FXML
    private Label email;
    @FXML
    private Label phone;
    @FXML
    private Label pos;
    @FXML
    private Button create;
    @FXML
    private Button edit;

    @FXML
    public void initialize() {
        employeeTable.getSelectionModel().selectedItemProperty().addListener(
                ((observableValue, oldValue, newValue) -> showEmployeeDetails(newValue)));

        nameColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getName()));
        surnameColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getSurname()));
        patronymicColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getPatronymic()));
        posColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getPosition()));
    }

    private void showEmployeeDetails(Employee newValue) {
        if (newValue != null) {
            birthdate.setText(newValue.getBirthdate());
            gender.setText(newValue.getGender());
            email.setText(newValue.getEmail());
            phone.setText(newValue.getPhoneNumber());
            pos.setText(newValue.getPosition());
        }
    }

    @FXML
    public void createEmployee() {
        Employee employee = new Employee();
        boolean isOkClicked = showEditDialog(employee);
        if (isOkClicked) {
            try {
                saveEmployee(employee);
                employees.clear();
                employees.addAll(main.loadEmployees());
                showNotification("создан");

            } catch (Exception e) {
                main.showError();
            }
        }
    }

    @FXML
    public void updateEmployee() {
        int selectedIndex = employeeTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Employee employee = employeeTable.getItems().get(selectedIndex);

            boolean isOkClicked = showEditDialog(employee);
            if (isOkClicked) {
                employeeTable.getItems().set(selectedIndex, employee);
                showEmployeeDetails(employee);
                try {
                    saveEmployee(employee);
                    showNotification("изменен");
                } catch (Exception e) {
                    main.showError();
                }
            }
        } else {
            showAlert();
        }
    }

    private boolean showEditDialog(Employee employee) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/employee/EmployeeEditDialog.fxml"));
            AnchorPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Редактирование сотрудника");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(main.getPrimaryStage());

            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            EmployeeEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setEmployee(employee);
            dialogStage.showAndWait();

            return controller.isOkClicked();

        } catch (IOException e) {
            return false;
        }
    }

    private void saveEmployee(Employee employee) {
        try {
            URL url = new URL("http://localhost:8080/employees");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

            String json = new Gson().toJson(employee);
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = json.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
                os.flush();
            }

            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }

                System.out.println("Выполнен запрос: " + response);
            }

        } catch (Exception e) {
            main.showError();
        }
    }

    @FXML
    public void showStats() {
        int selectedIndex = employeeTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Employee employee = employeeTable.getItems().get(selectedIndex);
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(main.getClass().getResource("/project/view/employee/BookingHistoryLayout.fxml"));
                AnchorPane page = loader.load();

                Stage dialogStage = new Stage();
                dialogStage.setTitle("История продаж");
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.initOwner(main.getPrimaryStage());

                Scene scene = new Scene(page);
                dialogStage.setScene(scene);

                BookingHistoryController controller = loader.getController();
                controller.setBookings(getEmployeeBookings(employee.getEmployeeId()));
                dialogStage.showAndWait();

            } catch (IOException e) {
                e.printStackTrace();
                main.showError();
            }
        }
    }

    private ObservableList<Booking> getEmployeeBookings(int id) {
        try {
            URL url = new URL("http://localhost:8080/bookings/by_employee/" + id);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();

            String stringResponse = response.toString();
            List<Booking> responseList = new Gson().fromJson(stringResponse, new TypeToken<List<Booking>>() {
            }.getType());
            return FXCollections.observableArrayList(responseList);

        } catch (Exception e) {
            main.showError();
            return FXCollections.observableArrayList();
        }
    }

    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(main.getPrimaryStage());
        alert.setTitle("Ошибка");
        alert.setHeaderText("Вы не выбрали клиента из таблицы!");
        alert.showAndWait();
    }

    private void showNotification(String type) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(main.getPrimaryStage());
        alert.setTitle("Информация");
        alert.setHeaderText("Сотрудник успешно " + type);
        alert.showAndWait();
    }

}
