package app;

import java.util.Iterator;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import controlador.HibernateUtil;

import tablas.*;

public class Main {

	public static void main(String[] args) {

		// ------------------UTILIZAMOS LO DEFINIDO ANTES-------------
		//obtener la f�brica de la conexi�n actual para crear una sesi�n
		SessionFactory fabrica = HibernateUtil.getSessionFactory();
		//------------------------------------------------------------
		// creamos la sesi�n
		Session sesion = fabrica.openSession();	
		// creamos la transacci�n de la sesi�n
		Transaction tx = sesion.beginTransaction();
		verEmpleados(sesion);
		
		tx.commit();
		
		
		
		sesion.close();
		fabrica.close();
		System.exit(0);	
		
	}

	private static void verEmpleados(Session sesion) {
		System.out.println("Leo los empleados");	
		Query<Empleado> q = sesion.createQuery("from Empleado");
		
		List <Empleado> lista = q.list();
		// Obtenemos un Iterador y recorremos la lista.
		Iterator <Empleado> iter = lista.iterator();
		System.out.println("Numero de registros:"  + lista.size());
		while (iter.hasNext())
		{
		   //extraer el objeto
			Empleado empleado = (Empleado) iter.next(); 
			System.out.println(empleado.toString());		   
		}
		System.out.println("FUNCIONO!!");
	}

}
