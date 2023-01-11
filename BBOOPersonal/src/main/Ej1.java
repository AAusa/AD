package main;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;

public class Ej1 {

	public static void main(String[] args) {
		ODB bd = ODBFactory.open("D:\\Documentos\\BBDD\\personal.db", "miUsuario", "Pass!123456");
		//bd.store();
		bd.close();
	}

}
