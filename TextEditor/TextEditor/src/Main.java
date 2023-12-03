import View.Login;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {
    //we implemented the layered architechture first there is the view that contains what is shown to user(buttons,pages etc)
    //then there is the controller which contains the busniness logic and then we have model which deals with database actions.
    public static void main(String[] args){
        Login login = new Login();
        Connection conn = DBConncection();
        login.createLoginPage(conn);
    }
    public static Connection DBConncection(){
        try{
            String driver = "com.mysql.cj.jdbc.Driver";
            String DBurl = "jdbc:mysql://localhost:3306/texteditor";
            String userName = "root";
            String password = "Arfasara1624928";
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(DBurl, userName, password);
            return conn;

        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
