package entities;
import javafx.beans.property.*;

public class Client {
	
	private final IntegerProperty id;

    private final StringProperty nume;

    private final StringProperty cnp;

    private final StringProperty userN;
    
    private final StringProperty pass;
    
    private final StringProperty email;    
    

	public Client(int id, String nume, String cnp, String userN, String pass, String email) {
		super();
		this.id = new SimpleIntegerProperty(id);
		this.nume = new SimpleStringProperty(nume);
		this.cnp = new SimpleStringProperty(cnp);
		this.userN = new SimpleStringProperty(userN);
		this.pass = new SimpleStringProperty(pass);
		this.email = new SimpleStringProperty(email);
	}

	public IntegerProperty getId() {
		return id;
	}

	public StringProperty getNume() {
		return nume;
	}

	public StringProperty getCnp() {
		return cnp;
	}

	public StringProperty getUserN() {
		return userN;
	}

	public StringProperty getPass() {
		return pass;
	}

	public StringProperty getEmail() {
		return email;
	}
    
	 public void setId(int id) {
	        this.id.set(id);
	    }
	 
	 public void setNume(String nume) {
	        this.nume.set(nume);
	    }
	 
	 public void setCnp(String cnp) {
	        this.cnp.set(cnp);
	    }
	 
	 public void setUserN(String userN) {
	        this.userN.set(userN);
	    }
	 
	 public void setPass(String pass) {
	        this.pass.set(pass);
	    }
	 
	 public void setEmail(String email) {
	        this.email.set(email);
	    }
}