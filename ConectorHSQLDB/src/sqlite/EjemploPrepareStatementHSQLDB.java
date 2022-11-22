package sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
 
public class EjemploPrepareStatementHSQLDB {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	int cantidad = 0;
    	EjemploPrepareStatementHSQLDB ej = new EjemploPrepareStatementHSQLDB();
        try {
        	// CARGAR EL CONTROLADOR JDBC de la base de datos
        	Class.forName("org.hsqldb.jdbc.JDBCDriver");
			Connection conexion = DriverManager.getConnection("jdbc:hsqldb:bd\\biblioteca", "miusuario", "Pass!123456");
            // parametro 1 = Driver que utilizamos y ruta y nombre de la base de datos
            //				jdbc:sqlite:C:\\Users\\Eva Royo\\Documents\\BBDD\\sqlite\\biblioteca.db
            // parametro 2 = nombre del usuario
            // parametro 3 = contrase�a del usuario
            
            
            // PREPARAMOS LA SENTENCIA SQL
            //Statement sentencia = (Statement) conexion.createStatement();
            boolean seguir = true;
            System.out.println("Introduce libros mientras no escribas no");
            while(seguir) {
            	int codigo = ej.obtenerCodigoValido(conexion);            	
            	
            	String sql = "INSERT INTO Libro VALUES (?,?,?,?,?,?,?,?)";
                PreparedStatement pstm = conexion.prepareStatement(sql);
                
                pstm.setInt(1, codigo);
                pstm.setString(2, ej.getTitulo());
                pstm.setString(3, ej.getAutor());
                pstm.setString(4, ej.getEditorial());
                pstm.setInt(5, ej.getAnno());
                pstm.setString(6, ej.getISBN());
                pstm.setInt(7, ej.getNumEjemplares());
                pstm.setInt(8, ej.getNumPaginas());

                cantidad = pstm.executeUpdate();
                System.out.println("se han insertado " + cantidad + " libros correctamente");
                
                System.out.println("¿Desea introducir otro libro? (s/n)");
                String resultado = sc.nextLine();  
                if(resultado.startsWith("n")) {
                	seguir = false;
                }
            }
            
            
            //int cantidad = sentencia.executeUpdate(sql);
            
            
            // recorro el resultado
            
            // LIBRERAR LOS RECURSOS
            //resultado.close();
            //sentencia.close();
            conexion.commit();//PONERLO SI O SI
            conexion.close();
            
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Error en la conexi�n de la base de datos");
            ex.printStackTrace();
        }
    }
	private int obtenerCodigoValido(Connection conexion) throws SQLException {
		Statement sentencia = (Statement) conexion.createStatement();
        ResultSet resultado = sentencia.executeQuery("select max(codigo) from Libro");
        int codigo = 0;
        if (resultado.next()) {//Cuando te va a dar un resultado se pone if no while
        	codigo = resultado.getInt(1) + 1;
        }
		return codigo;
	}
	private String getTitulo() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce titulo:");
        String titulo = sc.nextLine();
		return titulo;
	}
	
	private String getAutor() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce autor:");
        String autor = sc.nextLine();
		return autor;
	}
	
	private String getEditorial() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce editorial:");
        String editorial = sc.nextLine();
		return editorial;
	}
	
	private int getAnno() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce anno:");
        int anno = sc.nextInt();sc.nextLine();//Para que no haya error en el salto de linea
        if(anno > 0 || anno > 2022) anno = 2022;
		return anno;
	}
	
	private String getISBN() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce ISBN:");
        String isbn = sc.nextLine();
		return isbn;
	}
	
	private int getNumEjemplares() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce numEjemplares:");
        int numEjemplares = sc.nextInt();sc.nextLine();
        if(numEjemplares < 0) numEjemplares = 1;
		return numEjemplares;
	}
	
	private int getNumPaginas() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce numPaginas:");
        int numPaginas = sc.nextInt();sc.nextLine();
        if(numPaginas < 0) numPaginas = 1;
		return numPaginas;
	}
}