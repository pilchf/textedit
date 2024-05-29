package net.voidfn.textedit;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ActionSaveCopyAs extends AbstractAction {

    private TextEdit textedit;

    ActionSaveCopyAs(TextEdit textedit) {
        super("save copy as");
        this.textedit = textedit;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose a location to save the file");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text files, .txt", "txt"));
        int result = fileChooser.showSaveDialog(this.textedit.getContentPane());
        if (result == JFileChooser.APPROVE_OPTION) {
            this.textedit.saveCopyAs(fileChooser.getSelectedFile());
        }
    }
}