import NoteCraftpackage.*;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatAtomOneLightIJTheme;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;

public class Main
{
    public static void main(String[] args)  throws Exception
    {
        // Set FlatLaf Look and Feel
        try {
            UIManager.setLookAndFeel(new FlatAtomOneLightIJTheme()); // Try FlatDarkLaf for dark mode
            // Apply global font for the UI
            UIManager.put("defaultFont", new FontUIResource(new Font("Segoe UI", Font.PLAIN, 14)));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        new GUI();
    }
}