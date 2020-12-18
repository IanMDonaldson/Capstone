package Data;
import Data.ConnectionFactory;

import javax.management.Query;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {
    public static boolean validate(String username, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement("select * from user where user_uname = ? AND user_pw = ?;" );
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                conn.close();
                ps.close();
                rs.close();
                return true;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static String getUserType(String username, String password) {
        String usertype = null;
        try {
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement("select * from user where user_uname = ? AND user_pw = ?;" );
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            rs.next();
            usertype = rs.getString("user_type");
            conn.close();
            ps.close();
            rs.close();
            return usertype;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return usertype;
    }
    private int count;
    //defunct
    //the someValue was giving an error so i made an it an int varailble in this file temparally.
    int somevalue=1;
    public LoginDao(){}
    public boolean LoginCheck(String uname, String password){
        ConnectionFactory conn = (ConnectionFactory) ConnectionFactory.getConnection();
        //need to fix connection factory on my end maybe
       if (1==somevalue) {
           // if(get the login state of user )
           String adminlogin = "select admin_uname,uname_pw from admin as  where admin_name='" + uname + "' and admin_pw'" + password + "'";
           // run getALLadmins command here
           System.out.println("Total rows: " + count);
           if (count == 1) {
               return true;
           } else {
               return false;
           }
       }
       else{
           String Instructorlogin = "select admin_uname,uname_pw from admin as  where admin_name='" + uname + "' and admin_pw'" + password + "'";
           System.out.println("Total rows: " + count);
           if (count == 1) {
               return true;
           } else {
               return false;
           }
       }
    }

}
