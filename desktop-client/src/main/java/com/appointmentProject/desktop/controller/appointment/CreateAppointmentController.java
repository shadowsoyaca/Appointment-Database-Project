package com.appointmentProject.desktop.controller.appointment;

import com.appointmentProject.desktop.SceneNavigator;
import com.google.gson.JsonObject;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CreateAppointmentController {

    @FXML private TextField patientIdField;
    @FXML private TextField providerIdField;
    @FXML private TextField billingIdField;
    @FXML private TextField nurseIdField;
    @FXML private TextField prescriptionIdField;
    @FXML private TextField labOrderIdField;
    @FXML private TextField appointmentDateField;
    @FXML private TextField roomNumberField;
    @FXML private TextField reasonField;

    @FXML private Label patientIdError;
    @FXML private Label providerIdError;
    @FXML private Label billingIdError;
    @FXML private Label nurseIdError;
    @FXML private Label prescriptionIdError;
    @FXML private Label labOrderIdError;
    @FXML private Label appointmentDateError;
    @FXML private Label roomNumberError;
    @FXML private Label reasonError;
    @FXML private Label formError;

    private static final DateTimeFormatter DATE_FMT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @FXML
    private void handleSave() {
        clearErrors();

        try {
            int patientId = parseRequiredInt(patientIdField, patientIdError, "Patient ID must be a number.");
            int providerId = parseRequiredInt(providerIdField, providerIdError, "Provider ID must be a number.");
            int billingId = parseRequiredInt(billingIdField, billingIdError, "Billing ID must be a number.");

            Integer nurseId = parseOptionalInt(nurseIdField, nurseIdError);
            Integer prescriptionId = parseOptionalInt(prescriptionIdField, prescriptionIdError);
            Integer labOrderId = parseOptionalInt(labOrderIdField, labOrderIdError);

            if (appointmentDateField.getText().isBlank()) {
                appointmentDateError.setText("Date/time required.");
                return;
            }

            LocalDateTime apptDate;
            try {
                apptDate = LocalDateTime.parse(
                        appointmentDateField.getText().trim(), DATE_FMT);
            } catch (Exception e) {
                appointmentDateError.setText("Format: yyyy-MM-dd HH:mm");
                return;
            }

            if (roomNumberField.getText().isBlank()) {
                roomNumberError.setText("Room number is required.");
                return;
            }

            if (reasonField.getText().isBlank()) {
                reasonError.setText("Reason is required.");
                return;
            }

            // Build JSON
            JsonObject json = new JsonObject();
            json.addProperty("patientId", patientId);
            json.addProperty("providerId", providerId);
            json.addProperty("billingId", billingId);
            json.addProperty("appointmentDate", apptDate.toString());
            json.addProperty("roomNumber", roomNumberField.getText().trim());
            json.addProperty("reasonForVisiting", reasonField.getText().trim());

            if (nurseId != null) json.addProperty("nurseId", nurseId);
            if (prescriptionId != null) json.addProperty("prescriptionId", prescriptionId);
            if (labOrderId != null) json.addProperty("labOrderId", labOrderId);

            URL url = new URL("http://localhost:8080/appointments/create");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            try (OutputStream os = conn.getOutputStream()) {
                os.write(json.toString().getBytes());
            }

            int code = conn.getResponseCode();

            if (code >= 200 && code < 300) {
                ManageAppointmentController.successMessage =
                        "Appointment has been scheduled!";
                SceneNavigator.switchTo("/fxml/manage_appointments.fxml");
                return;
            }

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getErrorStream()));
            String msg = br.readLine();

            if (msg != null && msg.contains(":")) {
                String[] parts = msg.split(":", 2);
                String key = parts[0].trim();
                String message = parts[1].trim();

                switch (key) {
                    case "PATIENT_ID" -> patientIdError.setText(message);
                    case "PROVIDER_ID" -> providerIdError.setText(message);
                    case "BILLING_ID" -> billingIdError.setText(message);
                    case "APPOINTMENT_DATE" -> appointmentDateError.setText(message);
                    case "ROOM_NUMBER" -> roomNumberError.setText(message);
                    case "REASON" -> reasonError.setText(message);
                    case "START_TIME", "END_TIME" -> appointmentDateError.setText(message);
                    default -> formError.setText(message);
                }
            } else {
                formError.setText("Error: " + code);
            }

        } catch (Exception e) {
            formError.setText("Unexpected error: " + e.getMessage());
        }
    }

    private int parseRequiredInt(TextField field, Label errorLabel, String msg) {
        try {
            return Integer.parseInt(field.getText().trim());
        } catch (Exception e) {
            errorLabel.setText(msg);
            throw e;
        }
    }

    private Integer parseOptionalInt(TextField field, Label errorLabel) {
        if (field.getText().isBlank()) return null;

        try {
            return Integer.parseInt(field.getText().trim());
        } catch (Exception e) {
            errorLabel.setText("Must be a number or blank.");
            throw e;
        }
    }

    private void clearErrors() {
        patientIdError.setText("");
        providerIdError.setText("");
        billingIdError.setText("");
        nurseIdError.setText("");
        prescriptionIdError.setText("");
        labOrderIdError.setText("");
        appointmentDateError.setText("");
        roomNumberError.setText("");
        reasonError.setText("");
        formError.setText("");
    }

    @FXML
    private void handleCancel() {
        SceneNavigator.switchTo("/fxml/manage_appointments.fxml");
    }
}
