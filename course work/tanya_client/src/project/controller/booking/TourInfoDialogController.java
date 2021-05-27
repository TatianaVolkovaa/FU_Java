package project.controller.booking;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import project.entity.Tour;

public class TourInfoDialogController {

    @FXML
    private Label name;
    @FXML
    private Label beginDate;
    @FXML
    private Label endDate;
    @FXML
    private Label price;
    @FXML
    private Label description;

    private Tour tour;
    private Stage dialogStage;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
        name.setText(tour.getName());
        beginDate.setText(tour.getBeginDate());
        endDate.setText(tour.getEndDate());
        price.setText(tour.getPriceForOnePerson() + " руб.");
        description.setText(tour.getDescription());
    }

    @FXML
    public void handleCloseAction() {
        dialogStage.close();
    }
}
