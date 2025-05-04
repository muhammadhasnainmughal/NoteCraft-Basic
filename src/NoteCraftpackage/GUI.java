package NoteCraftpackage;


import javax.swing.*;
import javax.swing.event.*;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.icons.*;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatAtomOneLightIJTheme;

import javax.swing.plaf.FontUIResource;

public class GUI implements ActionListener {

    public static String selectedWord = null;

    JFrame window;
    JLabel usernameLabel;
    JTabbedPane tabbedPane;
    JTextArea statusLabel;
    JMenuBar menuBar;
    JTextArea currentTextArea;
    JMenu menuFile, menuEdit, menuFormat, menuView, menuHelp,menuAccount, savecloud;
    JMenuItem newFile, open, save, saveAs, print, exit;
    JMenuItem undo, redo, cut, copy, paste, find, replace, goTo, selectAll, dateAndTime;
    JMenuItem fontStyle, fontSize, fontColor, padColor;
    JMenu themes;
    JMenuItem Dark, dracula, wwhite, Red, Blue, Green, hacker;
    JCheckBoxMenuItem wordWrap;
    JMenuItem showStatusBar, zoomIn, zoomOut;
    JMenuItem aboutNotepad;
    JMenuItem login,signup,signout;
    JMenuItem savetocloud,viewnotes;

    UndoManager U = new UndoManager();
    Function_File file = new Function_File(this);
    Function_Format format = new Function_Format(this);
    Function_Edit edit = new Function_Edit(this);
    Function_View view = new Function_View(this);


    int prevLineCount = 1;
    int prevColumnCount = 1;

    public static void main(String[] args) {
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

    public GUI() {

// Rounded corners for various components
        UIManager.put("Button.arc", 20); // Buttons
        UIManager.put("TextField.arc", 15); // Text fields
        UIManager.put("PasswordField.arc", 15); // Password fields
        UIManager.put("Spinner.arc", 10); // Spinners
        UIManager.put("ProgressBar.arc", 10); // Progress bars
        UIManager.put("TabbedPane.tabInsets", new Insets(10, 20, 10, 20)); // Tab padding
        UIManager.put("TextArea.arc", 10); // Rounded corners for text area
        UIManager.put("TextComponent.arc", 10); // Text components

        // Color palette for modern feel
        UIManager.put("Component.focusColor", new Color(100, 149, 237)); // Focus color
        UIManager.put("Component.focusWidth", 2); // Focus border width
        UIManager.put("Button.background", new Color(240, 240, 255)); // Button background
        UIManager.put("Button.hoverBackground", new Color(220, 230, 255)); // Button hover
        UIManager.put("Button.focusedBackground", new Color(190, 210, 255)); // Button focused
        UIManager.put("Button.foreground", Color.BLACK); // Button text
        UIManager.put("Button.disabledText", new Color(150, 150, 150)); // Disabled button text
        UIManager.put("Panel.background", new Color(245, 245, 250)); // Panel background
        UIManager.put("TabbedPane.underlineColor", new Color(100, 149, 237)); // Underline for active tab
        UIManager.put("TabbedPane.contentAreaColor", new Color(245, 245, 245)); // Tab pane background
        UIManager.put("TabbedPane.tabAreaBackground", new Color(240, 240, 240)); // Tab area background
        UIManager.put("TabbedPane.hoverColor", new Color(190, 210, 240)); // Hover color for tabs
        UIManager.put("ScrollBar.thumb", new Color(180, 180, 180)); // Scrollbar thumb color
        UIManager.put("ScrollBar.hoverThumb", new Color(160, 160, 160)); // Scrollbar hover
        UIManager.put("ScrollBar.thumbArc", 10); // Rounded scrollbar thumb
        UIManager.put("ScrollBar.thumbInsets", new Insets(2, 2, 2, 2)); // Spacing inside scrollbar thumb
        UIManager.put("ScrollBar.track", new Color(230, 230, 230)); // Track color

        // Shadowing and borders
        UIManager.put("Button.shadowWidth", 3); // Button shadow width
        UIManager.put("PopupMenu.border", BorderFactory.createLineBorder(new Color(200, 200, 200))); // Popup menu border
        UIManager.put("Menu.shadowColor", new Color(200, 200, 200, 100)); // Menu shadow
        UIManager.put("TextField.borderWidth", 2); // TextField border width
        UIManager.put("Separator.foreground", new Color(200, 200, 200)); // Divider lines
        UIManager.put("Separator.background", new Color(245, 245, 245)); // Divider background

        // Text highlights and selection
        UIManager.put("TextField.selectionBackground", new Color(180, 220, 250)); // Text selection background
        UIManager.put("TextField.selectionForeground", Color.BLACK); // Text selection foreground
        UIManager.put("TextArea.background", new Color(245, 245, 250)); // TextArea background
        UIManager.put("TextArea.selectionBackground", new Color(180, 220, 250)); // TextArea selection
        UIManager.put("TextPane.background", new Color(250, 250, 255)); // TextPane background

        // Tooltips and dialogs
        UIManager.put("ToolTip.background", new Color(250, 250, 200)); // Tooltip background
        UIManager.put("ToolTip.border", BorderFactory.createLineBorder(new Color(200, 200, 150))); // Tooltip border
        UIManager.put("OptionPane.messageFont", new Font("SansSerif", Font.PLAIN, 14)); // Dialog font
        UIManager.put("OptionPane.background", new Color(240, 240, 255)); // Dialog background
        UIManager.put("OptionPane.messageForeground", Color.BLACK); // Dialog text color

        // Fonts for consistency
        Font defaultFont = new Font("Segoe UI", Font.PLAIN, 14);
        UIManager.put("defaultFont", defaultFont); // Apply font across all components
        UIManager.put("Menu.font", defaultFont); // Menu font
        UIManager.put("Button.font", defaultFont); // Button font
        UIManager.put("TabbedPane.font", new Font("Segoe UI Semibold", Font.PLAIN, 13)); // Tabs font
        UIManager.put("Label.font", defaultFont); // Labels
        UIManager.put("CheckBox.font", defaultFont); // Checkboxes

        // Menu hover and selection
        UIManager.put("Menu.hoverBackground", new Color(230, 240, 255)); // Menu hover
        UIManager.put("Menu.selectionBackground", new Color(100, 149, 237)); // Selected menu background
        UIManager.put("Menu.selectionForeground", Color.BLACK); // Selected menu text
        UIManager.put("MenuItem.selectionBackground", new Color(220, 220, 250)); // Selected menu item background
        UIManager.put("MenuItem.selectionForeground", Color.BLACK); // Selected menu item text
        UIManager.put("MenuItem.selectionType", "underline"); // Menu highlight style

        // ProgressBar styling
        UIManager.put("ProgressBar.foreground", new Color(100, 180, 255)); // Progress bar foreground
        UIManager.put("ProgressBar.background", new Color(220, 230, 240)); // Progress bar background
        UIManager.put("ProgressBar.selectionForeground", Color.BLACK); // Progress text foreground
        UIManager.put("ProgressBar.selectionBackground", Color.WHITE); // Progress text background




        createWindow();
        createTabbedPane();
        createStatusLabel();
        createMenuBar();
        createMenuItem();
        addNewTab("New Tab");
        window.setVisible(true);
        currentTextArea.requestFocusInWindow();
        createContextMenu();

    }

    public void createWindow() {

        window = new JFrame("NoteCraft");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(800, 600); // Increased size for better layout
        window.setLocationRelativeTo(null); // Center the window
        window.setLayout(new BorderLayout());

        FlatAtomOneLightIJTheme.updateUI();
    }

    public void createContextMenu()
    {
        JPopupMenu contextMenu = new JPopupMenu();

        // "Cut" menu item
        JMenuItem cutItem = new JMenuItem("Cut");
        cutItem.addActionListener(e -> edit.cut());
        contextMenu.add(cutItem);

        // "Copy" menu item
        JMenuItem copyItem = new JMenuItem("Copy");
        copyItem.addActionListener(e -> edit.copy());
        contextMenu.add(copyItem);

        // "Paste" menu item
        JMenuItem pasteItem = new JMenuItem("Paste");
        pasteItem.addActionListener(e -> edit.paste());
        contextMenu.add(pasteItem);

        // "Select All" menu item
        JMenuItem selectAllItem = new JMenuItem("Select All");
        selectAllItem.addActionListener(e -> edit.selectAll());
        contextMenu.add(selectAllItem);

        // "Define" menu item
        JMenuItem defineItem = new JMenuItem("Define");
        defineItem.addActionListener(e -> {
            if (selectedWord != null && !selectedWord.isEmpty()) {
                String result = Dictionary.findWordInfo(selectedWord);
                JOptionPane.showMessageDialog(null, result);
            } else {
                JOptionPane.showMessageDialog(null, "No word selected to define.");
            }
        });

        contextMenu.add(defineItem);

        // Add MouseListener to the textarea
        currentTextArea.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseReleased(MouseEvent e)
            {
                if (e.isPopupTrigger() && currentTextArea.getSelectedText() != null)
                {
                    selectedWord = currentTextArea.getSelectedText(); // Store the selected word
                    contextMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
    }


    public void createTabbedPane() {
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        window.add(tabbedPane, BorderLayout.CENTER);

        // Update currentTextArea when the selected tab changes
        tabbedPane.addChangeListener(e -> {
            JScrollPane currentScrollPane = (JScrollPane) tabbedPane.getSelectedComponent();
            if (currentScrollPane != null) {
                currentTextArea = (JTextArea) currentScrollPane.getViewport().getView();
            } else {
                currentTextArea = null; // No active text area
            }
        });

        // Add the "New Tab" button in the tab header
        JPanel newTabPanel = new JPanel(new BorderLayout());
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        //New Tab Button
        JButton newTabButton = new JButton("New Tab", new FlatFileChooserNewFolderIcon());
        newTabButton.addActionListener(e -> addNewTab("New Tab"));
        leftPanel.add(newTabButton);

        // Additional buttons
        JButton button1 = new JButton("Summarize");
        button1.addActionListener(e -> summerrizeButton()); // Add action listener
        leftPanel.add(button1); // Add Button 1 to the left panel

        JButton button2 = new JButton("Ask NoteCraft");
        button2.addActionListener(e -> chatbotButton()); // Add action listener
        leftPanel.add(button2); // Add Button 2 to the left panel

        JButton button3 = new JButton("PDF to Text");
        button3.addActionListener(e -> pdfconvertButton()); // Add action listener
        leftPanel.add(button3); // Add Button 3 to the left panel


        // Create the username label
        usernameLabel = new JLabel();
        usernameLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        usernameLabel.setForeground(Color.DARK_GRAY);
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        rightPanel.add(usernameLabel);

        // Add both panels (left and right) to the header
        newTabPanel.add(leftPanel, BorderLayout.WEST);
        newTabPanel.add(rightPanel, BorderLayout.EAST);

        // Add the header panel to the NORTH of the window
        window.add(newTabPanel, BorderLayout.NORTH);

    }

    public void updateUsername(String Username) {
        usernameLabel.setText(Username); // Update the label text
        usernameLabel.revalidate(); // Revalidate the label to update the layout
        usernameLabel.repaint(); // Repaint the label to reflect the new text
    }

    public void addNewTab(String title) {
        // Create a new JTextArea for the new tab
        JTextArea newTextArea = new JTextArea();
        newTextArea.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        newTextArea.setLineWrap(true);
        newTextArea.setWrapStyleWord(true);
        newTextArea.getDocument().addUndoableEditListener(e -> U.addEdit(e.getEdit()));
        newTextArea.addCaretListener(e -> updateStatus(newTextArea));

        // Create a JScrollPane for the JTextArea
        JScrollPane scrollPane = new JScrollPane(newTextArea);

        // Add a tab close button
        JPanel tabHeaderPanel = new JPanel(new BorderLayout());
        JLabel tabTitle = new JLabel(title);
        JButton closeButton = new JButton(new FlatWindowCloseIcon());
        closeButton.setToolTipText("Close Tab");
        closeButton.setPreferredSize(new Dimension(16, 16));
        closeButton.addActionListener(e -> tabbedPane.remove(tabbedPane.indexOfComponent(scrollPane)));

        tabHeaderPanel.add(tabTitle, BorderLayout.WEST);
        tabHeaderPanel.add(closeButton, BorderLayout.EAST);

        // Add the tab to the tabbedPane
        tabbedPane.addTab(title, scrollPane);
        tabbedPane.setTabComponentAt(tabbedPane.indexOfComponent(scrollPane), tabHeaderPanel);

        // Ensure the new tab is selected
        tabbedPane.setSelectedComponent(scrollPane);
        currentTextArea = newTextArea;

        currentTextArea.requestFocusInWindow();
    }

    public void summerrizeButton()
    {
        Summary summary = new Summary(this);
        summary.summary();

    }

    public void chatbotButton()
    {
        chatbot chatbot = new chatbot();
        chatbot.openChatbotWindow();
    }

    public void pdfconvertButton()
    {
        String extractedText = pdfConvert.convert();
        if(extractedText != null)
        {
            addNewTab("PDF Content", extractedText);
        }
        
    }



    public void addNewTab(String noteTitle, String noteContent) {
        // Create a new JTextArea for the new tab
        JTextArea newTextArea = new JTextArea(noteContent);
        newTextArea.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        newTextArea.setLineWrap(true);
        newTextArea.setWrapStyleWord(true);
        newTextArea.getDocument().addUndoableEditListener(e -> U.addEdit(e.getEdit()));
        newTextArea.addCaretListener(e -> updateStatus(newTextArea));

        // Create a JScrollPane for the JTextArea
        JScrollPane scrollPane = new JScrollPane(newTextArea);

        // Add a tab close button
        JPanel tabHeaderPanel = new JPanel(new BorderLayout());
        JLabel tabTitle = new JLabel(noteTitle);
        JButton closeButton = new JButton(new FlatWindowCloseIcon());
        closeButton.setToolTipText("Close Tab");
        closeButton.setPreferredSize(new Dimension(16, 16));
        closeButton.addActionListener(e -> tabbedPane.remove(tabbedPane.indexOfComponent(scrollPane)));

        tabHeaderPanel.add(tabTitle, BorderLayout.WEST);
        tabHeaderPanel.add(closeButton, BorderLayout.EAST);

        // Add the tab to the tabbedPane
        tabbedPane.addTab(noteTitle, scrollPane);
        tabbedPane.setTabComponentAt(tabbedPane.indexOfComponent(scrollPane), tabHeaderPanel);

        // Ensure the new tab is selected
        tabbedPane.setSelectedComponent(scrollPane);
        currentTextArea = newTextArea;
    }

    public void closeTab(JScrollPane scrollPane) {
        int index = tabbedPane.indexOfComponent(scrollPane);
        if (index != -1) {
            tabbedPane.remove(index);
        }
    }


    public void createStatusLabel() {
        statusLabel = new JTextArea("Ln 1, Col 1");
        statusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        statusLabel.setBorder(BorderFactory.createEtchedBorder());
        statusLabel.setEditable(false); // Prevent editing the status bar
        statusLabel.setBackground(UIManager.getColor("Panel.background")); // Match with theme
        window.add(statusLabel, BorderLayout.SOUTH);
    }


    private void updateStatus(JTextArea textArea) {
        int caretPosition = textArea.getCaretPosition();
        int lineNumber = 1;
        int columnNumber = 1;

        try {
            lineNumber = textArea.getLineOfOffset(caretPosition) + 1;
            columnNumber = caretPosition - textArea.getLineStartOffset(lineNumber - 1) + 1;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if (lineNumber != prevLineCount || columnNumber != prevColumnCount) {
            statusLabel.setText("Ln " + lineNumber + ", Col " + columnNumber);
            prevLineCount = lineNumber;
            prevColumnCount = columnNumber;
        }
    }

    public void createMenuBar() {
        menuBar = new JMenuBar();
        menuFile = new JMenu("File");
        menuEdit = new JMenu("Edit");
        menuFormat = new JMenu("Format");
        menuView = new JMenu("View");
        menuHelp = new JMenu("Help");
        menuAccount = new JMenu("Account");
        savecloud = new JMenu("Cloud");

        menuBar.add(menuFile);
        menuBar.add(menuEdit);
        menuBar.add(menuFormat);
        menuBar.add(menuView);
        menuBar.add(menuHelp);
        menuBar.add(menuAccount);
        menuBar.add(savecloud);
        window.setJMenuBar(menuBar);
    }

    public void createMenuItem() {
        //****FILE MENU ALL OPTIONS

        newFile = new JMenuItem("New");
        newFile.addActionListener(this);
        newFile.setActionCommand("New");
        newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
        menuFile.add(newFile);

        open = new JMenuItem("Open...");
        open.addActionListener(this);
        open.setActionCommand("Open");
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
        menuFile.add(open);

        save = new JMenuItem("Save");
        save.addActionListener(this);
        save.setActionCommand("Save");
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        menuFile.add(save);

        saveAs = new JMenuItem("Save As...");
        saveAs.addActionListener(this);
        saveAs.setActionCommand("SaveAs");
        saveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK + KeyEvent.SHIFT_DOWN_MASK));
        menuFile.add(saveAs);

        print = new JMenuItem("Print...");
        print.addActionListener(this);
        print.setActionCommand("Print");
        print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK));
        menuFile.add(print);

        exit = new JMenuItem("Exit");
        exit.addActionListener(this);
        exit.setActionCommand("Exit");
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, KeyEvent.CTRL_DOWN_MASK));
        menuFile.add(exit);

        //****EDIT MENU ALL OPTIONS

        undo = new JMenuItem("Undo");
        undo.addActionListener(this);
        undo.setActionCommand("Undo");
        undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK));
        menuEdit.add(undo);

        redo = new JMenuItem("Redo");
        redo.addActionListener(this);
        redo.setActionCommand("Redo");
        redo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, KeyEvent.CTRL_DOWN_MASK));
        menuEdit.add(redo);

        cut = new JMenuItem("Cut");
        cut.addActionListener(this);
        cut.setActionCommand("Cut");
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK));
        menuEdit.add(cut);

        copy = new JMenuItem("Copy");
        copy.addActionListener(this);
        copy.setActionCommand("Copy");
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK));
        menuEdit.add(copy);

        paste = new JMenuItem("Paste");
        paste.addActionListener(this);
        paste.setActionCommand("Paste");
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK));
        menuEdit.add(paste);

        find = new JMenuItem("Find...");
        find.addActionListener(this);
        find.setActionCommand("Find");
        find.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, KeyEvent.CTRL_DOWN_MASK));
        menuEdit.add(find);

        replace = new JMenuItem("Replace...");
        replace.addActionListener(this);
        replace.setActionCommand("Replace");
        replace.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, KeyEvent.CTRL_DOWN_MASK));
        menuEdit.add(replace);

        goTo = new JMenuItem("Go To...");
        goTo.addActionListener(this);
        goTo.setActionCommand("GoTo");
        goTo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, KeyEvent.CTRL_DOWN_MASK));
        menuEdit.add(goTo);

        selectAll = new JMenuItem("Select All");
        selectAll.addActionListener(this);
        selectAll.setActionCommand("SelectAll");
        selectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK));
        menuEdit.add(selectAll);

        dateAndTime = new JMenuItem("Date/Time");
        dateAndTime.addActionListener(this);
        dateAndTime.setActionCommand("DateAndTime");
        dateAndTime.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, KeyEvent.KEY_LOCATION_UNKNOWN));
        menuEdit.add(dateAndTime);

        //****FORMAT MENU ALL OPTIONS

        wordWrap = new JCheckBoxMenuItem("Word Wrap");
        wordWrap.addActionListener(this);
        wordWrap.setActionCommand("WordWrap");
        menuFormat.add(wordWrap);

        fontStyle = new JMenuItem("Change Font Style");
        fontStyle.addActionListener(this);
        fontStyle.setActionCommand("FontStyle");
        menuFormat.add(fontStyle);

        fontSize = new JMenuItem("Change Font Size");
        fontSize.addActionListener(this);
        fontSize.setActionCommand("FontSize");
        menuFormat.add(fontSize);

        fontColor = new JMenuItem("Change Font Color");
        fontColor.addActionListener(this);
        fontColor.setActionCommand("setFontColor");
        menuFormat.add(fontColor);

        padColor = new JMenuItem("Change Background Color");
        padColor.addActionListener(this);
        padColor.setActionCommand("setBackColor");
        menuFormat.add(padColor);

        themes = new JMenu("Themes");
        menuFormat.add(themes);

        //****THEME SUB MENU

        Dark = new JMenuItem("Dark++");
        Dark.addActionListener(this);
        Dark.setActionCommand("Dark++");
        themes.add(Dark);

        dracula = new JMenuItem("Dracula");
        dracula.addActionListener(this);
        dracula.setActionCommand("Dracula");
        themes.add(dracula);

        wwhite = new JMenuItem("White");
        wwhite.addActionListener(this);
        wwhite.setActionCommand("White");
        themes.add(wwhite);

        Red = new JMenuItem("Red");
        Red.addActionListener(this);
        Red.setActionCommand("Red");
        themes.add(Red);

        Blue = new JMenuItem("Blue");
        Blue.addActionListener(this);
        Blue.setActionCommand("Blue");
        themes.add(Blue);

        Green = new JMenuItem("Green");
        Green.addActionListener(this);
        Green.setActionCommand("Green");
        themes.add(Green);

        hacker = new JMenuItem("Hacker");
        hacker.addActionListener(this);
        hacker.setActionCommand("Hacker");
        themes.add(hacker);

        //****VIEW MENU ALL OPTIONS

        zoomIn = new JMenuItem("Zoom in");
        zoomIn.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS, KeyEvent.CTRL_DOWN_MASK));
        zoomIn.addActionListener(this);
        zoomIn.setActionCommand("ZoomIn");
        menuView.add(zoomIn);

        zoomOut = new JMenuItem("Zoom out");
        zoomOut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, KeyEvent.CTRL_DOWN_MASK));
        zoomOut.addActionListener(this);
        zoomOut.setActionCommand("ZoomOut");
        menuView.add(zoomOut);

        showStatusBar = new JMenuItem("Status Bar");
        showStatusBar.addActionListener(this);
        showStatusBar.setActionCommand("ShowStatusBar");
        menuView.add(showStatusBar);

        //****HELP MENU ALL OPTIONS

        aboutNotepad = new JMenuItem("About NoteCraft", new FlatHelpButtonIcon());
        aboutNotepad.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, KeyEvent.KEY_LOCATION_UNKNOWN));
        aboutNotepad.addActionListener(this);
        aboutNotepad.setActionCommand("Help");
        menuHelp.add(aboutNotepad);

        //******ACCOUNT MENU ALL OPTIONS

        login = new JMenuItem("Login");
        login.addActionListener(this);
        login.setActionCommand("login");
        menuAccount.add(login);

        signup = new JMenuItem("Sign Up");
        signup.addActionListener(this);
        signup.setActionCommand("signup");
        menuAccount.add(signup);

        signout = new JMenuItem("Sign Out");
        signout.addActionListener(this);
        signout.setActionCommand("signout");
        menuAccount.add(signout);

        savetocloud = new JMenuItem("Save to Cloud");
        savetocloud.addActionListener(this);
        savetocloud.setActionCommand("SaveCloud");
        savecloud.add(savetocloud);

        viewnotes = new JMenuItem("View Cloud Saved");
        viewnotes.addActionListener(this);
        viewnotes.setActionCommand("viewnotes");
        savecloud.add(viewnotes);

    }

    public void word_wrap(boolean wrap) {
        // Get the current JTextArea from the selected tab
        JScrollPane currentScrollPane = (JScrollPane) tabbedPane.getSelectedComponent();
        JTextArea currentTextArea = (currentScrollPane != null)
                ? (JTextArea) currentScrollPane.getViewport().getView()
                : null;

        if (currentTextArea != null) {
            currentTextArea.setLineWrap(wrap);
            currentTextArea.setWrapStyleWord(wrap);
        } else {
            JOptionPane.showMessageDialog(window, "No active text area to apply word wrap.");
        }
    }

    private JTextArea getCurrentTextArea() {
        // Fetch the current active text area from the selected tab
        JScrollPane currentScrollPane = (JScrollPane) tabbedPane.getSelectedComponent();
        if (currentScrollPane != null) {
            return (JTextArea) currentScrollPane.getViewport().getView();
        }
        return null; // Return null if no active text area is found
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        // Ensure currentTextArea is updated for the selected tab
        JTextArea currentTextArea = getCurrentTextArea(); // Fetch the active JTextArea

        // If no active JTextArea (no tab is open), show a message
        if (currentTextArea == null) {
            JOptionPane.showMessageDialog(window, "No active text area to apply the operation.");
            return; // Exit the method if no active tab is found
        }

        // Now you can safely perform actions on currentTextArea
        switch (command) {
            // File Menu Commands
            case "New":
                file.new_File();
                break;

            case "Open":
                file.open_File();
                break;

            case "Save":
                file.save_file();
                break;

            case "SaveAs":
                file.saveAs_file();
                break;

            case "Print":
                file.print();
                break;

            case "Exit":
                file.exit();
                break;

            // Edit Menu Commands
            case "Redo":
                edit.redo();
                break;

            case "Undo":
                edit.undo();
                break;

            case "Copy":
                currentTextArea.copy();  // Perform the copy operation on the active tab
                break;

            case "Cut":
                currentTextArea.cut();  // Perform the cut operation on the active tab
                break;

            case "Paste":
                currentTextArea.paste();  // Perform the paste operation on the active tab
                break;

            case "Find":
                edit.find();
                break;

            case "Replace":
                edit.replace();
                break;

            case "GoTo":
                edit.Goto();
                break;

            case "SelectAll":
                currentTextArea.selectAll();  // Select all text in the active tab
                break;

            case "DateAndTime":
                edit.Date();
                break;

            // Format Menu Commands
            case "WordWrap":
                word_wrap(wordWrap.isSelected());
                break;

            case "FontStyle":
                format.setFontStyle();
                break;

            case "FontSize":
                format.setFontSize();
                break;

            case "setBackColor":
                format.setBackColor();
                break;

            case "setFontColor":
                format.setFontColor();
                break;

            // Theme Commands
            case "Dark++":
                currentTextArea.setBackground(Color.BLACK);
                currentTextArea.setForeground(Color.WHITE);
                currentTextArea.setCaretColor(Color.WHITE);
                break;

            case "White":
                currentTextArea.setBackground(Color.WHITE);
                currentTextArea.setForeground(Color.BLACK);
                currentTextArea.setCaretColor(Color.BLACK);
                break;

            case "Dracula":
                currentTextArea.setBackground(Color.GRAY);
                currentTextArea.setForeground(Color.BLACK);
                currentTextArea.setCaretColor(Color.WHITE);
                break;

            case "Red":
                currentTextArea.setBackground(Color.RED);
                currentTextArea.setForeground(Color.YELLOW);
                currentTextArea.setCaretColor(Color.WHITE);
                break;

            case "Blue":
                currentTextArea.setBackground(Color.BLUE);
                currentTextArea.setForeground(Color.WHITE);
                currentTextArea.setCaretColor(Color.WHITE);
                break;

            case "Green":
                currentTextArea.setBackground(Color.GREEN);
                currentTextArea.setForeground(Color.RED);
                currentTextArea.setCaretColor(Color.WHITE);
                break;

            case "Hacker":
                currentTextArea.setBackground(Color.BLACK);
                currentTextArea.setForeground(Color.GREEN);
                currentTextArea.setCaretColor(Color.WHITE);
                break;

            // View Menu Commands
            case "ZoomIn":
                view.zoomIN();
                break;

            case "ZoomOut":
                view.zoomOut();
                break;

            // Help Menu Commands
            case "Help":
                new Function_Help();
                break;

            // ACCOUNT MENU COMMANDS
            case "login":
                int userID = UserAuth.loggedInUserId;
                if(userID != -1)
                {
                    JOptionPane.showMessageDialog(null, "User is already logged in.","Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                AuthDialog.showLoginDialog();
                String username = UserAuth.loggedInUser;
                updateUsername(username);
                break;

            case "signup":
                int user = UserAuth.loggedInUserId;
                if(user != -1)
                {
                    JOptionPane.showMessageDialog(null, "User is already logged in. Please Signout first!","Warning",JOptionPane.WARNING_MESSAGE);
                    return;
                }
                AuthDialog.showSignupDialog();
                break;

            case "signout":

                UserAuth.logout();
                JOptionPane.showMessageDialog(null, "Logged out successfully!","Information",JOptionPane.INFORMATION_MESSAGE);
                break;

            case "SaveCloud":
                if (!UserAuth.isLoggedIn()) {
                    JOptionPane.showMessageDialog(null, "You must log in first!","Error",JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Get the current file name (check if the user is editing an existing note)
                String currentFileName = ViewNotes.title.trim(); // For example, use a text field or some method to get the file name.

                // Check if the user already provided the file name or if they are editing an existing file
                if (!currentFileName.isEmpty()) {
                    // Check if the file exists
                    if (CloudSave.doesFileExist(currentFileName)) {
                        // File exists, ask the user if they want to update
                        int response = JOptionPane.showConfirmDialog(
                                null,
                                "File already exists. Do you want to update it?",
                                "Update File?",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE
                        );

                        // If the user selects "Yes", update the file
                        if (response == JOptionPane.YES_OPTION) {
                            String currentContent = currentTextArea.getText(); // The content of the current text area
                            CloudSave.saveToCloud(currentFileName, currentContent, true); // Save as update
                        }
                        // If the user selects "No", save as a new file
                        else if (response == JOptionPane.NO_OPTION) {
                            String currentContent = currentTextArea.getText();
                            String newFileName = JOptionPane.showInputDialog(null, "Enter File Name: ", "Save File", JOptionPane.PLAIN_MESSAGE);
                            CloudSave.saveToCloud(newFileName, currentContent, false); // Save as a new file
                        }

                    } else {
                        // File does not exist, save as new
                        String currentContent = currentTextArea.getText(); // The content of the current text area
                        CloudSave.saveToCloud(currentFileName, currentContent, false); // Save as new file
                    }
                } else {
                    // Handle the case where no file name was provided
                    // Ask the user to provide a file name
                    String newFileName = JOptionPane.showInputDialog(null, "Enter File Name: ", "Save File", JOptionPane.PLAIN_MESSAGE);

                    // Check if the user provided a valid file name
                    if (newFileName != null && !newFileName.trim().isEmpty()) {
                        // Proceed with saving the new file
                        String currentContent = currentTextArea.getText(); // The content of the current text area
                        CloudSave.saveToCloud(newFileName.trim(), currentContent, false); // Save as new file
                    } else {
                        // Handle the case where no file name was provided or the user canceled the dialog
                        JOptionPane.showMessageDialog(null, "You must provide a file name to save the file.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;



            case "viewnotes":

                ViewNotes.view();
                String title = ViewNotes.title;
                String content = ViewNotes.content;

                if(title != null && !title.trim().isEmpty() || content != null && !content.trim().isEmpty()) {

                    addNewTab(title, content);
                    return;
                }
                break;


            default:
                JOptionPane.showMessageDialog(window, "Unknown command: " + command,"Error", JOptionPane.ERROR_MESSAGE);
                break;
        }
    }


}