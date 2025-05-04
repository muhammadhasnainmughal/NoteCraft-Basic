package NoteCraftpackage;

import javax.swing.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.*;


public class Function_Edit
{

    GUI gui;

    //constructor
    public Function_Edit(GUI gui)
    {
        this.gui = gui;
    }

    public void redo()
    {
        if (gui.U.canRedo())
        {
            gui.U.redo();
        }
        else
        {
            showError("No Redo Found");
        }

    }

    public void undo()
    {
        if (gui.U.canUndo())
        {
            gui.U.undo();
        }
        else
        {
            showError("No Undo Found");
        }
    }

    public void copy()
    {
        gui.currentTextArea.copy();
    }

    public void cut()
    {
        gui.currentTextArea.cut();
    }

    public void paste()
    {
        gui.currentTextArea.paste();
    }
    public void find()
    {
        String searchText = JOptionPane.showInputDialog(gui.window, "Enter text to search:");

        if (searchText != null && !searchText.isEmpty())
        {
            String text = gui.currentTextArea.getText();
            int index = text.indexOf(searchText);

            if (index != -1)
            {
                gui.currentTextArea.requestFocusInWindow();
                gui.currentTextArea.select(index, index + searchText.length());
            }
            else
            {
                showInfo("Text not found");
            }
        }
    }

    public void replace()
    {
        String searchText = JOptionPane.showInputDialog(gui.window , "Enter text to search:" );

        if (searchText != null && !searchText.isEmpty())
        {
            String text = gui.currentTextArea.getText();
            // Use regular expression to match whole words
            String regex = "\\b" + Pattern.quote(searchText) + "\\b";
            // Use Matcher to find whole words
            Matcher matcher = Pattern.compile(regex).matcher(text);

            if (matcher.find())
            {
                // Text found, prompt for replacement text
                String replaceText = JOptionPane.showInputDialog(gui.window, "Enter text to replace '" + searchText + "' with:");

                if (replaceText != null)
                {
                    String newText = matcher.replaceAll(replaceText);
                    gui.currentTextArea.setText(newText);
                    showInfo("Text replaced successfully");
                }
            }
            else
            {
                showInfo("Text not found");
            }
        }
    }

    public void  Goto ()
    {
        String line = JOptionPane.showInputDialog(gui.window, "Enter Line NUmber: ");

        // check if there's a line
        if (line != null && !line.isEmpty())
        {
            try
            {
                int lineNumber = Integer.parseInt(line);

                // Counts the total number of lines in the text area
                int totalLines = gui.currentTextArea.getLineCount();

                if (totalLines > lineNumber)
                {
                    int position = gui.currentTextArea.getLineStartOffset(lineNumber-1);

                    gui.currentTextArea.setCaretPosition(position);

                    // Request focus for the text area
                    gui.currentTextArea.requestFocusInWindow();
                }
                else
                {
                    // Display an error message if the line number is invalid
                    showError("Invalid line number");
                }
            }

            catch (NumberFormatException e)
            {
                showError("Invalid line number");

            }

            catch (Exception e)
            {
                // Display an error message for other exceptions
                showError("Error: " + e.getMessage());
            }

        }

    }

    public void selectAll ()
    {
        gui.currentTextArea.selectAll();
    }

    public void Date ()
    {
        // Get the current date and time
        Date currentDate = new Date();

        // Format the date
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy (HH:mm:ss)");
        String formattedDate = dateFormat.format(currentDate);

        // Append the formatted date to the text area content
        gui.currentTextArea.append(formattedDate + "\n");
    }

    // Show info message dialog
    private void showInfo(String message) {
        JOptionPane.showMessageDialog(gui.window, message, "Information", JOptionPane.INFORMATION_MESSAGE);
    }

    // Show error message dialog
    private void showError(String message) {
        JOptionPane.showMessageDialog(gui.window, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

}
