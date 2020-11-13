package Data;
import Data.ConnectionFactory;

import javax.management.Query;
import java.sql.Connection;
public class LoginSrevice {
    private int count;
    //defunct
    public LoginSrevice(){}
    public boolean LoginCheck(String uname, String password){
        ConnectionFactory conn = (ConnectionFactory) ConnectionFactory.getConnection();
        //need to fix connection factory on my end maybe
       if (1=somevalue) {
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
