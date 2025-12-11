package com.appointmentProject.desktop.controller.appointment;

import com.appointmentProject.desktop.SceneNavigator;
import com.google.gson.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.*;
import java.util.*;

public class EditAppointmentController {

    private static final String BASE_URL = "http://localhost:8080/appointment";

    // ---------- FXML CONTROLS ----------
    @FXML private ComboBox<String>  patientDropdown;
    @FXML private ComboBox<String>  providerDropdown;
    @FXML private ComboBox<String>  nurseDropdown;

    @FXML private ComboBox<Integer> billingDropdown;
    @FXML private ComboBox<Integer> prescriptionDropdown;
    @FXML private ComboBox<Integer> labOrderDropdown;

    @FXML private DatePicker        datePicker;
    @FXML private ComboBox<String>  timeDropdown;
    @FXML private ComboBox<String>  startTimeDropdown;
    @FXML private ComboBox<String>  endTimeDropdown;

    @FXML private TextField roomField;
    @FXML private TextArea  reasonArea;

    @FXML private Button deleteButton;
    @FXML private Label  messageLabel;

    // ---------- STATE ----------
    private int appointmentId;

    // display-name → ID
    private final Map<String, Integer> patientMap  = new HashMap<>();
    private final Map<String, Integer> providerMap = new HashMap<>();
    private final Map<String, Integer> nurseMap    = new HashMap<>();

    // ---------- INIT ----------
    @FXML
    public void initialize() {

        // Appointment ID is set before scene switch
        appointmentId = ManageAppointmentController.selectedAppointmentId;

        // Only admins can delete, based on previousPage
        boolean isAdmin = "/fxml/admin_dashboard.fxml".equals(
                ManageAppointmentController.previousPage
        );
        deleteButton.setVisible(isAdmin);
        deleteButton.setManaged(isAdmin);

        messageLabel.setText("");

        setupTimeDropdowns();
        loadDropdownData();
        loadAppointmentData();
    }

    // ---------- TIME OPTIONS ----------
    private void setupTimeDropdowns() {
        List<String> times = new ArrayList<>();
        for (int hour = 7; hour <= 18; hour++) {
            times.add(String.format("%02d:00", hour));
            times.add(String.format("%02d:30", hour));
        }
        timeDropdown.getItems().addAll(times);
        startTimeDropdown.getItems().addAll(times);
        endTimeDropdown.getItems().addAll(times);
    }

    // ---------- DROPDOWN DATA ----------
    private void loadDropdownData() {
        loadPatients();
        loadProviders();
        loadNurses();
        loadSimpleIdList("http://localhost:8080/billing/all", billingDropdown);
        loadSimpleIdList("http://localhost:8080/prescription/all", prescriptionDropdown);
        loadSimpleIdList("http://localhost:8080/laborder/all", labOrderDropdown);
    }

    private void loadPatients() {
        try {
            JsonArray arr = JsonParser.parseString(fetchJson("http://localhost:8080/patient/all"))
                    .getAsJsonArray();

            for (JsonElement e : arr) {
                JsonObject o = e.getAsJsonObject();
                int id = o.get("id").getAsInt();
                String name = o.get("firstName").getAsString() + " " + o.get("lastName").getAsString();
                patientDropdown.getItems().add(name);
                patientMap.put(name, id);
            }
        } catch (Exception ex) {
            messageLabel.setText("Failed to load patients.");
        }
    }

    private void loadProviders() {
        try {
            JsonArray arr = JsonParser.parseString(fetchJson("http://localhost:8080/provider/all"))
                    .getAsJsonArray();

            for (JsonElement e : arr) {
                JsonObject o = e.getAsJsonObject();
                int id = o.get("id").getAsInt();
                String name = o.get("firstName").getAsString() + " " + o.get("lastName").getAsString();
                providerDropdown.getItems().add(name);
                providerMap.put(name, id);
            }
        } catch (Exception ex) {
            messageLabel.setText("Failed to load providers.");
        }
    }

    private void loadNurses() {
        try {
            JsonArray arr = JsonParser.parseString(fetchJson("http://localhost:8080/nurse/all"))
                    .getAsJsonArray();

            for (JsonElement e : arr) {
                JsonObject o = e.getAsJsonObject();
                int id = o.get("id").getAsInt();
                String name = o.get("firstName").getAsString() + " " + o.get("lastName").getAsString();
                nurseDropdown.getItems().add(name);
                nurseMap.put(name, id);
            }
        } catch (Exception ex) {
            messageLabel.setText("Failed to load nurses.");
        }
    }

    private void loadSimpleIdList(String url, ComboBox<Integer> combo) {
        try {
            JsonArray arr = JsonParser.parseString(fetchJson(url)).getAsJsonArray();
            for (JsonElement e : arr) {
                combo.getItems().add(e.getAsJsonObject().get("id").getAsInt());
            }
        } catch (Exception ex) {
            messageLabel.setText("Failed to load ID lists.");
        }
    }

    // ---------- LOAD APPOINTMENT ----------
    private void loadAppointmentData() {
        try {
            JsonObject o = JsonParser.parseString(
                    fetchJson(BASE_URL + "/" + appointmentId)
            ).getAsJsonObject();

            int patientId  = o.get("patientId").getAsInt();
            int providerId = o.get("providerId").getAsInt();
            int billingId  = o.get("billingId").getAsInt();
            int nurseId    = o.get("nurseId").isJsonNull() ? 0 : o.get("nurseId").getAsInt();

            LocalDateTime dt = LocalDateTime.parse(o.get("appointmentDate").getAsString());
            datePicker.setValue(dt.toLocalDate());
            timeDropdown.setValue(dt.toLocalTime().toString());

            roomField.setText(o.get("roomNumber").getAsString());
            reasonArea.setText(o.get("reasonForVisiting").getAsString());

            patientDropdown.setValue(getKey(patientMap, patientId));
            providerDropdown.setValue(getKey(providerMap, providerId));
            if (nurseId != 0) {
                nurseDropdown.setValue(getKey(nurseMap, nurseId));
            }

            billingDropdown.setValue(billingId);

            if (!o.get("startTime").isJsonNull()) {
                startTimeDropdown.setValue(o.get("startTime").getAsString());
            }
            if (!o.get("endTime").isJsonNull()) {
                endTimeDropdown.setValue(o.get("endTime").getAsString());
            }
            if (!o.get("prescriptionId").isJsonNull()) {
                prescriptionDropdown.setValue(o.get("prescriptionId").getAsInt());
            }
            if (!o.get("labOrderId").isJsonNull()) {
                labOrderDropdown.setValue(o.get("labOrderId").getAsInt());
            }

        } catch (Exception ex) {
            messageLabel.setText("Failed to load appointment.");
            ex.printStackTrace();
        }
    }

    // ---------- HELPERS ----------
    private String fetchJson(String urlStr) throws Exception {
        HttpURLConnection con = (HttpURLConnection) new URL(urlStr).openConnection();
        con.setRequestMethod("GET");

        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String ln;
        while ((ln = br.readLine()) != null) sb.append(ln);
        return sb.toString();
    }

    private <K, V> K getKey(Map<K, V> map, V value) {
        for (var e : map.entrySet()) {
            if (Objects.equals(e.getValue(), value)) {
                return e.getKey();
            }
        }
        return null;
    }

    // ---------- SAVE ----------
    @FXML
    private void handleSave() {
        messageLabel.setText("");
        try {
            JsonObject body = new JsonObject();
            body.addProperty("id", appointmentId);

            body.addProperty("patientId",  patientMap.get(patientDropdown.getValue()));
            body.addProperty("providerId", providerMap.get(providerDropdown.getValue()));
            body.addProperty("billingId",  billingDropdown.getValue());
            body.addProperty("roomNumber", roomField.getText());
            body.addProperty("reasonForVisiting", reasonArea.getText());

            if (nurseDropdown.getValue() != null) {
                body.addProperty("nurseId", nurseMap.get(nurseDropdown.getValue()));
            } else {
                body.add("nurseId", JsonNull.INSTANCE);
            }

            LocalDate date = datePicker.getValue();
            LocalTime time = LocalTime.parse(timeDropdown.getValue());
            body.addProperty("appointmentDate", date.atTime(time).toString());

            if (startTimeDropdown.getValue() != null) {
                body.addProperty("startTime", startTimeDropdown.getValue());
            }
            if (endTimeDropdown.getValue() != null) {
                body.addProperty("endTime", endTimeDropdown.getValue());
            }

            if (prescriptionDropdown.getValue() != null) {
                body.addProperty("prescriptionId", prescriptionDropdown.getValue());
            }
            if (labOrderDropdown.getValue() != null) {
                body.addProperty("labOrderId", labOrderDropdown.getValue());
            }

            sendPut(BASE_URL + "/update", body.toString());
            ManageAppointmentController.successMessage = "Appointment updated successfully.";
            SceneNavigator.switchTo("/fxml/manage_appointments.fxml");

        } catch (Exception ex) {
            messageLabel.setText("Failed to save: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void sendPut(String urlStr, String jsonBody) throws Exception {
        HttpURLConnection con = (HttpURLConnection) new URL(urlStr).openConnection();
        con.setRequestMethod("PUT");
        con.setDoOutput(true);
        con.setRequestProperty("Content-Type", "application/json");

        try (OutputStream os = con.getOutputStream()) {
            os.write(jsonBody.getBytes());
        }

        int status = con.getResponseCode();
        if (status < 200 || status >= 300) {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getErrorStream()));
            StringBuilder sb = new StringBuilder();
            String ln;
            while ((ln = br.readLine()) != null) sb.append(ln);
            throw new IllegalArgumentException("Backend error (" + status + "): " + sb);
        }
    }

    // ---------- DELETE ----------
    @FXML
    private void handleDelete() {
        messageLabel.setText("");
        try {
            HttpURLConnection con = (HttpURLConnection)
                    new URL(BASE_URL + "/delete/" + appointmentId).openConnection();
            con.setRequestMethod("DELETE");
            int status = con.getResponseCode();
            if (status < 200 || status >= 300) {
                throw new IllegalArgumentException("Backend returned status " + status);
            }
            ManageAppointmentController.successMessage = "Appointment deleted.";
            SceneNavigator.switchTo("/fxml/manage_appointments.fxml");
        } catch (Exception ex) {
            messageLabel.setText("Failed to delete appointment: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // ---------- BACK ----------
    @FXML
    private void handleBack() {
        SceneNavigator.switchTo("/fxml/manage_appointments.fxml");
    }
}
