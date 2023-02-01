package existDB;

import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XPathQueryService;

public class PreguntarEXISTDB {
	private static String URI = "xmldb:exist://192.168.56.104:8080/exist/xmlrpc";

    /**
     * args[0] Should be the name of the collection to access
     * args[1] Should be the XPath expression to execute
     */
    public static void main(String args[]) throws Exception {
        
        final String driver = "org.exist.xmldb.DatabaseImpl";
        final String nombreColeccion = "db/pruebas";
        final String nombreRecurso = "//libro[precio<50]/titulo";
        
        // initialize database driver
        Class cl = Class.forName(driver);
        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");
        DatabaseManager.registerDatabase(database);
        
        Collection col = null;
        try { 
            col = DatabaseManager.getCollection(URI + nombreColeccion, "miusuario", "Pass!123456");
            XPathQueryService xpqs = (XPathQueryService)col.getService("XPathQueryService", "1.0");
            xpqs.setProperty("indent", "yes");

            ResourceSet result = xpqs.query(nombreRecurso);
            ResourceIterator i = result.getIterator();
            Resource res = null;
            while(i.hasMoreResources()) {
                try {
                    res = i.nextResource();
                    System.out.println(res.getContent());
                } finally {
                    //dont forget to cleanup resources
                }
            }
        } finally {
            //dont forget to cleanup
            if(col != null) {
                try { col.close(); } catch(XMLDBException xe) {xe.printStackTrace();}
            }
        }
    }
}
