package main;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;
import datos.Departamento;
import datos.Empleado;
/**
 * Realiza un programa Java que utilice NeoDatis para ejecutar las siguientes operaciones de
gestión de objetos de la base de datos personal:
A) Insertar un departamento.
B) Insertar un empleado.
C) Visualizar todos los departamentos.
D) Visualizar todos los empleados, indicando para cada empleado su jefe y su
departamento. (OPCIONAL) 
 * @author alu
 *
 */
public class Ej1 {

	public static void main(String[] args) {
		ODB bd = ODBFactory.open("D:\\Documentos\\BBDD\\personal.db", "miUsuario", "Pass!123456");
		Ej1 ej1 = new Ej1();
		Scanner sn = new Scanner(System.in);
		boolean salir = false;
		int opcion;
		do{
			System.out.println("\nMenu:");
			System.out.println("\t1) Insertar un departamento.");
			System.out.println("\t2) Insertar un empleado.");
			System.out.println("\t3) Visualizar todos los departamentos.");
			System.out.println("\t4) Visualizar todos los empleados, indicando para cada empleado su jefe y su departamento. (OPCIONAL) ");
			System.out.println("\t5) Salir");
			try {
				opcion = Utilidades.pedirEntero("Escribe una de las opciones:");
				switch (opcion) {
				case 1:
					System.out.println("Opcion 1 seleccionada");
					ej1.addDpto(bd, sn);
					break;
				case 2:
					System.out.println("Opcion 2 seleccionada");
					/**
					Opciones:
					Para añadir el jefe y el dpto habra que pedirle todos los datos de estos
					Mostrar todos los empleados y preguntar al usuario que elemento de la lista es el jefe
					Tener un metodo que averigue los jefes de cada dpto y asi poder asignarlo
					*/
					break;
				case 3:
					System.out.println("Opcion 3 seleccionada");
					//Igual que el 4 pero sin mostrando todo
					break;
				case 4:
					ej1.verEmpleados(bd);
					break;
				case 5:
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
		while (!salir);

		bd.close();
	}

	private void verEmpleados(ODB bd) {
		System.out.println("Opcion 3 seleccionada");
		Objects<Empleado> empleados = bd.getObjects(Empleado.class);

		for (Empleado e: empleados) {
			if(e.getJefe() != null) {
				System.out.println(e);
				System.out.println("Jefe: "+e.getJefe().getApellido());
				System.out.println("Dpto: "+e.getDepartamento().getNombre());	
			}
		}
	}

	public void addDpto(ODB bd, Scanner sc) {
		int id = Utilidades.pedirEntero("Id:");
		String nombre = Utilidades.pedirCadena("Nombre:");
		String localidad = Utilidades.pedirCadena("Localidad:");	
		Departamento dep = new Departamento(id, nombre, localidad);
		bd.store(dep);
	}
}
