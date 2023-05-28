package com.example.restaurantmanagement.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.example.restaurantmanagement.Database.OrderService.bookingTable;
import static com.example.restaurantmanagement.Utils.FxUtils.changeScene;

public class ManagerTablesController {
    @FXML
    private ComboBox<LocalDate> DateChoice;

    @FXML
    private ComboBox<String> TimeChoice;

    @FXML
    private Label errorMsg;

    @FXML
    private TextField nameField;

    @FXML
    private ComboBox<Integer> tableChoice;

    @FXML
    void handleBack(ActionEvent event) {
        changeScene("ManagerJob.fxml", "Панель менеджера", 240, 300, event);
    }

    @FXML
    void handleBooking() throws SQLException {

        if (areFieldsFilled()) {
            errorMsg.setText(bookingTable(tableChoice.getValue(), DateChoice.getValue(), TimeChoice.getValue(), nameField.getText()));
        } else {
            errorMsg.setText("Пожалуйста, заполните все поля");
        }
    }

    private boolean areFieldsFilled() {
        return DateChoice.getValue() != null && TimeChoice.getValue() != null && tableChoice.getValue() != null && !nameField.getText().isEmpty();
    }

    @FXML
    void initialize() {
        populateDateChoice();
        populateTimeChoice();
        populateTableChoice();
    }

    private void populateDateChoice() {
        List<LocalDate> dates = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            dates.add(LocalDate.now().plusDays(i));
        }
        ObservableList<LocalDate> dateOptions = FXCollections.observableArrayList(dates);
        DateChoice.setItems(dateOptions);
    }

    private void populateTimeChoice() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        List<String> times = new ArrayList<>();
        for (int i = 12; i < 20; i++) {
            times.add(LocalTime.of(i, 0).format(timeFormatter));
            times.add(LocalTime.of(i, 30).format(timeFormatter));
        }
        ObservableList<String> timeOptions = FXCollections.observableArrayList(times);
        TimeChoice.setItems(timeOptions);
    }


    private void populateTableChoice() {
        List<Integer> tables = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            tables.add(i);
        }
        ObservableList<Integer> tableOptions = FXCollections.observableArrayList(tables);
        tableChoice.setItems(tableOptions);
    }
}