package NoteCraftpackage;

import java.sql.*;
import java.util.*;
import javax.swing.*;

public class ViewNotes {

    public static String title = "";
    public static String content = "";

    public static void view() {
        int userID = UserAuth.loggedInUserId;

        if (userID == -1) {
            JOptionPane.showMessageDialog(null, "Please login to view your saved notes.", "Login Required", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Fetch the saved notes for the logged-in user
        List<String> savedNotes = getUserSavedNotes(userID);

        if (savedNotes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No notes are saved.", "No Notes Found", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JList<String> notesList = new JList<>(savedNotes.toArray(new String[0]));
            notesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            JScrollPane listScrollPane = new JScrollPane(notesList);

            int option = JOptionPane.showOptionDialog(
                    null,
                    listScrollPane,
                    "Select a Note",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    null,
                    null
            );

            if (option == JOptionPane.OK_OPTION && !notesList.isSelectionEmpty()) {
                String selectedNoteTitle = notesList.getSelectedValue();
                String noteContent = getNoteContent(userID, selectedNoteTitle);

                content = noteContent;
                title = selectedNoteTitle;

            }
        }
    }

    private static List<String> getUserSavedNotes(int userID) {
        List<String> notes = new ArrayList<>();

        String query = "SELECT FileName FROM TabContents WHERE UserID = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                notes.add(rs.getString("FileName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return notes;
    }

    private static String getNoteContent(int userID, String title) {
        String content = "";

        String query = "SELECT Content FROM TabContents WHERE UserID = ? AND FileName = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userID);
            stmt.setString(2, title);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                content = rs.getString("Content");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return content;
    }
}
