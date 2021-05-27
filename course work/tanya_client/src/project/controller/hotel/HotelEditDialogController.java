package project.controller.hotel;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import project.Main;
import project.entity.AccommodationType;
import project.entity.City;
import project.entity.Feeding;
import project.entity.Hotel;

public class HotelEditDialogController {

    private Stage dialogStage;
    private Hotel hotel;
    private boolean okClicked = false;
    private Main main;

    public void setMain(Main main) {
        this.main = main;
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    void setHotel(Hotel hotel) {
        this.hotel = hotel;

        accommodationTypeComboBox.setItems(main.loadAccommodations());
        feedingComboBox.setItems(main.loadFeedings());
        cityComboBox.setItems(main.loadCities());

        if (hotel.getHotelId() != null) {
            name.setText(hotel.getName());
            rating.setText(hotel.getNumberOfStars().toString());
            price.setText(hotel.getPriceForOneNight().toString());
            accommodationTypeComboBox.setValue(hotel.getAccommodationType());
            feedingComboBox.setValue(hotel.getFeeding());
            cityComboBox.setValue(hotel.getCity());
        }
    }

    @FXML
    private TextField name;
    @FXML
    private TextField rating;
    @FXML
    private TextField price;
    @FXML
    private ComboBox<AccommodationType> accommodationTypeComboBox;
    @FXML
    private ComboBox<Feeding> feedingComboBox;
    @FXML
    private ComboBox<City> cityComboBox;

    @FXML
    public void handleOkAction() {
        boolean flag = true;

        if (name.getText() == null || name.getText().isEmpty()) {
            flag = false;
        }

        if (rating.getText() == null || rating.getText().isEmpty()) {
            flag = false;
        }

        if (price.getText() == null || price.getText().isEmpty()) {
            flag = false;
        }

        if (accommodationTypeComboBox.getValue() == null) {
            flag = false;
        }

        if (feedingComboBox.getValue() == null) {
            flag = false;
        }

        if (cityComboBox.getValue() == null) {
            flag = false;
        }

        if (!flag) {
            showAlert();

        } else {
            hotel.setName(name.getText());

            try {
                hotel.setNumberOfStars(Integer.parseInt(rating.getText()));
            } catch (Exception e) {
                showAlert();
                return;
            }

            try {
                hotel.setPriceForOneNight(Double.parseDouble(price.getText()));
            } catch (Exception e) {
                showAlert();
                return;
            }

            hotel.setAccommodationType(accommodationTypeComboBox.getValue());
            hotel.setFeeding(feedingComboBox.getValue());
            hotel.setCity(cityComboBox.getValue());

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
        alert.setContentText("Возможно числовые параметры содержат не число.");
        alert.showAndWait();
    }
}
