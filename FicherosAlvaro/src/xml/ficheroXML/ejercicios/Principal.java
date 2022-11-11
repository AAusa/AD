/**
 * 
 */
package xml.ficheroXML.ejercicios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Clase dedicada a pruebas
 * @author alu
 *
 */

public class Principal {

	/**
	 * Método que ejecuta lee y escribe a partir del objeto de la clase Conversion
	 * @param args
	 */
	public static void main(String[] args) {
		Conversion c = new Conversion();
		c.leerTXT();
		c.escribirXML();
		
	}

}
