package ejercicios.aleatorio.Ej20;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
/**
 * Clase que gestiona la lectura y escritura de departamentos
 * @author Álvaro
 *
 */
public class Gestion {
	private int dimensionNombre = 10;
	private int dimensionLocalidad = 20;
	private int dimensionNum = 4;
	/*
	 * Calculo TAMAGNOREGISTRO:
	 * Nombre: 10*2 = 20
	 * Localidad: 20*2 = 40
	 * Num: 4
	 * Total: 20+40+4 = 64 bytes
	 */
	public final static int TAMAGNOREGISTRO = 64;
	private RandomAccessFile fichero;
	private String nomFichero;
	
	
	public Gestion(String nomfichero) {
		this.nomFichero = nomfichero;
	}
	
	public void abrir() throws FileNotFoundException {
		fichero = new RandomAccessFile(nomFichero, "rw");
	}

	public void cerrar() throws IOException {
		if (fichero != null) {
			fichero.close();
		}
	}

	private int calculaposicion(int pos) {
		return (pos-1)* TAMAGNOREGISTRO;
	}

	public void escribir (Departamento registro, int pos) throws IOException {
		if (fichero != null) {
			fichero.seek(calculaposicion(pos));
			this.escribir(registro);
		}
	}

	public void escribir (Departamento registro) throws IOException {
		fichero.seek(calculaposicion(registro.getNum()));
		if (fichero != null) {
			fichero.writeInt(registro.getNum());

			StringBuffer bufferNombre= new StringBuffer(); 
			if (registro.getNombre() != null) {
				bufferNombre.append(registro.getNombre());
			}
			bufferNombre.setLength(dimensionNombre);
			fichero.writeChars(bufferNombre.toString());

			StringBuffer bufferLocalidad= new StringBuffer();
			if (registro.getLocalidad() != null) {
				bufferLocalidad.append(registro.getLocalidad());
			}
			bufferLocalidad.setLength(dimensionLocalidad);
			fichero.writeChars(bufferLocalidad.toString());
		}

	}
	
	/**
	 * Metodo encargado de modificar un registro e imprimir el antes y despues del registro
	 * @param registroNew
	 * @throws IOException
	 */
	public void modificar (Departamento registroNew) throws IOException {
		Departamento dpto = new Departamento();
		dpto = leer(registroNew.getNum());
		System.out.println("Antes:\n" + dpto);
		escribir(registroNew);
		dpto = leer(registroNew.getNum());
		System.out.println("Ahora:\n" + dpto);	
	}
	
	public void borrar (int num) throws IOException {
		Departamento dpto = new Departamento();
		Departamento vacio = new Departamento();
		dpto = leer(num);
		System.out.println("Antes:\n" + dpto);
		//escribir null
		fichero.seek(calculaposicion(num));
		if (fichero != null) {
			StringBuffer bufferNum= new StringBuffer();
			bufferNum.append(" ");
			bufferNum.setLength(dimensionNum);
			fichero.writeChars(bufferNum.toString());

			StringBuffer bufferNombre= new StringBuffer(); 
			bufferNombre.append(" ");
			bufferNombre.setLength(dimensionNombre);
			fichero.writeChars(bufferNombre.toString());

			StringBuffer bufferLocalidad= new StringBuffer();
			bufferLocalidad.append(" ");
			bufferLocalidad.setLength(dimensionLocalidad);
			fichero.writeChars(bufferLocalidad.toString());
		}
		
		dpto = leerBorrado(num); //0 es null en el caso del numero
		System.out.println("Ahora:\n" + dpto);	
	}
	
	/**
	 * Encargado de comprobar si el registro esta vacio o no
	 * @param num
	 * @return
	 * @throws IOException
	 */
	public boolean verEstadoRegistro (int num) throws IOException {
		Departamento dpto = new Departamento();
		try {
		dpto = leer(num);
		
			if(dpto.getNum() == 0) {
				return false;
			}
		} catch (NullPointerException e) {
			//Dpto. es null
			return false;
		} catch (IOException e2) {
			System.out.println("Num negativo introducido");
			return false;
		}
		
		
		return true;
	}


	public Departamento leer (int pos) throws IOException {

		if (fichero != null) {
			fichero.seek(calculaposicion(pos));
		}

		return leer();
	}

	public Departamento leer() {

		Departamento registro = null;

		if (fichero != null) {
			try {
				registro = new Departamento();
				
				registro.setNum(fichero.readInt());		

				char campoNombre[] = new char[dimensionNombre];
				for (int i = 0; i < dimensionNombre; i++) {
					campoNombre[i] = fichero.readChar();
				}	
				registro.setNombre(new String(campoNombre));
				
				char campoLocalidad[] = new char[dimensionLocalidad];
				for (int i = 0; i < dimensionLocalidad; i++) {
					campoLocalidad[i] = fichero.readChar();
				}			
				registro.setLocalidad(new String(campoLocalidad));

			} catch (Exception e) {
				registro = null;
			}
		}

		return registro;
	}
	
	public Departamento leerBorrado(int pos) throws IOException {
		fichero.seek(calculaposicion(pos));
		Departamento registro = null;

		if (fichero != null) {
			try {
				registro = new Departamento();
				
				registro.setNum(0);

				char campoNombre[] = new char[dimensionNombre];
				for (int i = 0; i < dimensionNombre; i++) {
					campoNombre[i] = fichero.readChar();
				}	
				registro.setNombre(new String(campoNombre));
				
				char campoLocalidad[] = new char[dimensionLocalidad];
				for (int i = 0; i < dimensionLocalidad; i++) {
					campoLocalidad[i] = fichero.readChar();
				}			
				registro.setLocalidad(new String(campoLocalidad));

			} catch (Exception e) {
				registro = null;
			}
		}

		return registro;
	}
	

	
	public void iniciar() throws IOException  {			
		fichero.seek(0);	
}

}
