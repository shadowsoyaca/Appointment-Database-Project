package com.appointmentProject.desktop.controller.appointment;

import com.appointmentProject.desktop.SceneNavigator;
import com.google.gson.JsonObject;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

    @FXML private DatePicker appointmentDatePicker;
    @FXML private ComboBox<String> appointmentTimeCombo;

    @FXML private TextField roomNumberField;
    @FXML private TextArea reasonField;

    @FXML private Label formError;

    @FXML
    public void initialize() {
        formError.setText("");

        // Populate time dropdown in 15-minute intervals
        for (int hour = 0; hour < 24; hour++) {
            appointmentTimeCombo.getItems().add(String.format("%02d:00", hour));
            appointmentTimeCombo.getItems().add(String.format("%02d:15", hour));
            appointmentTimeCombo.getItems().add(String.format("%02d:30", hour));
            appointmentTimeCombo.getItems().add(String.format("%02d:45", hour));
        }

        appointmentTimeCombo.setPromptText("Select time");
    }

    @FXML
    private void handleSave() {
        formError.setText("");

        try {
            int patientId = parseRequiredInt(patientIdField, "Patient ID");
            int providerId = parseRequiredInt(providerIdField, "Provider ID");
            int billingId  = parseRequiredInt(billingIdField, "Billing ID");

            Integer nurseId        = parseOptionalInt(nurseIdField);
            Integer prescriptionId = parseOptionalInt(prescriptionIdField);
            Integer labOrderId     = parseOptionalInt(labOrderIdField);

            // Date selection
            LocalDate date = appointmentDatePicker.getValue();
            if (date == null)
                throw new IllegalArgumentException("Appointment date is required.");

            // Time selection
            String timeText = appointmentTimeCombo.getValue();
            if (timeText == null)
                throw new IllegalArgumentException("Appointment time is required.");

            LocalTime time = LocalTime.parse(timeText);
            LocalDateTime appointmentDate = LocalDateTime.of(date, time);

            // Room
            String room = roomNumberField.getText().trim();
            if (room.isEmpty())
                throw new IllegalArgumentException("Room number is required.");

            // Reason
            String reason = reasonField.getText().trim();
            if (reason.isEmpty())
                throw new IllegalArgumentException("Reason is required.");

            // Build JSON
            JsonObject body = new JsonObject();
            body.addProperty("patientId", patientId);
            body.addProperty("providerId", providerId);
            body.addProperty("billingId", billingId);

            if (nurseId != null) body.addProperty("nurseId", nurseId);
            if (prescriptionId != null) body.addProperty("prescriptionId", prescriptionId);
            if (labOrderId != null) body.addProperty("labOrderId", labOrderId);

            body.addProperty("appointmentDate", appointmentDate.toString());

            body.addProperty("roomNumber", room);
            body.addProperty("reasonForVisiting", reason);

            // POST
            sendPost(BASE_URL + "/create", body.toString());

            // Success
            ManageAppointmentController.successMessage = "Appointment created successfully.";
            SceneNavigator.switchTo("/fxml/manage_appointments.fxml");

        } catch (Exception ex) {
            formError.setText(ex.getMessage());
            ex.printStackTrace();
        }
    }

    @FXML
    private void handleCancel() {
        SceneNavigator.switchTo("/fxml/manage_appointments.fxml");
    }

    private int parseRequiredInt(TextField field, String label) {
        String text = field.getText().trim();
        if (text.isEmpty())
            throw new IllegalArgumentException(label + " is required.");
        try { return Integer.parseInt(text); }
        catch (NumberFormatException ex) {
            throw new IllegalArgumentException(label + " must be a number.");
        }
    }

    private Integer parseOptionalInt(TextField field) {
        String text = field.getText().trim();
        if (text.isEmpty()) return null;
        try { return Integer.parseInt(text); }
        catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Optional fields must be numbers.");
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
        BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        status >= 200 && status < 300
                                ? con.getInputStream()
                                : con.getErrorStream()
                )
        );

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null)
            sb.append(line);

        if (status < 200 || status >= 300)
            throw new IllegalArgumentException("Backend error (" + status + "): " + sb);

        return sb.toString();
    }
}
