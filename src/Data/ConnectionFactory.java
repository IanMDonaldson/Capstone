package Data;

import com.mysql.jdbc.Driver;



import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.SQLException;



public class ConnectionFactory {







    public static final String URL = "jdbc:mysql://localhost:3306/sakila";



    public static final String USER = "insertYourUsername";



    public static final String PASS = "insertYourPassword";







    public static Connection getConnection()

//yes

    {



        try {



            DriverManager.registerDriver(new Driver());



            return DriverManager.getConnection(URL, USER, PASS);



        } catch (SQLException ex) {



            throw new RuntimeException("Error connecting to the database", ex);



        }



    }







}
