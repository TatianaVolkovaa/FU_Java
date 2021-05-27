package project.controller.tour;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import project.entity.City;

public class CitiesDialogController {

    @FXML
    private TableView<City> cityTable;
    @FXML
    private TableColumn<City, String> countryColumn;
    @FXML
    private TableColumn<City, String> cityColumn;

    private ObservableList<City> cities;

    public void setCities(ObservableList<City> cities) {
        this.cities = cities;
        cityTable.setItems(cities);
    }

    @FXML
    public void initialize() {
        countryColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCountry().getCountryName()));
        cityColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCityName()));
    }

}
