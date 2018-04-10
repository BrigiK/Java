package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

public interface ClientPage {
		
		public void initialize(URL location, ResourceBundle resources);
		public void addFurnizor(ActionEvent event);
		public void loadFurnizori();
		public void addMoney(ActionEvent event);
		public void sumaDinCont();
		public void plataFacturi(ActionEvent event) throws Exception;
		public void signOut(ActionEvent event);
}


