package gestionFichero;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Ej3 {
	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		String fichero = "";
		System.out.print("Fichero: ");
		fichero = teclado.nextLine(); //D:\Descargas\notepad.txt
		try {
			FileReader lector = new FileReader(fichero);
			BufferedReader cestaTemporal = new BufferedReader(lector);		
			String linea = cestaTemporal.readLine();
			while (linea != null) {
				System.out.println(linea);
				linea = cestaTemporal.readLine();
			}	
			cestaTemporal.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
}
