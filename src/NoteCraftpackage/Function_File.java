package NoteCraftpackage;

import java.awt.*;
import java.awt.print.PrinterException;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class Function_File {
    // Class variables
    private GUI gui;
    private String fileName;
    private String fileAddress;

    // Constructor
    public Function_File(GUI gui) {
        this.gui = gui;
        fileName = null;
        fileAddress = null;
    }

    // Create a new file
    public void new_File() {
        gui.currentTextArea.setText(""); // Fix double periods
        gui.window.setTitle("Untitled");
        fileName = null;
        fileAddress = null;
    }

    // Open an existing file
    public void open_File() {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Open File");
        int returnValue = fileChooser.showOpenDialog(gui.window);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            fileName = selectedFile.getName();
            fileAddress = selectedFile.getParent();
            gui.window.setTitle(fileName); // Set window title to the file name

            try (BufferedReader br = new BufferedReader(new FileReader(selectedFile))) {
                gui.currentTextArea.setText(""); // Clear the text area before loading
                String line;
                while ((line = br.readLine()) != null) {
                    gui.currentTextArea.append(line + "\n");
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(gui.window, "Failed to open the file.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Check if the text has been modified (expand logic as needed)
    public boolean isTextModified() {
        // Implement logic to track text modifications, if required
        return !gui.currentTextArea.getText().isEmpty();
    }

    // Save the current file
    public void save_file() {
        if (fileName == null) {
            saveAs_file();
        } else {
            File file = new File(fileAddress, fileName);
            try (FileWriter fw = new FileWriter(file)) {
                fw.write(gui.currentTextArea.getText());
                gui.window.setTitle(fileName);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(gui.window, "Failed to save the file.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Save file as a new file
    public void saveAs_file() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save File As");
        int returnValue = fileChooser.showSaveDialog(gui.window);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            fileName = selectedFile.getName();
            fileAddress = selectedFile.getParent();
            gui.window.setTitle(fileName);

            try (FileWriter fw = new FileWriter(selectedFile)) {
                fw.write(gui.currentTextArea.getText());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(gui.window, "Failed to save the file.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Print the current text
    public void print() {
        try {
            gui.currentTextArea.print();
        } catch (PrinterException ex) {
            Logger.getLogger(Function_File.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Exit the application
    public void exit() {
        if (isTextModified()) {
            Object[] options = {"Save", "Don't Save", "Cancel"};
            int result = JOptionPane.showOptionDialog(
                    gui.window,
                    "Do you want to save changes to " + (fileName != null ? fileName : "Untitled") + "?",
                    "NoteCraft",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            if (result == JOptionPane.YES_OPTION) {
                save_file();
                System.exit(0);
            } else if (result == JOptionPane.NO_OPTION) {
                System.exit(0);
            }
        } else {
            System.exit(0);
        }
    }
}
