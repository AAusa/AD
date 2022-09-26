package gestionFichero.Ej1;

import java.io.File;
import java.io.IOException;

public class Ej1 {
	public static void main(String[] args) throws IOException { 
		File miFichero = new File ("existe.txt");
		File miFichero2 = new File ("noExiste.txt");
		File miDirectorio = new File ("existe");
		File miDirectorio2 = new File ("noExiste");
		System.out.println(miFichero.exists());
		System.out.println(miFichero2.exists());
		System.out.println(miDirectorio.exists());
		System.out.println(miDirectorio2.exists());
		
		if(miFichero.isDirectory()) {
			System.out.println("Directorio");
		}
		else if(miFichero.isFile()) {
			System.out.println("Fichero: " + miFichero.getName());
		}	
		
		if(miDirectorio.isDirectory()) {
			System.out.println("Directorio: " + miDirectorio.getName());
		}
		else if(miFichero.isFile()) {
			System.out.println("Fichero");
		}
		
	}		
}
