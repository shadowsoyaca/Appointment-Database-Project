/********************************************************************
 *  LabOrderListController.java
 *
 *          This controller is responsible for providing a list of
 *          lab orders.
 *
 * @author Jack Mitchell
 * @version 1.0
 * @since 12/10/2025
 ********************************************************************/
package com.appointmentProject.desktop.controller.laborder;

import com.appointmentProject.desktop.SceneNavigator;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;

public class LabOrderListController {

    @FXML private TableView<LabOrderRow> labOrdersTable;
    @FXML private TableColumn<LabOrderRow, Integer> idCol;
    @FXML private TableColumn<LabOrderRow, Integer> providerRequesterIdCol;
    @FXML private TableColumn<LabOrderRow, Integer> providerReceiverIdCol;
    @FXML private TableColumn<LabOrderRow, Integer> nurseIdCol;
    @FXML private TableColumn<LabOrderRow, Integer> patientIdCol;
    @FXML private TableColumn<LabOrderRow, LocalDateTime> dateOfCompletionCol;
    @FXML private TableColumn<LabOrderRow, String> testingPurposeCol;
    @FXML private TableColumn<LabOrderRow, Boolean> resultsCol;

    @FXML private Label messageLabel;
    @FXML private TextField searchField;

    public static String previousPage = "/fxml/login.fxml";
    private final ObservableList<LabOrderRow> masterList = FXCollections.observableArrayList();

    public static class LabOrderRow {
        private final int id;
        private final int providerRequesterId;
        private final Integer providerReceiverId;
        private final Integer nurseId;
        private final int patientId;

        private final LocalDateTime dateOfCompletion;
        private final String testingPurpose;
        private final boolean results;

        public LabOrderRow(int id, int providerRequesterId, Integer providerReceiverId, Integer nurseId, int patientId, LocalDateTime dateOfCompletion, String testingPurpose, boolean results) {
            this.id = id;
            this.providerRequesterId = providerRequesterId;
            this.providerReceiverId = providerReceiverId;
            this.nurseId = nurseId;
            this.patientId = patientId;
            this.dateOfCompletion = dateOfCompletion;
            this.testingPurpose = testingPurpose;
            this.results = results;
        }

        public int getId() { return id; }
        public int getProviderRequesterId() { return providerRequesterId; }
        public Integer getProviderReceiverId() { return providerReceiverId; }
        public Integer getNurseId() { return nurseId; }
        public int getPatientId() { return patientId; }
        public LocalDateTime getDateOfCompletion() { return dateOfCompletion; }
        public String getTestingPurpose() { return testingPurpose; }
        public boolean getResults() { return results; }
    }

    @FXML
    private void initialize() {

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        providerRequesterIdCol.setCellValueFactory(new PropertyValueFactory<>("providerRequesterId"));
        providerReceiverIdCol.setCellValueFactory(new PropertyValueFactory<>("providerReceiverId"));
        nurseIdCol.setCellValueFactory(new PropertyValueFactory<>("nurseId"));
        patientIdCol.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        dateOfCompletionCol.setCellValueFactory(new PropertyValueFactory<>("dateOfCompletion"));
        testingPurposeCol.setCellValueFactory(new PropertyValueFactory<>("testingPurpose"));
        resultsCol.setCellValueFactory(new PropertyValueFactory<>("results"));

        applyColumnStyling();
        loadLabOrders();

        if (searchField != null) {
            searchField.textProperty().addListener((obs, oldVal, newVal) -> filterLabOrders(newVal));
        }
    }

    private void applyColumnStyling() {
        labOrdersTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

        centerAlign(idCol);
        leftAlign(providerRequesterIdCol);
        leftAlign(providerReceiverIdCol);
        leftAlign(nurseIdCol);
        leftAlign(patientIdCol);
        leftAlign(dateOfCompletionCol);
        leftAlign(testingPurposeCol);
        leftAlign(resultsCol);

        idCol.setMinWidth(60);
        providerRequesterIdCol.setMinWidth(130);
        providerReceiverIdCol.setMinWidth(130);
        nurseIdCol.setMinWidth(130);
        patientIdCol.setMinWidth(130);
        dateOfCompletionCol.setMinWidth(200);
        testingPurposeCol.setMinWidth(200);
        resultsCol.setMinWidth(130);
    }

    private <T> void centerAlign(TableColumn<T, ?> col) {
        col.setStyle("-fx-alignment: CENTER;");
    }

    private <T> void leftAlign(TableColumn<T, ?> col) {
        col.setStyle("-fx-alignment: CENTER-LEFT;");
    }

    private void loadLabOrders() {
        try {
            URL url = new URL("http://localhost:8080/laborder/all");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            in.close();

            JsonArray arr = com.google.gson.JsonParser.parseString(sb.toString()).getAsJsonArray();

            ObservableList<LabOrderRow> rows = FXCollections.observableArrayList();

            for (JsonElement el : arr) {
                JsonObject obj = el.getAsJsonObject();

                int id = obj.get("id").getAsInt();
                int providerRequesterId = obj.get("providerRequesterId").getAsInt();
                Integer providerReceiverId = obj.get("providerReceiverId").getAsInt();
                Integer nurseId = obj.get("nurseId").getAsInt();
                int patientId = obj.get("patientId").getAsInt();
                LocalDateTime dateOfCompletion = LocalDateTime.parse(obj.get("dateOfCompletion").getAsString());
                String testingPurpose = obj.get("testingPurpose").getAsString();
                boolean results = obj.get("results").getAsBoolean();

                rows.add(new LabOrderRow(id, providerRequesterId, providerReceiverId, nurseId, patientId, dateOfCompletion, testingPurpose, results));
            }

            masterList.setAll(rows);
            labOrdersTable.setItems(masterList);
            messageLabel.setText("");

        } catch (Exception e) {
            e.printStackTrace();
            messageLabel.setText("Error loading lab orders.");
        }
    }

    private void filterLabOrders(String query) {
        if (query == null || query.isBlank()) {
            labOrdersTable.setItems(masterList);
            return;
        }

        String lower = query.toLowerCase();

        ObservableList<LabOrderRow> filtered = masterList.filtered(n ->
                        String.valueOf(n.getProviderRequesterId()).toLowerCase().contains(lower) ||
                        String.valueOf(n.getProviderReceiverId()).toLowerCase().contains(lower) ||
                        String.valueOf(n.getNurseId()).toLowerCase().contains(lower) ||
                        String.valueOf(n.getPatientId()).toLowerCase().contains(lower) ||
                        String.valueOf(n.getDateOfCompletion()).toLowerCase().contains(lower) ||
                        n.getTestingPurpose().toLowerCase().contains(lower) ||
                        String.valueOf(n.getResults()).toLowerCase().contains(lower) ||
                        String.valueOf(n.getId()).contains(lower)
        );

        labOrdersTable.setItems(filtered);
    }

    @FXML
    public void handleBack() {
        SceneNavigator.switchTo(previousPage);
    }

    @FXML
    public void handleCreateLabOrder() {
        SceneNavigator.switchTo("/fxml/laborder_create.fxml");
    }

    @FXML
    public void handleEditLabOrder() {
        LabOrderRow row = labOrdersTable.getSelectionModel().getSelectedItem();
        if (row == null) {
            messageLabel.setText("Please select a lab order to edit.");
            return;
        }

        LabOrderEditController.selectedLabOrderId = row.getId();
        SceneNavigator.switchTo("/fxml/laborder_edit.fxml");
    }
}
