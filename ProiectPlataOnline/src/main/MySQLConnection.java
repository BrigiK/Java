package main;

import java.sql.*;

public class MySQLConnection {
	
	public static Connection getConnection() throws Exception
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/plataonlinedb","root","attigirb");
			return conn;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
}
