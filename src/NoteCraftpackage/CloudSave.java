package NoteCraftpackage;

import java.sql.*;
import javax.swing.*;

public class CloudSave {

    // Save or update note to the cloud
    public static void saveToCloud(String fileName, String content, boolean isUpdate) {
        if (!UserAuth.isLoggedIn()) {
            JOptionPane.showMessageDialog(null, "You must log in first!","Error",JOptionPane.ERROR_MESSAGE);
            return;
        }

        int userId = UserAuth.getLoggedInUserId();

        try (Connection conn = DatabaseConnection.connect()) {
            // If updating existing file
            if (isUpdate) {
                // Update the existing note
                String updateQuery = "UPDATE TabContents SET Content = ?, SavedAt = ? WHERE UserID = ? AND FileName = ?";
                try (PreparedStatement stmt = conn.prepareStatement(updateQuery)) {
                    stmt.setString(1, content);
                    stmt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
                    stmt.setInt(3, userId);
                    stmt.setString(4, fileName);
                    int rowsUpdated = stmt.executeUpdate();

                    if (rowsUpdated > 0) {
                        JOptionPane.showMessageDialog(null, "File updated successfully in the cloud!","Information",JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "No such file exists to update.","Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                // If saving a new file
                String insertQuery = "INSERT INTO TabContents (UserID, FileName, Content, SavedAt) VALUES (?, ?, ?, ?)";
                try (PreparedStatement stmt = conn.prepareStatement(insertQuery)) {
                    stmt.setInt(1, userId);
                    stmt.setString(2, fileName);
                    stmt.setString(3, content);
                    stmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
                    stmt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "File saved to cloud successfully!","Information",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Check if the file exists in the cloud
    public static boolean doesFileExist(String fileName) {
        boolean exists = false;
        int userId = UserAuth.getLoggedInUserId();

        String query = "SELECT COUNT(*) FROM TabContents WHERE UserID = ? AND FileName = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);
            stmt.setString(2, fileName);
            ResultSet rs = stmt.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                exists = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exists;
    }
}
