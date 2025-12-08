package com.appointmentProject.desktop.controller;

import com.appointmentProject.desktop.SceneNavigator;
import javafx.fxml.FXML;


public class ManagePartnersController {

    @FXML
    private void initialize() {
        System.out.println("Manage Partners Loaded.");
    }

    @FXML
    private void handlePharmacies() {
        SceneNavigator.switchTo("/fxml/pharmacies.fxml");
    }

    @FXML
    private void handleManufacturers() {
        SceneNavigator.switchTo("/fxml/manufacturers.fxml");
    }

    @FXML
    private void handleInsurances() {
        SceneNavigator.switchTo("/fxml/insurances.fxml");
    }
}
