package app;

import java.sql.Date;
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

		//Operaciones:
		Scanner sn = new Scanner(System.in);
		boolean salir = false;
		int opcion; //Guardaremos la opcion del usuario
		do{
			System.out.println("1.- Modificar un departamento");
			System.out.println("2.- Insertar un empleado");
			System.out.println("3.- Leer un empleado, y además, su departamento correspondiente");
			System.out.println("4.- Eliminar un empleado");
			System.out.println("5.- Eliminar un departamento. Previamente, se debe eliminar todos los empleados de dicho departamento. Utiliza una transacción.");
			System.out.println("6.- Salir");
			try {
				System.out.println("Escribe una de las opciones");
				opcion = sn.nextInt();
				switch (opcion) {
				case 1:
					System.out.println("Modificar un departamento");
					updateDpto(sesion);
					tx.commit();
					break;
				case 2:
					System.out.println("Insertar un empleado");
					break;
				case 3:
					System.out.println("Leer un empleado, y además, su departamento correspondiente");
					break;
				case 4:
					System.out.println("Eliminar un empleado");
					break;
				case 5:
					System.out.println("Eliminar un departamento. Previamente, se debe eliminar todos los empleados de dicho departamento. Utiliza una transacción.");
					break;
				case 6:
					salir = true;
					break;
				default:
					System.out.println("Solo números entre 1 y 6");
				}
			} catch (InputMismatchException e) {
				System.out.println("Debes insertar un número");
				sn.next();
			}
		}
		while (!salir) ;




		sesion.close();
		fabrica.close();
		System.exit(0);


	}


	private static void updateDpto(Session sesion) {
		Scanner sn = new Scanner(System.in);
		System.out.println("Introduce el id del dpto. a modificar:");
		int id = sn.nextInt();
		Departamento dpto = (Departamento)sesion.get(Departamento.class, (byte)id);
		int opcion; //Guardaremos la opcion del usuario
		System.out.println("1.- Modificar nombre");
		System.out.println("2.- Modificar localidad");
		try {
			System.out.println("Escribe una de las opciones");
			opcion = sn.nextInt();sn.nextLine();
			switch (opcion) {
			case 1:
				System.out.println("Introduce nuevo nombre:");
				String nombreNew = sn.nextLine(); 
				System.out.println(nombreNew);
				dpto.setNombre(nombreNew);
				break;
			case 2:
				System.out.println("Introduce nueva localidad:");
				String localidadNew = sn.nextLine();
				dpto.setLocalidad(localidadNew); sn.nextLine();
				break;
			default:
				System.out.println("Solo números entre 1 y 3");
			}
		} catch (InputMismatchException e) {
			System.out.println("Debes insertar un número");
			sn.next();
		}
		System.out.println(dpto);
		sesion.update(dpto);
		System.out.println("FUNCIONO!!");
	}
	
	private static void insertarEmpleado(Session sesion) {
		Scanner sn = new Scanner(System.in);
		System.out.println("Introduce id del dpto al q pertenece:");
		String idNew = sn.nextLine(); 
		System.out.println("Introduce nuevo apellido:");
		String apellido = sn.nextLine(); 
		System.out.println("Introduce nuevo oficio:");
		String oficio = sn.nextLine(); 
		System.out.println("Introduce nueva fecha alta:");
		String fecha = sn.nextLine();
		System.out.println("Introduce nuevo salario:");
		float salario = sn.nextInt();sn.nextLine();	
		System.out.println("Introduce nueva comision:");
		float comision = sn.nextInt();sn.nextLine();
		Empleado e = new Empleado(proximo_id(sesion), apellido, oficio, fecha, salario, comision);
	}
	
	private static int proximo_id(Session sesion) {
		Query<Integer> q = sesion.createQuery("select max(idEmp) from Empleado");
		Integer res = q.getSingleResult();
		return res.intValue()+1;
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
