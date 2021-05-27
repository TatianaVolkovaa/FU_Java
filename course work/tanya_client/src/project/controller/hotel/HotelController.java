package project.controller.hotel;

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
import project.entity.Hotel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class HotelController {

    private Main main;
    private ObservableList<Hotel> hotels;

    public void setMainApp(Main mainApp) {
        this.main = mainApp;

        String pos = mainApp.getEmployee().getPosition();
        //if (!pos.equals("Администратор") & !pos.equals("Менеджер")) {
        if (!pos.equals("admin") & !pos.equals("menedz")) {
            create.setDisable(true);
            edit.setDisable(true);
            delete.setDisable(true);
        }
    }

    public void setHotels(ObservableList<Hotel> hotels) {
        this.hotels = hotels;
        hotelTable.setItems(hotels);
    }

    @FXML
    private TableView<Hotel> hotelTable;
    @FXML
    private TableColumn<Hotel, String> nameColumn;
    @FXML
    private TableColumn<Hotel, String> ratingColumn;
    @FXML
    private TableColumn<Hotel, String> priceColumn;
    @FXML
    private Label name;
    @FXML
    private Label rating;
    @FXML
    private Label price;
    @FXML
    private Label type;
    @FXML
    private Label feeding;
    @FXML
    private Label city;
    @FXML
    private Button create;
    @FXML
    private Button edit;
    @FXML
    private Button delete;

    @FXML
    public void initialize() {
        hotelTable.getSelectionModel().selectedItemProperty().addListener(
                ((observableValue, oldValue, newValue) -> showHotelDetails(newValue)));

        nameColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getName()));
        ratingColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNumberOfStars().toString() + " / 5"));
        priceColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getPriceForOneNight() + " руб./ночь"));

    }

    private void showHotelDetails(Hotel hotel) {
        if (hotel != null) {
            name.setText(hotel.getName());
            rating.setText(hotel.getNumberOfStars().toString() + " / 5");
            price.setText(hotel.getPriceForOneNight() + " руб.");
            type.setText(hotel.getAccommodationType().getAccommodationTypeName());
            feeding.setText(hotel.getFeeding().getFeedingName());
            city.setText(hotel.getCity().getCountry().getCountryName() + ", " + hotel.getCity().getCityName());
        }
    }

    private boolean showEditDialog(Hotel hotel) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/hotel/HotelEditDialog.fxml"));
            AnchorPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Редактирование отеля");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(main.getPrimaryStage());

            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            HotelEditDialogController controller = loader.getController();
            controller.setMain(main);
            controller.setDialogStage(dialogStage);
            controller.setHotel(hotel);
            dialogStage.showAndWait();

            return controller.isOkClicked();

        } catch (IOException e) {
            return false;
        }
    }

    @FXML
    public void createHotel() {
        Hotel hotel = new Hotel();
        boolean isOkClicked = showEditDialog(hotel);
        if (isOkClicked) {
            try {
                saveHotel(hotel);
                hotels.clear();
                hotels.addAll(main.loadHotels());
                showNotification("создан");

            } catch (Exception e) {
                main.showError();
            }
        }
    }

    @FXML
    public void updateHotel() {
        int selectedIndex = hotelTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Hotel hotel = hotelTable.getItems().get(selectedIndex);

            boolean isOkClicked = showEditDialog(hotel);
            if (isOkClicked) {
                hotelTable.getItems().set(selectedIndex, hotel);
                showHotelDetails(hotel);
                try {
                    saveHotel(hotel);
                    showNotification("изменен");

                } catch (Exception e) {
                    main.showError();
                }
            }
        } else {
            showAlert();
        }
    }

    @FXML
    public void deleteHotel() {
        int selectedIndex = hotelTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            int id = hotelTable.getItems().get(selectedIndex).getHotelId();
            try {
                hotelTable.getItems().remove(selectedIndex);
                deleteHotel(id);
                showNotification("удален");

            } catch (Exception e) {
                main.showError();
            }
        } else {
            showAlert();
        }
    }

    private void saveHotel(Hotel hotel) {
        try {
            URL url = new URL("http://localhost:8080/hotels");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

            String json = new Gson().toJson(hotel);
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
            e.printStackTrace();
        }
    }

    private void deleteHotel(int id) {
        try {
            URL url = new URL("http://localhost:8080/hotels/" + id);
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
            e.printStackTrace();
        }
    }

    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(main.getPrimaryStage());
        alert.setTitle("Ошибка");
        alert.setHeaderText("Вы не выбрали отель из таблицы!");
        alert.showAndWait();
    }

    private void showNotification(String type) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(main.getPrimaryStage());
        alert.setTitle("Информация");
        alert.setHeaderText("Отель успешно " + type);
        alert.showAndWait();
    }
}
