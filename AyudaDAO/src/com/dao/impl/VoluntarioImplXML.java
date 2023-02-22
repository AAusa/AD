package com.dao.impl;

import java.io.Serializable;

import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XPathQueryService;
import com.dao.VoluntarioDAO;
import com.modelo.Voluntario;

public class VoluntarioImplXML implements VoluntarioDAO {
private static XPathQueryService servicio = null;

	
	public VoluntarioImplXML() {
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
	public boolean inserta(Voluntario elemento) {
		System.out.println("Aquí se realizaría la operación");
		return false;
	}

	@Override
	public boolean elimina(Integer id) {
		System.out.println("Aquí se realizaría la operación");
		return false;
	}

	@Override
	public boolean modifica(Voluntario elemento) {
		System.out.println("Aquí se realizaría la operación");
		return false;
	}

	@Override
	public String consulta() {
		// TODO Auto-generated method stub
		return consulta1()+consulta2();
	}
	
	private String consulta1() {
		String resultado = "Nombre de los voluntarios mayores de edad:\n";
		String query = "for $voluntario in doc(\"/db/pruebas/ayudaXML.xml\")//table[@name=\"Voluntario\"] "
				+ "where $voluntario/column[@name=\"Edad\"]>18 return $voluntario/column[@name=\"Nombre\"]/text()";
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
		String resultado = "Lista numerada en formato xml de los nombres todos los voluntarios:\n";
		String query = "for $voluntario at $i in doc(\"/db/pruebas/ayudaXML.xml\")//table[@name=\"Voluntario\"] "
				+ "return <voluntario>{$i}.{data($voluntario/column[@name=\"Nombre\"])}</voluntario>";
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
