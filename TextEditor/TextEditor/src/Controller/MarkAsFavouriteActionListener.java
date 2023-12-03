package Controller;

import View.NewDocument;
import Model.SaveFavouriteDocumentData;

import javax.swing.*;
import javax.swing.JOptionPane;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.concurrent.CountDownLatch;

public class MarkAsFavouriteActionListener implements ActionListener {
    private Connection connection;
    private NewDocument document;
    private SaveFavouriteDocumentData saveFavouriteDocumentData;

    public MarkAsFavouriteActionListener(Connection conn, NewDocument newDocument) {
        this.connection = conn;
        this.document = newDocument;
        this.saveFavouriteDocumentData = new SaveFavouriteDocumentData(this.connection);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(this.document.getDocumentName().equals("")){
            getDocumentName(new NameCallback() {
                @Override
                public void onNameEntered(String name) {
                    MarkAsFavouriteActionListener.this.document.setDocumentName(name);
                    MarkAsFavouriteActionListener.this.saveFavouriteDocumentData.saveData(MarkAsFavouriteActionListener.this.document.getUserName(), name);
                    JOptionPane.showMessageDialog(MarkAsFavouriteActionListener.this.document, "Document has been marked as favourite!");
                }
            });
        }
        else{
            this.saveFavouriteDocumentData.saveData(this.document.getUserName(), this.document.getDocumentName());
        }
    }
    //this would get the name of newly created document that is not saved in DB yet.
    private void getDocumentName(NameCallback callback) {
        JDialog dialog = new JDialog();
        dialog.setSize(200, 200);
        dialog.setLayout(new GridLayout(3, 1));

        JLabel label = new JLabel("Enter name of the document");
        dialog.add(label);

        JTextField nameField = new JTextField();
        dialog.add(nameField);
        JButton okBtn = new JButton("OK");
        dialog.add(okBtn);
        dialog.setLocationRelativeTo(null);

        okBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                callback.onNameEntered(name);
                dialog.dispose(); // Close the dialog
            }
        });

        dialog.setVisible(true);
    }

}
interface NameCallback {
    void onNameEntered(String name);
}
