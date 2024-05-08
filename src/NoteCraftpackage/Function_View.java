package NoteCraftpackage;
import javax.swing.*;
import java.awt.* ;

public class Function_View
{

    GUI gui;

    public Function_View(GUI gui)
    {
        this.gui = gui;
    }

    public void zoomIN()
    {
        Font currentFont = gui.textarea.getFont();
        float newSize = currentFont.getSize() * 1.1f; // Increase font size by 10%
        Font newFont = currentFont.deriveFont(newSize);
        gui.textarea.setFont(newFont);
    }

    public void zoomOut()
    {
        Font currentFont = gui.textarea.getFont();
        float newSize = currentFont.getSize() * 0.9f; // decrease font size by 10%
        Font newFont = currentFont.deriveFont(newSize);
        gui.textarea.setFont(newFont);
    }


}
