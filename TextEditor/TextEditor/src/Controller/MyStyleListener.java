package Controller;

import View.NewDocument;


import javax.swing.*;
import javax.swing.text.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//This class handles functionalities of apply color, bold text, italic text and add bullets.
public class MyStyleListener implements ActionListener {
    NewDocument document;
    public MyStyleListener(final NewDocument doc){
        this.document = doc;
    }

    //In it's action performed function, adding bullets functionality is implemented.
    @Override
    public void actionPerformed(ActionEvent e) {
        JTextPane textPane = this.document.getTextPane();
        StyledDocument doc = textPane.getStyledDocument();
        String selectedText = textPane.getSelectedText();

        if (selectedText != null) {
            String bullet = "\u2022";

            // Get the initial attributes of the selected text
            AttributeSet initialAttributes = doc.getCharacterElement(textPane.getSelectionStart()).getAttributes();

            // Initialize a variable to keep track of the current position
            int currentPosition = textPane.getSelectionStart();

            while (currentPosition < textPane.getSelectionEnd()) {
                // Find the end of the current style run
                int endOfRun = currentPosition;
                AttributeSet currentAttributes = doc.getCharacterElement(currentPosition).getAttributes();
                while (currentPosition < textPane.getSelectionEnd() &&
                        currentAttributes.isEqual(doc.getCharacterElement(currentPosition).getAttributes())) {
                    currentPosition++;
                }

                // Apply the bullet point style to the current run
                String newText = bullet + " " + selectedText.substring(endOfRun - textPane.getSelectionStart(), currentPosition - textPane.getSelectionStart())
                        .replace("\n", "\n" + bullet + " ");
                try {
                    doc.remove(endOfRun, currentPosition - endOfRun);
                    doc.insertString(endOfRun, newText, initialAttributes);
                    currentPosition += newText.length();
                } catch (BadLocationException ex) {
                    ex.printStackTrace();
                }
            }
        }

    }
    public void applyColor(){

        StyledDocument doc = this.document.getTextPane().getStyledDocument();
        int start = this.document.getTextPane().getSelectionStart();
        int end = this.document.getTextPane().getSelectionEnd();

        if (start != end) {
            Style style = this.document.getTextPane().addStyle("Color Style", null);
            StyleConstants.setForeground(style, this.document.getFontColorActionListener().getFontColor());
            doc.setCharacterAttributes(start, end - start, style, false);
        }
    }
    public void boldText(){
        AttributeSet attributeSet = this.document.getTextPane().getCharacterAttributes();

        //creating new desired style.
        SimpleAttributeSet newAttributes = new SimpleAttributeSet();
        StyleConstants.setBold(newAttributes, true);

        //Applying style to textPane.
        this.document.getTextPane().setCharacterAttributes(newAttributes, false);
    }
    public void italicText(){
        AttributeSet attributeSet = this.document.getTextPane().getCharacterAttributes();

        //creating new desired style.
        SimpleAttributeSet newAttributes = new SimpleAttributeSet();
        StyleConstants.setItalic(newAttributes, true);

        //Applying style to textPane.
        this.document.getTextPane().setCharacterAttributes(newAttributes, false);
    }

}
