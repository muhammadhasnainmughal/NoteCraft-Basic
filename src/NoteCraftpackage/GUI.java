package NoteCraftpackage;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.*;

public class GUI implements ActionListener
{

    JFrame window;
    JTextArea textarea;

    JTextArea statusLabel;

    JScrollPane scrollPane;
    JMenuBar menuBar;
    JMenu menuFile,menuEdit,menuFormat,menuView,menuHelp;
    JMenuItem newFile,open,save,saveAs,print,exit;
    JMenuItem undo,redo,cut,copy,paste,find,replace,goTo,selectAll,dateAndTime;
    JMenuItem fontStyle,fontSize,fontColor,padColor;
    JMenu themes;
    JMenuItem Dark, dracula, wwhite, Red, Blue, Green, hacker;

    JCheckBoxMenuItem wordWrap;
    JMenuItem showStatusBar,zoomIn,zoomOut;
    JMenuItem aboutNotepad;

    UndoManager U = new UndoManager();
    Function_File file = new Function_File(this);
    Function_Format format = new Function_Format(this);
    Function_Edit edit = new Function_Edit(this);
    Function_View view = new Function_View(this);
    //Function_Help help = new Function_Help();


    int prevLineCount = 1;
    int prevColumnCount = 1;

    public static void main(String[] args) throws Exception
    {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        new GUI();
    }

    //contractor
    public GUI()
    {
        createWindow();
        createTextArea();
        createStatusLabel();
        createMenuBar();
        createMenuItem();

        window.setVisible(true);
    }



    public void createWindow()
    {
        window = new JFrame("NoteCraft");
        window.setSize(900,500);
        window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        window.setLocationRelativeTo(null);

        window.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e) {
                file.exit();
            }
        });
    }

    public void createTextArea() {
        textarea = new JTextArea();
        textarea.setFont((new Font(Font.SANS_SERIF, Font.PLAIN, 20)));
        scrollPane = new JScrollPane(textarea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        window.add(scrollPane);


        textarea.getDocument().addUndoableEditListener(new UndoableEditListener() {
            public void undoableEditHappened(UndoableEditEvent e) {
                U.addEdit(e.getEdit());
            }
        });

        textarea.addCaretListener(new CaretListener() {
            public void caretUpdate(CaretEvent e) {
                updateStatus();
            }
        });

    }

    public void createStatusLabel()
    {
        statusLabel = new JTextArea("Ln 1, Col 1");
        statusLabel.setFont((new Font(Font.SANS_SERIF, Font.PLAIN, 12)));
        statusLabel.setBorder(BorderFactory.createEtchedBorder());
        window.add(statusLabel, BorderLayout.SOUTH);
    }

    // Method to update line and column numbers
    private void updateStatus()
    {
        int caretPosition = textarea.getCaretPosition();
        int lineNumber = 1;
        int columnNumber = 1;

        try
        {
            lineNumber = textarea.getLineOfOffset(caretPosition) + 1;
            columnNumber = caretPosition - textarea.getLineStartOffset(lineNumber - 1) + 1;
        }
        catch (Exception ex)
        {
//            ex.printStackTrace();
        }

        // Update status label only if the line or column number has changed
        if (lineNumber != prevLineCount || columnNumber != prevColumnCount) {
            statusLabel.setText("Ln " + lineNumber + ", Col " + columnNumber);
            prevLineCount = lineNumber;
            prevColumnCount = columnNumber;
        }
    }



    public void createMenuBar()
    {
        menuBar = new JMenuBar();
        window.setJMenuBar(menuBar);

        //file menu
        menuFile = new JMenu("File");
        menuBar.add(menuFile);

        //edit menu
        menuEdit = new JMenu("Edit");
        menuBar.add(menuEdit);

        //format menu
        menuFormat = new JMenu("Format");
        menuBar.add(menuFormat);

        //view menu
        menuView = new JMenu("View");
        menuBar.add(menuView);

        //help menu
        menuHelp = new JMenu("Help");
        menuBar.add(menuHelp);
    }

    public void createMenuItem()
    {
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

        aboutNotepad = new JMenuItem("About NoteCraft");
        aboutNotepad.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, KeyEvent.KEY_LOCATION_UNKNOWN));
        aboutNotepad.addActionListener(this);
        aboutNotepad.setActionCommand("Help");
        menuHelp.add(aboutNotepad);

    }

    public void word_wrap(boolean wrap)
    {
        textarea.setLineWrap(wrap);
        textarea.setWrapStyleWord(wrap);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String command = e.getActionCommand();

        switch(command)
        {
            //FILE MENU COMMAND
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

            //FORMAT MENU COMMAND

            case "Redo":
                edit.redo();
                break;

            case "Undo":
                edit.undo();
                break;

            case "Copy":
                edit.copy();
                break;

            case "Cut":
                edit.cut();
                break;

            case "Paste":
                edit.paste();
                break;

            case "Find" :
                edit.find();
                break ;

            case "Replace" :
                edit.replace();
                break ;

            case "GoTo" :
            {
                edit.Goto();
                break ;
            }

            case "SelectAll" :
            {
                edit.selectAll();
                break ;
            }

            case "DateAndTime" :
            {
                edit.Date();
                break ;
            }
            //FORMAT MENU COMMAND

            case "WordWrap":
                if ("WordWrap".equals(e.getActionCommand())) {
                    word_wrap(wordWrap.isSelected());
                }
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

            case "Dark++":
                 textarea.setBackground(Color.BLACK);
                 textarea.setForeground(Color.WHITE);
                 textarea.setCaretColor(Color.WHITE);
                break;
            case "White":
                 textarea.setBackground(Color.WHITE);
                 textarea.setForeground(Color.BLACK);
                 textarea.setCaretColor(Color.BLACK);
                break;
            case "Dracula":
                 textarea.setBackground(Color.GRAY);
                 textarea.setForeground(Color.BLACK);
                 textarea.setCaretColor(Color.WHITE);
                break;
            case "Red":
                 textarea.setBackground(Color.RED);
                 textarea.setForeground(Color.YELLOW);
                 textarea.setCaretColor(Color.WHITE);
                break;
            case "Blue":
                 textarea.setBackground(Color.BLUE);
                 textarea.setForeground(Color.WHITE);
                 textarea.setCaretColor(Color.WHITE);
                break;
            case "Green":
                 textarea.setBackground(Color.GREEN);
                 textarea.setForeground(Color.red);
                 textarea.setCaretColor(Color.WHITE);
                break;
            case "Hacker":
                 textarea.setBackground(Color.BLACK);
                 textarea.setForeground(Color.GREEN);
                 textarea.setCaretColor(Color.WHITE);
                break;

            //VIEW MENU COMMAND

            case "ZoomIn" :
            {
                view.zoomIN();
                break ;
            }

            case "ZoomOut" :
            {
                view.zoomOut();
                break ;
            }

            //HELP MENU COMMAND

            case "Help":
                new Function_Help();
                break;
        }
    }
}


