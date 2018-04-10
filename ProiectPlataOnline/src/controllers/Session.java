package controllers;

import entities.Client;
import entities.Furnizor;

public class Session {
	
	private static Session instance = null;
	private Client currentClient = null;
	private Furnizor currentFurnizor = null;
	
	public static Session getInstance()
	{
		if(instance == null)
			instance = new Session();
		
		return instance;
	}
	
	Client getCurrentClient()
	{
		return currentClient;
	}
	
	void setCurrentClient(Client client)
	{
		currentClient = client;
	}
	
	Furnizor getCurrentFurnizor()
	{
		return currentFurnizor;
	}
	
	void setCurrentFurnizor(Furnizor furnizor)
	{
		currentFurnizor = furnizor;
	}
}
