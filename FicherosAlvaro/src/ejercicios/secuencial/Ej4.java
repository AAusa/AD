package ejercicios.secuencial;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

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
		ArrayList<String> frases = new ArrayList<String>();
		Ej4 ej4 = new Ej4();
		Scanner teclado = new Scanner(System.in);
		boolean terminar = false;
		String frase = "";
		// Mientras no se introduzca "fin" se piden frases
		while(!terminar) {
			System.out.print("Frase: ");
			frase = teclado.nextLine();
			if(frase.equalsIgnoreCase("fin")) {
				ej4.eliminarRepetidos(frases);
				ej4.escribirFichero(frases);
				terminar = true;
			}
			else {
				frases.add(frase);
			}
		}
		ej4.leerFichero();
	}
	
	/*
	 * Asigna el ArrayList<String> frases a un HashSet
	 * Elimina el contenido de ArrayList<String> frases
	 * Añade el contenido del hashset al ArrayList<String> frases
	 * @param ArrayList<String> frases 
	 */
	public ArrayList eliminarRepetidos(ArrayList<String> frases) {
		Set<String> frasesNoRepetidas = new HashSet<String>(frases); 
		frases.clear();
		frases.addAll(frasesNoRepetidas);
		return frases;

	}

	/*
	 * Escribe el ArrayList<String> frases en el fichero
	 * @param ArrayList<String> frases
	 */
	public void escribirFichero(ArrayList<String> frases) {
		try {
			File mifichero = new File("ficheroEj4.txt");
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
	}
	
	/*
	 * Lee el fichero y lo imprime por pantalla
	 */
	
	public void leerFichero() {
		File mifichero = new File ("ficheroEj4.txt");
		System.out.println("\nLectura fichero:");
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
	}
}