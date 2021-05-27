package project.controller.flight;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import project.Main;
import project.entity.Flight;

public class FlightsController {

    private Main main;

    public void setMainApp(Main mainApp) {
        this.main = mainApp;
    }

    public void setFlights(ObservableList<Flight> flights) {
        flightTable.setItems(flights);
    }

    @FXML
    private TableView<Flight> flightTable;
    @FXML
    private TableColumn<Flight, String> numberColumn;
    @FXML
    private TableColumn<Flight, String> departureTimeColumn;
    @FXML
    private TableColumn<Flight, String> arrivalTimeColumn;
    @FXML
    private Label number;
    @FXML
    private Label company;
    @FXML
    private Label departureTime;
    @FXML
    private Label arrivalTime;
    @FXML
    private Label departureCity;
    @FXML
    private Label arrivalCity;
    @FXML
    private Label price;

    @FXML
    public void initialize() {
        flightTable.getSelectionModel().selectedItemProperty().addListener(
                ((observableValue, oldValue, newValue) -> showFlightDetails(newValue)));

        numberColumn.setCellValueFactory(c -> new SimpleStringProperty("FL" + c.getValue().getFlightId() + " " + c.getValue().getDepartureCity().getCityName() + "-" + c.getValue().getArrivalCity().getCityName()));
        departureTimeColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getDepartureTime()));
        arrivalTimeColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getArrivalTime()));
    }

    private void showFlightDetails(Flight newValue) {
        if (newValue != null) {
            number.setText(newValue.getDepartureCity().getCityName() + " - " + newValue.getArrivalCity().getCityName());
            company.setText(newValue.getCompany());
            departureTime.setText(newValue.getDepartureTime());
            arrivalTime.setText(newValue.getArrivalTime());
            departureCity.setText(newValue.getDepartureCity().getCityName());
            arrivalCity.setText(newValue.getArrivalCity().getCityName());
            price.setText(newValue.getPrice() + " руб.");
        }
    }

    private void showNotification() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(main.getPrimaryStage());
        alert.setTitle("Информация");
        alert.setHeaderText("Расписание рейсов успешно обновлено!");
        alert.showAndWait();
    }

    @FXML
    public void refreshFlights() {
        main.loadFlights();
        showNotification();
    }
}
