package net.voidfn.textedit;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class ActionSave extends AbstractAction {

    private TextEdit textedit;

    ActionSave(TextEdit textedit) {
        super("save");
        this.textedit = textedit;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.textedit.save();
    }

}