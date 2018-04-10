package models;
import java.sql.*;

import entities.Client;
import main.MySQLConnection;

public class LoginModel {
	
	Connection connection;
	public LoginModel()  {
		
		try {
			connection = MySQLConnection.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(connection == null)
		{
			System.out.println("Connection is not successful");
			System.exit(1);
		}
	}
	
	public boolean isDbConnected() {
		try {
			return !connection.isClosed(); //returns boolean value; if not closed => false; !false=true
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public Client login(String user, String pass) throws SQLException
	{
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "select idClient, userNameClient, numeClient, CNP, email from client where userNameClient = ? and parolaClient = ?"; //Querying the Database(retrieving data)
		try {
			// after preparedStatement is prepared, executes the query with the values user and pass, and they will be replaced
			// with the question marks; they will be saved in the resultSet
			preparedStatement = connection.prepareStatement(query); //prepareStatement = takes a string argument
			preparedStatement.setString(1, user); 
			preparedStatement.setString(2, pass); 
			
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) //resultSet - if it is returning any result or not
			{
				int id = resultSet.getInt("idClient");
				String username = resultSet.getString("userNameClient");
				String name = resultSet.getString("numeClient");
				String cnp = resultSet.getString("CNP");
				String email = resultSet.getString("email");
				
				return new Client(id, name, cnp, username, pass, email);
			}
			else
				return null;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return  null;
		}
	}
}
