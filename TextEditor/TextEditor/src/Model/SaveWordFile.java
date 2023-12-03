package Model;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import javax.swing.*;
import java.io.FileOutputStream;

public class SaveWordFile {
    public void saveToWordFile(JTextPane textPane, String filePath) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
            XWPFDocument document = new XWPFDocument();
            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();

            // Set the content from the JTextPane to the Word document
            run.setText(textPane.getText());

            // Save the document
            document.write(fileOutputStream);

            System.out.println("Word file saved successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
