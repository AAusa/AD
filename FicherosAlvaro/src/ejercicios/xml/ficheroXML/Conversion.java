package ejercicios.xml.ficheroXML;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Conversion {
	/*
	 * Metodo lee txt y escribe objeto en array
	 */
	ArrayList<Persona> personas = new ArrayList<Persona>();
	public void leerTxt() {
		File mifichero = new File ("datos.txt");
		try {
			FileReader lector = new FileReader(mifichero);	
			BufferedReader cestaTemporal = new BufferedReader(lector);
			
			String linea = cestaTemporal.readLine();
			while (linea != null) {
				System.out.println(linea);
				String[] atributos = linea.split("-");
				String nombre = atributos[0]; 
				String apellidos = atributos[1]; 
				double altura = Double.parseDouble(atributos[2]); 
				String fechaNacimiento = atributos[3]; 
				String tfno = atributos[4]; 
				personas.add(new Persona(nombre, apellidos, tfno, altura, conseguirEdad(fechaNacimiento)));
				linea = cestaTemporal.readLine();
			}
			
			cestaTemporal.close();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*
	 * Metodo crea una edad a partir de fechaNacimiento
	 */
	public int conseguirEdad(String fechaNacimiento) {		
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate fechaNac = LocalDate.parse(fechaNacimiento, fmt);
		LocalDate ahora = LocalDate.now();
		Period periodo = Period.between(fechaNac, ahora);
		return periodo.getYears();
	}

	/*
	 * Metodo lee objeto de array y escribe xml
	 */
	
}
