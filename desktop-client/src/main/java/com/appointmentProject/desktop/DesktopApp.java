/***********************************************************************
 *  DesktopApp.java
 *
 *          This class is a necessary file as it will launch our program.
 *          Spring Boot will boot up the REST server. While
 *          JavaFX will display the first window of the program.
 *
 * @author Matthew Kiyono
 * @version 1.0
 * @since 12/1/2025
************************************************************************/



package com.appointmentProject.desktop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DesktopApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        //Tells JavaFX what window to change to later.
        SceneNavigator.setMainStage(primaryStage);

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/fxml/login.fxml")
        );

        Scene scene = new Scene(loader.load());

        primaryStage.setTitle("Appointment System - Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args); // JavaFX bootstrap
    }
}
