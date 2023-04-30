package com.example.restaurantmanagement.Database;

import com.example.restaurantmanagement.Entities.Staff;
import com.example.restaurantmanagement.Utils.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class StaffService extends DBConnection {
    public static String requestRoleFromDataBase(String username, String password) throws SQLException {
        String role = null;

        String select = "SELECT role FROM staff WHERE login=? AND password=? AND dismissal_from_work IS NULL";

        try (Connection connection = DBConnection.getDbConnection();
             PreparedStatement prSt = connection.prepareStatement(select)) {

            prSt.setString(1, username);
            prSt.setString(2, password);
            ResultSet resSet = prSt.executeQuery();

            if (resSet.next()) {
                role = resSet.getString("role");
            }
        }
        return role;
    }

    public static ObservableList<Staff> getDataStaff() throws SQLException {
        ObservableList<Staff> list = FXCollections.observableArrayList();
        String select = "SELECT * FROM staff";
        try (Connection connection = getDbConnection();
             PreparedStatement ps = connection.prepareStatement(select);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapResultSetToStaff(rs));
            }
        }
        return list;
    }

    private static Staff mapResultSetToStaff(ResultSet rs) throws SQLException {
        return new Staff(
                rs.getInt("idstaff"),
                rs.getString("name"),
                rs.getString("login"),
                rs.getString("password"),
                rs.getString("role"),
                rs.getDate("apparatus_employed"),
                rs.getDate("dismissal_from_work")
        );
    }

    public static void createUser(String name, String login, String password, String role) {
        String insertQuery = "INSERT INTO staff (name, login, password, role, apparatus_employed) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = getDbConnection().prepareStatement(insertQuery)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, login);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, role);
            preparedStatement.setDate(5, new Date(System.currentTimeMillis()));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateUser(int id, String role) {
        String updateQuery = "UPDATE staff SET role = ? WHERE idstaff = ?";

        try (PreparedStatement preparedStatement = getDbConnection().prepareStatement(updateQuery)) {
            preparedStatement.setString(1, role);
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteUser(int id) {
        String deleteQuery = "DELETE FROM staff WHERE idstaff = ?";

        try (PreparedStatement preparedStatement = getDbConnection().prepareStatement(deleteQuery)) {
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void dismissStaff(int id) {
        String updateQuery = "UPDATE staff SET dismissal_from_work = ? WHERE idstaff = ?";

        try (PreparedStatement preparedStatement = getDbConnection().prepareStatement(updateQuery)) {
            preparedStatement.setDate(1, new Date(System.currentTimeMillis()));
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

