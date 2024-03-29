package main;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import datos.Departamento;
import datos.Empleado;

public class Ej3 {
	/**
	 * Realiza un programa Java que utilice NeoDatis para ejecutar las siguientes consultas sobre la
base de datos personal:
A) Obtener los empleados que tienen un salario superior a 1000.
B) Obtener los empleados nuevos del año 2015. (OPCIONAL)
C) Obtener los nombres de los empleados del departamento 10.
D) Obtener los nombres de los empleados cuyo jefe es “Pedro López“.
E) Obtener el número de empleados del departamento “Ventas”.
F) Obtener el número de empleados de cada departamento. (OPCIONAL) 
	 * @param args
	 */
	public static void main(String[] args) {
		Ej3 ej3 = new Ej3();
		Scanner sn = new Scanner(System.in);
		boolean salir = false;
		int opcion;
		ODB bd = ODBFactory.open("D:\\Documentos\\BBDD\\personal.db", "miUsuario", "Pass!123456");
		do{
			System.out.println("\nMenu:");
			System.out.println("\t1) Obtener los empleados que tienen un salario superior a 1000.");
			System.out.println("\t2) Obtener los empleados nuevos del año 2015. (OPCIONAL)");
			System.out.println("\t3) Obtener los nombres de los empleados del departamento 10.");
			System.out.println("\t4) Obtener los nombres de los empleados cuyo jefe es “Pedro López“.");
			System.out.println("\t5) Obtener el número de empleados del departamento “Ventas”.");
			System.out.println("\t6) Obtener el número de empleados de cada departamento. (OPCIONAL)");
			System.out.println("\t7) Salir");
			try {
				opcion = Utilidades.pedirEntero("Escribe una de las opciones:");
				switch (opcion) {
				case 1:
					System.out.println("Opcion 1 seleccionada");
					ej3.query1(bd);
					break;
				case 2:
					System.out.println("Opcion 2 seleccionada");
					//comparar date en ICriterion
					break;
				case 3:
					System.out.println("Opcion 3 seleccionada");
					ej3.query3(bd);
					//Igual que el 4 pero sin mostrando todo
					break;
				case 4:
					System.out.println("Opcion 4 seleccionada");
					ej3.query4(bd);
					break;
				case 5:
					ej3.query5(bd);
					break;
				case 6:
					ej3.query6(bd);
					
					break;
				case 7:
					salir = true;
					break;
				default:
					System.out.println("Solo números entre 1 y 7");
				}
			} catch (InputMismatchException e) {
				System.out.println("Debes insertar un número");
				sn.next();
			}
		}
		while (!salir);

		bd.close();


		//Parecido consultaDatos de ConexionBBDD



	}

	private void query1(ODB bd) {
		try {

			ICriterion criterio = Where.gt("salario", 1000);
			IQuery consulta = new CriteriaQuery(Empleado.class, criterio);

			//consulta.orderByAsc("edad");
			//tambien se pueden hacer con And o Or

			//mostrar los resultados de la consulta
			Objects<Empleado> empleados = bd.getObjects(consulta);

			for (Empleado e : empleados) {
				System.out.println("\t"+e.getApellido());
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void query3(ODB bd) {
		try {
			ICriterion criterio = Where.equal("departamento.id_dep", 10);			
			IQuery consulta = new CriteriaQuery(Empleado.class, criterio);
			Objects<Empleado> empleados = bd.getObjects(consulta);

			for (Empleado e : empleados) {
				System.out.println("\t"+e.getApellido());
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private void query4(ODB bd) {
		try {
			ICriterion criterio = Where.equal("jefe.apellido", "Martinez");			
			IQuery consulta = new CriteriaQuery(Empleado.class, criterio);
			Objects<Empleado> empleados = bd.getObjects(consulta);

			for (Empleado e : empleados) {
				System.out.println("\t"+e.getApellido());
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private void query5(ODB bd) {
		int contador = 0;
		try {
			ICriterion criterio = Where.equal("departamento.nombre", "Matematicas");			
			IQuery consulta = new CriteriaQuery(Empleado.class, criterio);
			Objects<Empleado> empleados = bd.getObjects(consulta);

			for (Empleado e : empleados) {
				contador++;
			}
			System.out.println("Hay "+contador+" empleados");

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private void query6(ODB bd) {
		int contador = 0;
		try {
			ICriterion criterio = Where.equal("departamento.nombre", "Matematicas");			
			IQuery consulta = new CriteriaQuery(Empleado.class, criterio);
			Objects<Departamento> departamentos = bd.getObjects(Departamento.class);
			
			// asignar el jugador al equipo
			for (Departamento d : departamentos) {
				ICriterion criterio2 = Where.equal("departamento.nombre", d.getNombre());			
				IQuery consulta2 = new CriteriaQuery(Empleado.class, criterio2);
				Objects<Empleado> empleados = bd.getObjects(consulta2);

				for (Empleado e : empleados) {
					contador++;
				}
				System.out.println("Hay "+contador+" empleados en: "+d.getNombre());

			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}