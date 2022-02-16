package org.client.model;
import javafx.scene.control.CheckBox;

import java.time.LocalDateTime;

public class Contract {
    private Long id;
    private Long number;
    private LocalDateTime contractCreationTime;
    private LocalDateTime contractLastUpdate;
    private CheckBox actual;

    public Contract(){
        this.actual = new CheckBox();
        this.actual.setDisable(true);
    }

    public CheckBox getActual() {
        return actual;
    }

    public void setActual(Boolean actual) {
        this.actual.setSelected(actual);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public LocalDateTime getContractCreationTime() {
        return contractCreationTime;
    }

    public void setContractCreationTime(LocalDateTime contractCreationTime) {
        this.contractCreationTime = contractCreationTime;
    }

    public LocalDateTime getContractLastUpdate() {
        return contractLastUpdate;
    }

    public void setContractLastUpdate(LocalDateTime contractLastUpdate) {
        this.contractLastUpdate = contractLastUpdate;
    }

    //Для парсинга json
    public void setContractCreationTime(String contractCreationTime) {
        this.contractCreationTime = LocalDateTime.parse(contractCreationTime);
    }
    //Для парсинга json
    public void setContractLastUpdate(String contractLastUpdate) {
        this.contractLastUpdate = LocalDateTime.parse(contractLastUpdate);
    }
}
