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
            JOptionPane.showMessageDialog(gui.window,"No Redo Found","Error",JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(gui.window,"No Undo Found","Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    public void copy()
    {
        gui.textarea.copy();
    }

    public void cut()
    {
        gui.textarea.cut();
    }

    public void paste()
    {
        gui.textarea.paste();
    }
    public void find()
    {
        String searchText = JOptionPane.showInputDialog(gui.window, "Enter text to search:");

        if (searchText != null && !searchText.isEmpty())
        {
            String text = gui.textarea.getText();
            int index = text.indexOf(searchText);

            if (index != -1)
            {
                gui.textarea.requestFocusInWindow();
                gui.textarea.select(index, index + searchText.length());
            }
            else
            {
                JOptionPane.showMessageDialog(gui.window, "Text not found", "Search", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    public void replace()
    {
        String searchText = JOptionPane.showInputDialog(gui.window , "Enter text to search:" );

        if (searchText != null && !searchText.isEmpty())
        {
            String text = gui.textarea.getText();
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
                    gui.textarea.setText(newText);
                    JOptionPane.showMessageDialog(gui.window, "Text replaced successfully", "Replace", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(gui.window, "Text not found", "Replace", JOptionPane.INFORMATION_MESSAGE);
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
                int totalLines = gui.textarea.getLineCount();

                if (totalLines > lineNumber)
                {
                    int position = gui.textarea.getLineStartOffset(lineNumber-1);

                    gui.textarea.setCaretPosition(position);

                    // Request focus for the text area
                    gui.textarea.requestFocusInWindow();
                }
                else
                {
                    // Display an error message if the line number is invalid
                    JOptionPane.showMessageDialog(gui.window, "Invalid line number", "Go To", JOptionPane.ERROR_MESSAGE);
                }
            }

            catch (NumberFormatException e)
            {
                JOptionPane.showMessageDialog(gui.window, "Invalid line number", "Go To", JOptionPane.ERROR_MESSAGE);

            }

            catch (Exception e)
            {
                // Display an error message for other exceptions
                JOptionPane.showMessageDialog(gui.window, "Error: " + e.getMessage(), "Go To", JOptionPane.ERROR_MESSAGE);
            }

        }

    }

    public void selectAll ()
    {
        gui.textarea.selectAll();
    }

    public void Date ()
    {
        // Get the current date and time
        Date currentDate = new Date();

        // Format the date
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd (HH:mm:ss)");
        String formattedDate = dateFormat.format(currentDate);

        // Append the formatted date to the text area content
        gui.textarea.append(formattedDate + "\n");
    }

}
