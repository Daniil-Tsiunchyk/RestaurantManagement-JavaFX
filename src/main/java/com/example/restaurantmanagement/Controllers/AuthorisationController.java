package com.example.restaurantmanagement.Controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.SQLException;

import static com.example.restaurantmanagement.Database.StaffService.requestRoleFromDataBase;
import static com.example.restaurantmanagement.Utils.FxUtils.changeScene;

public class AuthorisationController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label statusLabel;

    @FXML
    public void handleLogin(ActionEvent event) throws SQLException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            statusLabel.setText("Пожалуйста, заполните все поля.");
            return;
        }

        String role = requestRoleFromDataBase(username, password);
        if (role == null) {
            statusLabel.setText("Неверный логин или пароль.");
        } else {
            openRoleInterface(role, event);
        }
    }

    private void openRoleInterface(String role, ActionEvent event) {
        String fxmlFile;
        String title;
        int width, height;

        switch (role) {
            case "ОФИЦИАНТ" -> {
                fxmlFile = "WaiterJob.fxml";
                title = "Панель официанта";
                width = 240;
                height = 275;
            }
            case "МЕНЕДЖЕР" -> {
                fxmlFile = "ManagerJob.fxml";
                title = "Панель менеджера";
                width = 270;
                height = 300;
            }
            case "КУХНЯ" -> {
                fxmlFile = "KitchenJob.fxml";
                title = "Панель кухни";
                width = 800;
                height = 500;
            }
            case "АДМИНИСТРАТОР" -> {
                fxmlFile = "AdminJob.fxml";
                title = "Панель администратора";
                width = 250;
                height = 275;
            }
            default -> throw new IllegalArgumentException("Неверная роль: " + role);
        }

        changeScene(fxmlFile, title, width, height, event);
    }

    @FXML
    void handleExit(ActionEvent event) {
        Platform.exit();
    }
}
