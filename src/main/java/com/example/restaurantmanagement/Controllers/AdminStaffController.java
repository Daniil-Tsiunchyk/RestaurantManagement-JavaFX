package com.example.restaurantmanagement.Controllers;

import com.example.restaurantmanagement.Entities.Staff;
import com.example.restaurantmanagement.Enums.Role;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Date;
import java.sql.SQLException;

import static com.example.restaurantmanagement.Database.StaffService.*;
import static com.example.restaurantmanagement.Utils.FxUtils.changeScene;

public class AdminStaffController {

    @FXML
    private TableColumn<Staff, Date> end_time;

    @FXML
    private TextField enter_login;

    @FXML
    private TextField enter_name;

    @FXML
    private PasswordField enter_password;

    @FXML
    private TableColumn<Staff, String> login_column;

    @FXML
    private TableColumn<Staff, String> name_column;

    @FXML
    private TableColumn<Staff, Integer> number_column;

    @FXML
    private TableColumn<Staff, String> password_column;

    @FXML
    private PasswordField repeat_password;

    @FXML
    private ChoiceBox<Role> role_choicebox;

    @FXML
    private TableColumn<Staff, Role> role_column;

    @FXML
    private TableView<Staff> staff_table;

    @FXML
    private TableColumn<Staff, Date> start_time;

    @FXML
    private Label error_message;

    @FXML
    void handleAddAccount(ActionEvent event) throws SQLException {
        String name = enter_name.getText();
        String login = enter_login.getText();
        String password = enter_password.getText();
        String repeatPassword = repeat_password.getText();
        Role role = role_choicebox.getSelectionModel().getSelectedItem();
        if (name.isEmpty() || login.isEmpty() || password.isEmpty() || role == null) {
            error_message.setText("Пожалуйста, заполните все поля и выберите роль");
            return;
        }
        if (!password.equals(repeatPassword)) {
            error_message.setText("Пароли не совпадают");
            return;
        }
        createUser(name, login, password, role);
        enter_name.setText("");
        enter_login.setText("");
        enter_password.setText("");
        repeat_password.setText("");
        updateTable();
    }

    @FXML
    void handleChangeRole(ActionEvent event) throws SQLException {
        Staff selectedStaff = staff_table.getSelectionModel().getSelectedItem();
        if (selectedStaff == null) {
            error_message.setText("Пожалуйста, выберите сотрудника");
            return;
        }
        Role role = selectedStaff.getRole();
        Role newRole = switch (role) {
            case WAITER -> Role.KITCHEN;
            case KITCHEN -> Role.MANAGER;
            case MANAGER -> Role.ADMINISTRATOR;
            case ADMINISTRATOR -> Role.WAITER;
        };

        updateUser(selectedStaff.getIdstaff(), newRole);
        updateTable();
    }

    @FXML
    void handleDeleteAccount(ActionEvent event) throws SQLException {
        Staff selectedStaff = staff_table.getSelectionModel().getSelectedItem();
        if (selectedStaff == null) {
            error_message.setText("Пожалуйста, выберите сотрудника");
            return;
        }

        deleteUser(selectedStaff.getIdstaff());
        updateTable();
    }

    @FXML
    void handleDismissStaff(ActionEvent event) throws SQLException {
        Staff selectedStaff = staff_table.getSelectionModel().getSelectedItem();
        if (selectedStaff == null) {
            error_message.setText("Пожалуйста, выберите сотрудника");
            return;
        }
        dismissStaff(selectedStaff.getIdstaff());
        updateTable();
    }

    @FXML
    void handleBack(ActionEvent event) {
        changeScene("AdminJob.fxml", "Панель администратора", 250, 275, event);
    }

    void updateTable() throws SQLException {
        number_column.setCellValueFactory(new PropertyValueFactory<>("idstaff"));
        name_column.setCellValueFactory(new PropertyValueFactory<>("name"));
        login_column.setCellValueFactory(new PropertyValueFactory<>("login"));
        password_column.setCellValueFactory(new PropertyValueFactory<>("password"));
        role_column.setCellValueFactory(new PropertyValueFactory<>("role"));
        start_time.setCellValueFactory(new PropertyValueFactory<>("apparatus_employed"));
        end_time.setCellValueFactory(new PropertyValueFactory<>("dismissal_from_work"));

        staff_table.setItems(getDataStaff());
    }

    @FXML
    void initialize() throws SQLException {
        ObservableList<Role> roles = FXCollections.observableArrayList(Role.values());
        role_choicebox.setItems(roles);
        updateTable();
    }
}
