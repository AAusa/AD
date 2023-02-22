package com.controlador;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;

public class NeodatisUtils {

	private static ODB db = null;
	
	public static ODB buildODBConection(String basedatos) {
		
		try {
			if (db == null || db.isClosed()) {
				System.out.println("Es null o está cerrada");
				db = ODBFactory.open(basedatos); 
			}
			return db;
		} catch(org.neodatis.odb.ODBRuntimeException e) {
			System.out.println("Error intentando abrir la BD OO " + basedatos);
			throw e;
		}
		
	}
	
	public static ODB getODBConnection() {
		return db;
	}
}