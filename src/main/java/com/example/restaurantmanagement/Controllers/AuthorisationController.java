package com.example.restaurantmanagement.Controllers;

import com.example.restaurantmanagement.Client;
import com.example.restaurantmanagement.Database.StaffService;
import com.example.restaurantmanagement.Enums.Role;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.SQLException;

import static com.example.restaurantmanagement.Utils.FxUtils.changeScene;


public class AuthorisationController {
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
    public void handleLogin(ActionEvent event) throws SQLException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            statusLabel.setText("Пожалуйста, заполните все поля.");
            return;
        }
        Role role = StaffService.requestRoleFromDataBase(username, password);
        if (role == null) {
            statusLabel.setText("Неверный логин или пароль.");
        } else {

            switch (role) {
                case WAITER: {
                    changeScene("WaiterMainMenu.fxml", "Работа официанта", 250, 300, event);

                }
                case MANAGER: {
                    changeScene("ManagerJob.fxml", "Работа менеджера", 600, 400, event);


                }
                case KITCHEN: {
                    changeScene("KitchenJob.fxml", "Работа кухни", 600, 400, event);

                }
                case ADMINISTRATOR: {
                    changeScene("AdministratorJob.fxml", "Работа администратора", 600, 400, event);

                }

            }
            statusLabel.setText(String.valueOf(role));
        }

    }

//    private void openRoleInterface(Role role) {
//        Stage stage = (Stage) loginButton.getScene().getWindow();
//        FxUtils.openRoleInterface(role, stage);
//    }
}
