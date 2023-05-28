package com.example.restaurantmanagement.Controllers;

import com.example.restaurantmanagement.Entities.Staff;
import com.example.restaurantmanagement.Entities.WorkHours;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.time.LocalDate;
import java.util.List;

import static com.example.restaurantmanagement.Database.StaffService.*;
import static com.example.restaurantmanagement.Utils.FxUtils.changeScene;


public class ManagerHoursController {
    @FXML
    private TableView<Staff> adminTable;

    @FXML
    private TableColumn<Staff, Integer> numberAdmin;

    @FXML
    private TableColumn<Staff, String> nameAdmin;

    @FXML
    private TableColumn<WorkHours, LocalDate> columnDate;

    @FXML
    private TableColumn<WorkHours, Integer> columnHours;

    @FXML
    private ComboBox<LocalDate> dateChoice;

    @FXML
    private TableView<WorkHours> hoursTable;

    @FXML
    private TableView<Staff> kitchenTable;

    @FXML
    private TableColumn<Staff, Integer> numberKitchen;

    @FXML
    private TableColumn<Staff, String> nameKitchen;

    @FXML
    private TableView<Staff> managerTable;

    @FXML
    private TableColumn<Staff, Integer> numberManager;

    @FXML
    private TableColumn<Staff, String> nameManager;

    @FXML
    private Label monthHours;

    @FXML
    private TextField nameField;

    @FXML
    private TableView<Staff> waiterTable;

    @FXML
    private TableColumn<Staff, Integer> numberWaiter;

    @FXML
    private TableColumn<Staff, String> nameWaiter;

    @FXML
    private TextField numberField;


    @FXML
    void handleAddHours() {
        int staffId = Integer.parseInt(numberField.getText());
        LocalDate date = dateChoice.getValue();

        if (checkShiftAvailability(staffId, date)) {
            addShift(staffId, date);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Успешно");
            alert.setHeaderText("Смена добавлена");
            alert.setContentText("Смена успешно добавлена.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Невозможно добавить смену");
            alert.setContentText("Работник уже назначен на данную смену.");
            alert.showAndWait();
        }
    }

    @FXML
    void handleBack(ActionEvent event) {
        changeScene("ManagerJob.fxml", "Панель менеджера", 240, 300, event);
    }

    @FXML
    void populateFields(MouseEvent event) {
        if (event.getClickCount() == 2) {
            TableView<Staff> sourceTable = (TableView<Staff>) event.getSource();
            Staff selectedItem = sourceTable.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                numberField.setText(String.valueOf(selectedItem.getId()));
                nameField.setText(selectedItem.getName());
                int shiftsInMonth = getShiftsInMonth(selectedItem.getId());
                monthHours.setText("Смен в месяце: " + shiftsInMonth);
            }
        }
    }

    @FXML
    void initialize() {
        loadData();
        initDateChoice();
    }

    private void loadData() {
        List<Staff> staffList = getStaff();
        List<WorkHours> workHoursList = getWorkHours();
        ObservableList<WorkHours> workHoursObservableList = FXCollections.observableArrayList(workHoursList);
        ObservableList<Staff> admins = FXCollections.observableArrayList();
        ObservableList<Staff> waiters = FXCollections.observableArrayList();
        ObservableList<Staff> managers = FXCollections.observableArrayList();
        ObservableList<Staff> kitchenStaff = FXCollections.observableArrayList();

        for (Staff staff : staffList) {
            switch (staff.getRole()) {
                case "АДМИНИСТРАТОР" -> admins.add(staff);
                case "ОФИЦИАНТ" -> waiters.add(staff);
                case "МЕНЕДЖЕР" -> managers.add(staff);
                case "КУХНЯ" -> kitchenStaff.add(staff);
            }
        }

        numberAdmin.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        nameAdmin.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        adminTable.setItems(admins);

        numberWaiter.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        nameWaiter.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        waiterTable.setItems(waiters);

        numberManager.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        nameManager.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        managerTable.setItems(managers);

        numberKitchen.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        nameKitchen.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        kitchenTable.setItems(kitchenStaff);

        columnDate.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getDate()));
        columnHours.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getHours()).asObject());
        hoursTable.setItems(workHoursObservableList);
    }

    private void initDateChoice() {
        LocalDate today = LocalDate.now();
        ObservableList<LocalDate> dates = FXCollections.observableArrayList();

        for (int i = 0; i < 7; i++) {
            dates.add(today.plusDays(i));
        }

        dateChoice.setItems(dates);
        dateChoice.setValue(today);
    }
}
