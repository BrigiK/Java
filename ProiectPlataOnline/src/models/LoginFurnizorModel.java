package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entities.Furnizor;
import main.MySQLConnection;

public class LoginFurnizorModel {

	Connection connection;
	public LoginFurnizorModel()  {
		try {
			connection = MySQLConnection.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
	
	public Furnizor loginFurnizor(String user, String pass) throws SQLException
	{
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "select idFurnizor, numeFurnizor, adresaFurnizor from furnizor where userNameFurnizor = ? and parolaFurnizor = ?"; //Querying the Database(retrieving data)
		try {
			preparedStatement = connection.prepareStatement(query); //prepareStatement = takes a string argument
			preparedStatement.setString(1, user); 
			preparedStatement.setString(2, pass); 
			
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) //resultSet - if it is returning any result or not
			{
				int id = resultSet.getInt("idFurnizor");
				String name = resultSet.getString("numeFurnizor");
				String address = resultSet.getString("adresaFurnizor");
				
				return new Furnizor(id, name, address);
			}
			else
				return null;
		} catch (Exception e) {
			return  null;
		}
		
	}
}
