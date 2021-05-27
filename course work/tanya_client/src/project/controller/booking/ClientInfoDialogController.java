package project.controller.booking;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import project.entity.Client;

public class ClientInfoDialogController {

    @FXML
    private Label name;
    @FXML
    private Label surname;
    @FXML
    private Label patronymic;
    @FXML
    private Label birthday;
    @FXML
    private Label email;
    @FXML
    private Label phone;

    private Client client;
    private Stage dialogStage;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setClient(Client client) {
        this.client = client;
        name.setText(client.getName());
        surname.setText(client.getSurname());
        patronymic.setText(client.getPatronymic());
        birthday.setText(client.getBirthdate());
        email.setText(client.getEmail());
        phone.setText(client.getPhoneNumber());
    }

    @FXML
    public void handleCloseAction() {
        dialogStage.close();
    }
}
