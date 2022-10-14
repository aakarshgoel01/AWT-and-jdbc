package EmployLeave;

import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {
	public static void main(String[] args) {
		try {
			Statement stmt = ConnectionProvider.creatConnection().createStatement();
            stmt.executeUpdate("alter table leaves add id int");

            ConnectionProvider.creatConnection().close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
}
