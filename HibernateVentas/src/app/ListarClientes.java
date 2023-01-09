package app;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import controlador.HibernateUtil;

import tablas.*;
/**
 * Realiza un programa Java que utilice Hibernate para ejecutar las siguientes manipulaciones de
datos (UPDATE o DELETE) sobre la base de datos ventas:
A) Modificar el precio de todos los productos de forma que incluyan un IVA del 21%.
B) Eliminar las ventas realizadas por el cliente 20.
En cada apartado, utiliza parámetros nombrados
 * @author alu
 *
 */
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
		addIVA(sesion);
		
		tx.commit();
		
		
		
		sesion.close();
		fabrica.close();
		System.exit(0);	
		
	}

	//UPDATE Producto SET precio=12 WHERE id = 2
	private static void addIVA(Session sesion) {
		System.out.println("Leo los libros");
		List<Float> nuevosPrecios = new ArrayList<Float>();
		Query<Producto> q = sesion.createQuery("from Producto");
		
		List <Producto> lista = q.list();
		// Obtenemos un Iterador y recorremos la lista.
		Iterator <Producto> iter = lista.iterator();
		System.out.println("Numero de registros:"  + lista.size());
		while (iter.hasNext())
		{
		   //extraer el objeto
			Producto productos = (Producto) iter.next(); 
			nuevosPrecios.add((float) (productos.getPrecio() * 1.21));
			//System.out.println("Anterior: "+productos.getPrecio()+" ");		   
		}

		System.out.println("FUNCIONO!!");
	}

}
