package ejemplos.secuencial;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
/*
 * EJ1:
 * 		FileReader
 * 		BufferedReader
 */
public class Ej1 {
	public static void main(String[] args) throws IOException { 
		//Leo el fichero info.jpg
		//Leo el fichero datos.txt
		File mifichero = new File ("datos.txt");
		FileReader lector = new FileReader(mifichero);
		BufferedReader cestaLectora = new BufferedReader(lector);

		//escribir en el fichero tarjetas.txt
		File mificherodestino = new File("tarjetas.txt");
		FileWriter escritor = new FileWriter(mificherodestino,true); 
		BufferedWriter cestaEscritura = new BufferedWriter(escritor);

		String lineaTarjeta = "";
		try{
			int contador = 1;
			String lineaDatos = cestaLectora.readLine(); 
			while(lineaDatos != null) {                

				for(int i = 0; i <= lineaDatos.length()-1; i++) {
					if (i == 0) {
						lineaTarjeta = "Nombre: ";
					}
					if(lineaDatos.charAt(i) == ',' || i == lineaDatos.length()-1) {
						if(i == lineaDatos.length()-1) {
							lineaTarjeta += lineaDatos.charAt(i);	
						}
						cestaEscritura.write(lineaTarjeta);
						lineaTarjeta = "";
						switch(contador) {
						case 1:
							lineaTarjeta = "Apellidos: ";
							cestaEscritura.newLine(); //Salto de línea
							break;
						case 2:
							break;
						case 3:
							lineaTarjeta = "Fecha de nacimiento: ";
							cestaEscritura.newLine(); //Salto de línea
							break;
						case 4:
							lineaTarjeta = "Teléfono: ";
							contador = -1;
							cestaEscritura.newLine(); //Salto de línea
							break;
						}
						contador++;

					}	
					if(lineaDatos.charAt(i) != ',' && contador != 3) {
						lineaTarjeta += lineaDatos.charAt(i);	
					}
				}
				lineaDatos = cestaLectora.readLine(); 
				cestaEscritura.newLine();
				cestaEscritura.newLine();
			}   

			// COMO PUEDO MEJORAR ESTE BUCLE?!!!!

		} catch(IOException e) {
			System.out.println("Mensaje: " + e.getMessage());
			e.printStackTrace();

		} finally {
			cestaLectora.close();
			lector.close();
			cestaEscritura.close();
			escritor.close();
		}

		System.out.println("TERMINÓ!!!");

	}
}

