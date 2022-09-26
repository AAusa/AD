package gestionFichero.Ej4;

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
			if(frase != "fin") {
				frases.add(frase);
			}
			else {
				terminar = true;
			}
		}
	}
}
