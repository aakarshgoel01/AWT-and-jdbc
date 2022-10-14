package EmployLeave;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionProvider {
   static Connection creatConnection() {
    	Connection con = null;
    	
    	if(con == null) {
    		try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myxampp","root","182357");
				System.out.println("Connection created...........");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    	return con;
    }
}
