package NoteCraftpackage;
import java.awt.*;
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class Function_File {

    //make class object
    GUI gui;
    String fileName;
    String fileAddress;

    //constructor
    public Function_File(GUI gui)
    {
        this.gui = gui;
    }

    public void new_File()
    {
        gui.textarea.setText("");
        gui.window.setTitle("New");
        fileName = null;
        fileAddress = null;
    }

    public void open_File()
    {
        FileDialog fd = new FileDialog(gui.window,"Open", FileDialog.LOAD);
        fd.setVisible(true);

        if(fd.getFiles() != null)
        {
            fileName = fd.getFile();
            fileAddress = fd.getDirectory();
            gui.window.setTitle(fileName);
        }
        
        try{
            BufferedReader br = new BufferedReader(new FileReader(fileAddress + fileName));
            gui.textarea.setText("");

            String Line = null;

            while((Line = br.readLine()) != null)
            {
                gui.textarea.append(Line + "\n");
            }
            br.close();

        }
        catch(Exception e){

            JOptionPane.showMessageDialog(gui.window,"File Not Open","Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean isTextModified() {
        return true;
    }

    public void save_file()
    {
        if(fileName == null)
        {
            saveAs_file();
        }
        else {
            try{
                FileWriter fw = new FileWriter(fileAddress + fileName);
                fw.write(gui.textarea.getText());
                gui.window.setTitle(fileName);
                fw.close();
            }
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(gui.window,"File Not Save","Error",JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void saveAs_file()
    {
        FileDialog fd = new FileDialog(gui.window, "Save", FileDialog.SAVE);
        fd.setVisible(true);

        if((fd.getFiles()) != null)
        {
            fileName = fd.getFile();
            fileAddress = fd.getDirectory();
            gui.window.setTitle(fileName);
        }

        try{
            FileWriter fw = new FileWriter(fileAddress + fileName);
            fw.write(gui.textarea.getText());
            fw.close();
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(gui.window,"File Not Save","Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    public void print()
    {
        try {
            gui.textarea.print();
        } catch (PrinterException ex) {
            Logger.getLogger(Function_File.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void exit()
    {
        if (gui.textarea != null) {
            if (isTextModified()) {
                Object[] options = {"Save", "Don't Save", "Cancel"};
                int result = JOptionPane.showOptionDialog(gui.window,
                        "Do you want to save changes to " + fileName + " ?",
                        "NoteCraft",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);

                if (result == JOptionPane.YES_OPTION) {
                    save_file();
                    System.exit(0);
                } else if (result == JOptionPane.NO_OPTION) {
                    System.exit(0);
                } else if (result == JOptionPane.CANCEL_OPTION || result == JOptionPane.CLOSED_OPTION) {
                    // Do nothing, user wants to cancel
                }
            } else {
                System.exit(0);
            }
        } else {
            System.exit(0);
        }
    }
    }

