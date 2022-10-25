package ejercicios.xml.ficheroXML;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
/**
 * Clase encargada de la conversion entre un fichero txt y uno xml
 * @author alu
 */
public class Conversion {
	private ArrayList<Persona> personas = new ArrayList<Persona>();
	/**
	 * Metodo lee txt y escribe objeto en array personas
	 */
	public void leerTXT() {
		File mifichero = new File ("src\\ejercicios\\xml\\ficheroXML\\datos.txt");
		try {
			FileReader lector = new FileReader(mifichero);	
			BufferedReader cestaTemporal = new BufferedReader(lector);

			String linea = cestaTemporal.readLine();
			while (linea != null) {
				//System.out.println(linea);
				String[] atributos = linea.split(",");
				String nombre = atributos[0]; 
				String apellidos = atributos[1]; 
				double altura = Double.parseDouble(atributos[2]); 
				String fechaNacimiento = atributos[3]; 
				String tfno = atributos[4]; 
				personas.add(new Persona(nombre, apellidos, tfno, altura, conseguirEdad(fechaNacimiento)));
				linea = cestaTemporal.readLine();
			}

			cestaTemporal.close();


		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo crea una edad a partir de fechaNacimiento
	 * @param fechaNacimiento
	 * @return
	 */
	public int conseguirEdad(String fechaNacimiento) {		
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy"); //Indica el formato de fecha
		LocalDate fechaNac = LocalDate.parse(fechaNacimiento, fmt); //Formatea la fecha introducida
		LocalDate ahora = LocalDate.now(); ////Fecha actual
		Period periodo = Period.between(fechaNac, ahora); //Obtiene la diferencia entre ambas fechas
		return periodo.getYears();
	}

	/*
	 * Metodo lee objeto de array y escribe xml
	 */
	public void escribirXML() {
		DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();

		try {
			DocumentBuilder builder = factoria.newDocumentBuilder();
			DOMImplementation implementatio = builder.getDOMImplementation();
			Document document = implementatio.createDocument(null, "Personas", null);
			document.setXmlVersion("1.0");
			for (Persona persona : personas) {
				Element nodoPadre = document.createElement("persona");
				document.getDocumentElement().appendChild(nodoPadre);

				Element elem = document.createElement("nombre");
				Text text = document.createTextNode(persona.getNombre());
				nodoPadre.appendChild(elem);
				elem.appendChild(text);

				elem = document.createElement("apellidos");
				text = document.createTextNode(persona.getApellidos());
				nodoPadre.appendChild(elem);
				elem.appendChild(text);
				
				elem = document.createElement("tfno");
				text = document.createTextNode(persona.getTfno());
				nodoPadre.appendChild(elem);
				elem.appendChild(text);
				
				elem = document.createElement("altura");
				text = document.createTextNode(String.valueOf(persona.getAltura()));
				nodoPadre.appendChild(elem);
				elem.appendChild(text);

				elem = document.createElement("edad");
				text = document.createTextNode(Integer.toString(persona.getEdad()));
				nodoPadre.appendChild(elem);
				elem.appendChild(text);
			}

			TransformerFactory xformFactory = TransformerFactory.newInstance();  
			Transformer idTransform = xformFactory.newTransformer();
			Source input = new DOMSource(document);
			Result output = new StreamResult(new File("src\\\\ejercicios\\\\xml\\\\ficheroXML\\\\datos.xml"));
			idTransform.transform(input, output);

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
