package project.controller.client;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import project.Main;
import project.entity.Client;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ClientEditDialogController {

    private Stage dialogStage;
    private Client client;
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

    public void setClient(Client client) {
        this.client = client;

        if (client.getClientId() != null) {
            name.setText(client.getName());
            surname.setText(client.getSurname());
            patronymic.setText(client.getPatronymic());
            birthdate.setValue(LocalDate.parse(client.getBirthdate(), formatter));
            gender.setText(client.getGender());
            email.setText(client.getEmail());
            phone.setText(client.getPhoneNumber());
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
    public void initialize() {
    }

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

        if (!flag) {
            showAlert();

        } else {

            client.setName(name.getText());
            client.setSurname(surname.getText());
            client.setPatronymic(patronymic.getText());
            client.setBirthdate(birthdate.getValue().format(formatter));
            client.setGender(gender.getText());
            client.setEmail(email.getText());
            client.setPhoneNumber(phone.getText());

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
        alert.showAndWait();
    }

}
