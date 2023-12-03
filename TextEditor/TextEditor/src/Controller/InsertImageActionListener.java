package Controller;

import View.NewDocument;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.StyleConstants;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JTextPane;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import java.io.StringReader;
import java.io.IOException;

public class InsertImageActionListener implements ActionListener {
    NewDocument doc;
    private boolean haveSetContentType;
    public InsertImageActionListener(NewDocument doc){
        this.doc = doc;
        this.haveSetContentType = false;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(((JMenuItem)e.getSource()).getText().equals("Insert Image")){
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedImageFile = fileChooser.getSelectedFile();
                insertImage(this.doc.getTextPane(), selectedImageFile);
            }
        }
        else{
        }
    }
    private void insertImage(JTextPane textPane, File imageFile) {
        if(!haveSetContentType){
            this.setContentType();
        }
        try {
            ImageIcon imageIcon = new ImageIcon(imageFile.getAbsolutePath());
            int width = imageIcon.getIconWidth();
            int height = imageIcon.getIconHeight();
            HTMLDocument doc = (HTMLDocument) textPane.getDocument();
            Element element = doc.getElement(doc.getDefaultRootElement(), StyleConstants.NameAttribute, HTML.Tag.BODY);
            String imgTag = "<img src='file:" + imageFile.getAbsolutePath() + "' width='" + width + "' height='" + height + "'>";
            doc.insertBeforeEnd(element, imgTag);
            System.out.println("Path: " + imageFile.getAbsolutePath());
        } catch (IOException | BadLocationException e) {
            e.printStackTrace();
        }
    }
    public void setContentType() {
        this.haveSetContentType = true;
        String text = this.doc.getTextPane().getText();

        this.doc.getTextPane().setContentType("text/html");

        try{
            this.doc.getTextPane().getDocument().insertString(0, text, null);
        }
        catch (BadLocationException e){
            System.out.println(e.getMessage());
        }

    }
    public String getTextWithoutHTMLTags(JTextPane textPane) {
        // Get the HTML content from the text pane
        String htmlContent = textPane.getText();

        // Create a StringBuilder to store the text without HTML tags
        StringBuilder plainText = new StringBuilder();

        // Create an HTML parser
        ParserDelegator parserDelegator = new ParserDelegator();

        try {
            // Parse the HTML content
            parserDelegator.parse(new StringReader(htmlContent), new HTMLEditorKit.ParserCallback() {
                @Override
                public void handleText(char[] data, int pos) {
                    // Append text to the plainText StringBuilder
                    plainText.append(data);
                }

                @Override
                public void handleStartTag(HTML.Tag t, MutableAttributeSet a, int pos) {
                    // Handle start tags if needed
                }

                @Override
                public void handleEndTag(HTML.Tag t, int pos) {
                    // Handle end tags if needed
                }
            }, true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Convert the StringBuilder to a String and return it
        return plainText.toString();
    }
}
