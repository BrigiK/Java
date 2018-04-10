package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import entities.Furnizor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.LoginFurnizorModel;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import javafx.scene.control.PasswordField;

public class LoginFurnizorController implements Initializable{
	
	public LoginFurnizorModel loginFurnizorModel = new LoginFurnizorModel();  // instance of LoginModel
	
	@FXML
	private Label isConnected;
	@FXML
	private TextField txtUsername;
	@FXML
	private PasswordField txtPassword;
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if(loginFurnizorModel.isDbConnected())
			isConnected.setText("Connected");
		else
			isConnected.setText("Not Connected");
	}
	
	public void LoginFurnizor(ActionEvent event) 
	{ // successful login 
		try {
			Furnizor furnizor = loginFurnizorModel.loginFurnizor(txtUsername.getText(), txtPassword.getText());
			if(furnizor != null)
			{
				isConnected.setText("Username and password is correct");
				Session.getInstance().setCurrentFurnizor(furnizor);
				
				((Node) event.getSource()).getScene().getWindow().hide(); // this hides the stage
				Stage primaryStage = new Stage();
				FXMLLoader loader = new FXMLLoader();
				Pane root = loader.load(getClass().getResource("/layout/FurnizorPage.fxml").openStream());
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
