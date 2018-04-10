import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Magazin implements CumparareVanzareTricou {
	
	private List<Tricou> stocTricouri;
	
	public Magazin(Collection<Tricou> stocTricouriInitial)
	{
		stocTricouri = new ArrayList<>();
		for (Tricou tricou : stocTricouriInitial)
			stocTricouri.add(tricou);
	}

	@Override
	public void cumparare(Tricou tricou, int cantitate) {
		
		for(int i=0;i<cantitate;i++)
			stocTricouri.add(tricou);
	}
	
	@Override
	public void vanzare(Tricou tricou, int cantitate) {
		
		while(cantitate > 0)
		{
			if(stocTricouri.remove(tricou) == true)
				cantitate--;
			else
				break;
		}

	}
	
	public void afisareStoc()
	{
		for(Tricou tricou : stocTricouri)
			System.out.println(tricou);
	}

}
