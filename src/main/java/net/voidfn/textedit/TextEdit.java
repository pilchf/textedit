package net.voidfn.textedit;

import java.awt.Color;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

class TextEdit extends JFrame {
    private JMenuItem menuSave;
    private JMenuItem menuSaveAs;
    private JMenuItem menuSaveCopyAs;
    private JMenuItem menuClose;

    private JTextArea textArea;
    private File file;

    TextEdit() {
        this.setTitle("Text Edit");
        this.setSize(500, 400);

        ActionOpen actionOpen = new ActionOpen(this);
        ActionSave actionSave = new ActionSave(this);
        ActionSaveAs actionSaveAs = new ActionSaveAs(this);
        ActionSaveCopyAs actionSaveCopyAs = new ActionSaveCopyAs(this);

        JMenuItem menuOpen = new JMenuItem("open");
        menuOpen.setAction(actionOpen);
        menuOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        menuOpen.setMnemonic(KeyEvent.VK_O);

        this.menuSave = new JMenuItem("save");
        this.menuSave.setAction(actionSave);
        this.menuSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        this.menuSave.setMnemonic(KeyEvent.VK_S);
        this.menuSave.setEnabled(false);

        this.menuSaveAs = new JMenuItem("save as");
        this.menuSaveAs.setAction(actionSaveAs);
        this.menuSaveAs.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK | InputEvent.ALT_DOWN_MASK));
        this.menuSaveAs.setMnemonic(KeyEvent.VK_A);
        this.menuSaveAs.setEnabled(false);

        this.menuSaveCopyAs = new JMenuItem("save copy as");
        this.menuSaveCopyAs.setAction(actionSaveCopyAs);
        this.menuSaveCopyAs.setMnemonic(KeyEvent.VK_V);
        this.menuSaveCopyAs.setEnabled(false);

        this.menuClose = new JMenuItem("close");
        this.menuClose.setAction(new ActionClose(this));
        this.menuClose.setMnemonic(KeyEvent.VK_C);
        this.menuClose.setEnabled(false);

        JMenuItem menuExit = new JMenuItem("exit");
        menuExit.setAction(new ActionExit(this));
        menuExit.setMnemonic(KeyEvent.VK_E);

        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        fileMenu.add(menuOpen);
        fileMenu.add(this.menuSave);
        fileMenu.add(this.menuSaveAs);
        fileMenu.add(this.menuSaveCopyAs);
        fileMenu.addSeparator();
        fileMenu.add(this.menuClose);
        fileMenu.add(menuExit);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(fileMenu);
        this.setJMenuBar(menuBar);

        JTextArea textArea = new JTextArea();
        textArea.setBackground(Color.LIGHT_GRAY);
        textArea.setTabSize(4);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEnabled(false);

        JScrollPane scrollPane = new JScrollPane(textArea);

        this.getContentPane().add(scrollPane);
        this.textArea = textArea;

        this.setVisible(true);
    }

    void setFile(File file) {
        try {
            this.textArea.setText(Util.readFile(file));
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "The file does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "The file could not be read.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        this.file = file;
        this.setTitle("Text Edit - " + file.getName());
        this.textArea.setEnabled(true);
        this.menuSave.setEnabled(true);
        this.menuSaveAs.setEnabled(true);
        this.menuSaveCopyAs.setEnabled(true);
        this.menuClose.setEnabled(true);
        this.textArea.setBackground(Color.WHITE);
    }

    File getFile() {
        return this.file;
    }

    void close() {
        this.file = null;
        this.setTitle("Text Edit");
        this.textArea.setEnabled(false);
        this.menuSave.setEnabled(false);
        this.menuSaveAs.setEnabled(false);
        this.menuSaveCopyAs.setEnabled(false);
        this.menuClose.setEnabled(false);
        this.textArea.setBackground(Color.LIGHT_GRAY);
        this.textArea.setText("");
    }

    void save() {
        Util.writeFile(this.file, this.textArea.getText());
    }

    void saveAs(File file) {
        this.file = file;
        Util.writeFile(file, this.textArea.getText());
    }

    void saveCopyAs(File file) {
        Util.writeFile(file, this.textArea.getText());
    }

    public static void main(String[] args) {
        new TextEdit();
    }

}
