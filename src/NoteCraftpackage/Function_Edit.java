package NoteCraftpackage;

import javax.swing.*;
import javax.swing.undo.UndoManager;

public class Function_Edit {

    GUI gui;

    //constructor
    public Function_Edit(GUI gui)
    {
        this.gui = gui;
    }

    public void redo()
    {
        if (gui.U.canRedo()) {
            gui.U.redo();
        } else {
            JOptionPane.showMessageDialog(gui.window,"No Redo Found","Error",JOptionPane.ERROR_MESSAGE);
        }

    }

    public void undo()
    {
        if (gui.U.canUndo()) {
            gui.U.undo();
        } else {
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
}
