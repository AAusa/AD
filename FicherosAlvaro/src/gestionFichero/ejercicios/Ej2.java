package gestionFichero.ejercicios;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Ej2 {
	public static void main(String[] args) throws IOException { 
		String directorio, extension = ".", nombreFich;
		Scanner teclado = new Scanner(System.in);
		System.out.print("Directorio: ");
		directorio = teclado.nextLine();
		System.out.print("Extension:");
		extension += teclado.nextLine();
		File miDir = new File (directorio);
		String [] files = miDir.list();
		for(int i = 0; i < files.length; i++) {
			nombreFich = files[i];
			if(nombreFich.contains(extension)) {
				System.out.println(nombreFich);
			}
		}
	}
}
