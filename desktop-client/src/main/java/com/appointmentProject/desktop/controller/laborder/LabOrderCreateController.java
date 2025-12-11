/********************************************************************
 *  LabOrderCreateController.java
 *
 *          This controller is responsible for providing an admin
 *          user the ability to create a new lab order.
 *
 *
 * @author Jack Mitchell
 * @version 1.0
 * @since 12/10/2025
 ********************************************************************/

package com.appointmentProject.desktop.controller.laborder;

import com.appointmentProject.desktop.SceneNavigator;
import com.google.gson.JsonObject;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class LabOrderCreateController {

    @FXML private TextField appointmentIdField;
    @FXML private TextField providerRequesterIdField;
    @FXML private TextField providerReceiverIdField;
    @FXML private TextField nurseIdField;
    @FXML private TextField patientIdField;
    @FXML private TextField dateOfCompletionField;
    @FXML private TextField testingPurposeField;
    @FXML private TextField resultsField;

    @FXML private Label messageLabel;

    @FXML
    private void handleCreateLabOrder() {
        try {
            JsonObject body = new JsonObject();
            body.addProperty("appointmentId", appointmentIdField.getText());
            body.addProperty("providerRequesterId", providerRequesterIdField.getText());
            body.addProperty("providerReceiverId", providerReceiverIdField.getText());
            body.addProperty("nurseId", nurseIdField.getText());
            body.addProperty("patientId", patientIdField.getText());
            body.addProperty("dateOfCompletion", dateOfCompletionField.getText());
            body.addProperty("testingPurpose", testingPurposeField.getText());
            body.addProperty("results", resultsField.getText());

            URL url = new URL("http://localhost:8080/laborder/add");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setRequestProperty("Content-Type", "application/json");

            OutputStream os = con.getOutputStream();
            os.write(body.toString().getBytes());
            os.flush();
            os.close();

            if (con.getResponseCode() == 200) {
                messageLabel.setStyle("-fx-text-fill: green;");
                messageLabel.setText("Lab order created successfully!");
            } else {
                messageLabel.setText("Creation failed.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            messageLabel.setText("Server error.");
        }
    }

    @FXML
    private void handleBack() {
        SceneNavigator.switchTo("/fxml/laborder_list.fxml");
    }
}
