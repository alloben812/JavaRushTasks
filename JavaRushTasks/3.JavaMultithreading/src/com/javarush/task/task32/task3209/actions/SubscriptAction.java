package com.javarush.task.task32.task3209.actions;

import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;
import java.awt.event.ActionEvent;

/**
 * Created by NazarenkoDS on 17.05.2017.
 */
public class SubscriptAction extends  StyledEditorKit.StyledTextAction{

    public SubscriptAction() {
        super(StyleConstants.StrikeThrough.toString());
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
