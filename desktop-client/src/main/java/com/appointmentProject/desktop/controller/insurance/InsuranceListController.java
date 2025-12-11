package com.appointmentProject.desktop.controller.insurance;

import com.appointmentProject.desktop.SceneNavigator;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class InsuranceListController {

    @FXML private TextField searchField;

    @FXML private TableView<JsonObject> insuranceTable;
    @FXML private TableColumn<JsonObject, String> idColumn;
    @FXML private TableColumn<JsonObject, String> nameColumn;
    @FXML private TableColumn<JsonObject, String> phoneColumn;
    @FXML private TableColumn<JsonObject, String> emailColumn;
    @FXML private TableColumn<JsonObject, String> addressColumn;

    @FXML private Label messageLabel;

    private final ObservableList<JsonObject> insuranceList = FXCollections.observableArrayList();
    private FilteredList<JsonObject> filteredList;

    private final Gson gson = new Gson();

    @FXML
    public void initialize() {
        setupTable();
        setupSearch();
        loadInsurance();
    }

    private void setupTable() {

        idColumn.setCellValueFactory(data ->
                Bindings.createStringBinding(() ->
                        data.getValue().get("id").getAsString()
                ));

        nameColumn.setCellValueFactory(data ->
                Bindings.createStringBinding(() ->
                        data.getValue().get("insuranceName").getAsString()
                ));

        phoneColumn.setCellValueFactory(data ->
                Bindings.createStringBinding(() ->
                        data.getValue().get("phone").getAsString()
                ));

        emailColumn.setCellValueFactory(data ->
                Bindings.createStringBinding(() ->
                        data.getValue().get("email").getAsString()
                ));

        addressColumn.setCellValueFactory(data ->
                Bindings.createStringBinding(() ->
                        data.getValue().get("address").getAsString()
                ));

        filteredList = new FilteredList<>(insuranceList, p -> true);
        insuranceTable.setItems(filteredList);
    }

    private void setupSearch() {
        searchField.textProperty().addListener((obs, oldVal, newVal) -> {

            String lower = newVal.toLowerCase();

            filteredList.setPredicate(ins -> {
                if (lower.isEmpty()) return true;

                return ins.get("insuranceName").getAsString().toLowerCase().contains(lower)
                        || ins.get("email").getAsString().toLowerCase().contains(lower)
                        || ins.get("phone").getAsString().toLowerCase().contains(lower)
                        || ins.get("address").getAsString().toLowerCase().contains(lower)
                        || ins.get("id").getAsString().toLowerCase().contains(lower);
            });
        });
    }


    private void loadInsurance() {
        try {
            URL url = new URL("http://localhost:8080/insurance/all");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            JsonArray arr = gson.fromJson(new InputStreamReader(con.getInputStream()), JsonArray.class);

            insuranceList.clear();
            arr.forEach(el -> insuranceList.add(el.getAsJsonObject()));

            messageLabel.setText("");

        } catch (Exception e) {
            e.printStackTrace();
            messageLabel.setText("Failed to load insurance.");
        }
    }

    @FXML
    private void handleRefresh() {
        loadInsurance();
    }

    @FXML
    private void handleCreateInsurance() {
        SceneNavigator.switchTo("/fxml/insurance_create.fxml");
    }

    @FXML
    private void handleEditInsurance() {

        JsonObject selected = insuranceTable.getSelectionModel().getSelectedItem();

        if (selected == null) {
            messageLabel.setText("Please select an insurance record.");
            return;
        }

        // pass selected id to edit controller
        InsuranceEditController.selectedInsuranceId = selected.get("id").getAsInt();

        SceneNavigator.switchTo("/fxml/insurance_edit.fxml");
    }

    @FXML
    private void handleDeleteInsurance() {

        JsonObject selected = insuranceTable.getSelectionModel().getSelectedItem();

        if (selected == null) {
            messageLabel.setText("Please select a record to delete.");
            return;
        }

        try {
            int id = selected.get("id").getAsInt();

            URL url = new URL("http://localhost:8080/insurance/delete/" + id);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("DELETE");

            if (con.getResponseCode() == 200) {
                loadInsurance();
                messageLabel.setStyle("-fx-text-fill: green;");
                messageLabel.setText("Record deleted.");
            } else {
                messageLabel.setText("Delete failed.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            messageLabel.setText("Server error.");
        }
    }

    @FXML
    private void handleBack() {
        SceneNavigator.switchTo("/fxml/admin_dashboard.fxml");
    }
}