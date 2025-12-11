package com.appointmentProject.desktop.controller.appointment;

import com.appointmentProject.desktop.SceneNavigator;
import com.google.gson.JsonObject;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CreateAppointmentController {

    private static final String BASE_URL = "http://localhost:8080/appointment";
    private static final DateTimeFormatter DATE_TIME_FMT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @FXML private TextField patientIdField;
    @FXML private TextField providerIdField;
    @FXML private TextField billingIdField;
    @FXML private TextField nurseIdField;
    @FXML private TextField prescriptionIdField;
    @FXML private TextField labOrderIdField;
    @FXML private TextField appointmentDateField; // yyyy-MM-dd HH:mm
    @FXML private TextField roomNumberField;
    @FXML private TextArea  reasonField;

    @FXML private Label messageLabel;

    @FXML
    public void initialize() {
        messageLabel.setText("");
    }

    // ---------- BUTTON HANDLERS ----------

    @FXML
    private void handleCreate() {
        messageLabel.setText("");

        try {
            // ----- Parse & validate required numeric IDs -----
            int patientId = parseRequiredInt(patientIdField, "Patient ID");
            int providerId = parseRequiredInt(providerIdField, "Provider ID");
            int billingId  = parseRequiredInt(billingIdField,  "Billing ID");

            // optional IDs
            Integer nurseId        = parseOptionalInt(nurseIdField);
            Integer prescriptionId = parseOptionalInt(prescriptionIdField);
            Integer labOrderId     = parseOptionalInt(labOrderIdField);

            // ----- Appointment date/time -----
            String dateText = appointmentDateField.getText().trim();
            if (dateText.isEmpty()) {
                throw new IllegalArgumentException("Appointment Date is required.");
            }

            LocalDateTime appointmentDate;
            try {
                appointmentDate = LocalDateTime.parse(dateText, DATE_TIME_FMT);
            } catch (Exception ex) {
                throw new IllegalArgumentException(
                        "Appointment Date must be in format yyyy-MM-dd HH:mm (example: 2025-12-11 09:30).");
            }

            // ----- Room & reason -----
            String room = roomNumberField.getText().trim();
            if (room.isEmpty()) {
                throw new IllegalArgumentException("Room Number is required.");
            }

            String reason = reasonField.getText().trim();
            if (reason.isEmpty()) {
                throw new IllegalArgumentException("Reason for visiting is required.");
            }

            // ----- Build JSON body -----
            JsonObject body = new JsonObject();
            body.addProperty("patientId", patientId);
            body.addProperty("providerId", providerId);
            body.addProperty("billingId", billingId);
            if (nurseId != null)        body.addProperty("nurseId", nurseId);
            if (prescriptionId != null) body.addProperty("prescriptionId", prescriptionId);
            if (labOrderId != null)     body.addProperty("labOrderId", labOrderId);

            body.addProperty("appointmentDate", appointmentDate.toString());
            body.addProperty("roomNumber", room);
            body.addProperty("reasonForVisiting", reason);

            // Start & end time are null at creation
            // (backend validation already allows them to be null)

            // ----- Send POST -----
            String response = sendPost(BASE_URL + "/create", body.toString());

            // If we reach here, HTTP status was 2xx
            ManageAppointmentController.successMessage = "Appointment created successfully.";
            SceneNavigator.switchTo("/fxml/manage_appointments.fxml");

        } catch (IllegalArgumentException ex) {
            // validation errors
            messageLabel.setText(ex.getMessage());
        } catch (Exception ex) {
            messageLabel.setText("Failed to create appointment: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    @FXML
    private void handleCancel() {
        SceneNavigator.switchTo("/fxml/manage_appointments.fxml");
    }

    // ---------- HELPERS ----------

    private int parseRequiredInt(TextField field, String label) {
        String text = field.getText().trim();
        if (text.isEmpty()) {
            throw new IllegalArgumentException(label + " is required.");
        }
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException(label + " must be a whole number.");
        }
    }

    private Integer parseOptionalInt(TextField field) {
        String text = field.getText().trim();
        if (text.isEmpty()) return null;
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Optional ID fields must be whole numbers.");
        }
    }

    private String sendPost(String urlStr, String jsonBody) throws Exception {
        URL url = new URL(urlStr);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        con.setRequestProperty("Content-Type", "application/json");

        try (OutputStream os = con.getOutputStream()) {
            os.write(jsonBody.getBytes());
        }

        int status = con.getResponseCode();
        BufferedReader br;

        if (status >= 200 && status < 300) {
            br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        } else {
            // Read error body to include in messageLabel if needed
            br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
        }

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) sb.append(line);

        if (status < 200 || status >= 300) {
            throw new IllegalArgumentException("Backend error (" + status + "): " + sb);
        }

        return sb.toString();
    }
}
