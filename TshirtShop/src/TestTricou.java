import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Scanner;

public class TestTricou {

	public static void main(String[] args) throws FileNotFoundException {
		
		HashSet<Tricou> tricouri=new HashSet<>();
		
		Scanner scan=new Scanner(new File("stoc.txt"));
		
		while(scan.hasNext())
		{
			// read values from file and set them
			Tricou tricou=new Tricou();
			tricou.setCod(scan.next());
			tricou.setProducator(scan.next());
			tricou.setCuloare(scan.next());
			tricou.setPret(scan.nextDouble());
			tricou.setMarime(scan.next());
			
			// add to list
			if(tricouri.add(tricou)== false)
			{
				System.out.println("Stocul nu contine doar elemente unice.");
				return;
			}			
		}
		
		Magazin magazin = new Magazin(tricouri);
		magazin.cumparare(new Tricou("x15a", "Deichmann", "rosu", 10.99, "M"), 3);
		magazin.vanzare(new Tricou("x15a"), 2);
		
		magazin.afisareStoc();
				
		// rapoarte
		int a=10;
		 PrintStream out = new PrintStream(new FileOutputStream("OutFile.txt"));
		 out.println(a);
	}

}
