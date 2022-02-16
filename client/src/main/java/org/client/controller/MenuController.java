package org.client.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.client.model.Contract;
import org.client.service.ContractService;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    @FXML
    private Button getContract;

    @FXML
    private AnchorPane coreArea;

    @FXML
    private TableView<Contract> table;

    @FXML
    private TableColumn<Contract, CheckBox> actual;

    @FXML
    private TableColumn<Contract, LocalDateTime> contractCreationTime;

    @FXML
    private TableColumn<Contract, LocalDateTime> contractLastUpdate;

    @FXML
    private TableColumn<Contract, Integer> number;
    private final ContractService contractService = new ContractService();

    @FXML
    public void getContractAll(ActionEvent event) {
        table.getItems().clear();

        if(contractService.checkContractList()){
            ObservableList<Contract> observableList = FXCollections.observableArrayList(
                    contractService.getContractList()
            );
            table.setItems(observableList);
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Произошла ошибка");
            alert.setHeaderText("Проблемы с подключением к серверу (см. logfile)!");
            alert.showAndWait();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        actual.setCellValueFactory(new PropertyValueFactory<>("actual"));
        contractCreationTime.setCellValueFactory(new PropertyValueFactory<>("contractCreationTime"));
        contractLastUpdate.setCellValueFactory(new PropertyValueFactory<>("contractLastUpdate"));
        number.setCellValueFactory(new PropertyValueFactory<>("number"));
    }
}
