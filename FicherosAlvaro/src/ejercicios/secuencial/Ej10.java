package ejercicios.secuencial;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


public class Ej10 {

	public static void main(String[] args) {
		File mifichero = new File ("src\\ejercicios\\secuencial\\Ej10.txt");
		System.out.println("Lectura fichero:");
		//ArrayList guarda char y Collections.frequency(list, "a");
		ArrayList<Character> caracteres = new ArrayList<Character>();
		try (FileReader lector = new FileReader(mifichero)){		
			int caracter = lector.read();
			while (caracter > 0) {
				caracteres.add((char)caracter);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (Character character : caracteres) {
			System.out.println(character + " " + Collections.frequency(caracteres, character));
		}
	}

}
