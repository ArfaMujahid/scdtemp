package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseWork {
    private Connection connection;
    public DatabaseWork(Connection conn){
        this.connection = conn;
    }
    // This function will give total number of documents made by a user which is recieved as parameter.
    public int getTotalDocuments(final String username){
        String query = "select count(documentName) from " +
                "(select distinct documentName from document where username = ?) as f;";
        int count = 0;
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            count = resultSet.getInt("count(documentName)");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return count;
    }
    public int getTotalFavouriteDocuments(final String username){
        String query = "select count(documentName) from " +
                "(select distinct documentName from favouritedocuments where username = ?) as f;";
        int count = 0;
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            count = resultSet.getInt("count(documentName)");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return count;
    }
    public List getDocumentNames(final String username){
        String query = "select distinct documentName from document where username = ?;";
        List<String> documentNames = new ArrayList<>();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                String names = resultSet.getString(1);
                documentNames.add(names);
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return documentNames;
    }
    public List getFavouriteDocumentNames(final String username){
        String query = "select distinct documentName from favouritedocuments where username = ?;";
        List<String> documentNames = new ArrayList<>();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                String names = resultSet.getString(1);
                documentNames.add(names);
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return documentNames;
    }
    public String getDocumentType(final String userName, final String documentName){
        String query = "select type from document where username = ? and documentName = ?;";
        String type = "";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, documentName);
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            type = resultSet.getString("type");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return type;
    }
    public boolean isFavouriteDocumentExists(final String userName){
        String query = "select count(*) from favouritedocuments where username = ?;";
        int count = 0;
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userName);
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            count = resultSet.getInt("count(*)");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        if(count > 0){
            return true;
        }
        return false;
    }
}
