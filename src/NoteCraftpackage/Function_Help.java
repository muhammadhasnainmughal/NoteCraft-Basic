package NoteCraftpackage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class Function_Help extends JFrame
{

    GUI gui;

    public Function_Help()
    {
        setTitle("About Notepad");
        setSize(400, 320);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        panel.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("About NoteCraft");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setBounds(100, 10, 200, 30);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titleLabel);

        JLabel versionLabel = new JLabel("Version 101.0.1.1");
        versionLabel.setBounds(100, 50, 200, 20);
        versionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(versionLabel);

        JLabel authorLabel = new JLabel("© 2024 NoteCraft. All rights reserved.");
        authorLabel.setBounds(40, 70, 320, 20);
        authorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(authorLabel);

        JLabel descriptionLabel = new JLabel("<html>It is developed in Java programming Language.<br>Developed & Created by Muhammad Hasnain & Hassan Raza</html>");
        descriptionLabel.setBounds(40, 100, 320, 60);
        descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(descriptionLabel);

        ImageIcon icon = createImageIcon("Icon.png", "Notepad Icon");
        if (icon != null) {
            JLabel iconLabel = new JLabel(icon);
            iconLabel.setBounds(20, 20, 80, 80);
            panel.add(iconLabel);
        }

        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                dispose();
            }
        });
        okButton.setBounds(150, 200, 100, 30);
        panel.add(okButton);

        add(panel);
        setVisible(true);
    }

    protected ImageIcon createImageIcon(String path, String description)
    {
        URL imgURL = getClass().getResource(path);
        if (imgURL != null)
        {
            return new ImageIcon(imgURL, description);
        }
        else
        {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(Function_Help::new);
    }

}
