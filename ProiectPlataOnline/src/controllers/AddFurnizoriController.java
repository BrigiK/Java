package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import entities.Furnizor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.MySQLConnection;
import javafx.scene.control.TableColumn;

public class AddFurnizoriController implements Initializable {
	@FXML
	private TableView<Furnizor> furnizorTable;
	@FXML
	private TableColumn<Furnizor, Integer> idCol;
	@FXML
	private TableColumn<Furnizor, String> nameCol;
	@FXML
	private TableColumn<Furnizor, String> addressCol;
	@FXML
	private Label lblUserN;
	@FXML
	private Label lblAlert;
	@FXML
	private Button btnBack;
	
	private ObservableList<Furnizor> furnizor;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		String sql = "SELECT * FROM furnizor";

		//furnizorTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		lblUserN.setText(Session.getInstance().getCurrentClient().getNume().get());
		
		Connection conn= null;		
		try {
			conn=MySQLConnection.getConnection();
			this.furnizor = FXCollections.observableArrayList();
			ResultSet rs = conn.createStatement().executeQuery(sql);
			
			while(rs.next())
			{
				this.furnizor.add(new Furnizor(rs.getInt(1),rs.getString(2),rs.getString(3)));
			}
			conn.close();
			
		} catch (SQLException e) {
			lblAlert.setText("Un furnizor nu a fost adaugat cu succes!");//System.err.println("Error " + e);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.idCol.setCellValueFactory(new PropertyValueFactory<Furnizor, Integer>("id"));
		this.nameCol.setCellValueFactory(new PropertyValueFactory<Furnizor, String>("nume"));
		this.addressCol.setCellValueFactory(new PropertyValueFactory<Furnizor, String>("adresa"));
		
		this.furnizorTable.setItems(null);
		this.furnizorTable.setItems(this.furnizor);	
	}
	
	public void addFurnizori(ActionEvent e) throws Exception {

		String query = "INSERT INTO client_furnizor (idClient, idFurnizor) VALUES (?,?)";
		int idClient = Session.getInstance().getCurrentClient().getId().get();
		
		if(furnizorTable.getSelectionModel().getSelectedItem() != null)
		{			
			Connection conn = null;
			try {
				conn = MySQLConnection.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(query);
				//PreparedStatement preparedStatement2 = conn.prepareStatement(queryUnique);
				for(Furnizor furnizor : furnizorTable.getSelectionModel().getSelectedItems())
				{
					String queryUnique= "SELECT * FROM client_furnizor WHERE idFurnizor ='"+furnizor.getId()+"' AND idClient='"+Integer.toString(idClient)+"'";
					Statement st=conn.createStatement();
					ResultSet resultSet = st.executeQuery(queryUnique);
					if(resultSet.next())
					{
						conn.close(); 
						lblAlert.setText("Nu se poate selecta acelasi furnizor de 2 ori");
					}
					else 
					{
						preparedStatement.setInt(1, idClient);
						preparedStatement.setInt(2, furnizor.getId());
						preparedStatement.execute();
						lblAlert.setText("Furnizor adaugat cu succes!");
					}
						
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			finally
			{
				if(conn != null)
					conn.close();
			}
		}
	}
	
	public void Back(ActionEvent ex) throws IOException {
		((Stage) btnBack.getScene().getWindow()).close();
	}
}


