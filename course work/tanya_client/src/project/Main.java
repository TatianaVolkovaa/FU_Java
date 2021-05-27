package project;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import project.controller.RootController;
import project.entity.*;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class Main extends Application {

    private Employee employee;

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void showError() {
        return;
    }

    public Employee getEmployee() {
        return employee;
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Тур. агенство \"Т-Tour\"");
        initRootLayout();
    }

    private Stage primaryStage;

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/RootLayout.fxml"));
            BorderPane rootLayout = loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("icon.png")));
            primaryStage.show();

            RootController rootController = loader.getController();
            rootController.setMainApp(this);
            rootController.setRootLayout(rootLayout);

            if (getEmployee() == null) {
                rootController.startLayout();
                rootController.getMenuBar().setDisable(true);
            } else {
                rootController.allBooking();
                rootController.getMenuBar().setDisable(false);
            }

        } catch (IOException e) {
            showError();
        }
    }

    public ObservableList<Booking> loadBookings() {
        try {
            URL url = new URL("http://localhost:8080/bookings");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();

            String stringResponse = response.toString();
            List<Booking> responseList = new Gson().fromJson(stringResponse, new TypeToken<List<Booking>>() {
            }.getType());
            return FXCollections.observableArrayList(responseList);

        } catch (Exception e) {
            return FXCollections.observableArrayList();
        }
    }

    public ObservableList<Client> loadClients() {
        try {
            URL url = new URL("http://localhost:8080/clients");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();

            String stringResponse = response.toString();
            List<Client> responseList = new Gson().fromJson(stringResponse, new TypeToken<List<Client>>() {
            }.getType());
            return FXCollections.observableArrayList(responseList);

        } catch (Exception e) {
            return FXCollections.observableArrayList();
        }
    }

    public ObservableList<Tour> loadTours() {
        try {
            URL url = new URL("http://localhost:8080/tours");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();

            String stringResponse = response.toString();
            List<Tour> responseList = new Gson().fromJson(stringResponse, new TypeToken<List<Tour>>() {
            }.getType());
            return FXCollections.observableArrayList(responseList);

        } catch (Exception e) {
            return FXCollections.observableArrayList();
        }
    }

    public ObservableList<Employee> loadEmployees() {
        try {
            URL url = new URL("http://localhost:8080/employees");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();

            String stringResponse = response.toString();
            List<Employee> responseList = new Gson().fromJson(stringResponse, new TypeToken<List<Employee>>() {
            }.getType());
            return FXCollections.observableArrayList(responseList);

        } catch (Exception e) {
            return FXCollections.observableArrayList();
        }
    }

    public ObservableList<City> loadCities() {
        try {
            URL url = new URL("http://localhost:8080/cities");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();

            String stringResponse = response.toString();
            List<City> responseList = new Gson().fromJson(stringResponse, new TypeToken<List<City>>() {
            }.getType());
            return FXCollections.observableArrayList(responseList);

        } catch (Exception e) {
            return FXCollections.observableArrayList();
        }
    }

    public ObservableList<Hotel> loadHotels() {
        try {
            URL url = new URL("http://localhost:8080/hotels");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();

            String stringResponse = response.toString();
            List<Hotel> responseList = new Gson().fromJson(stringResponse, new TypeToken<List<Hotel>>() {
            }.getType());
            return FXCollections.observableArrayList(responseList);

        } catch (Exception e) {
            return FXCollections.observableArrayList();
        }
    }

    public ObservableList<TourType> loadTourTypes() {
        try {
            URL url = new URL("http://localhost:8080/tours/types");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();

            String stringResponse = response.toString();
            List<TourType> responseList = new Gson().fromJson(stringResponse, new TypeToken<List<TourType>>() {
            }.getType());
            return FXCollections.observableArrayList(responseList);

        } catch (Exception e) {
            return FXCollections.observableArrayList();
        }
    }

    public ObservableList<Feeding> loadFeedings() {
        try {
            URL url = new URL("http://localhost:8080/feedings");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();

            String stringResponse = response.toString();
            List<Feeding> responseList = new Gson().fromJson(stringResponse, new TypeToken<List<Feeding>>() {
            }.getType());
            return FXCollections.observableArrayList(responseList);

        } catch (Exception e) {
            return FXCollections.observableArrayList();
        }
    }

    public ObservableList<AccommodationType> loadAccommodations() {
        try {
            URL url = new URL("http://localhost:8080/accommodations");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();

            String stringResponse = response.toString();
            List<AccommodationType> responseList = new Gson().fromJson(stringResponse, new TypeToken<List<AccommodationType>>() {
            }.getType());
            return FXCollections.observableArrayList(responseList);

        } catch (Exception e) {
            return FXCollections.observableArrayList();
        }
    }

    public ObservableList<Flight> loadFlights() {
        try {
            URL url = new URL("http://localhost:8080/flights");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();

            String stringResponse = response.toString();
            List<Flight> responseList = new Gson().fromJson(stringResponse, new TypeToken<List<Flight>>() {
            }.getType());
            return FXCollections.observableArrayList(responseList);

        } catch (Exception e) {
            return FXCollections.observableArrayList();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
