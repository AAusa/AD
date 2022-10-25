package ejercicios.aleatorio.Ej20;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Principal {
	public static void main(String[] args) throws IOException {
		Scanner teclado = new Scanner(System.in);
		Gestion agenda = new Gestion("src\\ejercicios\\aleatorio\\Ej20\\departamento.dat");

		try {
			agenda.abrir();
			agenda.iniciar();
			
			Departamentto d1 = new Departamentto(1, "d1", "Zgz");
			Departamentto d2 = new Departamentto(2, "d2", "Bcna");
			Departamentto d3 = new Departamentto(3, "d3","Mdrd");
			Departamentto d4 = new Departamentto(4, "d4", "Zam");
			
			agenda.escribir(d1);
			agenda.escribir(d2);
			agenda.escribir(d3);
			agenda.escribir(d4);	
		} 
		catch (FileNotFoundException e) {
			System.out.println("Error, fichero no encontrado");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error, de escritura");
			e.printStackTrace();
		}
		System.out.println("Num. registro a modificar: ");
		int num = teclado.nextInt();
		teclado.nextLine(); // Para que no dé error en el siguiente teclado.nextLine();
		/*
		 * verEstadoRegistro(num) indica si se puede modificar o no
		 */
		if(agenda.verEstadoRegistro(num)) {
			agenda.borrar(num);
		}
		else {
			System.out.println("Registro vacío o nulo");
		}	
		System.out.println("getNumDptos: " + agenda.getNumDptos());
		
	}
}
