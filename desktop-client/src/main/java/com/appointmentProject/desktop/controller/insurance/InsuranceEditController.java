package com.appointmentProject.desktop.controller.insurance;

import com.appointmentProject.desktop.SceneNavigator;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class InsuranceEditController {

    public static int selectedInsuranceId;   // set by InsuranceListController

    @FXML private TextField idField;
    @FXML private TextField insuranceNameField;
    @FXML private TextField phoneField;
    @FXML private TextField emailField;
    @FXML private TextField addressField;

    @FXML private Label messageLabel;

    private final Gson gson = new Gson();

    @FXML
    private void initialize() {
        loadInsuranceData();
    }

    private void loadInsuranceData() {
        try {
            URL url = new URL("http://localhost:8080/insurance/" + selectedInsuranceId);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            JsonObject obj = gson.fromJson(br.readLine(), JsonObject.class);
            br.close();

            idField.setText(String.valueOf(obj.get("id").getAsInt()));
            insuranceNameField.setText(obj.get("insuranceName").getAsString());

            String phoneDigits = obj.get("phone").getAsString();
            phoneField.setText(formatPhone(phoneDigits));

            emailField.setText(obj.get("email").getAsString());
            addressField.setText(obj.get("address").getAsString());

            idField.setEditable(false);
            idField.setStyle("-fx-opacity: 0.5;");

        } catch (Exception e) {
            e.printStackTrace();
            messageLabel.setText("Error loading insurance.");
        }
    }

    private String formatPhone(String digits) {
        if (digits == null) return "";
        digits = digits.replaceAll("[^0-9]", "");

        if (digits.length() == 10) {
            return "(" + digits.substring(0, 3) + ") "
                    + digits.substring(3, 6) + "-"
                    + digits.substring(6);
        }

        if (digits.length() == 11 && digits.startsWith("1")) {
            return "+1 (" + digits.substring(1, 4) + ") "
                    + digits.substring(4, 7) + "-"
                    + digits.substring(7);
        }

        return digits;
    }

    @FXML
    private void handleSaveInsurance() {
        try {
            JsonObject body = new JsonObject();

            int id = Integer.parseInt(idField.getText().trim());
            body.addProperty("id", id);
            body.addProperty("insuranceName", insuranceNameField.getText().trim());

            String rawPhone = phoneField.getText().trim();
            String numericPhone = rawPhone.replaceAll("[^0-9]", "");
            body.addProperty("phone", numericPhone);

            body.addProperty("email", emailField.getText().trim());
            body.addProperty("address", addressField.getText().trim());

            URL url = new URL("http://localhost:8080/insurance/update");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("PUT");
            con.setDoOutput(true);
            con.setRequestProperty("Content-Type", "application/json");

            OutputStream os = con.getOutputStream();
            os.write(body.toString().getBytes());
            os.flush();
            os.close();

            int code = con.getResponseCode();
            if (code == 200) {
                messageLabel.setStyle("-fx-text-fill: green;");
                messageLabel.setText("Insurance updated successfully!");
            } else {
                messageLabel.setStyle("-fx-text-fill: red;");
                messageLabel.setText("Update failed.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            messageLabel.setStyle("-fx-text-fill: red;");
            messageLabel.setText("Server error.");
        }
    }

    @FXML
    private void handleDeleteInsurance() {
        try {
            int id = Integer.parseInt(idField.getText().trim());

            URL url = new URL("http://localhost:8080/insurance/delete/" + id);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("DELETE");

            int code = con.getResponseCode();
            if (code == 200) {
                messageLabel.setStyle("-fx-text-fill: green;");
                messageLabel.setText("Insurance deleted successfully!");
            } else {
                messageLabel.setStyle("-fx-text-fill: red;");
                messageLabel.setText("Delete failed.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            messageLabel.setStyle("-fx-text-fill: red;");
            messageLabel.setText("Error deleting insurance.");
        }
    }
    @FXML
    private void handleBack() {
        SceneNavigator.switchTo("/fxml/insurance_list.fxml");
    }
}
