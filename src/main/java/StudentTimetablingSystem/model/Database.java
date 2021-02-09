package StudentTimetablingSystem.model;
import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    public static Connection getConnection(){
        String URL = "jdbc:mysql://localhost:3306/studenttimetablingdb";
        String username = "root";
        String password = "Sathsara2019$";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL,username,password);

            return con;
        }
        catch(Exception ex){
            System.out.println("Database.getConnection() Error -->"+ ex.getMessage());
            return null;
        }
    }



}
