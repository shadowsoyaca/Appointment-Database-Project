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

        // TEMPORARY: Local Login Simulation
        switch (username) {

            case "admin":
                if (password.equals("admin")) {
                    SceneNavigator.switchTo("/fxml/admin_dashboard.fxml");
                    return;
                }
                break;

            case "provider":
                if (password.equals("provider")) {
                    SceneNavigator.switchTo("/fxml/provider_dashboard.fxml");
                    return;
                }
                break;

            case "nurse":
                if (password.equals("nurse")) {
                    SceneNavigator.switchTo("/fxml/nurse_dashboard.fxml");
                    return;
                }
                break;

            case "receptionist":
                if (password.equals("receptionist")) {
                    SceneNavigator.switchTo("/fxml/receptionist_dashboard.fxml");
                    return;
                }
                break;
        }

        errorLabel.setText("Incorrect username or password.");
    }
}
