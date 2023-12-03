package Controller;
import Model.DatabaseWork;
import Model.GetFileData;
import View.NewDocument;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

public class LoadFileActionListener implements ActionListener {
    private GetFileData getFileData;
    private NewDocument document;
    private DatabaseWork databaseWork;
    Connection connection;

    public LoadFileActionListener(Connection conn, final String userName){
        this.getFileData = new GetFileData();
        this.connection = conn;
        this.document = new NewDocument(conn, userName);
        this.databaseWork = new DatabaseWork(this.document.getConnection());
    }
    @Override
    public void actionPerformed(ActionEvent e){
        String fileName = ((JButton)e.getSource()).getText();
        fileName = fileName.substring(5);// to remove open and getting only file name.
        String type = this.databaseWork.getDocumentType(this.document.getUserName(), fileName);
        if(type.equals("pdf")){
            this.document.getTextPane().setContentType("text/html");
        }
        else{
            //do nothing. remained content type as by default.
        }
        try {
            getFileData.LoadDataofFile(this.document.getTextPane(), this.connection, fileName);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        this.document.display(fileName);
    }
}
