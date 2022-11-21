package sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
/**
 * Realiza un programa Java que utilice el driver o conector JDBC con la base de datos biblioteca,
creada en SQLite o MySQL, y que modifique un socio existente de la tabla SOCIO. Los datos
del socio a modificar son el domicilio y el teléfono y se introducirán por teclado. En el caso de
que el socio a modificar no exista en la tabla SOCIO, se deberá mostrar un mensaje por
pantalla.
 * @author Alvaro
 */
public class Ej9 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int cantidad = 0;
		try {
			//Conexion con la BBDD:
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			Connection conexion = DriverManager.getConnection("jdbc:hsqldb:bd\\biblioteca", "miusuario", "Pass!123456");
			Statement sentencia = (Statement) conexion.createStatement();
			
			//Sentencia SQL para mostrar los codigos y nombres de los socios:
            ResultSet resultado = sentencia.executeQuery("select Codigo, Nombre from Socio;");
            
            while (resultado.next()) {
            	System.out.println("Codigo: " + resultado.getString(1) + "\tNombre: " + resultado.getString(2));
            }
            
            //Preparacion de sentencia que actualiza el registro:
			System.out.println("Introduce idSocio a modificar: ");
			int idSocio = sc.nextInt(); sc.nextLine();
			
			//Comprobacion de que existe el socio indicado:
			resultado = sentencia.executeQuery("SELECT * FROM Socio WHERE Codigo="+idSocio+";");
            if (resultado.next()) {
            	//Pedida de datos y actualizacion del registro:
            	System.out.println("Introduce el nuevo domicilio: ");
    			String domicilio = sc.nextLine();
    			System.out.println("Introduce el nuevo tfno: ");
    			String tfno = sc.nextLine();
    			String sql = "update socio set Domicilio = ?, Telefono = ? where Codigo = ?;";
    			
    			PreparedStatement pstm = conexion.prepareStatement(sql);
    			pstm.setString(1, domicilio);
    			pstm.setString(2, tfno);
    			pstm.setInt(3, idSocio);
    			
    			cantidad = pstm.executeUpdate();
    			System.out.println("se ha modificado " + cantidad + " socio correctamente");
            }
            else {
            	System.out.println("El socio no existe");
            }
			resultado.close();
			sentencia.close();
			conexion.setAutoCommit(false);//Para que no se haga el autocommit
			conexion.commit();
			conexion.close();
			

		} catch (ClassNotFoundException ex) {
			System.out.println("No se encuentra la tabla Socio de la BBDD");
			ex.printStackTrace();
		} catch (SQLException ex) {
			System.out.println("Error al rellenar los datos");
			ex.printStackTrace();
		}
	}
}