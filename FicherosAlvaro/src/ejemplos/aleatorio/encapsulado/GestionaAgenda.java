package ejemplos.aleatorio.encapsulado;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 * Aqu� se van a almacenar los datos de las personas
 * @author 
 *
 */
public class GestionaAgenda {

	private int dimensionNombre = 10;
	private int dimensionEmail = 30;
	// nombre de una persona ocupa 10 characteres --> 10 *2 = 20 bytes para almacenar el nombre 
	// 4 bytes para almacenar el entero de edad
	// el email del due�o de la persona ocupa 50 caracteres --> 30*2 = 60 bytes para almacenar el email
	// en total 20 + 4 + 60 = 84
	public final static int TAMAGNOREGISTRO = 84;


	private RandomAccessFile fichero;
	private String nomFichero;
	ArrayList<Persona> personas = new ArrayList<Persona>();


	/**
	 * CONSTRUCTOR CON EL NOMBRE DEL FICHERO QUE UTILIZAREMOS
	 * @param fichero
	 */
	public GestionaAgenda(String nomfichero) {
		this.nomFichero = nomfichero;
	}

	/**
	 * Abrir el fichero con los contactos para leer y escribir
	 * @throws FileNotFoundException
	 */
	public void abrir() throws FileNotFoundException {
		fichero = new RandomAccessFile(nomFichero, "rw");
	}

	/**
	 * Cerrar el fichero de las personas
	 * @throws IOException
	 */
	public void cerrar() throws IOException {
		if (fichero != null) {
			fichero.close();
		}
	}

	/**
	 * Escribir una persona en mi agenda en la posici�n indicada
	 * @param registro la persona que quiero escribir
	 * @param pos la posici�n donde quiero escribir
	 * @throws IOException
	 */
	public void escribir (Persona registro, int pos) throws IOException {
		if (fichero != null) {
			// posiciono el puntero en el sitio que quiero ...
			fichero.seek(calculaposicion(pos));
			this.escribir(registro);
		}
	}


	/**
	 * Escribir una persona en la posici�n en la que actualmente est� el cursor 
	 * @param registro la persona que se quiere escribir en el fichero
	 * @throws IOException
	 */
	public void escribir (Persona registro) throws IOException {
		if (fichero != null) {
			personas.add(registro);
			// nombre de una persona ocupa 10 characteres --> 10 *2 = 20 bytes para almacenar el nombre 
			// 4 bytes para almacenar el entero de edad
			// el email del due�o de la persona ocupa 50 caracteres --> 30*2 = 60 bytes para almacenar el email 

			// PARA ESCRIBIR EL NOMBRE
			StringBuffer bufferN= new StringBuffer();
			if (registro.getNombre() != null) {
				bufferN.append(registro.getNombre());
			}
			bufferN.setLength(dimensionNombre);
			fichero.writeChars(bufferN.toString());

			// escribir la EDAD
			fichero.writeInt(registro.getEdad());

			// PARA ESCRIBIR EL EMAIL
			StringBuffer bufferE= new StringBuffer();
			if (registro.getEmail() != null) {
				bufferE.append(registro.getEmail());
			}
			bufferE.setLength(dimensionEmail);
			fichero.writeChars(bufferE.toString());
		}

	}

	/**
	 * Leer del fichero un registro cualquiera, el par�metro indica la posici�n
	 * del registro
	 * @param pos
	 * @return
	 * @throws IOException
	 */
	public Persona leer (int pos) throws IOException {

		if (fichero != null) {
			fichero.seek(calculaposicion(pos));
		}

		return this.leer();
	}

	public void leerTodoArray () {
		for (Persona persona : personas) {
			System.out.println(persona);
		}
	}

	/*
	 * En proceso... De uno en uno
	 */
	public void leerTodo () {
		Persona registro = null;
		int pos = 1;
		for(int i = fichero.seek(pos); i < fichero.length(); i = fichero.seek(pos++)) {
			
		}
		if (fichero != null) {
			try {
				registro = new Persona();

				// TENGO QUE IR LEYENDO EN ORDEN LO QUE HE ESCRITO ANTES
				// leo el NOMBRE
				char campoN[] = new char[dimensionNombre];
				for (int i = 0; i < dimensionNombre; i++) {
					campoN[i] = fichero.readChar();
				}	
				registro.setNombre(new String(campoN));

				registro.setEdad(fichero.readInt());		

				// leo el EMAIL
				char campoE[] = new char[dimensionEmail];
				for (int i = 0; i < dimensionEmail; i++) {
					campoE[i] = fichero.readChar();
				}			
				registro.setEmail(new String(campoE));

			} catch (Exception e) {
				// entrar� aqu� cuando haya llegado al final del fichero
				registro = null;
			}
		}

		return registro;
	}


	/**
	 * Leer del fichero la persona que se encuentra en la posici�n actual del cursor
	 * 
	 * @return la persona leida o null si ha llegado al final del fichero
	 */
	public Persona leer() {

		Persona registro = null;

		if (fichero != null) {
			try {
				registro = new Persona();

				// TENGO QUE IR LEYENDO EN ORDEN LO QUE HE ESCRITO ANTES
				// leo el NOMBRE
				char campoN[] = new char[dimensionNombre];
				for (int i = 0; i < dimensionNombre; i++) {
					campoN[i] = fichero.readChar();
				}	
				registro.setNombre(new String(campoN));

				registro.setEdad(fichero.readInt());		

				// leo el EMAIL
				char campoE[] = new char[dimensionEmail];
				for (int i = 0; i < dimensionEmail; i++) {
					campoE[i] = fichero.readChar();
				}			
				registro.setEmail(new String(campoE));

			} catch (Exception e) {
				// entrar� aqu� cuando haya llegado al final del fichero
				registro = null;
			}
		}

		return registro;
	}

	/**
	 * Calcula la posici�n en la que tendr� que situarse el cursor
	 * dentro del fichero
	 * 
	 * @param pos -se considera que la primera posici�n es la 1 (no la 0)
	 * @return la posici�n
	 */
	private int calculaposicion(int pos) {
		return (pos-1)* TAMAGNOREGISTRO;
	}

	/**
	 * Posiciona el cursor al inico del fichero
	 * 
	 * @throws IOException
	 */
	public void iniciar() throws IOException  {			
		fichero.seek(0);	
	}


}
