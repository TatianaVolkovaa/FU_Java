package project.controller.employee;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import project.entity.Booking;

public class BookingHistoryController {

    @FXML
    private TableView<Booking> bookingTable;
    @FXML
    private TableColumn<Booking, String> dateColumn;
    @FXML
    private TableColumn<Booking, String> clientColumn;
    @FXML
    private TableColumn<Booking, String> statusColumn;

    void setBookings(ObservableList<Booking> bookings) {
        bookingTable.setItems(bookings);
    }

    @FXML
    public void initialize() {
        dateColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getDate()));
        clientColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getClient().getSurname()
                + " " + c.getValue().getClient().getName()
                + " " + c.getValue().getClient().getPatronymic()));
        statusColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getStatus()));
    }
}
