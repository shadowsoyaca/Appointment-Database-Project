package com.appointmentProject.desktop.controller.insurance;

import com.appointmentProject.desktop.SceneNavigator;
import com.google.gson.JsonObject;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class InsuranceCreateController {

    @FXML private TextField insuranceNameField;
    @FXML private TextField phoneField;
    @FXML private TextField emailField;
    @FXML private TextField addressField;

    @FXML private Label messageLabel;

    @FXML
    private void handleCreateInsurance() {
        try {
            JsonObject body = new JsonObject();

            body.addProperty("insuranceName", insuranceNameField.getText().trim());

            String rawPhone = phoneField.getText().trim();
            String numericPhone = rawPhone.replaceAll("[^0-9]", "");
            body.addProperty("phone", numericPhone);

            body.addProperty("email", emailField.getText().trim());
            body.addProperty("address", addressField.getText().trim());

            URL url = new URL("http://localhost:8080/insurance/add");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setRequestProperty("Content-Type", "application/json");

            OutputStream os = con.getOutputStream();
            os.write(body.toString().getBytes());
            os.flush();
            os.close();

            int code = con.getResponseCode();
            if (code == 200) {
                messageLabel.setStyle("-fx-text-fill: green;");
                messageLabel.setText("Insurance created successfully!");
            } else {
                messageLabel.setStyle("-fx-text-fill: red;");
                messageLabel.setText("Creation failed.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            messageLabel.setStyle("-fx-text-fill: red;");
            messageLabel.setText("Server error.");
        }
    }

    @FXML
    private void handleBack() {
        SceneNavigator.switchTo("/fxml/insurance_list.fxml");
    }
}
