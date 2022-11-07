package sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
public class EjemploPrepareStatementSQlite {
    public static void main(String[] args) {
        try {
        	// CARGAR EL CONTROLADOR JDBC de la base de datos
        	Class.forName("org.sqlite.JDBC");
        	
        	// ESTABLECER LA CONEXI�N con la base de datos
            Connection conexion = DriverManager.getConnection("jdbc:sqlite:src\\sqlite\\biblioteca.db");
            // parametro 1 = Driver que utilizamos y ruta y nombre de la base de datos
            //				jdbc:sqlite:C:\\Users\\Eva Royo\\Documents\\BBDD\\sqlite\\biblioteca.db
            // parametro 2 = nombre del usuario
            // parametro 3 = contrase�a del usuario
            
            
            // PREPARAMOS LA SENTENCIA SQL
            //Statement sentencia = (Statement) conexion.createStatement();
            String sql = "INSERT INTO socio VALUES (?,?,?,?,?,?)";
            PreparedStatement pstm = conexion.prepareStatement(sql);
            pstm.setInt(1, 14);
            pstm.setString(2, "Pepe");
            pstm.setString(3, "Martinez");
            pstm.setString(4, "1970-11-09");
            pstm.setString(5, "C/ Patatin, 33");
            pstm.setInt(6, 650766546);
            
            int cantidad = pstm.executeUpdate();
            //int cantidad = sentencia.executeUpdate(sql);
            
            
            // recorro el resultado
            System.out.println("se han insertado " + cantidad + "socios");
            
            // LIBRERAR LOS RECURSOS
            //resultado.close();
            //sentencia.close();
            conexion.close();
            
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Error en la conexi�n de la base de datos");
            ex.printStackTrace();
        }
    }
}