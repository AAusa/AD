package ejercicios.secuencial.objetos;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import ejemplos.secuencial.objetos.Agenda;

public class Ej15 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String nombreFichero = "empleados.txt";
		ArrayList<Empleado> empleados = new ArrayList();
		try (FileOutputStream fs = new FileOutputStream(nombreFichero);
			ObjectOutputStream os = new ObjectOutputStream(fs)) {
			String salir = "";
			while(!salir.equalsIgnoreCase("s")) {
				System.out.print("Nombre: ");
				String nombre = sc.nextLine();
				System.out.print("Sueldo: ");
				double sueldo = sc.nextDouble();		
				System.out.print("Año nacimiento: ");
				int anno = sc.nextInt();
				System.out.print("Antiguedad: ");
				int antiguedad = sc.nextInt();
				empleados.add(new Empleado(nombre, sueldo, anno, antiguedad));
				System.out.print("s para salir: ");
				sc.nextLine(); //Para que coja un salto de línea aparte y permita introducir string despues
				salir = sc.nextLine();	
			}
			for(Empleado e : empleados) {
				os.writeObject(e);
			}
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		
		System.out.print("Indique sueldo minimo: ");
		double sueldoMin = sc.nextDouble();

		Empleado empleado = new Empleado();
		try (FileInputStream fis = new FileInputStream(nombreFichero);
				ObjectInputStream ois = new ObjectInputStream(fis)) {
			empleado = (Empleado) ois.readObject();
			// Mientras haya objetos
			while (empleado != null) {	
				if(empleado.getSueldo() >= sueldoMin) {
					System.out.println(empleado);
					empleado = (Empleado) ois.readObject();
				}
				
			}
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(EOFException e){
			System.out.println("Fin de archivo");
		}catch(IOException e){
			e.printStackTrace();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}

}
