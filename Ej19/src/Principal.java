import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Principal {
	public static void main(String[] args) throws IOException {
		Scanner teclado = new Scanner(System.in);
		Gestion agenda = new Gestion("src\\departamento.dat");

		try {
			agenda.abrir();
			agenda.iniciar();
			
			Departamento d1 = new Departamento(1, "d1", "Zgz");
			Departamento d2 = new Departamento(2, "d2", "Bcna");
			Departamento d3 = new Departamento(3, "d3","Mdrd");
			Departamento d4 = new Departamento(4, "d4", "Zam");
			
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
		
		 //verEstadoRegistro(num) indica si se puede modificar o no
		 
		if(agenda.verEstadoRegistro(num)) {
			System.out.println("Nombre nuevo: ");
			String nombre = teclado.nextLine();
			System.out.println("Localidad nueva: ");
			String localidad = teclado.nextLine();
			Departamento dpto = new Departamento(num, nombre, localidad);
			agenda.modificar(dpto);
		}
		else {
			System.out.println("Registro vacío o nulo");
		}	
		
	}
}
