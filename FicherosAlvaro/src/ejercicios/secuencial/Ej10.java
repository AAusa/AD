package ejercicios.secuencial;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;


public class Ej10 {
/*
 * Funciona pero lo dice con caracteres repes
 */
	public static void main(String[] args) {
		File mifichero = new File ("src\\ejercicios\\secuencial\\Ej10.txt");
		System.out.println("Lectura fichero:");
		//ArrayList guarda char y Collections.frequency(list, "a");
		ArrayList<Character> caracteres = new ArrayList<Character>();
		/*
		try (FileReader lector = new FileReader(mifichero)){		
			char caracter = (char) lector.read();
			while (caracter > 0) {
				//caracteres.add(caracter);
				System.out.println(caracter);
			}
			lector.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
		try {
			FileReader lector = new FileReader(mifichero);
			int caracter = lector.read();
			while (caracter > 0) {
				caracteres.add((char)caracter);
				System.out.print((char)caracter);
				caracter = lector.read();
			}
			lector.close();
	
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (Character character : caracteres) {
			System.out.println(character + " " + Collections.frequency(caracteres, character));
		}
	}

}
