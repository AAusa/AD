package aTenerEnCuenta;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLNonTransientException;
import java.sql.SQLRecoverableException;
import java.sql.SQLTransientException;
import java.sql.Statement;

public class aTenerEnCuenta {
	/**
Práctica:
String a int: int numEntero = Integer.parseInt(numCadena);
Int a string → String numCadena= String.valueOf(numEntero);

String en fecha
	private static Date pasarStringaDate(String fecha) throws ParseException {
		SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
		Date fechaDate = (Date) formato.parse(fecha);
		return fechaDate;
	}

fecha en edad
	private static Date pasarStringaDate(String fecha) throws ParseException {
		SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
		Date fechaDate = (Date) formato.parse(fecha);
		return fechaDate;
	}

Pasar contenido de fichero a array:
public ArrayList<Persona> leerTodoArray () throws IOException {
		Persona registro = new Persona();
		int pos = 1;
		iniciar();
		while(fichero.getFilePointer() < fichero.length()) {
			registro = leer(pos);
			if(registro.getEdad() != 0) {//Comprobacion de que está relleno
				personas.add(registro);
			}
			pos++;
		}
		return personas;
	}

cerrar recursos y try catch
sqlException

Indice ejercicios:
ejercicios.gestionFichero
	1,2,3 → de Ejercicios
ejemplos.secuencial correspondiente a secuencial.ejercicios de la profe en el que el ejercicio2 utiliza DataInputStream como genérico.
	Ej1 → Ejercicios ficheros iniciales
ejercicios.secuencial
	Ej4
	Ej10 (no tanto)
ejemplos.secuencial.objetos correspondiente a secuencial.objetos
	TestAgenda → en el personal incluye la lectura con bucle
ejercicios.secuencial.objetos
	Ej15
secuencial.objetosClase de la profe
	Ej15 abstrayendo la clase de gestión
aleatorio profe
	Leer y escribir a pelo
aleatorio ejercicios profe
	Gestion leer y escribir
aleatorio.ejercicios.Ej18Ej19
	Gestion leer, escribir y modificar
aleatorio.ejercicios.Ej20
	Gestion leer, escribir, modificar y borrar
aleatorio.encapsulado
	para mostrar contenido de todo el fichero, leerlo, pasarlo a un array

xml.lectura
	lee
xml.escritura
	EscribeXml
		Guarda en fichero
	Fibonacci
		Imprime por pantalla
xml.ficheroXML.ejercicios
	Lee txt y escribe xml
xml.ejercicios.Ej21
	Lee dat y escribe xml
	Text text = document.createTextNode(agenda.leer(i).getNombre().trim());//Quita espacios en blanco

PruebaSQLite (ejemplos de ConectorSQLite)
	EjemploPrepareStatementSQlite 
	ConectorMySQL
		EjConexion
Ej8MySQL
	Inserción incrementando Código
Ej9MySQL
	Modificación con comprobación
	Diferencia entre sqlite y mysql las fechas en comparativas y a la hora de leer el resultado getDate o getString

Aleatorio tamannos:
	boolean (1 bit)
	byte (1 byte)
	carácter Unicode (2 bytes)
	short (2 bytes)
	int (4 bytes)             	
	long (8 bytes)
	float (4 bytes)
	double (8 bytes)
	 */
	public static void main(String[] args) {
		//Solo se conecta al principio para todas las operaciones
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//Como con prepare statement solo se necesita cerrar la conexion se pone en el try
		String sql = "INSERT INTO socio VALUES (?,?,?,?,?,?)";
		try(Connection conexion = DriverManager.getConnection("jdbc:sqlite:src\\aTenerEnCuenta\\biblioteca.db");
				PreparedStatement pstm = conexion.prepareStatement(sql)) {
			pstm.setInt(1, 46);
			pstm.setString(2, "Pepe");
			pstm.setString(3, "Martinez");
			pstm.setString(4, "1970-11-09");
			pstm.setString(5, "C/ Patatin, 33");
			pstm.setInt(6, 650766546);

			int cantidad = pstm.executeUpdate();
			//int cantidad = sentencia.executeUpdate(sql);


			// recorro el resultado
			System.out.println("se han insertado " + cantidad + "socios");

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

		//Con createStatement se pone la conexion, sentencia y resultado en el try
		try(Connection conexion = DriverManager.getConnection("jdbc:sqlite:src\\aTenerEnCuenta\\biblioteca.db");
				Statement sentencia = (Statement) conexion.createStatement();
				ResultSet resultado = sentencia.executeQuery("select * from socio")) {
			
			while (resultado.next()) {
				System.out.println(resultado.getInt(1) + "-"+ resultado.getString(2));
			}
		} catch (SQLException ex) {
			System.out.println("Error en la conexi�n de la base de datos");
			ex.printStackTrace();
		}
	}
}
