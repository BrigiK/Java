package entities;
import javafx.beans.property.*;

public class Furnizor {
	
	private IntegerProperty id;

    private final StringProperty nume;

	private final StringProperty adresa;

	 public Furnizor(Integer id, String nume, String adresa) {
			super();
			this.id = new SimpleIntegerProperty(id);
			this.nume = new SimpleStringProperty(nume);
			this.adresa = new SimpleStringProperty(adresa);
		}
	 
	public Integer getId() {
		return id.get();
	}
	
	public IntegerProperty idProperty() {
		return id;
	}
	
	public String getNume() {
		return nume.get();
	}
	
	public StringProperty numeProperty() {
		return nume;
	}
	
	public String getAdresa() {
		return adresa.get();
	}

	public StringProperty adresaProperty() {
		return adresa;
	}
	
	public void setId(Integer id) {
        this.id.set(id);
    }
 
	public void setNume(String nume) {
        	this.nume.set(nume);
	}
 
	public void setAdresa(String adresa) {
		this.adresa.set(adresa);
	}
 
}
