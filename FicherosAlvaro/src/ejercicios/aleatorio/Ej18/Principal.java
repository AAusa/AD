package ejercicios.aleatorio.Ej18;

import java.io.FileNotFoundException;
import java.io.IOException;

/*
 * FALTA ORDENAR
 */
public class Principal {
	public static void main(String[] args) {
		
		Gestion agenda = new Gestion("src\\ejercicios\\aleatorio\\Ej18\\departamento.dat");
		
				
		//  guarda las personas en el fichero secuencial
		// la primera persona estar� en la posici�n 1, la segunda en la posici�n 2
		// y asi sucesivamente....
		try {
			agenda.abrir();
			agenda.iniciar();
			
			// escribir las personas
			Departamento d1 = new Departamento(1, "d1", "Zgz");
			Departamento d2 = new Departamento(2, "d2", "Bcna");
			Departamento d3 = new Departamento(3, "d3","Mdrd");
			Departamento d4 = new Departamento(4, "d4", "Zam");
			
			agenda.escribir(d1,1);
			agenda.escribir(d2,2);
			agenda.escribir(d3,3);
			agenda.escribir(d4,4);
				
			
		} // COMPLETA CON LOS CATCH QUE HAGAN FALTA  
		catch (FileNotFoundException e) {
			System.out.println("Error, fichero no econtrado");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error, de escritura");
			e.printStackTrace();
		}
		
		// leer las personas
		try {
			System.out.println(agenda.leer(1));
			System.out.println(agenda.leer(2));
			System.out.println(agenda.leer(3));
			System.out.println(agenda.leer(4));
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
}
