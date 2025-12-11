/********************************************************************
 *  LabOrderEditController.java
 *
 *          This controller is responsible for providing an admin
 *          user the ability to edit or delete an existing lab order.
 *
 *
 * @author Jack Mitchell
 * @version 1.0
 * @since 12/10/2025
 ********************************************************************/

package com.appointmentProject.desktop.controller.laborder;

import com.appointmentProject.desktop.SceneNavigator;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class LabOrderEditController {

    public static int selectedLabOrderId;

    @FXML private TextField appointmentIdField;
    @FXML private TextField providerRequesterIdField;
    @FXML private TextField providerReceiverIdField;
    @FXML private TextField nurseIdField;
    @FXML private TextField patientIdField;
    @FXML private TextField dateOfCompletionField;
    @FXML private TextField testingPurposeField;
    @FXML private TextField resultsField;

    @FXML private Label messageLabel;

    private final Gson gson = new Gson();

    @FXML
    private void initialize() {
        loadLabOrderData();
    }

    private void loadLabOrderData() {
        try {
            URL url = new URL("http://localhost:8080/laborder/" + selectedLabOrderId);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            JsonObject obj = gson.fromJson(br.readLine(), JsonObject.class);
            br.close();

            appointmentIdField.setText(obj.get("appointmentId").getAsString());
            providerRequesterIdField.setText(obj.get("providerRequesterId").getAsString());
            providerReceiverIdField.setText(obj.get("providerReceiverId").getAsString());
            nurseIdField.setText(obj.get("nurseId").getAsString());
            patientIdField.setText(obj.get("patientId").getAsString());
            dateOfCompletionField.setText(obj.get("dateOfCompletion").getAsString());
            testingPurposeField.setText(obj.get("testingPurpose").getAsString());
            resultsField.setText(obj.get("results").getAsString());

        } catch (Exception e) {
            e.printStackTrace();
            messageLabel.setText("Error loading lab order.");
        }
    }

    @FXML
    private void handleSaveLabOrder() {
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

            URL url = new URL("http://localhost:8080/laborder/update/" + selectedLabOrderId);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("PUT");
            con.setDoOutput(true);
            con.setRequestProperty("Content-Type", "application/json");

            OutputStream os = con.getOutputStream();
            os.write(body.toString().getBytes());
            os.flush();
            os.close();

            if (con.getResponseCode() == 200) {
                messageLabel.setStyle("-fx-text-fill: green;");
                messageLabel.setText("Lab order updated successfully!");
            } else {
                messageLabel.setText("Update failed.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            messageLabel.setText("Server error.");
        }
    }

    @FXML
    private void handleDeleteLabOrder() {
        try {
            URL url = new URL("http://localhost:8080/laborder/delete/" + selectedLabOrderId);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("DELETE");

            con.getInputStream().close();

            messageLabel.setStyle("-fx-text-fill: green;");
            messageLabel.setText("Lab order deleted successfully!");

        } catch (Exception e) {
            e.printStackTrace();
            messageLabel.setText("Error deleting lab order.");
        }
    }

    @FXML
    private void handleBack() {
        SceneNavigator.switchTo("/fxml/laborder_list.fxml");
    }
}
