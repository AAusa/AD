package app;

import java.util.Iterator;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import controlador.HibernateUtil;

import tablas.*;

public class ListarClientes {

	public static void main(String[] args) {

		// ------------------UTILIZAMOS LO DEFINIDO ANTES-------------
		//obtener la f�brica de la conexi�n actual para crear una sesi�n
		SessionFactory fabrica = HibernateUtil.getSessionFactory();
		//------------------------------------------------------------
		// creamos la sesi�n
		Session sesion = fabrica.openSession();	
		// creamos la transacci�n de la sesi�n
		Transaction tx = sesion.beginTransaction();
		verClientes(sesion);
		
		tx.commit();
		
		
		
		sesion.close();
		fabrica.close();
		System.exit(0);	
		
	}

	private static void verClientes(Session sesion) {
		System.out.println("Leo los libros");	
		Query<Clientes> q = sesion.createQuery("from Clientes");
		
		List <Clientes> lista = q.list();
		// Obtenemos un Iterador y recorremos la lista.
		Iterator <Clientes> iter = lista.iterator();
		System.out.println("Numero de registros:"  + lista.size());
		while (iter.hasNext())
		{
		   //extraer el objeto
			Clientes clientes = (Clientes) iter.next(); 
			System.out.println(clientes.getId().getNombre());		   
		}

		System.out.println("FUNCIONO!!");
	}

}
