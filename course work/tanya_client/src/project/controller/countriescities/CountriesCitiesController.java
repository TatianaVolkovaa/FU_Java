package project.controller.countriescities;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import project.entity.City;

public class CountriesCitiesController {

    public void setCities(ObservableList<City> cities) {
        cityTable.setItems(cities);
    }

    @FXML
    private TableView<City> cityTable;
    @FXML
    private TableColumn<City, String> countryColumn;
    @FXML
    private TableColumn<City, String> cityColumn;

    @FXML
    public void initialize() {
        countryColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCountry().getCountryName()));
        cityColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCityName()));
    }
}
