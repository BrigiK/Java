public class Tricou 
{
	private String cod;
	private String producator;
	private String culoare;
	private double pret;
	private String marime;
	
	public Tricou()
	{
		cod=null;
		producator=null;
		culoare=null;
		pret=0;
		marime=null;

	}
	
	public Tricou(String cod)
	{
		this.cod=cod;
	}
	
	public Tricou(String cod, String producator, String culoare, double pret, String marime)
	{
		this.cod=cod;
		this.producator=producator;
		this.culoare=culoare;
		this.pret=pret;
		this.marime=marime;
	}
	
	@Override
    public int hashCode() {
        return cod.hashCode();
    }
	
	@Override
    public boolean equals(Object other) {
        return hashCode() == other.hashCode();
    }

	@Override
	public String toString() {
		return "Tricou [cod=" + cod + ", producator=" + producator + ", culoare=" + culoare
				+ ", pret=" + pret + ", marime="+ marime + "]";
	}

	public String getMarime() {
		return marime;
	}

	public void setMarime(String marime) {
		this.marime = marime;
	}

	public String getCod() 
	{
		return cod;
	}

	public void setCod(String cod) 
	{
		this.cod = cod;
	}

	public String getProducator() 
	{
		return producator;
	}

	public void setProducator(String producator) 
	{
		this.producator = producator;
	}

	public String getCuloare() 
	{
		return culoare;
	}

	public void setCuloare(String culoare) 
	{
		this.culoare = culoare;
	}

	public double getPret() 
	{
		return pret;
	}

	public void setPret(double pret) 
	{
		this.pret = pret;
	}
	
}

