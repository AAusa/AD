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
public class Ej8 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int cantidad = 0;
		int idSocio = 0;
		try {
			//Conexion con la BBDD:
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://192.168.56.104:3306/biblioteca","miusuario","Pass!123456");
			Statement sentencia = (Statement) conexion.createStatement();

			//Sentencia SQL para mostrar los codigos y nombres de los socios:
			ResultSet resultado = sentencia.executeQuery("SELECT MAX(Codigo) FROM libro");

			if (resultado.next()) {
				idSocio = resultado.getInt(1)+1;
			}

			//En caso de q el isbn no se repita 
			String sql = "INSERT INTO libro VALUES (?, 't', 'a', 'e', 12, 132120,66, 2);";

			PreparedStatement pstm = conexion.prepareStatement(sql);
			pstm.setInt(1, idSocio);

			cantidad = pstm.executeUpdate();
			System.out.println("se ha added " + cantidad + " socio correctamente");

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