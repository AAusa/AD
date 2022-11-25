package aTenerEnCuenta;

public class aTenerEnCuenta2 {
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
		Utilidades.pedirCadena("Introduce cadena:", "hola".toUpperCase());
		Utilidades.pedirEntero("Introduce entero:");
	}
}
