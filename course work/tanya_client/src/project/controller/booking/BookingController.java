package project.controller.booking;

import com.google.gson.Gson;
import javafx.beans.property.SimpleStringProperty;
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
import project.entity.Client;
import project.entity.Tour;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class BookingController {

    private Main main;
    private ObservableList<Booking> bookings;

    public void setMainApp(Main mainApp) {
        this.main = mainApp;

        String pos = mainApp.getEmployee().getPosition();
        if (!pos.equals("admin") & !pos.equals("menedz")) {
        //if (!pos.equals("Администратор") & !pos.equals("Менеджер")) {
            create.setDisable(true);
            edit.setDisable(true);
            cancel.setDisable(true);
        }
    }

    public void setBookings(ObservableList<Booking> bookings) {
        this.bookings = bookings;
        bookingTable.setItems(bookings);
    }

    @FXML
    private TableView<Booking> bookingTable;
    @FXML
    private TableColumn<Booking, String> dateColumn;
    @FXML
    private TableColumn<Booking, String> clientColumn;
    @FXML
    private TableColumn<Booking, String> statusColumn;
    @FXML
    private Label adults;
    @FXML
    private Label children;
    @FXML
    private Label price;
    @FXML
    private Label discount;
    @FXML
    private Label finalPrice;
    @FXML
    private Button create;
    @FXML
    private Button edit;
    @FXML
    private Button cancel;

    @FXML
    public void initialize() {
        bookingTable.getSelectionModel().selectedItemProperty().addListener(
                ((observableValue, oldValue, newValue) -> showBookingDetails(newValue)));

        dateColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getDate()));
        clientColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getClient().getSurname()
                + " " + c.getValue().getClient().getName()
                + " " + c.getValue().getClient().getPatronymic()));
        statusColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getStatus()));
    }

    private void showBookingDetails(Booking newValue) {
        adults.setText(newValue.getNumberOfAdults() + " чел.");
        children.setText(newValue.getNumberOfChildren() + " чел.");
        double totalPrice = newValue.getTour().getPriceForOnePerson() * (newValue.getNumberOfChildren() + newValue.getNumberOfAdults());
        price.setText(totalPrice + " руб.");
        discount.setText(newValue.getDiscount() + " %");

        double _finalPrice = totalPrice - totalPrice * newValue.getDiscount() / 100;

        finalPrice.setText(_finalPrice + " руб.");
    }

    @FXML
    public void createBooking() {
        Booking booking = new Booking();
        boolean isOkClicked = showEditDialog(booking);
        if (isOkClicked) {
            saveBooking(booking);
            bookings.clear();
            bookings.addAll(main.loadBookings());
            showNotification("создана");
        }
    }

    @FXML
    public void updateBooking() {
        int selectedIndex = bookingTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Booking booking = bookingTable.getItems().get(selectedIndex);

            boolean isOkClicked = showEditDialog(booking);
            if (isOkClicked) {
                bookingTable.getItems().set(selectedIndex, booking);
                showBookingDetails(booking);
                saveBooking(booking);
                showNotification("изменена");
            }

        } else showAlert();
    }

    @FXML
    public void cancelBooking() {
        int selectedIndex = bookingTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            int id = bookingTable.getItems().get(selectedIndex).getId();
            bookingTable.getItems().remove(selectedIndex);
            deleteBooking(id);
            showNotification("отменена");

        } else {
            showAlert();
        }
    }

    @FXML
    public void showClientInfo() {
        int selectedIndex = bookingTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Client client = bookingTable.getItems().get(selectedIndex).getClient();
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(main.getClass().getResource("view/booking/ClientInfoDialog.fxml"));
                AnchorPane page = loader.load();

                Stage dialogStage = new Stage();
                dialogStage.setTitle("Информация о клиенте");
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.initOwner(main.getPrimaryStage());

                Scene scene = new Scene(page);
                dialogStage.setScene(scene);

                ClientInfoDialogController controller = loader.getController();
                controller.setClient(client);
                controller.setDialogStage(dialogStage);
                dialogStage.showAndWait();

            } catch (IOException e) {
                main.showError();
            }

        } else showAlert();
    }

    @FXML
    public void showTourInfo() {
        int selectedIndex = bookingTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Tour tour = bookingTable.getItems().get(selectedIndex).getTour();
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(main.getClass().getResource("view/booking/TourInfoDialog.fxml"));
                AnchorPane page = loader.load();

                Stage dialogStage = new Stage();
                dialogStage.setTitle("Информация о туре");
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.initOwner(main.getPrimaryStage());

                Scene scene = new Scene(page);
                dialogStage.setScene(scene);

                TourInfoDialogController controller = loader.getController();
                controller.setTour(tour);
                controller.setDialogStage(dialogStage);
                dialogStage.showAndWait();

            } catch (IOException e) {
                main.showError();
            }

        } else showAlert();
    }


    private boolean showEditDialog(Booking booking) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/booking/BookingEditDialog.fxml"));
            AnchorPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Редактирование брони");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(main.getPrimaryStage());

            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            BookingEditDialogController controller = loader.getController();
            controller.setMain(main);
            controller.setDialogStage(dialogStage);
            controller.setBooking(booking);
            dialogStage.showAndWait();

            return controller.isOkClicked();

        } catch (IOException e) {
            return false;
        }
    }

    private void saveBooking(Booking booking) {
        try {
            URL url = new URL("http://localhost:8080/bookings");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

            String json = new Gson().toJson(booking);
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

    private void deleteBooking(int id) {
        try {
            URL url = new URL("http://localhost:8080/bookings/" + id);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");

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
        alert.setHeaderText("Вы не выбрали бронь из таблицы!");
        alert.showAndWait();
    }

    private void showNotification(String type) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(main.getPrimaryStage());
        alert.setTitle("Информация");
        alert.setHeaderText("Бронь успешно " + type);
        alert.showAndWait();
    }
}
