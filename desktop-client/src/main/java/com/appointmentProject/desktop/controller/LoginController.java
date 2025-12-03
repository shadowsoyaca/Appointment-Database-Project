/********************************************************************
 *  LoginController.java
 *
 *          This class provides a working login screen and screen
 *          navigator.
 *
 * @author Matthew Kiyono
 * @version 1.0
 * @since 12/1/2025
 ********************************************************************/

package com.appointmentProject.desktop.controller;

import com.appointmentProject.desktop.SceneNavigator;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorLabel;

    @FXML
    public void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // TEMPORARY LOGIN LOGIC (until backend login endpoint is added)
        if (username.equals("admin") && password.equals("admin")) {
            SceneNavigator.switchTo("/fxml/admin_dashboard.fxml");
            return;
        }

        if (username.equals("nurse") && password.equals("nurse")) {
            SceneNavigator.switchTo("/fxml/nurse_dashboard.fxml");
            return;
        }

        errorLabel.setText("Incorrect username or password.");
    }
}
