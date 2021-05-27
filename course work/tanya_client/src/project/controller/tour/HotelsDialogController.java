package project.controller.tour;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import project.entity.Hotel;

public class HotelsDialogController {

    @FXML
    private TableView<Hotel> hotelTable;
    @FXML
    private TableColumn<Hotel, String> nameColumn;
    @FXML
    private TableColumn<Hotel, String> ratingColumn;
    @FXML
    private TableColumn<Hotel, String> priceColumn;

    private ObservableList<Hotel> hotels;

    public void setHotels(ObservableList<Hotel> hotels) {
        this.hotels = hotels;
        hotelTable.setItems(hotels);
    }

    @FXML
    public void initialize() {
        nameColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getName()));
        ratingColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNumberOfStars().toString() + " / 5"));
        priceColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getPriceForOneNight() + " руб./ночь"));
    }
}
