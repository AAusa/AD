/**
 * 
 */
package sqlite;

import java.io.IOException;

/**
 * @author alu
 *
 */
public class Principal {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Conversion c = new Conversion();
		try {
			c.leerTXT();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c.escribirBD();
	}

}
