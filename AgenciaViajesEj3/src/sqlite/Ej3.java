package sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLNonTransientException;
import java.sql.SQLRecoverableException;
import java.sql.SQLTransientException;
import java.sql.Statement;

public class Ej3 {

	public static void main(String[] args) {
		try {
        	// CARGAR EL CONTROLADOR JDBC de la base de datos
			Class.forName("com.mysql.jdbc.Driver");
        	
        	// ESTABLECER LA CONEXI�N con la base de datos
			Connection conexion = DriverManager.getConnection("jdbc:mysql://192.168.56.104:3306/Vacacionesforyou","miusuario","Pass!123456");
            
			
            // PREPARAMOS LA SENTENCIA SQL
            Statement sentencia = (Statement) conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery("select * from Clientes where Clientes.VIP=1;");
        	System.out.println("A)");

            // recorro el resultado
            while (resultado.next()) {
            	System.out.println("\t"+resultado.getInt(1) + "-"+ resultado.getString(2) + resultado.getString(3) + "-"+ resultado.getString(4) + resultado.getString(5) + "-"+ resultado.getString(6) + resultado.getInt(7));
            }
            resultado = sentencia.executeQuery("select * from Clientes where Clientes.VIP=0;");
            
            // recorro el resultado
            while (resultado.next()) {
            	System.out.println("\t"+resultado.getInt(1) + "-"+ resultado.getString(2) + resultado.getString(3) + "-"+ resultado.getString(4) + resultado.getString(5) + "-"+ resultado.getString(6) + resultado.getInt(7));
            }
            
        	System.out.println("\nB)");
            
            resultado = sentencia.executeQuery("select * from Clientes");
            
            // recorro el resultado
            while (resultado.next()) {
            	System.out.println("\t"+resultado.getInt(1) + "-"+ resultado.getString(2) + resultado.getString(3) + "-"+ resultado.getString(4) + resultado.getString(5) + "-"+ resultado.getString(6) + resultado.getInt(7));
            }
            int id = Utilidades.pedirEntero("Id de cliente a modificar:");
            String tfno = Utilidades.pedirCadena("Nuevo tfno:");
            String sql = "update Clientes set Telefono='"+tfno+"' where Id = "+id+";";
            PreparedStatement pstm = conexion.prepareStatement(sql);
            
            int cantidad = pstm.executeUpdate();
            
            
            // recorro el resultado
            System.out.println("se han modificado " + cantidad + " cliente");
            
            // LIBRERAR LOS RECURSOS
            resultado.close();
            sentencia.close();
            conexion.close();
            
            
        } catch (ClassNotFoundException ex) {
            System.out.println("Error en la conexi�n de la base de datos");
            ex.printStackTrace();
        }catch(SQLNonTransientException e) {
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
