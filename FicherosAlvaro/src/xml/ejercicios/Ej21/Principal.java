package xml.ejercicios.Ej21;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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
/*
 * POR QUÉ SALEN SUMBOLOS EN XML
 */
public class Principal {
	public static void main(String[] args) throws IOException {
		Scanner teclado = new Scanner(System.in);
		Gestion agenda = new Gestion("src\\ejercicios\\xml\\Ej21\\departamento.dat");
		ArrayList<Departamento> departamentos = new ArrayList<Departamento>();
		
		/*
		try {
			agenda.abrir();
			agenda.iniciar();
			
			Departamento d1 = new Departamento(1, "d1", "Zgz");
			Departamento d2 = new Departamento(2, "d2", "Bcna");
			Departamento d3 = new Departamento(3, "d3","Mdrd");
			Departamento d4 = new Departamento(4, "d4", "Zam");
			Departamento d5 = new Departamento(55, "d5", "Zgz");
			Departamento d6 = new Departamento(12, "d6", "Bcna");
			Departamento d7 = new Departamento(65, "d7","Mdrd");
			Departamento d8 = new Departamento(31, "d8", "Zam");

			agenda.escribir(d1);
			agenda.escribir(d2);
			agenda.escribir(d3);
			agenda.escribir(d4);	
			agenda.escribir(d5);
			agenda.escribir(d6);
			agenda.escribir(d7);
			agenda.escribir(d8);	
		} 
		catch (FileNotFoundException e) {
			System.out.println("Error, fichero no encontrado");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error, de escritura");
			e.printStackTrace();
		}
		*/
		
		
		
		agenda.abrir();
		agenda.iniciar();

		//ESCRIBIR
		DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
		boolean leerTodo = true;
		try {
			DocumentBuilder builder = factoria.newDocumentBuilder();
			DOMImplementation implementatio = builder.getDOMImplementation();
			Document document = implementatio.createDocument(null, "Departamentos", null);
			document.setXmlVersion("1.0");

			int edad = 21;
			int i = 1;
			//Gestion con while, como leer null sin error
			//for (int i = 1; i <= 10; i++) {
			departamentos = agenda.leerTodoArray();
			for (Departamento departamento : departamentos) {
				Element nodoPadre = document.createElement("Departamento");
				document.getDocumentElement().appendChild(nodoPadre);

				Element elem = document.createElement("Nombre");
				Text text = document.createTextNode(agenda.leer(i).getNombre().trim());//Quita espacios en blanco
				elem.appendChild(text);
				nodoPadre.appendChild(elem);
				

				elem = document.createElement("Numero");
				text = document.createTextNode(Integer.toString(agenda.leer(i).getNum()).trim());
				nodoPadre.appendChild(elem);
				elem.appendChild(text);

				elem = document.createElement("Localidad");
				text = document.createTextNode(agenda.leer(i).getLocalidad().trim());
				nodoPadre.appendChild(elem);
				elem.appendChild(text);
				i++;
			}

			TransformerFactory xformFactory = TransformerFactory.newInstance();  
			Transformer idTransform = xformFactory.newTransformer();
			Source input = new DOMSource(document);
			Result output = new StreamResult(new File("src\\ejercicios\\xml\\Ej21\\departamento.xml"));
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
		
		//LEER
		File mifichero = new File ("src\\\\ejercicios\\\\xml\\\\Ej21\\\\departamento.xml");
		try {
			FileReader lector = new FileReader(mifichero);

			// ahora voy a leer utilizando un buffer
			BufferedReader cestaTemporal = new BufferedReader(lector);

			String linea = cestaTemporal.readLine();
			while (linea != null) {
				System.out.println(linea);
				linea = cestaTemporal.readLine();
			}

			cestaTemporal.close();


		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
