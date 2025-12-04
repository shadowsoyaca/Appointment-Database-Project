/*********************************************************************
 *   SceneNavigator.java
 *
 *      This class is responsible for redirecting the users to
 *      specific dashboards depending on their user's account type.
 *
 * @author Matthew Kiyono
 * @version 1.0
 * @since 12/1/2025
 *********************************************************************/
package com.appointmentProject.desktop;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneNavigator {

    private static Stage mainStage;

    public static void setMainStage(Stage stage) {
        mainStage = stage;
    }

    public static void switchTo(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneNavigator.class.getResource(fxmlPath));
            Scene scene = new Scene(loader.load());
            mainStage.setScene(scene);
            mainStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
