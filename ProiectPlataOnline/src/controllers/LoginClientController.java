package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import entities.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.LoginModel;

public class LoginClientController implements Initializable{
	
	public LoginModel loginModel = new LoginModel();  // instance of LoginModel
	
	@FXML
	private TextField txtUsername;
	
	@FXML
	private TextField txtPassword;
	
	@FXML
	private Label isConnected;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		if(loginModel.isDbConnected())
			isConnected.setText("Connected");
		else
			isConnected.setText("Not Connected");
	}
	
	public void Login(ActionEvent event) 
	{ // successful login 
		try {
			Client client = loginModel.login(txtUsername.getText(), txtPassword.getText());
			if(client != null)
			{
				isConnected.setText("Username and password is correct");
				
				Session.getInstance().setCurrentClient(client);
				
				((Node) event.getSource()).getScene().getWindow().hide(); // this hides the stage
				Stage primaryStage = new Stage();
				FXMLLoader loader = new FXMLLoader();
				Pane root = loader.load(getClass().getResource("/layout/ClientPage.fxml").openStream());
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
			}
			else
				isConnected.setText("Username and password is incorrect");
		
			} catch (SQLException e) {
			isConnected.setText("Username and password is incorrect");
			e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	@FXML
	void newClient(ActionEvent event) 
	{ // successful login 
		try {	
				((Node) event.getSource()).getScene().getWindow().hide(); // this hides the stage
				Stage primaryStage = new Stage();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/layout/NewUserClient.fxml"));
				Parent root = (Parent) loader.load();
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();		
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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

