package sqlite;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
public class EjemploMySQL {
    public static void main(String[] args) {
        try {
        	// CARGAR EL CONTROLADOR JDBC de la base de datos
        	//Conexion con la BBDD:
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			Connection conexion = DriverManager.getConnection("jdbc:hsqldb:bd\\biblioteca", "miusuario", "Pass!123456");
			Statement sentencia = (Statement) conexion.createStatement();
			
			//Sentencia SQL para mostrar los codigos y nombres de los socios:
            ResultSet resultado = sentencia.executeQuery("select * from libro;");
            
            while (resultado.next()) {
            	System.out.println("TITULO: " + resultado.getString("TITULO"));
            }
            
            // LIBRERAR LOS RECURSOS
            resultado.close();
            sentencia.close();
            conexion.commit();//PONERLO SI O SI
            conexion.close();
            
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Error en la conexi�n de la base de datos");
            ex.printStackTrace();
        }
    }
}