package com.example.restaurantmanagement.Database;

import com.example.restaurantmanagement.Entities.Staff;
import com.example.restaurantmanagement.Entities.WorkHours;
import com.example.restaurantmanagement.Utils.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
                rs.getInt("id"),
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
        String updateQuery = "UPDATE staff SET role = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = getDbConnection().prepareStatement(updateQuery)) {
            preparedStatement.setString(1, role);
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteUser(int id) {
        String deleteQuery = "DELETE FROM staff WHERE id = ?";

        try (PreparedStatement preparedStatement = getDbConnection().prepareStatement(deleteQuery)) {
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void dismissStaff(int id) {
        String updateQuery = "UPDATE staff SET dismissal_from_work = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = getDbConnection().prepareStatement(updateQuery)) {
            preparedStatement.setDate(1, new Date(System.currentTimeMillis()));
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Staff> getStaff() {
        List<Staff> staffList = new ArrayList<>();
        String query = "SELECT * FROM staff";
        try (Connection connection = getDbConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");
                Date apparatusEmployed = resultSet.getDate("apparatus_employed");
                Date dismissalFromWork = resultSet.getDate("dismissal_from_work");

                Staff staff = new Staff(id, name, login, password, role, apparatusEmployed, dismissalFromWork);
                staffList.add(staff);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return staffList;
    }

    public static boolean checkShiftAvailability(int staffId, LocalDate date) {
        String query = "SELECT * FROM work_hours WHERE staff_id = ? AND DATE = ?";
        try (Connection connection = getDbConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, staffId);
            preparedStatement.setDate(2, Date.valueOf(date));

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (!resultSet.next()) {
                    return true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void addShift(int staffId, LocalDate date) {
        String query = "INSERT INTO work_hours (staff_id, DATE) VALUES (?, ?)";
        try (Connection connection = getDbConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, staffId);
            preparedStatement.setDate(2, Date.valueOf(date));

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getShiftsInMonth(int staffId) {
        int shiftsInMonth = 0;
        String query = "SELECT COUNT(*) FROM work_hours WHERE staff_id = ? AND MONTH(DATE) = MONTH(CURRENT_DATE()) AND YEAR(DATE) = YEAR(CURRENT_DATE())";
        try (Connection connection = getDbConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, staffId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    shiftsInMonth = resultSet.getInt(1);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shiftsInMonth;
    }

    public static List<WorkHours> getWorkHours() {
        List<WorkHours> workHoursList = new ArrayList<>();

        try {
            Connection connection = getDbConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM work_hours";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int staffId = resultSet.getInt("staff_id");
                LocalDate date = resultSet.getObject("DATE", LocalDate.class);
                int hours = resultSet.getInt("staff_id");

                WorkHours workHours = new WorkHours(id, staffId, date, hours);
                workHoursList.add(workHours);
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return workHoursList;
    }
}

