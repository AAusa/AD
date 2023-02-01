package existDB;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XPathQueryService;


public class Ej1 {

	public static void main(String[] args) {
		// driver que necesito para conectar existDB
		String driver  =  "org.exist.xmldb.DatabaseImpl";
		Collection miDB = null;
		// cargar el driver
		Class cl;
		Scanner sn = new Scanner(System.in);
		boolean salir = false;
		int opcion;
		Ej1 ej1 = new Ej1();
		try {
			cl = Class.forName(driver);
			
			//instancia de la BD
			Database BD = (Database)cl.newInstance();
			
			// registrar el Driver
	        DatabaseManager.registerDatabase(BD);
	        
	        // La cadena de conexion
	        String URI = "xmldb:exist://192.168.56.104:8080/exist/xmlrpc/db/pruebas";
	        
	        miDB = DatabaseManager.getCollection(URI, "miusuario", "Pass!123456");
	        
	        XPathQueryService servicio = (XPathQueryService)miDB.getService("XPathQueryService", "1.0");
	        
	       
			do{
				System.out.println("\nMenu:");
				System.out.println("\t1) Obtener los apellidos distintos de los autores.");
				System.out.println("\t2) Obtener los nodos libro que tengan un nodo editor.");
				System.out.println("\t3) Obtener los títulos de los libros cuyo precio esté por debajo de 50 euros.");
				System.out.println("\t4) Por cada libro con autores, seleccionar el título del libro y sus autores. Si el libro no tiene autores, pero el editor sí, la consulta devolverá el título del libro y la afiliación del editor.");
				System.out.println("\t5) Salir");
				try {
					opcion = Utilidades.pedirEntero("Escribe una de las opciones:");
					switch (opcion) {
					case 1:
						System.out.println("Opcion 1 seleccionada");
				        ej1.getApellidos(servicio);
						break;
					case 2:
						System.out.println("Opcion 2 seleccionada");
						ej1.getLibrosEditor(servicio);
						break;
					case 3:
						System.out.println("Opcion 3 seleccionada");
						ej1.getLibrosPrecio(servicio);
						break;
					case 4:
						System.out.println("Opcion 4 seleccionada");
						break;
					case 5:
						salir = true;
						break;
					default:
						System.out.println("Solo números entre 1 y 5");
					}
				} catch (InputMismatchException e) {
					System.out.println("Debes insertar un número");
					sn.next();
				}
			}
			while (!salir);
			

		} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XMLDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void getApellidos(XPathQueryService servicio) throws XMLDBException {
		String query = "for $apellido in distinct-values(doc(\"/db/pruebas/libros.xml\")//libro/autor) return $apellido";
        //String query = "for $l in doc(\"/db/pruebas/libros.xml\")//libro where $l/precio < 50 return $l/titulo/text()";

		ResourceSet resultado = servicio.query(query);
		ResourceIterator itera = resultado.getIterator();
      
		if (!itera.hasMoreResources()) {
			System.out.println("No hay resultados");
		} else {
			while (itera.hasMoreResources()) {
				Resource elemento = itera.nextResource();
				System.out.println(elemento.getContent());
			}
		}
	}
	
	private void getLibrosEditor(XPathQueryService servicio) throws XMLDBException {
		String query = "for $l in doc(\"/db/pruebas/libros.xml\")//libro where exists($l/editor) return $l";
        //String query = "for $l in doc(\"/db/pruebas/libros.xml\")//libro where $l/precio < 50 return $l/titulo/text()";

		ResourceSet resultado = servicio.query(query);
		ResourceIterator itera = resultado.getIterator();
      
		if (!itera.hasMoreResources()) {
			System.out.println("No hay resultados");
		} else {
			while (itera.hasMoreResources()) {
				Resource elemento = itera.nextResource();
				System.out.println(elemento.getContent());
			}
		}
	}
	
	private void getLibrosPrecio(XPathQueryService servicio) throws XMLDBException {
		String query = "for $l in doc(\"/db/pruebas/libros.xml\")//libro where $l/precio < 50 return $l/titulo/text()";

		ResourceSet resultado = servicio.query(query);
		ResourceIterator itera = resultado.getIterator();
      
		if (!itera.hasMoreResources()) {
			System.out.println("No hay resultados");
		} else {
			while (itera.hasMoreResources()) {
				Resource elemento = itera.nextResource();
				System.out.println(elemento.getContent());
			}
		}
	}
}
