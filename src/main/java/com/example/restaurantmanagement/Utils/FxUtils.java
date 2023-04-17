package com.example.restaurantmanagement.Utils;

import com.example.restaurantmanagement.Enums.Role;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class FxUtils {
    public static void openRoleInterface(Role role, Stage stage) {
        if (Objects.equals(role, Role.ADMINISTRATOR)) {
            openNewSceneOnThisStage(stage, "", "");
        }
        if (Objects.equals(role, Role.MANAGER)) {
            openNewSceneOnThisStage(stage, "", "");

        }
        if (Objects.equals(role, Role.KITCHEN)) {
            openNewSceneOnThisStage(stage, "", "");

        }
        if (Objects.equals(role, Role.WAITER)) {
            openNewSceneOnThisStage(stage, "", "");

        }

    }

    public static void openNewSceneOnThisStage(Stage stage, String window, String title) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FxUtils.class.getResource(window));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        stage.setTitle(title);
        //stage.getIcons().add(new Image((Objects.requireNonNull(Main.class.getResourceAsStream("image/logo.png")))));
        stage.setScene(new Scene(root));
        stage.show();
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
