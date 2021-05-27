package project.controller.tour;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import project.Main;
import project.entity.Tour;
import project.entity.TourType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class TourEditDialogController {

    private Stage dialogStage;
    private Tour tour;
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

    public void setTour(Tour tour) {
        this.tour = tour;

        formatter = formatter.withLocale(Locale.getDefault());
        type.setItems(main.loadTourTypes());

        if (tour.getTourId() != null) {
            name.setText(tour.getName());
            beginDate.setValue(LocalDate.parse(tour.getBeginDate(), formatter));
            endDate.setValue(LocalDate.parse(tour.getEndDate(), formatter));
            descr.setText(tour.getDescription());
            price.setText(tour.getPriceForOnePerson().toString());
            type.setValue(tour.getTourType());
        }
    }

    @FXML
    private TextField name;
    @FXML
    private DatePicker beginDate;
    @FXML
    private DatePicker endDate;
    @FXML
    private TextField descr;
    @FXML
    private TextField price;
    @FXML
    private ComboBox<TourType> type;

    @FXML
    public void handleOkAction() {
        boolean flag = true;

        if (name.getText() == null || name.getText().isEmpty()) {
            flag = false;
        }

        if (price.getText() == null || price.getText().isEmpty()) {
            flag = false;
        }

        if (type.getValue() == null) {
            flag = false;
        }

        if (beginDate.getValue() == null) {
            flag = false;
        }

        if (endDate.getValue() == null) {
            flag = false;
        }

        if (!flag) {
            showAlert();

        } else {

            tour.setName(name.getText());
            tour.setBeginDate(beginDate.getValue().format(formatter));
            tour.setEndDate(endDate.getValue().format(formatter));
            tour.setDescription(descr.getText());
            try {
                tour.setPriceForOnePerson(Double.parseDouble(price.getText()));
            } catch (Exception e) {
                showAlert();
                return;
            }
            tour.setTourType(type.getValue());

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
