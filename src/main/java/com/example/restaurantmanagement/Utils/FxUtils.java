package com.example.restaurantmanagement.Utils;

import com.example.restaurantmanagement.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FxUtils {

    public static void changeScene(String name, String title, int width, int height, ActionEvent event) {
        Stage stage = getStageFromEvent(event);
        FXMLLoader fxmlLoader = getFXMLLoader(name);

        try {
            Scene scene = new Scene(fxmlLoader.load(), width, height);
            setStageProperties(stage, title, scene);
        } catch (IOException e) {
            System.err.println("Error loading the scene: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error changing the scene: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static Stage getStageFromEvent(ActionEvent event) {
        return (Stage) ((Node) event.getSource()).getScene().getWindow();
    }

    private static FXMLLoader getFXMLLoader(String name) {
        return new FXMLLoader(Main.class.getResource(name));
    }

    private static void setStageProperties(Stage stage, String title, Scene scene) {
        stage.setResizable(false);
        stage.setTitle(title);
        stage.setScene(scene);
    }
}
