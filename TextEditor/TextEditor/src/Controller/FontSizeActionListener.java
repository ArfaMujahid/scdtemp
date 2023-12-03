package Controller;

import View.NewDocument;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FontSizeActionListener implements ActionListener {
    private NewDocument document;
    public FontSizeActionListener(NewDocument doc){
        this.document = doc;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String fontSizeText = ((JMenuItem) e.getSource()).getText();

        int fontSize = Integer.parseInt(fontSizeText);

        //creating new desired style.
        SimpleAttributeSet newAttributes = new SimpleAttributeSet();
        StyleConstants.setFontSize(newAttributes, fontSize);

        //Applying style to textPane.
        this.document.getTextPane().setCharacterAttributes(newAttributes, false);
    }
}