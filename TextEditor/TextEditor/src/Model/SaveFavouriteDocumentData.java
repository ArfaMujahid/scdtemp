package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SaveFavouriteDocumentData {
    private Connection connection;
    public SaveFavouriteDocumentData(Connection conn){
        this.connection = conn;
    }
    public void saveData(final String userName, final String documentName){
        String query = "insert into favouritedocuments values(?, ?);";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, documentName);

            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
