package ayuda.app;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.controlador.NecesidadControlador;
import com.controlador.NecesitadoControlador;
import com.controlador.VoluntarioControlador;
import com.modelo.Necesidad;
import com.modelo.Necesitado;
import com.modelo.Voluntario;

public class App {
	public static void main(String[] args) {
		boolean salir = false;
		int opcionBD, opcionCRUD, opcionTabla;
		Scanner sn = new Scanner(System.in);
		String BD = "";
		App app = new App();
		Voluntario v = null;
		Necesitado n = null;
		Necesidad n2 = null;

		int id = 0;
		do{
			System.out.println("Indica la BD con la que deseas trabajar:");
			System.out.println("\t1) MySQL (Hibernate)");
			System.out.println("\t2) OO (Neodatis)");
			System.out.println("\t3) XML (ExistDB)");
			System.out.println("\t4) Salir");
			try {
				opcionBD = Utilidades.pedirEntero("Escribe una de las opciones:");
				switch (opcionBD) {
				case 1:
					BD = "mysql";
					break;
				case 2:
					BD = "oo";
					break;
				case 3:
					BD = "xml";
					break;
				case 4:
					salir = true;
					break;
				default:
					System.out.println("Solo numeros entre 1 y 4");
				}
				System.out.println("Indica la accion que deseas realizar:");
				System.out.println("\t1) Insertar");
				System.out.println("\t2) Eliminar");
				System.out.println("\t3) Modificar");
				System.out.println("\t4) Consultar");
				System.out.println("\t5) Salir");
				
				opcionCRUD = Utilidades.pedirEntero("Escribe una de las opciones:");
				if(opcionCRUD == 5) salir = true;
				
				System.out.println("Indica la tabla con la que deseas trabajar:");
				System.out.println("\t1) Voluntario");
				System.out.println("\t2) Necesitado");
				System.out.println("\t3) Necesidad");
				System.out.println("\t4) Salir");
				opcionTabla = Utilidades.pedirEntero("Escribe una de las opciones:");
				switch (opcionTabla) {
				case 1:
					VoluntarioControlador vc = new VoluntarioControlador(BD);
					switch (opcionCRUD) {
					case 1:
						v = app.getVoluntario(opcionBD);
						vc.inserta(v);
						break;
					case 2:
						id = app.getId();
						vc.elimina(id);
						break;
					case 3:
						v = app.getVoluntario(opcionBD);
						vc.modifica(null, v);
						break;
					case 4:
						vc.consulta(null);
						break;
					case 5:
						salir = true;
						break;
					default:
						System.out.println("Solo numeros entre 1 y 5");
					}
					break;
				case 2:
					NecesitadoControlador nc = new NecesitadoControlador(BD);
					switch (opcionCRUD) {
					case 1:
						n = app.getNecesitado(opcionBD);
						nc.inserta(n);
						break;
					case 2:
						id = app.getId();
						nc.elimina(id);
						break;
					case 3:
						n = app.getNecesitado(opcionBD);
						nc.modifica(null, n);
						break;
					case 4:
						//pedir datos:
						nc.consulta(null);
						break;
					case 5:
						salir = true;
						break;
					default:
						System.out.println("Solo numeros entre 1 y 5");
					}
					break;
				case 3:
					NecesidadControlador nd = new NecesidadControlador(BD);
					switch (opcionCRUD) {
					case 1:
						n2 = app.getNecesidad(opcionBD);
						nd.inserta(n2);
						break;
					case 2:
						id = app.getId();
						nd.elimina(id);
						break;
					case 3:
						n2 = app.getNecesidad(opcionBD);
						nd.modifica(null, n2);
						break;
					case 4:
						//pedir datos:
						nd.consulta(null);
						break;
					case 5:
						salir = true;
						break;
					default:
						System.out.println("Solo numeros entre 1 y 5");
					}
					break;
				case 4:
					salir = true;
					break;
				default:
					System.out.println("Solo numeros entre 1 y 4");
				}
				
				} catch (InputMismatchException e) {
					System.out.println("Debes insertar un numero");
					sn.next();
				}
		}
		while (!salir);
	}
	
	//Pide datos y da voluntario:
	private Voluntario getVoluntario(int opcionBD) {
		Utilidades.mostrarEnPantalla("Cuestionario voluntario:");
		int id = getIdBD(opcionBD);
		String nombre = Utilidades.pedirCadena("Introduce nombre:");
		String apellido = Utilidades.pedirCadena("Introduce apellido:");
		int edad = Utilidades.pedirEntero("Introduce edad:");
		String sexo = Utilidades.pedirCadena("Introduce sexo (Masculino / Femenino):");
		String estadoCivil = Utilidades.pedirCadena("Introduce estadoCivil (Soltero / Soltera - Casado / Casada - Viudo / Viuda)");
		String disponibilidad = Utilidades.pedirCadena("Introduce disponibilidad (Mañana / Tarde / Noche):");
		return new Voluntario(id, nombre, apellido, edad, sexo, estadoCivil, disponibilidad);
	}
	
	//Pide datos y da Necesitado:
	private Necesitado getNecesitado(int opcionBD) {
		Utilidades.mostrarEnPantalla("Cuestionario necesitado:");
		int id = getIdBD(opcionBD);
		String nombre = Utilidades.pedirCadena("Introduce nombre:");
		String apellido = Utilidades.pedirCadena("Introduce apellido:");
		int edad = Utilidades.pedirEntero("Introduce edad:");
		String sexo = Utilidades.pedirCadena("Introduce sexo (Masculino / Femenino):");
		String estadoCivil = Utilidades.pedirCadena("Introduce estadoCivil (Soltero / Soltera - Casado / Casada - Viudo / Viuda)");
		return new Necesitado(id, nombre, apellido, edad, sexo, estadoCivil);
	}
	
	//Pide datos y da Necesidad:
	private Necesidad getNecesidad(int opcionBD) {
		Utilidades.mostrarEnPantalla("Cuestionario necesidad:");
		int id = getIdBD(opcionBD);
		Necesitado n = getNecesitado(opcionBD);
		Voluntario v = getVoluntario(opcionBD);
		String nombre = Utilidades.pedirCadena("Introduce nombre:");
		String disponibilidad = Utilidades.pedirCadena("Introduce disponibilidad (Mañana / Tarde / Noche):");
		return new Necesidad(id, n, v, nombre, disponibilidad);
	}		
	
	//Devuelve el siguiente id en el que se puede insertar en la BD:
	private int getIdBD(int opcionBD) {
		return 0;
	}
	
	//Pide al usuario un id y lo devuelve
	private int getId() {
		int id = Utilidades.pedirEntero("Introduce id:");
		return id;
	}

	private int proximo_id(Session sesion) {
		Query<Integer> q = sesion.createQuery("select max(codigo) from Libro");

		Integer res = q.getSingleResult();
		return res.intValue() + 1;
	}
	
}
