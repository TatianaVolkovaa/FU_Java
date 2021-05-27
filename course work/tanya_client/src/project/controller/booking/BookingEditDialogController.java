package project.controller.booking;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import project.Main;
import project.entity.Booking;
import project.entity.Client;
import project.entity.Employee;
import project.entity.Tour;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BookingEditDialogController {

    private Stage dialogStage;
    private Booking booking;
    private boolean okClicked = false;
    private Main main;

    private SpinnerValueFactory<Integer> childrenVF = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 3, 0);
    private SpinnerValueFactory<Integer> adultsVF = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 3, 1);
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    public void setMain(Main main) {
        this.main = main;
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;

        tour.setItems(main.loadTours());
        client.setItems(main.loadClients());

        //if (main.getEmployee().getPosition().equals("Администратор")) {
        if (main.getEmployee().getPosition().equals("admin")) {
            employee.setItems(main.loadEmployees());
        } else {
            employee.setValue(main.getEmployee());
        }

        status.setItems(FXCollections.observableArrayList("Поступил", "Принят", "Выполнен", "Отменен"));

        if (booking.getId() != null) {
            numberOfAdults.getValueFactory().setValue(booking.getNumberOfAdults());
            numberOfChildren.getValueFactory().setValue(booking.getNumberOfChildren());
            discount.setText(booking.getDiscount().toString());

            status.setValue(booking.getStatus());
            client.setValue(booking.getClient());
            employee.setValue(booking.getEmployee());
            tour.setValue(booking.getTour());
        }
    }

    @FXML
    private Spinner<Integer> numberOfAdults;
    @FXML
    private Spinner<Integer> numberOfChildren;
    @FXML
    private TextField discount;
    @FXML
    private ComboBox<Client> client;
    @FXML
    private ComboBox<Employee> employee;
    @FXML
    private ComboBox<Tour> tour;
    @FXML
    private ComboBox<String> status;

    @FXML
    public void initialize() {
        numberOfAdults.setValueFactory(adultsVF);
        numberOfChildren.setValueFactory(childrenVF);
    }

    @FXML
    public void handleOkAction() {
        boolean flag = true;

        if (tour.getValue() == null) {
            flag = false;
        }

        if (client.getValue() == null) {
            flag = false;
        }

        if (employee.getValue() == null) {
            flag = false;
        }

        if (discount.getText() == null || discount.getText().isEmpty()) {
            flag = false;

        }

        if (status.getValue() == null) {
            flag = false;

        }

        if (!flag) {
            showAlert();

        } else {
            booking.setTour(tour.getValue());
            booking.setClient(client.getValue());
            booking.setEmployee(employee.getValue());
            booking.setStatus(status.getValue());
            booking.setDate(LocalDateTime.now().format(formatter));
            booking.setNumberOfAdults(numberOfAdults.getValue());
            booking.setNumberOfChildren(numberOfChildren.getValue());

            try {
                booking.setDiscount(Integer.parseInt(discount.getText()));

            } catch (Exception e) {
                showAlert();
                return;
            }

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
        alert.showAndWait();
    }
}
