package NoteCraftpackage;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class pdfConvert {

    // Method to handle the PDF-to-text conversion and adding a new tab
    public static String convert() {

        // Show file chooser dialog
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            if (selectedFile.getName().endsWith(".pdf")) {
                try {
                    // Extract text from the selected PDF
                    String extractedText = extractTextFromPDF(selectedFile);

                    return extractedText;

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error reading PDF: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please select a valid PDF file.",
                        "Invalid File", JOptionPane.WARNING_MESSAGE);
            }
        }
        return null;
    }

    // Extract text from PDF using Apache PDFBox
    private static String extractTextFromPDF(File pdfFile) throws Exception {
        try (PDDocument document = PDDocument.load(pdfFile)) {
            PDFTextStripper textStripper = new PDFTextStripper();
            return textStripper.getText(document);
        }
    }

}
