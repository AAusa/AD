package gestionFichero.Ej4;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Ej4 {
	public static void main(String[] args) {
		List<String> frases = new ArrayList<String>();
		Scanner teclado = new Scanner(System.in);
		boolean terminar = false;
		String frase = "";
		while(!terminar) {
			System.out.print("Frase: ");
			frase = teclado.nextLine();
			if(frase.equals("fin")) {
				File mifichero = new File("ficheroEj4.txt");

				try {
					FileWriter escritor=new FileWriter(mifichero); // Sin true no concatena
					BufferedWriter cestaEscritura=new BufferedWriter(escritor);
					for(String f : frases) {
						cestaEscritura.write(frase);
					}
					cestaEscritura.close();  
					escritor.close();
				}
				catch (IOException ex) {
					ex.printStackTrace();
					System.out.println("No se encontro el archivo");
				}
				terminar = true;
			}
			else {
				frases.add(frase);
			}
		}
	}
}