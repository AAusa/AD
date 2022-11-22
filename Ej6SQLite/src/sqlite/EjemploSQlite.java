package sqlite;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class EjemploSQlite {
	public static void main(String[] args) {
		try {
			Scanner sc = new Scanner(System.in);
			/*
			 * Base de los inner joins:
			 * select * from Prestamo, Libro, Socio
				where
					Libro.Codigo = Prestamo.Codigo_libro AND
					Socio.Codigo = Prestamo.Codigo_socio;
			 */
			
			String[] classForName = {"org.sqlite.JDBC",//sqlite
					"com.mysql.jdbc.Driver",////mysql
					"org.hsqldb.jdbc.JDBCDriver",//hsqldb
					"org.apache.derby.jdbc.EmbeddedDriver",//derby
					};

			String[] consultas = {"select * from libro;",
					"select * from socio;", "select * from prestamo;",
					"select Titulo, Nombre from Prestamo, Libro, Socio where Libro.Codigo = Prestamo.Codigo_libro AND Socio.Codigo = Prestamo.Codigo_socio AND date(Prestamo.Fecha_fin) > date(\"now\");",
					"select count(Prestamo.Codigo_libro) as 'numLibros' from Prestamo, Socio where Socio.Codigo = Prestamo.Codigo_socio AND Socio.Nombre = 'Mustafa';",
					"select Titulo, Nombre from Prestamo, Libro, Socio where Libro.Codigo = Prestamo.Codigo_libro AND Socio.Codigo = Prestamo.Codigo_socio AND date(Prestamo.Fecha_fin) < date(\"now\");",
			"select Socio.Nombre, Socio.Apellidos from Prestamo, Libro, Socio where	Libro.Codigo = Prestamo.Codigo_libro AND Socio.Codigo = Prestamo.Codigo_socio AND date(Prestamo.Fecha_fin) < date(\"now\") GROUP BY Socio.Nombre;"};
						
			System.out.print("0. sqlite\n"
					+ "1. mysql\n"
					+ "2. hsqldb\n"
					+ "3. apacheDerby\n");
			int opcion = sc.nextInt();
			
			// CARGAR EL CONTROLADOR JDBC de la base de datos
			Class.forName(classForName[opcion]);
			Connection conexion = null;
			switch(opcion) {
			case 0:
				conexion = DriverManager.getConnection("jdbc:sqlite:src\\sqlite\\biblioteca.db");
				break;
			case 1:
				conexion = DriverManager.getConnection("jdbc:mysql://192.168.56.104:3306/biblioteca","pruebas","Pass!123456");
				break;
			case 2:
				conexion = DriverManager.getConnection("jdbc:hsqldb:bd\\biblioteca", "miusuario", "Pass!123456");
				break;
			case 3:
				conexion = DriverManager.getConnection("jdbc:derby:bd\\biblioteca", "miusuario", "Pass!123456");
				break;
			}
			
			Statement sentencia = (Statement) conexion.createStatement();
			ResultSet resultado = sentencia.executeQuery(consultas[5]);
			System.out.println(resultado.next());
			// recorro el resultado
			while (resultado.next()) {
				System.out.println("Libro: " + resultado.getString(1));
			}

			// LIBRERAR LOS RECURSOS
			resultado.close();
			sentencia.close();
			conexion.close();

		} catch (SQLException | ClassNotFoundException ex) {
			System.out.println("Error en la conexi�n de la base de datos");
			ex.printStackTrace();
		}
	}
}