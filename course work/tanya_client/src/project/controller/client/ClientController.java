package project.controller.client;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import project.Main;
import project.entity.Client;
import project.entity.Tour;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ClientController {

    private Main main;
    private ObservableList<Client> clients;

    public void setMainApp(Main mainApp) {
        this.main = mainApp;
    }

    public void setClients(ObservableList<Client> clients) {
        this.clients = clients;
        clientTable.setItems(clients);

    }

    @FXML
    private TableView<Client> clientTable;
    @FXML
    private TableColumn<Client, String> nameColumn;
    @FXML
    private TableColumn<Client, String> surnameColumn;
    @FXML
    private TableColumn<Client, String> patronymicColumn;
    @FXML
    private Label birthdate;
    @FXML
    private Label gender;
    @FXML
    private Label email;
    @FXML
    private Label phone;
    @FXML
    private Label phone2;


    @FXML
    public void initialize() {
        clientTable.getSelectionModel().selectedItemProperty().addListener(
                ((observableValue, oldValue, newValue) -> showClientDetails(newValue)));

        nameColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getName()));
        surnameColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getSurname()));
        patronymicColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getPatronymic()));
    }

    private void showClientDetails(Client client) {
        if (client != null) {
            birthdate.setText(client.getBirthdate());
            gender.setText(client.getGender());
            email.setText(client.getEmail());
            phone.setText(client.getPhoneNumber());

            if (client.getPhoneNumber2() != null && !client.getPhoneNumber2().isEmpty()) {
                phone2.setText(client.getPhoneNumber2());

            } else {
                phone2.setText("не указано");
            }
        }
    }

    @FXML
    public void showTourHistory() {
        int selectedIndex = clientTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Client client = clientTable.getItems().get(selectedIndex);
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(main.getClass().getResource("/project/view/client/TourHistoryDialog.fxml"));
                AnchorPane page = loader.load();

                Stage dialogStage = new Stage();
                dialogStage.setTitle("Итория туров");
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.initOwner(main.getPrimaryStage());

                Scene scene = new Scene(page);
                dialogStage.setScene(scene);

                TourHistoryDialogController controller = loader.getController();
                controller.setHistory(getClientTours(client.getClientId()));
                controller.setDialogStage(dialogStage);
                dialogStage.showAndWait();

            } catch (IOException e) {
                main.showError();
            }
        }
    }

    private ObservableList<Tour> getClientTours(int id) {
        try {
            URL url = new URL("http://localhost:8080/tours/by_client/" + id);
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
            List<Tour> responseList = new Gson().fromJson(stringResponse, new TypeToken<List<Tour>>() {
            }.getType());
            return FXCollections.observableArrayList(responseList);

        } catch (Exception e) {
            main.showError();
            return FXCollections.observableArrayList();
        }
    }

    @FXML
    public void createClient() {
        Client client = new Client();
        boolean isOkClicked = showEditDialog(client);
        if (isOkClicked) {
            try {
                saveClient(client);
                clients.clear();
                clients.addAll(main.loadClients());
                showNotification("создан");

            } catch (Exception e) {
                main.showError();
            }
        }
    }

    @FXML
    public void updateClient() {
        int selectedIndex = clientTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Client client = clientTable.getItems().get(selectedIndex);

            boolean isOkClicked = showEditDialog(client);
            if (isOkClicked) {
                clientTable.getItems().set(selectedIndex, client);
                showClientDetails(client);
                try {
                    saveClient(client);
                    showNotification("изменен");

                } catch (Exception e) {
                    main.showError();
                }
            }
        } else {
            showAlert();
        }
    }


    private boolean showEditDialog(Client client) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/client/ClientEditDialog.fxml"));
            AnchorPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Редактирование клиента");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(main.getPrimaryStage());

            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            ClientEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setClient(client);
            dialogStage.showAndWait();

            return controller.isOkClicked();

        } catch (IOException e) {
            return false;
        }
    }

    private void saveClient(Client client) {
        try {
            URL url = new URL("http://localhost:8080/clients");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

            String json = new Gson().toJson(client);
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
        alert.setHeaderText("Клиент успешно " + type);
        alert.showAndWait();
    }
}
