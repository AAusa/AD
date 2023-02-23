package ayuda.app;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.controlador.NecesidadControlador;
import com.controlador.NecesitadoControlador;
import com.controlador.VoluntarioControlador;
import com.modelo.Necesidad;
import com.modelo.Necesitado;
import com.modelo.Voluntario;
/**
 * Clase encargada de mostrar el menu y ejecutar operaciones interactuando con las clases Gestion y los controladores
 * @author Alvaro
 *
 */
public class Menu {
	/**
	 * Muestra el menu y ejecutar operaciones interactuando con las clases Gestion y los controladores
	 */
	public void mostrarMenu() {
		boolean salir = false;
		int opcionBD, opcionCRUD, opcionTabla;
		Scanner sn = new Scanner(System.in);
		String BD = "";
		Gestion gestion = new Gestion();
		Menu menu = new Menu();
		Voluntario v = null;
		Necesitado n = null;
		Necesidad n2 = null;
		int id = 0;
		do {
			try {
				opcionBD = getOpcionDB();
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
					return;
				default:
					System.out.println("Solo numeros entre 1 y 4");
				}
				opcionCRUD = getOpcionCRUD();
				//---
				if (opcionCRUD == 5) {
					salir = true;
					return;
				}

				opcionTabla = getOpcionTabla();
				switch (opcionTabla) {
				case 1:
					VoluntarioControlador vc = new VoluntarioControlador(BD);
					switch (opcionCRUD) {
					case 1:
						v = gestion.getVoluntario(opcionBD, opcionTabla, false);
						if(!vc.inserta(v)) {
							System.out.println("Operacion fallida");
						}
						break;
					case 2:
						id = gestion.getId();
						if(!vc.elimina(id)) {
							System.out.println("Operacion fallida");
						}
						break;
					case 3:
						v = gestion.getVoluntario(opcionBD, opcionTabla, true);
						if(!vc.modifica(v)) {
							System.out.println("Operacion fallida");
						}						
						break;
					case 4:
						System.out.println(vc.consulta());
						break;
					case 5:
						salir = true;
						return;
					default:
						System.out.println("Solo numeros entre 1 y 5");
					}
					break;
				case 2:
					NecesitadoControlador nc = new NecesitadoControlador(BD);
					switch (opcionCRUD) {
					case 1:
						n = gestion.getNecesitado(opcionBD, opcionTabla, false);
						if(!nc.inserta(n)) {
							System.out.println("Operacion fallida");
						}
						break;
					case 2:
						id = gestion.getId();
						if(!nc.elimina(id)) {
							System.out.println("Operacion fallida");
						}
						break;
					case 3:
						n = gestion.getNecesitado(opcionBD, opcionTabla, true);
						if(!nc.modifica(n)) {
							System.out.println("Operacion fallida");
						}						
						break;
					case 4:
						System.out.println(nc.consulta());
						break;
					case 5:
						salir = true;
						return;
					default:
						System.out.println("Solo numeros entre 1 y 5");
					}
					break;
				case 3:
					NecesidadControlador nd = new NecesidadControlador(BD);
					switch (opcionCRUD) {
					case 1:
						n2 = gestion.getNecesidad(opcionBD, opcionTabla, false);
						if(!nd.inserta(n2)) {
							System.out.println("Operacion fallida");
						}
						break;
					case 2:
						id = gestion.getId();
						if(!nd.elimina(id)) {
							System.out.println("Operacion fallida");
						}
						break;
					case 3:
						n2 = gestion.getNecesidad(opcionBD, opcionTabla, true);
						if(!nd.modifica(n2)) {
							System.out.println("Operacion fallida");
						}
						break;
					case 4:
						System.out.println(nd.consulta());
						break;
					case 5:
						salir = true;
						return;
					default:
						System.out.println("Solo numeros entre 1 y 5");
					}
					break;
				case 4:
					salir = true;
					return;
				default:
					System.out.println("Solo numeros entre 1 y 4");
				}

			} catch (InputMismatchException e) {
				System.out.println("Debes insertar un numero");
				sn.next();
			}
		} while (!salir);
	}
	
	/**
	 * Obtiene la tabla a usar:
	 * @return
	 */
	private int getOpcionTabla() {
		int opcionTabla;
		System.out.println("Indica la tabla con la que deseas trabajar:");
		System.out.println("\t1) Voluntario");
		System.out.println("\t2) Necesitado");
		System.out.println("\t3) Necesidad");
		System.out.println("\t4) Salir");
		opcionTabla = Utilidades.pedirEntero("Escribe una de las opciones:");
		return opcionTabla;
	}

	/**
	 * Obtiene el metodo del crud a usar:
	 * @return
	 */
	private int getOpcionCRUD() {
		int opcionCRUD;
		System.out.println("Indica la accion que deseas realizar:");
		System.out.println("\t1) Insertar");
		System.out.println("\t2) Eliminar");
		System.out.println("\t3) Modificar");
		System.out.println("\t4) Consultar");
		System.out.println("\t5) Salir");
		opcionCRUD = Utilidades.pedirEntero("Escribe una de las opciones:");
		return opcionCRUD;
	}

	/**
	 * Obtiene la BD a usar:
	 * @return
	 */
	private int getOpcionDB() {
		int opcionBD;
		System.out.println("Indica la BD con la que deseas trabajar:");
		System.out.println("\t1) MySQL (Hibernate)");
		System.out.println("\t2) OO (Neodatis)");
		System.out.println("\t3) XML (ExistDB)");
		System.out.println("\t4) Salir");
		opcionBD = Utilidades.pedirEntero("Escribe una de las opciones:");
		return opcionBD;
	}
}
