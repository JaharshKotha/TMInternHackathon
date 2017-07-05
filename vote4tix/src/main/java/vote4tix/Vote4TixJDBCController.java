package vote4tix;


import java.sql.*;


public class Vote4TixJDBCController
{
      public void insertUser(){
          try{
          String myDriver = "com.mysql.jdbc.Driver";
          String myUrl = "jdbc:mysql://sql3.freemysqlhosting.net:3306/sql3182766";
          Class.forName(myDriver);
          Connection conn = DriverManager.getConnection(myUrl, "sql3182766", "6GWVYhlQpV");
          String query = " insert into Users (emailId, password, eventId, groupId)"
           + " values (?, ?, ?, ?)";
           PreparedStatement preparedStmt = conn.prepareStatement(query);
          preparedStmt.setString (1, "Prashanth");
          preparedStmt.setString (2, "Rubble");
          preparedStmt.setString   (3, "1sdfsdf2");
          preparedStmt.setString(4, "34dsfsdf");
          preparedStmt.execute();
      
          conn.close();
        }
        catch (Exception e)
       {
           System.err.println("Got an exception!");
           System.err.println(e.getMessage());
       }
    }
          
}
