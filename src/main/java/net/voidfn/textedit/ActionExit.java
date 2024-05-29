package net.voidfn.textedit;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

public class ActionExit extends AbstractAction {

    private TextEdit textEdit;

    ActionExit(TextEdit textEdit) {
        super("exit");
        this.textEdit = textEdit;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.textEdit.getFile() != null) {
            int choice = JOptionPane.showConfirmDialog(this.textEdit, "Do you want to save the opened file?", "Save?",
                    JOptionPane.YES_NO_CANCEL_OPTION);
            if (choice == JOptionPane.CANCEL_OPTION) {
                return;
            }
            if (choice == JOptionPane.OK_OPTION) {
                this.textEdit.save();
            }
        }
        System.exit(0);
    }
}
