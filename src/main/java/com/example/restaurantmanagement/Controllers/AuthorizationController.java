package com.example.restaurantmanagement.Controllers;

import com.example.restaurantmanagement.Client;
import com.example.restaurantmanagement.Enums.Role;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AuthorizationController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    @FXML
    private Label statusLabel;

    private Client client;

    public void setClient(Client client) {
        this.client = client;
    }

    @FXML
    public void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            statusLabel.setText("Please fill in all fields.");
            return;
        }

        Role role = requestRoleFromServer(username, password);

        if (role == null) {
            statusLabel.setText("Invalid username or password.");
            return;
        }

        openRoleInterface(role);
    }

    private Role requestRoleFromServer(String username, String password) {
        // Реализуйте метод для отправки запроса на сервер и получения роли пользователя
        return null;
    }

    private void openRoleInterface(Role role) {
        // Реализуйте метод для открытия интерфейса в зависимости от роли пользователя
        // и закрытия окна авторизации
        Stage stage = (Stage) loginButton.getScene().getWindow();
        FxUtils.openRoleInterface(role, stage);
    }
}
