package existDB;

import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XPathQueryService;

public class ConexionClaseExistDB {

	public static void main(String[] args) {
		// driver que necesito para conectar existDB
		String driver  =  "org.exist.xmldb.DatabaseImpl";
		
		// cargar el driver
		Class cl;
		try {
			cl = Class.forName(driver);
			
			//instancia de la BD
			Database BD = (Database)cl.newInstance();
			
			// registrar el Driver
	        DatabaseManager.registerDatabase(BD);
	        
	        // La cadena de conexion
	        String URI = "xmldb:exist://192.168.56.102:8080/exist/xmlrpc/db/ejemplos";
	        
	        Collection miDB = DatabaseManager.getCollection(URI, "miUsuario", "Pass!123456");
	        
	        XPathQueryService servicio = (XPathQueryService)miDB.getService("XPathQueryService", "1.0");
	        
	        String query = "for $l in doc(\"/db/ejemplos/libros.xml\")//libro where $l/precio < 50 return $l";
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
}
