package com.appointmentProject.desktop.controller.appointment;

import com.appointmentProject.desktop.SceneNavigator;
import com.appointmentProject.desktop.model.AppointmentRow;
import com.google.gson.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ManageAppointmentController {

    // Static navigation helpers
    public static String previousPage = "/fxml/login.fxml";
    public static String successMessage = null;

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

    private final ObservableList<AppointmentRow> masterList = FXCollections.observableArrayList();

    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter TIME_FMT = DateTimeFormatter.ofPattern("HH:mm");

    @FXML
    private void initialize() {
        setupCreateButtonVisibility();
        setupColumns();
        loadData();
        setupSearch();
        showSuccessMessageIfAny();
    }

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

    private void loadData() {
        try {
            HttpURLConnection conn =
                    (HttpURLConnection) new URL("http://localhost:8080/appointments/all").openConnection();
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

                String patientName = fetchName("http://localhost:8080/patients/" + patientId);
                String providerName = fetchName("http://localhost:8080/providers/" + providerId);

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
        }
    }

    private String fetchName(String endpoint) {
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(endpoint).openConnection();
            conn.setRequestMethod("GET");

            JsonObject obj = JsonParser.parseReader(
                    new BufferedReader(new InputStreamReader(conn.getInputStream()))
            ).getAsJsonObject();

            return obj.get("firstName").getAsString() + " " +
                    obj.get("lastName").getAsString();

        } catch (Exception e) {
            return "Unknown";
        }
    }

    private void setupSearch() {
        FilteredList<AppointmentRow> filtered = new FilteredList<>(masterList, p -> true);

        searchField.textProperty().addListener((obs, old, val) -> {
            String lower = val.toLowerCase();

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

    @FXML
    private void handleCreateAppointment() {
        SceneNavigator.switchTo("/fxml/appointment_create.fxml");
    }

    @FXML
    private void handleBack() {
        SceneNavigator.switchTo(previousPage);
    }
}
