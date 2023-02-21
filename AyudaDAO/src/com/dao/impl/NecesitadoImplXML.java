package com.dao.impl;

import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XPathQueryService;

import com.dao.NecesitadoDAO;
import com.modelo.Necesitado;

public class NecesitadoImplXML implements NecesitadoDAO {
private static XPathQueryService servicio = null;

	
	public NecesitadoImplXML() {
		Class cl = null;
		String driver  =  "org.exist.xmldb.DatabaseImpl";
		Collection miDB = null;
		Database BD = null;
	    String URI = "xmldb:exist://192.168.56.104:8080/exist/xmlrpc/db/pruebas";

		try {
			cl = Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			BD = (Database)cl.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        try {
			DatabaseManager.registerDatabase(BD);
			miDB = DatabaseManager.getCollection(URI, "miusuario", "Pass!123456");
			servicio = (XPathQueryService)miDB.getService("XPathQueryService", "1.0");

		} catch (XMLDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	
	public static XPathQueryService crearConexion() {
		return servicio;
	}
	@Override
	public boolean inserta(Necesitado elemento) {
		System.out.println("Aquí se realizaría la operación");
		return false;
	}

	@Override
	public boolean elimina(Integer id) {
		System.out.println("Aquí se realizaría la operación");
		return false;
	}

	@Override
	public boolean modifica(Integer id, Necesitado elemento) {
		System.out.println("Aquí se realizaría la operación");
		return false;
	}

	@Override
	public String consulta(Integer id) {
		// TODO Auto-generated method stub
		return consulta1()+consulta2();
	}
	
	private String consulta1() {
		String resultado = "Nombre de los necesitados mayores de edad:\n";
		String query = "for $necesitado in doc(\"/db/pruebas/ayudaXML.xml\")//table[@name=\"Necesitado\"] "
				+ "where $necesitado/column[@name=\"Edad\"]>18 return $necesitado/column[@name=\"Nombre\"]/text()";
		ResourceSet res;
		try {
			res = servicio.query(query);
			ResourceIterator itera = res.getIterator();
		      
			if (!itera.hasMoreResources()) {
				System.out.println("No hay resultados");
			} else {
				while (itera.hasMoreResources()) {
					Resource elemento = itera.nextResource();
					resultado += "\t- "+elemento.getContent()+"\n";
				}
			}
		} catch (XMLDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return resultado;
	}
	
	private String consulta2() {
		String resultado = "\nLista numerada en formato xml de los nombres de todos los necesitados:\n";
		String query = "for $necesitado at $i in doc(\"/db/pruebas/ayudaXML.xml\")//table[@name=\"Necesitado\"]\r\n"
				+ "return <necesitado>{$i}.{data($necesitado/column[@name=\"Nombre\"])}</necesitado>";
		ResourceSet res;
		try {
			res = servicio.query(query);
			ResourceIterator itera = res.getIterator();
		      
			if (!itera.hasMoreResources()) {
				System.out.println("No hay resultados");
			} else {
				while (itera.hasMoreResources()) {
					Resource elemento = itera.nextResource();
					resultado += "\t"+elemento.getContent()+"\n";
				}
			}
		} catch (XMLDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return resultado;
	}

}
