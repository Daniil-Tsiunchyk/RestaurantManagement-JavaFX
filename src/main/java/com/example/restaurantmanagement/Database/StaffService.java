package com.example.restaurantmanagement.Database;

import com.example.restaurantmanagement.Entities.Staff;
import com.example.restaurantmanagement.Enums.Role;
import com.example.restaurantmanagement.Utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StaffService {
    public static Role requestRoleFromDataBase(String username, String password) throws SQLException {
        Role role = null;

        String select = "SELECT role FROM staff WHERE login=? AND password=?";

        try (Connection connection = DBConnection.getDbConnection();
             PreparedStatement prSt = connection.prepareStatement(select)) {

            prSt.setString(1, username);
            prSt.setString(2, password);
            ResultSet resSet = prSt.executeQuery();

            if (resSet.next()) {
                role = Role.valueOf(resSet.getString("role"));
            }
        }
        return role;
    }

    void createUser(Staff user) {
    }

    void updateUser(Staff user) {
    }

    void deleteUser(int userId) {
    }

    Staff getUserById(int userId) {
        return null;
    }

    Staff getUserByEmail(String email) {
        return null;
    }

    List<Staff> getUsersByRole(Role role) {
        return null;
    }

}

