package NoteCraftpackage;
import java.sql.*;
import javax.swing.*;

public class UserAuth {
    public static int loggedInUserId = -1;
    public static String loggedInUser = "";


    public static boolean login(String username, String password) {

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT ID, UserName FROM Users WHERE Username = ? AND Password = ?")) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // If login is successful, set loggedInUserId
                loggedInUserId = rs.getInt("ID");
                loggedInUser = rs.getString("UserName");
                return true;  // Login successful
            } else {
                // Invalid username or password
                JOptionPane.showMessageDialog(null, "Invalid username or password.","Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "An error occurred while logging in.","Error",JOptionPane.ERROR_MESSAGE);
        }
        return false;  // Return false if login was unsuccessful
    }



    public static boolean signup(String username, String password) {
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO Users (Username, Password) VALUES (?, ?)")) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void logout() {
        loggedInUserId = -1;
    }

    public static boolean isLoggedIn() {
        return loggedInUserId != -1;
    }

    public static int getLoggedInUserId() {
        return loggedInUserId;
    }
}

