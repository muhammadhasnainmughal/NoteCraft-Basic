package NoteCraftpackage;
import javax.swing.*;
import java.awt.*;

public class Function_Format {

    GUI gui;
    String selectedFont;

    Color c2;

    //constructor
    public Function_Format(GUI gui)
    {
        this.gui = gui;
    }

    public void word_wrap()
    {
        if (!gui.wordWrap.getState()) {
            gui.wordWrap.setState(true);
            gui.textarea.setLineWrap(true);
            gui.textarea.setWrapStyleWord(true);
        } else if (gui.wordWrap.getState()) {
            gui.wordWrap.setState(false);
            gui.textarea.setLineWrap(false);
            gui.textarea.setWrapStyleWord(false);
        }
    }

    public void setFontStyle() {

        Font textAreaFont = gui.textarea.getFont();

        // Get the font size from the text area font
        int defaultFontSize = textAreaFont.getSize();

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontNames = ge.getAvailableFontFamilyNames();
        selectedFont = (String) JOptionPane.showInputDialog(gui.window, "Choose Font Style:", "Font Selection", JOptionPane.PLAIN_MESSAGE, null, fontNames, fontNames[0]);

        if (selectedFont != null) {
            gui.textarea.setFont(new Font(selectedFont, Font.PLAIN, defaultFontSize));
        }
    }

    public void setFontSize() {
        // Create an array for font sizes
        Integer[] fontSizes = {8, 9, 10, 11, 12, 14, 16, 18, 20, 22, 24, 26, 28, 36, 48, 72};

        // Create a dropdown for selecting font size
        Integer selectedFontSize = (Integer) JOptionPane.showInputDialog(gui.window, "Choose Font Size:", "Font Size Selection", JOptionPane.PLAIN_MESSAGE, null, fontSizes, fontSizes[0]);

        if (selectedFontSize != null) {
            // Set the font size for the JTextArea
            Font currentFont = gui.textarea.getFont();
            gui.textarea.setFont(currentFont.deriveFont((float)selectedFontSize));
        }
    }

    public void setBackColor() {
        Color c2 = JColorChooser.showDialog(gui.window, "Select Color:", Color.WHITE);
        gui.textarea.setBackground(c2);
    }


    public void setFontColor() {

        Color c1 = JColorChooser.showDialog(gui.window, "Select Color:", Color.WHITE);
        gui.textarea.setForeground(c1);

    }

}
