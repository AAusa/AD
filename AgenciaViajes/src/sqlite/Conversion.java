/**
 * 
 */
package sqlite;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLNonTransientException;
import java.sql.SQLRecoverableException;
import java.sql.SQLTransientException;
import java.util.ArrayList;

/**
 * @author alu
 *
 */
public class Conversion {
	int id = 0;
	private ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	
	/**
	 * Metodo lee txt y escribe objeto en array clientes
	 * @throws IOException 
	 */
	public void leerTXT() throws IOException {
		int vip = 0, id = 10;
		String nombre= "", apellidos = "", tfno= "", direccion= "", correoElectronico= "";
		File mifichero = new File ("bd\\VacacionesForyou.txt");
		try {
			FileReader lector = new FileReader(mifichero);	
			BufferedReader cestaTemporal = new BufferedReader(lector);
			String linea = cestaTemporal.readLine();
			while (linea != null) {
				//System.out.println(linea);String nombre, String apellidos, String tfno, String direccion, String correoElectronico, int vip
				String[] atributos = linea.split(";");
				nombre = atributos[0]; 
				apellidos = atributos[1];
				tfno = atributos[2]; 
				direccion = atributos[3]; 
				correoElectronico = atributos[4]; 				
				vip = atributos[5].equals("VIP") ? 1 : 0; 
				clientes.add(new Cliente(id++, nombre, apellidos, tfno, direccion, correoElectronico, vip));
				linea = cestaTemporal.readLine();
			}
			cestaTemporal.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo lee array clientes y escribe en bd
	 */
	public void escribirBD() {
		try {
        	// CARGAR EL CONTROLADOR JDBC de la base de datos
        	Class.forName("org.sqlite.JDBC");
        	
        	// ESTABLECER LA CONEXI�N con la base de datos
            Connection conexion = DriverManager.getConnection("jdbc:sqlite:bd\\VacacionesForyou.db");

            for (Cliente cliente : clientes) {
                String sql = "INSERT INTO Clientes VALUES (?,?,?,?,?,?,?)";
                PreparedStatement pstm = conexion.prepareStatement(sql);
                pstm.setInt(1, cliente.getId());
                pstm.setString(2, cliente.getNombre());
                pstm.setString(3, cliente.getApellidos());
                pstm.setString(4, cliente.getTfno());
                pstm.setString(5, cliente.getDireccion());
                pstm.setString(6, cliente.getCorreoElectronico());
                pstm.setInt(7, cliente.getVip());
                
                int cantidad = pstm.executeUpdate();
                System.out.println("se ha insertado " + cantidad + " clientes");
			}
			
            
            conexion.close();
            
        } catch (ClassNotFoundException ex) {
            System.out.println("Error en la conexi�n de la base de datos");
            ex.printStackTrace();
        } catch(SQLNonTransientException e) {
			System.out.println("Sentencia correcta pero repetida");
			e.printStackTrace();
		} catch(SQLTransientException es) {
			System.out.println("Sentencia correcta pero transitoria");
			es.printStackTrace();
		} catch(SQLRecoverableException es) {
			System.out.println("Error de reestablecimiento de conexion");
			es.printStackTrace();
		} catch (SQLException ex) {
			System.out.println("Error en la conexi�n de la base de datos");
			ex.printStackTrace();
		}
	}

}
