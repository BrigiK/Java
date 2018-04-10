package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.MySQLConnection;

public class AddMoneyController {

	@FXML
	private Button btnAdd;
	@FXML
	private TextField txtAddMoney;
	@FXML
	private Button btnBack;
	@FXML
	private Label lbl;
	@FXML
	private Label lblName;
	@FXML
	private Label lblAlert;

	public AddMoneyController() {
	}

	public void initialize() {
		lblName.setText(Session.getInstance().getCurrentClient().getNume().get());
	}

	public void Back(ActionEvent ex) throws IOException {
		((Stage) btnBack.getScene().getWindow()).close();
	}

	public void AddMoney(ActionEvent e) throws Exception {
		String balanceToAdd = txtAddMoney.getText().trim();

		double balance = 0;
		try {
			balance = Double.parseDouble(balanceToAdd);
		} catch (NumberFormatException ex) {
			lblAlert.setText("Suma adaugata este invalida!");
			return;
		}

		if (balance < 0) {
			lblAlert.setText("Suma adaugata nu poate fi un numar negativ!");
			return;
		}
		if (100 > balance) {
			lblAlert.setText("Suma adaugata nu poate fi mai putin de 100!");
			return;
		}
		if (balance > 1000) {
			lblAlert.setText("Suma adaugata nu poate fi mai mult de 1000!");
			return;
		}

		String query = "UPDATE client SET bani = bani + ? WHERE userNameClient = ?";
		try {
			Connection conn=MySQLConnection.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, this.txtAddMoney.getText()); 
			preparedStatement.setString(2, Session.getInstance().getCurrentClient().getUserN().get()); 
			preparedStatement.executeUpdate();
			lblAlert.setText("Banii au fost adaugati in cont cu succes!");
			
			conn.close();
			} catch (SQLException e2) {
			e2.printStackTrace();
			}
		((Stage) btnBack.getScene().getWindow()).close();
	}

}
