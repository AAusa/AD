package secuencial.objetos.ejemploModificado;

import java.io.*;
import java.util.Scanner;

public class TestAgenda {


	public static void main(String[] args){

		String nombreFichero = "D:\\Documentos\\WS-AD\\AD\\FicherosAlvaro\\src\\secuencial\\objetos\\ejemploModificado\\agenda.dat";
		Agenda a = new Agenda();
		try{
			FileOutputStream fs = new FileOutputStream(nombreFichero);//Creamos el archivo
			ObjectOutputStream os = new ObjectOutputStream(fs);//Esta clase tiene el m�todo writeObject() que necesitamos
			Scanner sc = new Scanner(System.in);
			String salir = "";
			while(!salir.equalsIgnoreCase("salir")) {
				String nombre = sc.nextLine();
				String pa = sc.nextLine();				
				String sa = sc.nextLine();
				a.setNombre(nombre);
				a.setP_Apellido(pa);
				a.setS_Apellido(sa);;
				os.writeObject(a);
				salir = sc.nextLine();
			}

			os.close();//Hay que cerrar siempre el archivo

		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}


		//Ahora lo leemos
		try (FileInputStream fis = new FileInputStream(nombreFichero);
			ObjectInputStream ois = new ObjectInputStream(fis)) {
			a = (Agenda) ois.readObject();
			// Mientras haya objetos
			while (a != null) {					
				System.out.println(a);  // Se escribe en pantalla el objeto
				a = (Agenda) ois.readObject();
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
