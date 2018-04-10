package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.MySQLConnection;
import javafx.fxml.FXML;

import javafx.scene.control.Label;

public class NewUserClientController implements Initializable {
	
	//public NewUserClientModel newUserClientModel = new NewUserClientModel();
	
	@FXML
	private Label newCLbl;
	
	@FXML
	private TextField txtnumPrenum;
	
	@FXML
	private TextField txtcnp;
	
	@FXML
	private TextField txtuserN;
	
	@FXML
	private TextField txtpass;
	
	@FXML
	private TextField txtEmail;
	
	@FXML
	private Label isUserNValid;
	
	@FXML
	private Label lblAlert;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	@FXML
	public void submit(ActionEvent event) throws Exception 
	{ 
		String query = "INSERT INTO client (numeClient, CNP, userNameClient, parolaClient, email) VALUES (?,?,?,?,?)";
		String queryUnique="SELECT * FROM client WHERE userNameClient = ?";
		Connection conn;
		try {
			conn=MySQLConnection.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			PreparedStatement preparedStatement2 = conn.prepareStatement(queryUnique);
			
			preparedStatement.setString(1, this.txtnumPrenum.getText()); 
			preparedStatement.setString(2, this.txtcnp.getText()); 
			preparedStatement.setString(3, this.txtuserN.getText());
			preparedStatement.setString(4, this.txtpass.getText());
			preparedStatement.setString(5, this.txtEmail.getText());
			
			preparedStatement2.setString(1, txtuserN.getText()); 
			
			ResultSet resultSet = preparedStatement2.executeQuery();
			
			if(resultSet.next()) {
				isUserNValid.setText("Username deja existent!");
				throw new Exception("Not Valid!");
			}
			else
			{
				isUserNValid.setText("Username-ul este valid");
				if(txtcnp.getText().length()==13)
					 preparedStatement.execute(); 
				else 
					{
					isUserNValid.setText("CNP incorect!");
					throw new Exception("Not Valid!");
					}
			}

			conn.close();

			((Node) event.getSource()).getScene().getWindow().hide(); // this hides the stage
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/layout/LoginClient.fxml").openStream());
		
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (SQLException e) {
			e.printStackTrace();
		}  catch (Exception e) {
			System.out.println(e.getMessage());
		}
	
	}
	
	public void signOut(ActionEvent event)
	{
		try {
		((Node) event.getSource()).getScene().getWindow().hide(); // this hides the stage
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/layout/MainScreen.fxml").openStream());
		
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
		} catch( Exception e)
		{
			
		}
	}

}
