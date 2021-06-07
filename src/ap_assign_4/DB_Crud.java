//Mohammad Zohaib Abbas, 17L-6305, l176305@lhr.nu.edu.pk, AP-8A

package ap_assign_4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author mzohaib
 */
public class DB_Crud {
    private static String jdbcDriver = "com.mysql.jdbc.Driver";
    private static String dbName = "AccountRecord";
    private static DB_Crud db_instance= new DB_Crud();
    private Statement s;
    private Connection conn;

    // Establish Database Connection and Create relevant database and table to store AddressInformation if they don't exist
    private DB_Crud() { 
       try{
            Class.forName(jdbcDriver);
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root&password=arianagrande");
            s = conn.createStatement();
            int Result = s.executeUpdate("CREATE DATABASE IF NOT EXISTS "+dbName);
            
            String AddressTable = "CREATE TABLE IF NOT EXISTS AccountRecord.Account (" 
            + "username varchar(25) NOT NULL,"
            + "password varchar(15) NOT NULL,"   
            + "PRIMARY KEY (username));";  
            
            boolean flag = s.execute(AddressTable);
            
        } catch(Exception e){
            System.out.println("Error! "+e);
       }
    }
    
    //Singleton Design Pattern Implemented to ensure one instance of addressDB 
    public static DB_Crud getInstance(){
        return db_instance;
    }
    
    public int insertUser(User u){
       
       int flag=1;
       
       try{
            String SQL="SELECT * FROM AccountRecord.Account WHERE username='"+u.getUsername()+"'";
                         ResultSet rs=s.executeQuery(SQL);

                         if(!rs.next()){ // if the Account is not already in the database , then insert

                             String sql="INSERT INTO AccountRecord.Account (username,password)"
                                     + " VALUES (?,?)";

                             PreparedStatement statement=conn.prepareStatement(sql);
                             statement.setString(1,u.getUsername());
                             statement.setString(2,u.getPassword());

                             int row = statement.executeUpdate();

                             if(row >0) {
                                 System.out.println("A new Account inserted in database Successfully");
                             }
                         } 
                         else{
                             System.err.println("Error! An Account with this username already exists in Database");
                             flag=-1; // if flag==-1, username already exists
                         }
       }catch(SQLException e){
           flag=0;
            System.out.println("Error! "+e);
       }
     return flag;
   }
    
   public int updateAccountUsername(User u, String newUsername){
            try{
            
            if(conn!=null){
                
                    String SQL="SELECT * FROM AccountRecord.Account WHERE username='"+newUsername+"'";
                                 ResultSet rs=s.executeQuery(SQL);

                                 if(rs.next()){ // if User found in Database
                                     return -1; // returning -1 indicates that this username already exists in database
                                 } 
                
                        String sql="UPDATE AccountRecord.Account SET username=? WHERE username='"+u.getUsername()+"' and password='"+u.getPassword()+"'";

                        PreparedStatement statement=conn.prepareStatement(sql);

                        statement.setString(1,newUsername);
                        
                        int row = statement.executeUpdate();

                        if(row >0) {
                            System.out.println("Updated Successfully!");
                            return 1;
                        }
                  }
            
        } catch(SQLException ex){
            System.out.println("Update Failed!");
            ex.printStackTrace();
            return 0;
        }
      return 0;
    }
   
    public boolean updateAccountPassword(User u, String newPassword){
            try{
            
            if(conn!=null){
                
                        String sql="UPDATE AccountRecord.Account SET password=? WHERE username='"+u.getUsername()+"' and password='"+u.getPassword()+"'";

                        PreparedStatement statement=conn.prepareStatement(sql);

                        statement.setString(1,newPassword);
                        
                        int row = statement.executeUpdate();

                        if(row >0) {
                            System.out.println("Updated Successfully!");
                            return true;
                        }
                  }
            
        } catch(SQLException ex){
            System.out.println("Update Failed!");
            ex.printStackTrace();
            return false;
        }
      return false;
    }

    public boolean loginUsers(User u) {
       
       try{
            String SQL="SELECT * FROM AccountRecord.Account WHERE username='"+u.getUsername()+"' and password='"+u.getPassword()+"'";
                         ResultSet rs=s.executeQuery(SQL);

                         if(rs.next()){ // if User found in Database
                             return true;
                         } 
                         else{
                             System.err.println("Error! User not Found in Database");
                         }
       }catch(SQLException e){
            System.out.println("Error! "+e);
       }
     return false;
    }
}
