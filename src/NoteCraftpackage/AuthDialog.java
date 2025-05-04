package NoteCraftpackage;

import javax.swing.*;

public class AuthDialog {

    public static void showLoginDialog() {
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        Object[] message = {
                "Username:", usernameField,
                "Password:", passwordField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Login", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            if (UserAuth.login(usernameField.getText(), new String(passwordField.getPassword()))) {
                JOptionPane.showMessageDialog(null, "Login successful! " + UserAuth.loggedInUser, "Information", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid username or password.","Error",JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void showSignupDialog() {
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        Object[] message = {
                "Username:", usernameField,
                "Password:", passwordField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Sign Up", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            if (UserAuth.signup(usernameField.getText(), new String(passwordField.getPassword()))) {
                JOptionPane.showMessageDialog(null, "Account created successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Error creating account.");
            }
        }
    }
}

