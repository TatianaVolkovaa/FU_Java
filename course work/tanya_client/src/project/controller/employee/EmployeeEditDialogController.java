package project.controller.employee;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import project.Main;
import project.entity.Employee;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EmployeeEditDialogController {

    private Stage dialogStage;
    private Employee employee;
    private boolean okClicked = false;
    private Main main;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public void setMain(Main main) {
        this.main = main;
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
        //, "Администратор"
        position.setItems(FXCollections.observableArrayList("operator", "menedz", "admin"));

        if (employee.getEmployeeId() != null) {
            name.setText(employee.getName());
            surname.setText(employee.getSurname());
            patronymic.setText(employee.getPatronymic());
            birthdate.setValue(LocalDate.parse(employee.getBirthdate(), formatter));
            gender.setText(employee.getGender());
            email.setText(employee.getEmail());
            phone.setText(employee.getPhoneNumber());
            pass.setText(employee.getPass());
            position.setValue(employee.getPosition());
        }
    }

    @FXML
    private TextField name;
    @FXML
    private TextField surname;
    @FXML
    private TextField patronymic;
    @FXML
    private DatePicker birthdate;
    @FXML
    private TextField gender;
    @FXML
    private TextField email;
    @FXML
    private TextField phone;
    @FXML
    private ChoiceBox<String> position;
    @FXML
    private TextField pass;

    @FXML
    public void handleOkAction() {
        boolean flag = true;
        if (name.getText() == null || name.getText().isEmpty()) {
            flag = false;
        }

        if (surname.getText() == null || surname.getText().isEmpty()) {
            flag = false;
        }

        if (patronymic.getText() == null || patronymic.getText().isEmpty()) {
            flag = false;
        }

        if (birthdate.getValue() == null) {
            flag = false;
        }
        if (gender.getText() == null || gender.getText().isEmpty()) {
            flag = false;
        }

        if (email.getText() == null || email.getText().isEmpty()) {
            flag = false;
        }

        if (phone.getText() == null || phone.getText().isEmpty()) {
            flag = false;
        }

        if (pass.getText() == null || pass.getText().isEmpty() || pass.getText().length() < 8) {
            flag = false;
        }

        if (position.getValue() == null) {
            flag = false;
        }

        if (!flag) {
            showAlert();

        } else {

            employee.setName(name.getText());
            employee.setSurname(surname.getText());
            employee.setPatronymic(patronymic.getText());
            employee.setBirthdate(birthdate.getValue().format(formatter));
            employee.setGender(gender.getText());
            employee.setEmail(email.getText());
            employee.setPhoneNumber(phone.getText());
            employee.setPass(pass.getText());
            employee.setPosition(position.getValue());

            okClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    public void handleCancelAction() {
        dialogStage.close();
    }

    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(main.getPrimaryStage());
        alert.setTitle("Ошибка");
        alert.setHeaderText("Указаны не все обязательные поля!");
        alert.setContentText("Пароль должен содержать миниум 8 символов.");
        alert.showAndWait();
    }
}
