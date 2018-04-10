package controllers;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

import entities.Factura;
import entities.Furnizor;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;
import main.MySQLConnection;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;

public class ClientPageController implements Initializable, ClientPage {
	
	@FXML
	private TableView<Furnizor> furnizorTable;
	@FXML
	private TableColumn<Furnizor, Integer> idCol;
	@FXML
	private TableColumn<Furnizor, String> nameCol;
	@FXML
	private TableColumn<Furnizor, String> addressCol;
	@FXML
	private Button loadbutton;
	@FXML
	private ListView<Integer> listView;
	@FXML
	private Label lblName;
	@FXML
	private Label suma;
	@FXML
	private Label lblAlert;
	@FXML
	private TableView<Factura> facturaTable;
	@FXML
	private TableColumn<Factura, String> FnameCol;
	@FXML
	private TableColumn<Factura, Double> valCol;
	@FXML
	private TableColumn<Factura, Date> dateCol;
	@FXML
	private TableColumn<Factura, Date> scadCol;
	@FXML
	private TableColumn<Factura, String> platitCol;
	
	
	public void getBani(String bani)
	{
		suma.setText(bani);
	}

	private ObservableList<Furnizor> furnizor;
	private ObservableList<Factura> factura;
	
	ObservableList<Integer> selectedIndexesFurnizor = FXCollections.observableArrayList();
	ObservableList<Integer> selectedIndexesFactura = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		lblName.setText(Session.getInstance().getCurrentClient().getNume().get());

		sumaDinCont();
		
		String idC=Integer.toString(Session.getInstance().getCurrentClient().getId().get());
		
			String sql ="SELECT numeFurnizor,data,scadenta,valoarea,platit FROM factura fct, client c WHERE c.idClient = '" 
						+idC+"'"+"AND fct.idClient ='"+idC+"'";
			
			try {			
				Connection conn=MySQLConnection.getConnection();
				this.factura = FXCollections.observableArrayList();
				
				ResultSet rs = conn.createStatement().executeQuery(sql);
				while(rs.next())
				{
					this.factura.add(new Factura(0,0,0,rs.getString("numeFurnizor"),rs.getDate("data"),rs.getDate("scadenta"),rs.getDouble("valoarea"),rs.getString("platit")));
				}
				conn.close();
				
			} catch (SQLException e) {
				System.err.println("Error " + e);
			} catch (Exception e) {
				System.err.println("Error " + e);
			}
			
			this.FnameCol.setCellValueFactory(new PropertyValueFactory<Factura, String>("numeFurnizor"));
			this.valCol.setCellValueFactory(new PropertyValueFactory<Factura, Double>("valoare"));
			this.dateCol.setCellValueFactory(new PropertyValueFactory<Factura, Date>("data"));
			this.scadCol.setCellValueFactory(new PropertyValueFactory<Factura, Date>("scadenta"));
			this.platitCol.setCellValueFactory(new PropertyValueFactory<Factura, String>("platit"));
			
			this.facturaTable.setItems(null);
			this.facturaTable.setItems(this.factura);
			
			loadFurnizori();
	}
	
	@FXML
	public void addFurnizor(ActionEvent event)
	{  
		try{
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/layout/AddFurnizori.fxml").openStream());
		Scene scene = new Scene(root);
		//primaryStage.setTitle("az");
		scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.showAndWait();
		loadFurnizori();
		} catch( Exception e)
		{
		}
	}
	
	@FXML
	public void loadFurnizori()
	{
		String sql = "SELECT f.idFurnizor,numeFurnizor,adresaFurnizor FROM furnizor f, client_furnizor cf, client c WHERE f.idFurnizor=cf.idFurnizor AND "
				+ "cf.idClient = c.idClient AND c.idClient = " + Session.getInstance().getCurrentClient().getId().get();
		try {			
			Connection conn=MySQLConnection.getConnection();
			this.furnizor = FXCollections.observableArrayList();
			
			ResultSet rs = conn.createStatement().executeQuery(sql);
			while(rs.next())
			{
				this.furnizor.add(new Furnizor(rs.getInt("f.idFurnizor"),rs.getString("numeFurnizor"),rs.getString("adresaFurnizor")));
			}
			conn.close();
			
		} catch (SQLException e) {
			System.err.println("Error " + e);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.nameCol.setCellValueFactory(new PropertyValueFactory<Furnizor, String>("nume"));
		this.addressCol.setCellValueFactory(new PropertyValueFactory<Furnizor, String>("adresa"));
		
		this.furnizorTable.setItems(null);
		this.furnizorTable.setItems(this.furnizor);
	}
	
	public void addMoney(ActionEvent event)
	{  
		try{
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/layout/AddMoney.fxml").openStream());
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.showAndWait();
		
		sumaDinCont();
		
		} catch( Exception e)
		{}
	}
	
	public void sumaDinCont()
	{
		String sql="SELECT bani FROM client WHERE userNameClient='"+Session.getInstance().getCurrentClient().getUserN().get()+"'";
		
		try{
			Connection conn=MySQLConnection.getConnection();
			ResultSet rs = conn.createStatement().executeQuery(sql);
			
			while(rs.next())
				suma.setText(Integer.toString(rs.getInt(1)));
			
			conn.close();
			
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public void plataFacturi(ActionEvent event) throws Exception
	{
		if(!facturaTable.getSelectionModel().getSelectedItems().isEmpty())
		{			
			Connection conn = null;
			try {
				conn = MySQLConnection.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement( "UPDATE client SET bani = ? WHERE idClient ='" + Session.getInstance().getCurrentClient().getId().get()+"'");
				PreparedStatement preparedStatement2 = conn.prepareStatement( "UPDATE factura SET platit = ? WHERE idClient ='" + Session.getInstance().getCurrentClient().getId().get()+"'");
				for(Factura factura : facturaTable.getSelectionModel().getSelectedItems())
				{
					if(factura.getPlatit().compareTo("da")==0)
						lblAlert.setText("Factura este deja platita!");
					else{
						if(Integer.parseInt(suma.getText())>=factura.getValoare())
						{
						double sumaNoua=Integer.parseInt(suma.getText())-factura.getValoare();
						preparedStatement.setDouble(1, sumaNoua);
						preparedStatement.executeUpdate();
						lblAlert.setText("Factura a fost platita cu succes!");
						
						preparedStatement2.setString(1,"da");
						preparedStatement2.executeUpdate();	
						// platitCol.
						factura.setPlatit("da");
						
						}
						else
							lblAlert.setText("Nu aveti destui bani in cont!");
					}
				}
				sumaDinCont();
			} catch (SQLException e2) {
				e2.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally
			{
				if(conn != null)
					conn.close();
			}
		}
	}
	
	public void removeFurnizori(ActionEvent e)  {

		if(furnizorTable.getSelectionModel().getSelectedItem() != null)
		{			
			Connection conn = null;
			try {
				conn = MySQLConnection.getConnection();

				Furnizor furnizor = furnizorTable.getSelectionModel().getSelectedItem();

				int idClient = Session.getInstance().getCurrentClient().getId().get();
				// selectam toate facturile neplatite
				String queryPlatit= "SELECT platit FROM factura WHERE idFurnizor ='"+furnizor.getId()+"' AND idClient='"+idClient+"' AND platit='nu'";
				
				Statement st=conn.createStatement();
				ResultSet resultSet = st.executeQuery(queryPlatit);
				
				// daca exista facturi neplatite, nu putem dezabona de la furnizor
				if(resultSet.next())
				{
					lblAlert.setText("Nu aveti achitate toate facturile de la acest furnizor!");
				}	
				else
				{
					String query = "DELETE FROM client_furnizor WHERE idClient = ? AND idFurnizor = ?";
					PreparedStatement preparedStatement = conn.prepareStatement(query);
					preparedStatement.setInt(1, idClient);
					preparedStatement.setInt(2, furnizor.getId());
					preparedStatement.execute();
					lblAlert.setText("Dezabonare efectuata cu succes!");
					loadFurnizori();
				}
					
				conn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	}
	
	public void signOut(ActionEvent event)
	{
		try {
		Session.getInstance().setCurrentClient(null);
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