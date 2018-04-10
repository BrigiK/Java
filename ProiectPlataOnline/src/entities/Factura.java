package entities;
import java.time.DateTimeException;
import java.util.Date;
import java.util.GregorianCalendar;

import javafx.beans.property.*;
import javafx.beans.value.WritableIntegerValue;

public class Factura {
	
	private IntegerProperty idFactura;

    private final IntegerProperty idClient;
    
    private final IntegerProperty idFurnizor;
    
    private final StringProperty numeFurnizor;
    
    private Date data;
    
    private Date scadenta;

	private final DoubleProperty valoare;
	
	private final StringProperty platit;
	
	public Factura(Integer idFactura, Integer idClient, Integer idFurnizor, String numeFurnizor, Date data,
			Date scadenta, Double valoare, String platit) {
		super();
		this.idFactura = new SimpleIntegerProperty(idFactura);
		this.idClient = new SimpleIntegerProperty(idClient);
		this.idFurnizor = new SimpleIntegerProperty(idFurnizor);
		this.numeFurnizor=new SimpleStringProperty(numeFurnizor);
		this.data = data;
		this.scadenta = scadenta;
		this.valoare = new SimpleDoubleProperty(valoare);
		this.platit = new SimpleStringProperty(platit);
	}
	/*public Factura(String numeFurnizor, Date data,Date scadenta, Double valoare, String platit) {
		super();
		this.numeFurnizor=new SimpleStringProperty(numeFurnizor);
		this.data = data;
		this.scadenta = scadenta;
		this.valoare = new SimpleDoubleProperty(valoare);
		this.platit = new SimpleStringProperty(platit);
	}*/
	 
	public Integer getIdFactura() {
		return idFactura.get();
	}
	
	public IntegerProperty idFacturaProperty() {
		return idFactura;
	}
	
	public Integer getIdClient() {
		return idClient.get();
	}
	
	public IntegerProperty idClientProperty() {
		return idClient;
	}
	
	public Integer getIdFurnizor() {
		return idFurnizor.get();
	}
	
	public IntegerProperty idFurnizorProperty() {
		return idFurnizor;
	}
	
	public Date getData() {
		return data;
	}
	
	public Date getScadenta() {
		return scadenta;
	}
	
	public Double getValoare() {
		return valoare.get();
	}
	
	public DoubleProperty valoareProperty() {
		return valoare;
	}
	
	public String getPlatit() {
		return platit.get();
	}

	public StringProperty platitProperty() {
		return platit;
	}
	
	public String getNumeFurnizor() {
		return numeFurnizor.get();
	}

	public StringProperty numeFurnizorProperty() {
		return numeFurnizor;
	}
	
	public void setIdFactura(Integer idFactura) {
        this.idFactura.set(idFactura);
    }
	
	public void setIdClient(Integer idClient) {
        this.idClient.set(idClient);
    }
	
	public void setIdFurnizor(Integer idFurnizor) {
        this.idFurnizor.set(idFurnizor);
    }
 
	public void setData(Date data) {
        	 this.data=data;
	}
	
	public void setScadenta(Date scadenta) {
   	 this.scadenta=scadenta;
	}
	
	public void setValoare(Double valoare) {
		this.valoare.set(valoare);
	}
 
	public void setPlatit(String platit) {
		this.platit.set(platit);
	}
	
	public void setNumeFurnizor(String numeFurnizor) {
		this.numeFurnizor.set(numeFurnizor);
	}
 
}
