/********************************************************************
 *  LabOrderCreateController.java
 *
 *  Provides UI logic to create new lab orders.
 *
 *  Updated to properly handle LocalDateTime with DatePicker + time boxes.
 *
 * @author Jack Mitchell
 ********************************************************************/

package com.appointmentProject.desktop.controller.laborder;

import com.appointmentProject.desktop.SceneNavigator;
import com.google.gson.JsonObject;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;

public class LabOrderCreateController {

    @FXML private TextField appointmentIdField;
    @FXML private TextField providerRequesterIdField;
    @FXML private TextField providerReceiverIdField;
    @FXML private TextField nurseIdField;
    @FXML private TextField patientIdField;

    @FXML private DatePicker dateOfCompletionPicker;

    @FXML private ChoiceBox<String> hourBox;
    @FXML private ChoiceBox<String> minuteBox;
    @FXML private ChoiceBox<String> amPmBox;

    @FXML private ChoiceBox<String> resultsChoiceBox;
    @FXML private TextField testingPurposeField;

    @FXML private Label messageLabel;

    @FXML
    public void initialize() {
        // Populate hour selector (1–12)
        hourBox.setItems(FXCollections.observableArrayList(
                "01","02","03","04","05","06","07","08","09","10","11","12"
        ));

        // Populate minutes (00, 15, 30, 45)
        minuteBox.setItems(FXCollections.observableArrayList(
                "00","15","30","45"
        ));

        // AM/PM selector
        amPmBox.setItems(FXCollections.observableArrayList("AM", "PM"));
    }

    @FXML
    private void handleCreateLabOrder() {
        try {
            LocalDate date = dateOfCompletionPicker.getValue();
            if (date == null) {
                messageLabel.setText("Please select a completion date.");
                return;
            }

            String hour = hourBox.getValue();
            String minute = minuteBox.getValue();
            String ampm = amPmBox.getValue();

            if (hour == null || minute == null || ampm == null) {
                messageLabel.setText("Please select a valid time.");
                return;
            }

            // Convert to 24-hour format
            int hourInt = Integer.parseInt(hour);
            if (ampm.equals("PM") && hourInt != 12) hourInt += 12;
            if (ampm.equals("AM") && hourInt == 12) hourInt = 0;

            // Build full timestamp (ISO-like)
            String timestamp = date + "T" + String.format("%02d:%s:00", hourInt, minute);

            JsonObject body = new JsonObject();
            body.addProperty("appointmentId", appointmentIdField.getText());
            body.addProperty("providerRequesterId", providerRequesterIdField.getText());
            body.addProperty("providerReceiverId", providerReceiverIdField.getText());
            body.addProperty("nurseId", nurseIdField.getText());
            body.addProperty("patientId", patientIdField.getText());
            body.addProperty("dateOfCompletion", timestamp);
            body.addProperty("testingPurpose", testingPurposeField.getText());
            body.addProperty("results", resultsChoiceBox.getValue());

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
                messageLabel.setText("Creation failed. Check backend.");
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
