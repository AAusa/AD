package main;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;

import datos.Departamento;
import datos.Empleado;

public class CrearBaseDatos {
	public static void main(String[] args) {
		Departamento dep = new Departamento(0, "Informatica", "Zaragoza");
		Departamento dep1 = new Departamento(0, "Matematicas", "Zaragoza");
		Departamento dep2 = new Departamento(0, "Fisica", "Zaragoza");
		Departamento dep3 = new Departamento(0, "Filosofia", "Zaragoza");
		Departamento dep4 = new Departamento(10, "Filosofia", "Zaragoza");
		
		//es el jefe de informatica
		Empleado emp = new Empleado(0, "Martinez", "profesor", null, 12000, 12, null, dep);
		//empleados de informatica
		Empleado emp2 = new Empleado(1, "Rodriguez", "profesor", null, 12000, 12, emp, dep);
		Empleado emp3 = new Empleado(2, "Muñoz", "profesor", null, 12000, 12, emp, dep);
		Empleado emp4 = new Empleado(3, "Roy", "profesor", null, 12000, 12, emp, dep);
		// jefe mates
		Empleado emp5 = new Empleado(4, "Abdelcarim", "profesor", null, 12000, 12, null, dep1);
		// empleados de mates
		Empleado emp6 = new Empleado(1, "Jimena", "profesor", null, 12000, 12, emp5, dep1);
		Empleado emp7 = new Empleado(1, "Perez", "profesor", null, 12000, 12, emp5, dep1);
		Empleado emp8 = new Empleado(5, "Perez", "profesor", null, 12000, 12, emp5, dep4);
		
		//abrir la base de datos
		ODB bd = ODBFactory.open("D:\\Documentos\\BBDD\\personal.db", "miUsuario", "Pass!123456");
		bd.store(dep);
		bd.store(dep1);
		bd.store(dep2);
		//bd.store(dep3);
		bd.store(emp);
		bd.store(emp2);
		bd.store(emp3);
		bd.store(emp4);
		bd.store(emp5);
		bd.store(emp6);
		bd.store(emp7);
		
		bd.store(dep4);		
		bd.store(emp8);
		bd.close();
	}
}
