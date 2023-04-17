package com.example.restaurantmanagement.Controllers;

import com.example.restaurantmanagement.Enums.Role;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FxUtils {
    public static void openRoleInterface(Role role, Stage stage) {

    }

    public static void openNewScene(String window, String title) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FxUtils.class.getResource(window));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setTitle(title);
        //stage.getIcons().add(new Image((Objects.requireNonNull(Main.class.getResourceAsStream("image/logo.png")))));
        stage.setScene(new Scene(root));
        stage.show();
    }
}
