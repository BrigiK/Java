package controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import entities.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import main.MySQLConnection;



public class FurnizorPageController implements Initializable{
	
	@FXML
	private DatePicker txtData;
	@FXML
	private DatePicker txtScadenta;
	@FXML
	private TextField txtName;
	@FXML
	private TextField txtMoney;
	@FXML
	private Label lblAlert;
	@FXML 
	private ComboBox<Client> clientCombo;
	@FXML
	private ComboBoxTableCell<Client, String> nameCell;
	
	ObservableList<Client> clients;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
			String sql = "SELECT c.idClient, numeClient FROM client c, client_furnizor cf, furnizor f WHERE c.idClient = cf.idClient AND "
					+ "cf.idFurnizor=f.idFurnizor AND f.idFurnizor = " + Session.getInstance().getCurrentFurnizor().idProperty().get();
			try {			
				Connection conn=MySQLConnection.getConnection();
				this.clients = FXCollections.observableArrayList();
				
				ResultSet rs = conn.createStatement().executeQuery(sql);
				while(rs.next())
				{
					this.clients.add(new Client(rs.getInt("idClient"), rs.getString("numeClient"), "","","",""));
				}
				conn.close();
				
			} catch (SQLException e) {
				System.err.println("Error " + e);
			} catch (Exception e) {
				System.err.println("Error " + e);
			}
			finally
			{
			}

			clientCombo.setConverter(new StringConverter<Client>(){

		        @Override
		        public String toString(Client item) {
		            return item == null ? null : item.getNume().get() +" "+ item.getId().get();
		        }

				@Override
				public Client fromString(String arg0) {
					return null;
				}

		    });
			
			clientCombo.setCellFactory(new Callback<ListView<Client>, ListCell<Client>>() {
			    @Override
			    public ListCell<Client> call(ListView<Client> arg0) {
			        return new ListCell<Client>() {
			            @Override
			            protected void updateItem(Client item, boolean empty) {
			                super.updateItem(item, empty);
			                if (item == null || empty) {
			                    setGraphic(null);
			                } else {
			                    setText(item.getNume().get() +" "+ item.getId().get());
			                }
			            }
			            
			        };
			    }
			});
			
			this.clientCombo.setItems(null);
			this.clientCombo.setItems(this.clients);
		
		
	}
	
	@FXML
	public void submit(ActionEvent event)
	{
		Client client = clientCombo.getSelectionModel().getSelectedItem();
		
		java.util.Date date= java.util.Date.from(txtData.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		java.util.Date scadenta= java.util.Date.from(txtScadenta.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
		java.sql.Date sqlScadenta = new java.sql.Date(scadenta.getTime());
		
		String query = "INSERT INTO factura (idClient, idFurnizor ,data, scadenta, valoarea, numeFurnizor) VALUES (?,?,?,?,?,?)";
	
		Connection conn;
		try {
			conn=MySQLConnection.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(query);
		
			preparedStatement.setInt(1, client.getId().get()); 
			preparedStatement.setInt(2, Session.getInstance().getCurrentFurnizor().idProperty().get()); 
			preparedStatement.setDate(3, sqlDate);
			preparedStatement.setDate(4, sqlScadenta);
			preparedStatement.setString(5,this.txtMoney.getText());
			preparedStatement.setString(6, Session.getInstance().getCurrentFurnizor().numeProperty().get());

			
			if(sqlDate.compareTo(sqlScadenta)<0)
			{
				preparedStatement.execute();
				lblAlert.setText("Factura emisa cu succes!");
			}
			
			else
			{
				lblAlert.setText("Data emiterii facturii nu poate fi mai  mare decat data scadentei!");
				conn.close();
			}
			
			conn.close();
			
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
