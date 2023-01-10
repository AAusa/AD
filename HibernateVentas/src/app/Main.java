package app;


import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
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
 * @author Alvaro
 *
 */
public class Main {
	/**
	 * Menu en el que el usuario selecciona la opcion a ejecutar y la lanza
	 * @param args
	 */
	public static void main(String[] args) {

		SessionFactory fabrica = HibernateUtil.getSessionFactory();
		Session sesion = fabrica.openSession();	
		Transaction tx = sesion.beginTransaction();
		
		Scanner sn = new Scanner(System.in);
		boolean salir = false;
		int opcion;
		do{
			System.out.println("\nMenu:");
			System.out.println("\t1.- Modificar el precio de todos los productos de forma que incluyan un IVA del 21%");
			System.out.println("\t2.- Eliminar las ventas realizadas por el cliente 20.");
			System.out.println("\t3.- Salir");
			try {
				opcion = Utilidades.pedirEntero("Escribe una de las opciones:");
				switch (opcion) {
				case 1:
					System.out.println("Opcion 1 seleccionada");
					addIVA(sesion);
					break;
				case 2:
					System.out.println("Opcion 2 seleccionada");
					eliminarVentas(sesion);
					break;
				case 3:
					salir = true;
					break;
				default:
					System.out.println("Solo números entre 1 y 3");
				}
			} catch (InputMismatchException e) {
				System.out.println("Debes insertar un número");
				sn.next();
			}
		}
		while (!salir);
		tx.commit();
		sesion.close();
		fabrica.close();
		System.exit(0);	
		
	}

	/**
	 * Opcion que annade el iva a los productos
	 * @param sesion
	 */
	private static void addIVA(Session sesion) {
		Query<Producto> q = sesion.createQuery("from Producto");
		List <Producto> lista = q.list();
		Iterator <Producto> iter = lista.iterator();
		System.out.println("Numero de productos a modificar:"  + lista.size());
		while (iter.hasNext())
		{
			Producto productos = (Producto) iter.next();
			System.out.print("\tAnterior: "+productos.getPrecio()+"\t");
			Producto p = (Producto)sesion.get(Producto.class, (int)productos.getId());
			p.setPrecio((float) (productos.getPrecio() * 1.21));
			System.out.print("Nuevo: "+p.getPrecio()+"\n");
			sesion.update(p);

		}
	}
	
	/**
	 * Elimina las ventas del cliente cuyo id sea introducido, en caso contrario lo muestra con un mensaje
	 * @param sesion
	 */
	private static void eliminarVentas(Session sesion) {
		int id = Utilidades.pedirEntero("Introduce el id del cliente");
		Query<Cliente> q = sesion.createQuery("from Cliente where id=:id");
		q.setParameter("id", id);
		List <Cliente> lista = q.list();
		Iterator <Cliente> iter = lista.iterator();
		
		//Sale si no esta el cliente indicado
		if(lista.size() == 0) {
			System.out.println("El id indicado no corresponde a ningun cliente");
			return;
		}
		
		//Recorre el cliente indicado
		while (iter.hasNext())
		{
			Cliente clientes = (Cliente) iter.next();
			Query<Venta> q2 = sesion.createQuery("from Venta where cliente.id = :id");
			q2.setParameter("id", id);
			List <Venta> lista2 = q2.list();
			Iterator <Venta> iter2 = lista2.iterator();
			
			//Sale si no tiene ventas
			if(lista2.size() == 0) {
				System.out.println("El cliente no tiene ventas");
				return;
			}
			//Recorre las ventas y las elimina
			while (iter2.hasNext())
			{
				Venta ventas = (Venta) iter2.next();
				Venta v = (Venta)sesion.get(Venta.class, (int)ventas.getIdVenta());
				sesion.delete(v);
			}

		}
		System.out.println("Operacion realizada correctamente");
	}

}
