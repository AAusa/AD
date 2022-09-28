package gestionFichero.Ej4;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * Escribe un programa Java que pida una serie de frases por teclado hasta que se inserte
 * como frase la palabra “fin”.
 * Dichas frases deberán guardarse en un fichero de texto.
 * A continuación, el programa visualizará el contenido del fichero, frase por frase.
 * Cada vez que se ejecute el programa, se tienen que descartar las frases que ya estaban
 * escritas en el fichero. 
 * Realiza este ejercicio sin usar la clase BufferedReader.
 */

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
					//fichero
					FileWriter escritor=new FileWriter(mifichero); // Sin true no concatena
					BufferedWriter cestaEscritura=new BufferedWriter(escritor);
					for(int i = 0; i < frases.size(); i++) {
						cestaEscritura.write(frases.get(i));
						cestaEscritura.newLine();
					}
					cestaEscritura.close();  
					escritor.close();
				}
				catch (IOException ex) {
					ex.printStackTrace();
					System.out.println("No se encontro el archivo");
				}
				//fichero
				terminar = true;
				
			}
			else {
				frases.add(frase);
			}
		}
		//lectura pantalla
		File mifichero = new File ("ficheroEj4.txt");
		try {
			FileReader lector = new FileReader(mifichero);
			int caracter = lector.read();
			while (caracter > 0) {
				System.out.print((char)caracter);
				caracter = lector.read();
			}
			lector.close();
	
		} catch (IOException e) {
			e.printStackTrace();
		}
		//lectura pantalla
	}
}