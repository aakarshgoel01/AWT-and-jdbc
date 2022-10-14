package bookShop;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionProvider {
	
   static Connection giveConnection() {
		Connection con = null;
	   
	    try {
			if(con == null) {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myxampp","root","182357");
			}
		}catch(Exception ee) {
			System.out.println(ee+"...............");
		}
		
		return con;
	}

}
