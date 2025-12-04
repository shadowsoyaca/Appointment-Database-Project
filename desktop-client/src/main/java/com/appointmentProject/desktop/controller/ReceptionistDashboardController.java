/*************************************************************************
 *  ReceptionistDashboardController.java
 *
 *      This Class acts as the caller to the backend for specific data.
 *      It will first connect each table column to a respective field in
 *      a specified table. Using API client, it will talk to Spring Boot
 *      backend. It will receive a response (in JSON format) and turn it
 *      into a Java object. Afterwards it will display the obejcts into
 *      a table for display.
 *
 * @author Matthew Kiyono
 * @version 1.0
 * @since 12/3/2025
 **************************************************************************/
package com.appointmentProject.desktop.controller;

import com.appointmentProject.desktop.SceneNavigator;
import javafx.fxml.FXML;

public class ReceptionistDashboardController {

    @FXML
    private void initialize() {
        System.out.println("Receptionist Dashboard Loaded.");
    }

    @FXML
    private void handleLogout() {
        SceneNavigator.switchTo("/fxml/login.fxml");
    }
}
