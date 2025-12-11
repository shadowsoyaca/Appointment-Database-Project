package com.appointmentProject.desktop.controller.appointment;

import com.appointmentProject.desktop.SceneNavigator;
import com.appointmentProject.desktop.model.AppointmentRow;
import com.google.gson.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class ManageAppointmentController {

    // -------- Static navigation helpers --------
    public static String previousPage = "/fxml/login.fxml";
    public static String successMessage = null;

    // ID of the appointment selected for editing
    public static int selectedAppointmentId = -1;

    // -------- FXML controls --------
    @FXML private TableView<AppointmentRow> appointmentsTable;
    @FXML private TableColumn<AppointmentRow, Integer> idCol;
    @FXML private TableColumn<AppointmentRow, String> patientNameCol;
    @FXML private TableColumn<AppointmentRow, String> providerNameCol;
    @FXML private TableColumn<AppointmentRow, String> appointmentDateCol;
    @FXML private TableColumn<AppointmentRow, String> roomNumberCol;
    @FXML private TableColumn<AppointmentRow, String> startTimeCol;
    @FXML private TableColumn<AppointmentRow, String> endTimeCol;

    @FXML private TextField searchField;
    @FXML private Button createAppointmentButton;
    @FXML private Label messageLabel;
    @FXML private Label editErrorLabel;

    // Maps for resolving patient/provider names
    private final Map<Integer, String> patientNameMap = new HashMap<>();
    private final Map<Integer, String> providerNameMap = new HashMap<>();

    private final ObservableList<AppointmentRow> masterList = FXCollections.observableArrayList();

    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter TIME_FMT = DateTimeFormatter.ofPattern("HH:mm");

    // -------- INITIALIZE --------
    @FXML
    public void initialize() {
        loadPatientNames();
        loadProviderNames();

        setupColumns();
        loadData();       // load appointments from backend
        setupSearch();    // hook up search filtering

        setupCreateButtonVisibility();
        showSuccessMessageIfAny();
    }

    // -------- UI helpers --------

    private void setupCreateButtonVisibility() {
        boolean isAdmin =
                "/fxml/admin_dashboard.fxml".equals(previousPage);
        boolean isReceptionist =
                "/fxml/receptionist_dashboard.fxml".equals(previousPage);

        boolean allowed = isAdmin || isReceptionist;

        createAppointmentButton.setVisible(allowed);
        createAppointmentButton.setManaged(allowed);
    }

    private void showSuccessMessageIfAny() {
        if (successMessage != null) {
            messageLabel.setText(successMessage);
            successMessage = null;
        } else {
            messageLabel.setText("");
        }
    }

    private void setupColumns() {
        idCol.setCellValueFactory(d -> d.getValue().idProperty().asObject());
        patientNameCol.setCellValueFactory(d -> d.getValue().patientNameProperty());
        providerNameCol.setCellValueFactory(d -> d.getValue().providerNameProperty());
        appointmentDateCol.setCellValueFactory(d -> d.getValue().appointmentDateProperty());
        roomNumberCol.setCellValueFactory(d -> d.getValue().roomNumberProperty());
        startTimeCol.setCellValueFactory(d -> d.getValue().startTimeProperty());
        endTimeCol.setCellValueFactory(d -> d.getValue().endTimeProperty());
    }

    // -------- DATA LOADING --------

    private void loadData() {
        try {
            HttpURLConnection conn =
                    (HttpURLConnection) new URL("http://localhost:8080/appointment/all").openConnection();
            conn.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();

            String line;
            while ((line = br.readLine()) != null) sb.append(line);

            JsonArray arr = JsonParser.parseString(sb.toString()).getAsJsonArray();
            masterList.clear();

            for (JsonElement elem : arr) {
                JsonObject obj = elem.getAsJsonObject();

                int id = obj.get("id").getAsInt();
                int patientId = obj.get("patientId").getAsInt();
                int providerId = obj.get("providerId").getAsInt();

                String patientName = patientNameMap.getOrDefault(patientId, "Unknown");
                String providerName = providerNameMap.getOrDefault(providerId, "Unknown");

                LocalDateTime dt = LocalDateTime.parse(obj.get("appointmentDate").getAsString());
                String apptDateDisplay = DATE_FMT.format(dt);

                String room = obj.get("roomNumber").getAsString();

                LocalTime start = obj.get("startTime").isJsonNull() ? null :
                        LocalTime.parse(obj.get("startTime").getAsString());

                LocalTime end = obj.get("endTime").isJsonNull() ? null :
                        LocalTime.parse(obj.get("endTime").getAsString());

                AppointmentRow row = new AppointmentRow(
                        id,
                        patientName,
                        providerName,
                        apptDateDisplay,
                        room,
                        start == null ? "-" : TIME_FMT.format(start),
                        end == null ? "-" : TIME_FMT.format(end)
                );

                masterList.add(row);
            }

            appointmentsTable.setItems(masterList);

        } catch (Exception ex) {
            ex.printStackTrace();
            messageLabel.setText("Failed to load appointments.");
        }
    }

    private void setupSearch() {
        FilteredList<AppointmentRow> filtered = new FilteredList<>(masterList, p -> true);

        searchField.textProperty().addListener((obs, old, val) -> {
            String lower = val == null ? "" : val.toLowerCase();

            filtered.setPredicate(row ->
                    row.getPatientName().toLowerCase().contains(lower)
                            || row.getProviderName().toLowerCase().contains(lower)
                            || row.getAppointmentDate().toLowerCase().contains(lower)
                            || row.getRoomNumber().toLowerCase().contains(lower)
            );
        });

        SortedList<AppointmentRow> sorted = new SortedList<>(filtered);
        sorted.comparatorProperty().bind(appointmentsTable.comparatorProperty());
        appointmentsTable.setItems(sorted);
    }

    private void loadPatientNames() {
        try {
            URL url = new URL("http://localhost:8080/patient/all");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            JsonArray arr = JsonParser.parseReader(br).getAsJsonArray();

            for (JsonElement e : arr) {
                JsonObject obj = e.getAsJsonObject();
                int id = obj.get("id").getAsInt();
                String first = obj.get("firstName").getAsString();
                String last = obj.get("lastName").getAsString();

                patientNameMap.put(id, first + " " + last);
            }

        } catch (Exception e) {
            System.out.println("Could not load patient names: " + e.getMessage());
        }
    }

    private void loadProviderNames() {
        try {
            URL url = new URL("http://localhost:8080/provider/all");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            JsonArray arr = JsonParser.parseReader(br).getAsJsonArray();

            for (JsonElement e : arr) {
                JsonObject obj = e.getAsJsonObject();
                int id = obj.get("id").getAsInt();
                String first = obj.get("firstName").getAsString();
                String last = obj.get("lastName").getAsString();

                providerNameMap.put(id, first + " " + last);
            }

        } catch (Exception e) {
            System.out.println("Could not load provider names: " + e.getMessage());
        }
    }

    // -------- BUTTON HANDLERS --------

    @FXML
    private void handleCreateAppointment() {
        SceneNavigator.switchTo("/fxml/appointment_create.fxml");
    }

    @FXML
    private void handleEditAppointment() {
        editErrorLabel.setText(""); // clear old errors

        AppointmentRow selected = appointmentsTable.getSelectionModel().getSelectedItem();

        if (selected == null) {
            editErrorLabel.setText("Please select an appointment to edit.");
            return;
        }

        // Store selected ID in this controller's static field
        selectedAppointmentId = selected.getId();

        SceneNavigator.switchTo("/fxml/appointment_edit.fxml");
    }

    @FXML
    private void handleBack() {
        SceneNavigator.switchTo(previousPage);
    }
}
