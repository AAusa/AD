package ejercicios.aleatorio.Ej18Ej19;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/*
 * FALTA ORDENAR
 */
public class Principal {
	public static void main(String[] args) throws IOException {
		Scanner teclado = new Scanner(System.in);
		Gestion agenda = new Gestion("src\\ejercicios\\aleatorio\\Ej18\\departamento.dat");
		
				
		// guarda las personas en el fichero secuencial
		// la primera persona estar� en la posici�n 1, la segunda en la posici�n 2
		// y asi sucesivamente....
		try {
			agenda.abrir();
			agenda.iniciar();
			
			// escribir las personas
			Departamento d1 = new Departamento(2, "d1", "Zgz");
			Departamento d2 = new Departamento(43, "d2", "Bcna");
			Departamento d3 = new Departamento(1, "d3","Mdrd");
			Departamento d4 = new Departamento(3, "d4", "Zam");
			
			agenda.escribir(d1);
			agenda.escribir(d2);
			agenda.escribir(d3);
			agenda.escribir(d4);
				
			
		} // COMPLETA CON LOS CATCH QUE HAGAN FALTA  
		catch (FileNotFoundException e) {
			System.out.println("Error, fichero no econtrado");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error, de escritura");
			e.printStackTrace();
		}
		System.out.println("Num. registro a modificar: ");
		int num = teclado.nextInt();
		teclado.nextLine();
		if(agenda.verEstadoRegistro(num)) {
			System.out.println("Nombre: ");
			String nombre = teclado.nextLine();
			System.out.println("Localidad: ");
			String localidad = teclado.nextLine();
			Departamento dpto = new Departamento(num, nombre, localidad);
			agenda.modificar(dpto);
		}
		else {
			System.out.println("Registro vacío");
		}

		// leer las personas
		/*
		try {
			System.out.println(agenda.leer(1));
			System.out.println(agenda.leer(2));
			System.out.println(agenda.leer(3));
			System.out.println(agenda.leer(4));
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		*/
		
		
	}
}
