module com.example.restaurantmanagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.restaurantmanagement to javafx.fxml;
    exports com.example.restaurantmanagement;
    exports com.example.restaurantmanagement.Controllers;
    opens com.example.restaurantmanagement.Controllers to javafx.fxml;
    exports com.example.restaurantmanagement.Entities;
    opens com.example.restaurantmanagement.Entities to javafx.fxml;
    exports com.example.restaurantmanagement.Enums;
    opens com.example.restaurantmanagement.Enums to javafx.fxml;
    exports com.example.restaurantmanagement.Utils;
    opens com.example.restaurantmanagement.Utils to javafx.fxml;
}