package existDB;

import javax.xml.transform.OutputKeys;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;


/**
 * Hello world!
 *
 */
public class ConectarEXISTDB 
{
	private static String URI = "xmldb:exist://192.168.56.104:8080/exist/xmlrpc";

    /**
     * args[0] Should be the name of the collection to access
     * args[1] Should be the name of the resource to read from the collection
     */
    public static void main(String args[]) throws Exception {
        
        final String driver = "org.exist.xmldb.DatabaseImpl";
        final String nombreColeccion = "db/pruebas";
        final String nombreRecurso = "libros.xml";
        
        // initialize database driver
        Class cl = Class.forName(driver);
        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");
        DatabaseManager.registerDatabase(database);
        
        Collection col = null;
        XMLResource res = null;
        try {    
            // get the collection
	        col = DatabaseManager.getCollection(URI + nombreColeccion, "miusuario", "Pass!123456");
            //col = DatabaseManager.getCollection(URI + nombreColeccion);
            col.setProperty(OutputKeys.INDENT, "no");
            
            res = (XMLResource)col.getResource(nombreRecurso);
            
            if(res == null) {
                System.out.println("document not found!");
            } else {
                System.out.println(res.getContent());
            }
            
        } finally {
            //dont forget to clean up!
            
            if(res != null) {
                
            }
            
            if(col != null) {
                try { col.close(); } catch(XMLDBException xe) {xe.printStackTrace();}
            }
        }
    }}
