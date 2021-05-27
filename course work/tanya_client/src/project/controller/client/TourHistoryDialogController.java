package project.controller.client;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import project.entity.Tour;

public class TourHistoryDialogController {

    private Stage dialogStage;

    private ObservableList<Tour> history;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    void setHistory(ObservableList<Tour> history) {
        this.history = history;
        tourTable.setItems(history);
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
    private TableColumn<Tour, String> priceColumn;

    @FXML
    public void initialize() {
        nameColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getName()));
        beginDateColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getBeginDate()));
        endDateColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getEndDate()));
        priceColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getPriceForOnePerson() + " руб."));
    }
}
