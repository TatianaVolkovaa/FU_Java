package project.controller.tour;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import project.entity.Flight;

public class FlightsDialogController {

    @FXML
    private TableView<Flight> flightTable;
    @FXML
    private TableColumn<Flight, String> numberColumn;
    @FXML
    private TableColumn<Flight, String> departureTimeColumn;
    @FXML
    private TableColumn<Flight, String> arrivalTimeColumn;

    private ObservableList<Flight> flights;

    public void setFlights(ObservableList<Flight> flights) {
        this.flights = flights;
        flightTable.setItems(flights);
    }

    @FXML
    public void initialize() {
        numberColumn.setCellValueFactory(c -> new SimpleStringProperty("FL" + c.getValue().getFlightId() + " " + c.getValue().getDepartureCity().getCityName() + "-" + c.getValue().getArrivalCity().getCityName()));
        departureTimeColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getDepartureTime()));
        arrivalTimeColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getArrivalTime()));
    }
}
