package net.voidfn.textedit;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ActionOpen extends AbstractAction {

    private TextEdit textEdit;

    ActionOpen(TextEdit textEdit) {
        super("open");
        this.textEdit = textEdit;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose a file to open");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text files, .txt", "txt"));
        int result = fileChooser.showOpenDialog(this.textEdit.getContentPane());
        if (result != JFileChooser.APPROVE_OPTION) {
            return;
        }
        File selectedFile = fileChooser.getSelectedFile();
        File currentFile = this.textEdit.getFile();
        if (currentFile != null) {
            if (currentFile.getAbsolutePath().equals(selectedFile.getAbsolutePath())) {
                JOptionPane.showMessageDialog(this.textEdit, "The file is already opened.", "Info",
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            // we're changing files now
            int choice = JOptionPane.showConfirmDialog(this.textEdit, "Do you want to save the file?");
            if (choice == JOptionPane.CANCEL_OPTION) {
                return;
            }
            if (choice == JOptionPane.OK_OPTION) {
                this.textEdit.save();
            }
            // JOptionPane.NO_OPTION does not need to be handle explicitly
        }
        this.textEdit.setFile(selectedFile);
    }
}
