package com.appointmentProject.desktop.controller;

import com.appointmentProject.desktop.SceneNavigator;
import javafx.fxml.FXML;

public class AccountManagementController {

    private static String previousPage = "/fxml/login.fxml";

    public static void setPreviousPage(String fxmlPath) {
        previousPage = fxmlPath;
    }

    @FXML
    public void handleBack() {
        SceneNavigator.switchTo(previousPage);
    }

    @FXML
    public void handleEditEmail() {
        SceneNavigator.switchTo("/fxml/edit_email.fxml");
    }

    @FXML
    public void handleChangePassword() {
        SceneNavigator.switchTo("/fxml/edit_password.fxml");
    }


}
