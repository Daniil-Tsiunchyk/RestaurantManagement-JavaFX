package com.example.restaurantmanagement.Controllers;

import com.example.restaurantmanagement.Client;
import com.example.restaurantmanagement.Database.StaffService;
import com.example.restaurantmanagement.Enums.Role;
import com.example.restaurantmanagement.Utils.FxUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;


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
    public void handleLogin() throws SQLException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            statusLabel.setText("Please fill in all fields.");
            return;
        }

        Role role = StaffService.requestRoleFromDataBase(username, password);

        if (role == null) {
            statusLabel.setText("Invalid username or password.");
            return;
        } else statusLabel.setText(String.valueOf(role));

        openRoleInterface(role);
    }

    private void openRoleInterface(Role role) {
        Stage stage = (Stage) loginButton.getScene().getWindow();
        FxUtils.openRoleInterface(role, stage);
    }
}
