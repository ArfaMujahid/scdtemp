package Controller;

import View.NewDocument;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FontStyleActionListener implements ActionListener {
    private NewDocument document;
    public FontStyleActionListener(NewDocument doc){
        this.document = doc;
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        String fontStyle = ((JMenuItem) e.getSource()).getText();

        //creating new desired style.
        SimpleAttributeSet newAttributes = new SimpleAttributeSet();
        StyleConstants.setFontFamily(newAttributes, fontStyle);

        //Applying style to textPane.
        this.document.getTextPane().setCharacterAttributes(newAttributes, false);
    }
}
