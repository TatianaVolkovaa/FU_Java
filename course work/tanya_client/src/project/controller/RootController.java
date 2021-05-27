package project.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import project.Main;
import project.controller.booking.BookingController;
import project.controller.client.ClientController;
import project.controller.countriescities.CountriesCitiesController;
import project.controller.employee.EmployeeController;
import project.controller.flight.FlightsController;
import project.controller.hotel.HotelController;
import project.controller.main.StartController;
import project.controller.tour.TourController;

import java.io.IOException;

public class RootController {

    private Main main;
    private BorderPane rootLayout;

    @FXML
    private MenuBar menuBar;

    public MenuBar getMenuBar() {
        return menuBar;
    }

    public void setRootLayout(BorderPane rootLayout) {
        this.rootLayout = rootLayout;
    }

    public void setMainApp(Main main) {
        this.main = main;
    }

    @FXML
    public void allBooking() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/booking/BookingLayout.fxml"));
            AnchorPane anchorPane = loader.load();

            rootLayout.setCenter(anchorPane);

            BookingController controller = loader.getController();
            controller.setMainApp(main);
            controller.setBookings(main.loadBookings());

        } catch (IOException e) {
            main.showError();
        }
    }

    @FXML
    private void allTours() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/tour/TourLayout.fxml"));
            AnchorPane anchorPane = loader.load();

            rootLayout.setCenter(anchorPane);

            TourController controller = loader.getController();
            controller.setMainApp(main);
            controller.setTours(main.loadTours());

        } catch (IOException e) {
            main.showError();
        }
    }

    @FXML
    private void hotels() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/hotel/HotelLayout.fxml"));
            AnchorPane anchorPane = loader.load();

            rootLayout.setCenter(anchorPane);

            HotelController controller = loader.getController();
            controller.setMainApp(main);
            controller.setHotels(main.loadHotels());

        } catch (IOException e) {
            main.showError();
        }
    }

    @FXML
    private void flights() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/flight/FlightsLayout.fxml"));
            AnchorPane anchorPane = loader.load();

            rootLayout.setCenter(anchorPane);

            FlightsController controller = loader.getController();
            controller.setMainApp(main);
            controller.setFlights(main.loadFlights());

        } catch (IOException e) {
            main.showError();
        }
    }

    public void startLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/main/StartLayout.fxml"));
            AnchorPane anchorPane = loader.load();

            rootLayout.setCenter(anchorPane);

            StartController controller = loader.getController();
            controller.setMainApp(main);

        } catch (IOException e) {
            main.showError();
        }
    }

    @FXML
    private void countriesAndCities() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/countriescities/CountriesCitiesLayout.fxml"));
            AnchorPane anchorPane = loader.load();

            rootLayout.setCenter(anchorPane);

            CountriesCitiesController controller = loader.getController();
            controller.setCities(main.loadCities());

        } catch (IOException e) {
            main.showError();
        }
    }

    @FXML
    private void clients() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/client/ClientLayout.fxml"));
            AnchorPane anchorPane = loader.load();

            rootLayout.setCenter(anchorPane);

            ClientController controller = loader.getController();
            controller.setMainApp(main);
            controller.setClients(main.loadClients());

        } catch (IOException e) {
            main.showError();
        }
    }

    @FXML
    private void employees() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/employee/EmployeeLayout.fxml"));
            AnchorPane anchorPane = loader.load();

            rootLayout.setCenter(anchorPane);

            EmployeeController controller = loader.getController();
            controller.setMainApp(main);
            controller.setEmployees(main.loadEmployees());

        } catch (IOException e) {
            main.showError();
        }
    }

    @FXML
    private void aboutMe() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/about/AboutMeLayout.fxml"));
            AnchorPane anchorPane = loader.load();

            rootLayout.setCenter(anchorPane);

        } catch (IOException e) {
            main.showError();
        }
    }
}
