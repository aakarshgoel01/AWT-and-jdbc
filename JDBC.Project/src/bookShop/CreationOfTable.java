package bookShop;

import java.sql.Connection;
import java.sql.Statement;

public class CreationOfTable {

	public static void main(String[] args) {
		try {
			Connection con = ConnectionProvider.giveConnection();
			Statement stmt =con.createStatement();
		    stmt.executeUpdate("create table booksshop (id int primary key, name varchar(30) , bookname varchar(30) , edition int )");
		    System.out.println("Table created...");
		} catch (Exception e) {
			
			e.printStackTrace();
		
		}

	}

}
