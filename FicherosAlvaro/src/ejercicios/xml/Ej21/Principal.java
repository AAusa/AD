package ejercicios.xml.Ej21;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
		Gestion agenda = new Gestion("src\\ejercicios\\aleatorio\\Ej18Ej19\\departamento.dat");

		try {
			agenda.abrir();
			agenda.iniciar();

			Departamento d1 = new Departamento(1, "d1", "Zgz");
			Departamento d2 = new Departamento(2, "d2", "Bcna");
			Departamento d3 = new Departamento(3, "d3","Mdrd");
			Departamento d4 = new Departamento(4, "d4", "Zam");

			agenda.escribir(d1);
			agenda.escribir(d2);
			agenda.escribir(d3);
			agenda.escribir(d4);	
		} 
		catch (FileNotFoundException e) {
			System.out.println("Error, fichero no encontrado");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error, de escritura");
			e.printStackTrace();
		}

		//ESCRIBIR
		DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();

		try {
			DocumentBuilder builder = factoria.newDocumentBuilder();
			DOMImplementation implementatio = builder.getDOMImplementation();
			Document document = implementatio.createDocument(null, "Departamentos", null);
			document.setXmlVersion("1.0");

			int edad = 21;
			for (int i=1; i<=4;i++ ) {
				Element nodoPadre = document.createElement("Departamento");
				document.getDocumentElement().appendChild(nodoPadre);

				Element elem = document.createElement("Nombre");
				Text text = document.createTextNode(agenda.leer(i).getNombre());
				nodoPadre.appendChild(elem);
				elem.appendChild(text);

				elem = document.createElement("Numero");
				text = document.createTextNode(Integer.toString(agenda.leer(i).getNum()));
				nodoPadre.appendChild(elem);
				elem.appendChild(text);

				elem = document.createElement("Localidad");
				text = document.createTextNode(agenda.leer(i).getLocalidad());
				nodoPadre.appendChild(elem);
				elem.appendChild(text);

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
