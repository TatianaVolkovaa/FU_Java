package project.controller.tour;

import com.google.gson.Gson;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import project.Main;
import project.entity.City;
import project.entity.Flight;
import project.entity.Hotel;
import project.entity.Tour;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Set;

public class TourController {

    private Main main;
    private ObservableList<Tour> tours;

    public void setMainApp(Main mainApp) {
        this.main = mainApp;
        String pos = mainApp.getEmployee().getPosition();
        //if (!pos.equals("Администратор") & !pos.equals("Менеджер")) {
        if (!pos.equals("admin") & !pos.equals("menedz")) {
            delete.setDisable(true);
        }
    }

    public void setTours(ObservableList<Tour> tours) {
        this.tours = tours;
        tourTable.setItems(tours);
    }

    @FXML
    private TableView<Tour> tourTable;
    @FXML
    private TableColumn<Tour, String> nameColumn;
    @FXML
    private TableColumn<Tour, String> beginDateColumn;
    @FXML
    private TableColumn<Tour, String> endDateColumn;
    @FXML
    private Label name;
    @FXML
    private Label descr;
    @FXML
    private Label beginDate;
    @FXML
    private Label endDate;
    @FXML
    private Label price;
    @FXML
    private Label type;
    @FXML
    private Button delete;

    @FXML
    public void initialize() {
        tourTable.getSelectionModel().selectedItemProperty().addListener(
                ((observableValue, oldValue, newValue) -> showTourDetails(newValue)));

        nameColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getName()));
        beginDateColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getBeginDate()));
        endDateColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getEndDate()));
    }

    private void showTourDetails(Tour newValue) {
        if (newValue != null) {
            name.setText(newValue.getName());
            descr.setText(newValue.getDescription());
            beginDate.setText(newValue.getBeginDate());
            endDate.setText(newValue.getEndDate());
            price.setText(newValue.getPriceForOnePerson() + " руб.");
            type.setText(newValue.getTourType().getTypeName());
        }
    }

    @FXML
    public void createTour() {
        Tour tour = new Tour();
        boolean isOkClicked = showEditDialog(tour);
        if (isOkClicked) {
            try {
                saveTour(tour);
                tours.clear();
                tours.addAll(main.loadTours());
                showNotification("создан");

            } catch (Exception e) {
                main.showError();
            }
        }
    }

    @FXML
    public void updateTour() {
        int selectedIndex = tourTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Tour tour = tourTable.getItems().get(selectedIndex);

            boolean isOkClicked = showEditDialog(tour);
            if (isOkClicked) {
                tourTable.getItems().set(selectedIndex, tour);
                showTourDetails(tour);
                try {
                    saveTour(tour);
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
    public void deleteTour() {
        int selectedIndex = tourTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            int id = tourTable.getItems().get(selectedIndex).getTourId();
            try {
                tourTable.getItems().remove(selectedIndex);
                deleteTour(id);
                showNotification("удален");

            } catch (Exception e) {
                main.showError();
            }
        } else {
            showAlert();
        }
    }

    private boolean showEditDialog(Tour tour) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/tour/TourEditDialog.fxml"));
            AnchorPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Редактирование тура");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(main.getPrimaryStage());

            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            TourEditDialogController controller = loader.getController();
            controller.setMain(main);
            controller.setDialogStage(dialogStage);
            controller.setTour(tour);
            dialogStage.showAndWait();

            return controller.isOkClicked();

        } catch (IOException e) {
            return false;
        }
    }

    private void saveTour(Tour tour) {
        try {
            URL url = new URL("http://localhost:8080/tours");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

            String json = new Gson().toJson(tour);
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
            main.showError();
        }
    }

    private void deleteTour(int id) {
        try {
            URL url = new URL("http://localhost:8080/tours/" + id);
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
            main.showError();
        }
    }

    @FXML
    public void showFlights() {
        int selectedIndex = tourTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Set<Flight> flights = tourTable.getItems().get(selectedIndex).getFlights();
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(main.getClass().getResource("/project/view/tour/FlightsDialog.fxml"));
                AnchorPane page = loader.load();

                Stage dialogStage = new Stage();
                dialogStage.setTitle("Рейсы в тур");
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.initOwner(main.getPrimaryStage());

                Scene scene = new Scene(page);
                dialogStage.setScene(scene);

                FlightsDialogController controller = loader.getController();
                controller.setFlights(FXCollections.observableArrayList(flights));
                dialogStage.showAndWait();

            } catch (IOException e) {
                main.showError();
            }
        }
    }

    @FXML
    public void showCities() {
        int selectedIndex = tourTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Set<City> cities = tourTable.getItems().get(selectedIndex).getCities();
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(main.getClass().getResource("/project/view/tour/CitiesDialog.fxml"));
                AnchorPane page = loader.load();

                Stage dialogStage = new Stage();
                dialogStage.setTitle("Города тура");
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.initOwner(main.getPrimaryStage());

                Scene scene = new Scene(page);
                dialogStage.setScene(scene);

                CitiesDialogController controller = loader.getController();
                controller.setCities(FXCollections.observableArrayList(cities));
                dialogStage.showAndWait();

            } catch (IOException e) {
                main.showError();
            }
        }
    }

    @FXML
    public void showHotels() {
        int selectedIndex = tourTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Set<Hotel> hotels = tourTable.getItems().get(selectedIndex).getHotels();
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(main.getClass().getResource("/project/view/tour/HotelsDialog.fxml"));
                AnchorPane page = loader.load();

                Stage dialogStage = new Stage();
                dialogStage.setTitle("Отели тура");
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.initOwner(main.getPrimaryStage());

                Scene scene = new Scene(page);
                dialogStage.setScene(scene);

                HotelsDialogController controller = loader.getController();
                controller.setHotels(FXCollections.observableArrayList(hotels));
                dialogStage.showAndWait();

            } catch (IOException e) {
                main.showError();
            }
        }
    }

    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(main.getPrimaryStage());
        alert.setTitle("Ошибка");
        alert.setHeaderText("Вы не выбрали тур из таблицы!");
        alert.showAndWait();
    }

    private void showNotification(String type) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(main.getPrimaryStage());
        alert.setTitle("Информация");
        alert.setHeaderText("Тур успешно " + type);
        alert.showAndWait();
    }
}
