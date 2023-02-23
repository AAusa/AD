package ayuda.app;

import java.util.InputMismatchException;

import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.neodatis.odb.ODB;
import org.neodatis.odb.Objects;
import com.dao.impl.NecesidadImplMySQL;
import com.dao.impl.NecesidadImplOO;
import com.dao.impl.NecesitadoImplMySQL;
import com.dao.impl.NecesitadoImplOO;
import com.dao.impl.VoluntarioImplMySQL;
import com.dao.impl.VoluntarioImplOO;
import com.modelo.Necesidad;
import com.modelo.Necesitado;
import com.modelo.Voluntario;
/**
 * Clase encargada de crear los objetos e IDs con los que trabajar con la BD
 * @author Alvaro
 *
 */
public class Gestion {
	/**
	 * Pide datos y da voluntario:
	 * @param opcionBD
	 * @param opcionTabla
	 * @param modificar: boolean que dice si la opcion es insertar (false) o modificar (true) 
	 * @return
	 */
	public Voluntario getVoluntario(int opcionBD, int opcionTabla, boolean modificar) {
		int id = 0;
		Utilidades.mostrarEnPantalla("Cuestionario voluntario:");
		if(!modificar) {//insertar
			id = getIdBD(opcionBD, opcionTabla);			
		}
		else { //modificar
			id = getId();			
		}
		String nombre = Utilidades.pedirCadena("Introduce nombre:");
		String apellido = Utilidades.pedirCadena("Introduce apellido:");
		int edad = Utilidades.pedirEntero("Introduce edad:");
		String sexo = Utilidades.pedirCadena("Introduce sexo (Masculino / Femenino):");
		String estadoCivil = Utilidades
				.pedirCadena("Introduce estadoCivil (Soltero / Soltera - Casado / Casada - Viudo / Viuda)");
		String disponibilidad = Utilidades.pedirCadena("Introduce disponibilidad (Mañana / Tarde / Noche):");
		return new Voluntario(id, nombre, apellido, edad, sexo, estadoCivil, disponibilidad);
	}

	/**
	 * Pide datos y da necesitado:
	 * @param opcionBD
	 * @param opcionTabla
	 * @param modificar: boolean que dice si la opcion es insertar (false) o modificar (true) 
	 * @return
	 */
	public Necesitado getNecesitado(int opcionBD, int opcionTabla, boolean modificar) {
		int id = 0;
		Utilidades.mostrarEnPantalla("Cuestionario necesitado:");
		if(!modificar) {//insertar
			id = getIdBD(opcionBD, opcionTabla);			
		}
		else { //modificar
			id = getId();			
		}
		String nombre = Utilidades.pedirCadena("Introduce nombre:");
		String apellido = Utilidades.pedirCadena("Introduce apellido:");
		int edad = Utilidades.pedirEntero("Introduce edad:");
		String sexo = Utilidades.pedirCadena("Introduce sexo (Masculino / Femenino):");
		String estadoCivil = Utilidades
				.pedirCadena("Introduce estadoCivil (Soltero / Soltera - Casado / Casada - Viudo / Viuda)");
		return new Necesitado(id, nombre, apellido, edad, sexo, estadoCivil);
	}

	/**
	 * Pide datos y da necesidad:
	 * @param opcionBD
	 * @param opcionTabla
	 * @param modificar: boolean que dice si la opcion es insertar (false) o modificar (true) 
	 * @return
	 */
	public Necesidad getNecesidad(int opcionBD, int opcionTabla, boolean modificar) {
		int id = 0;
		Utilidades.mostrarEnPantalla("Cuestionario necesidad:");
		if(!modificar) {//insertar
			id = getIdBD(opcionBD, opcionTabla);			
		}
		else { //modificar
			id = getId();			
		}		Necesitado n = getNecesitado(opcionBD, opcionTabla, true);
		Voluntario v = getVoluntario(opcionBD, opcionTabla, true);
		String nombre = Utilidades.pedirCadena("Introduce nombre:");
		String disponibilidad = Utilidades.pedirCadena("Introduce disponibilidad (Mañana / Tarde / Noche):");
		return new Necesidad(id, n, v, nombre, disponibilidad);
	}

	/**
	 * Devuelve el siguiente id en el que se puede insertar en la BD:
	 * @param opcionBD:    mysql, oo, xml
	 * @param opcionTabla: voluntario, necesitado, necesidad
	 * @return
	 */
	private int getIdBD(int opcionBD, int opcionTabla) {
		Session sesion = null;
		ODB bd = null;
		int contador = 0;
		if (opcionBD == 1) { // mysql
			if (opcionTabla == 1) {// vol
				sesion = VoluntarioImplMySQL.crearConexion();
				Query<Integer> q = sesion.createQuery("select max(id) from Voluntario");
				Integer res = q.getSingleResult();
				return res.intValue() + 1;
			} else if (opcionTabla == 2) {// necesitado
				sesion = NecesitadoImplMySQL.crearConexion();
				Query<Integer> q = sesion.createQuery("select max(id) from Necesitado");
				Integer res = q.getSingleResult();
				return res.intValue() + 1;
			} else if (opcionTabla == 3) {// necesidad
				sesion = NecesidadImplMySQL.crearConexion();
				Query<Integer> q = sesion.createQuery("select max(id) from Necesidad");
				Integer res = q.getSingleResult();
				return res.intValue() + 1;
			}
		} else if (opcionBD == 2) { // oo
			if (opcionTabla == 1) {// vol
				bd = VoluntarioImplOO.crearConexion();
				Objects<Voluntario> voluntarios = bd.getObjects(Voluntario.class);
				for (Voluntario v : voluntarios) {
					contador++;
				}
				return contador + 1;
			} else if (opcionTabla == 2) {// necesitado
				bd = NecesitadoImplOO.crearConexion();
				Objects<Necesitado> necesitados = bd.getObjects(Necesitado.class);
				for (Necesitado n : necesitados) {
					contador++;
				}
				return contador + 1;
			} else if (opcionTabla == 3) {// necesidad
				bd = NecesidadImplOO.crearConexion();
				Objects<Necesidad> necesidades = bd.getObjects(Necesidad.class);
				for (Necesidad n : necesidades) {
					contador++;
				}
				return contador + 1;
			}
		}
		return 0;
	}

	/**
	 * Pide id al usuario:
	 * @return
	 */
	public int getId() {
		int id = Utilidades.pedirEntero("Introduce id:");
		return id;
	}
}
