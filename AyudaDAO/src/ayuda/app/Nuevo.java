package ayuda.app;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.controlador.NecesidadControlador;
import com.controlador.NecesitadoControlador;
import com.controlador.VoluntarioControlador;
import com.modelo.Necesidad;
import com.modelo.Necesitado;
import com.modelo.Voluntario;

public class Nuevo {

	public static void main(String[] args) {

		// ------------------UTILIZAMOS LO DEFINIDO ANTES-------------
		//obtener la f�brica de la conexi�n actual para crear una sesi�n
		//SessionFactory fabrica = com.controlador.HibernateUtil.getSessionFactory();
		//------------------------------------------------------------
		// creamos la sesi�n
		//Session sesion = fabrica.openSession();	
		// creamos la transacci�n de la sesi�n
		//Transaction tx = sesion.beginTransaction();
		
		Voluntario vol1 = new Voluntario(1, "Juan", "Garcia", 31, "Masculino", "Soltero", "Mañana");
		Voluntario vol2 = new Voluntario(2, "Pepa", "Garrote", 64, "Femenino", "Casada", "Tarde");
		Voluntario vol3 = new Voluntario(3, "Jimena", "Valero", 43, "Femenino", "Casada", "Noche");
		
		VoluntarioControlador vc = new VoluntarioControlador("xml");
		System.out.println(vc.consulta(1));
		
		//vc.inserta(vol1);
		//vc.inserta(vol2);
		//vc.inserta(vol3);
		//System.out.println(vc.consulta(1));
		//System.out.println(vc.consulta(vol1.getId()));

		//System.out.println(vc.consulta(vol1.getId()));
		

		/*
		vc.inserta(vol1);
		vc.inserta(vol2);
		vc.inserta(vol3);
		*/
		//vc.modifica(vol2.getId(), vol2);
		
		Necesitado nec1 = new Necesitado(1, "Alvaro", "Perez", 19, "Masculino", "Soltero");
		Necesitado nec2 = new Necesitado(2, "Francisca", "Legarre", 76, "Femenino", "Casada");
		Necesitado nec3 = new Necesitado(3, "Eustaquio", "Ferrer", 54, "Masculino", "Casado");
		NecesitadoControlador nc = new NecesitadoControlador("xml");
		System.out.println(nc.consulta(nec1.getId()));
		//nc.elimina(nec2.getId());
		//nc.inserta(nec1);
		//nc.inserta(nec2);
		//nc.inserta(nec3);
		Necesidad nece1 = new Necesidad(1, nec1, vol1, "Transporte", "Mañana");
		Necesidad nece2 = new Necesidad(2, nec2, vol2, "Comida", "Tarde");
		Necesidad nece3 = new Necesidad(3, nec3, vol3, "Limpieza", "Noche");

		NecesidadControlador necec = new NecesidadControlador("xml");
		System.out.println(necec.consulta(nece1.getId()));

		//necec.inserta(nece1);
		//necec.inserta(nece2);
		//necec.inserta(nece3);
		//necec.modifica(nece.getId(), nece2);
		/*
		 * PRUEBAS OO:
		

		VoluntarioControlador vc = new VoluntarioControlador("OO");
		
		vc.inserta(vol);
		vc.modifica(vol.getId(), vol2);
		vc.elimina(vol2.getId());
		
		Necesitado nec = new Necesitado(1, "nombre", "Apellido", 19, "Masculino", "Soltero");
		Necesitado nec2 = new Necesitado(2, "nombre2", "Apellido", 19, "Masculino", "Soltero");
		Necesitado nec3 = new Necesitado(3, "nombre3", "Apellido", 19, "Masculino", "Soltero");
		NecesitadoControlador nc = new NecesitadoControlador("OO");
		nc.inserta(nec);
		nc.inserta(nec3);
		nc.modifica(nec.getId(), nec2);
		nc.elimina(nec3.getId());
		
		Necesidad nece = new Necesidad(1, nec, vol, "Transporte", "Mañana");
		Necesidad nece2 = new Necesidad(2, nec, vol, "Comida", "Mañana");
		NecesidadControlador necec = new NecesidadControlador("OO");
		necec.inserta(nece);
		necec.inserta(nece2);
		
		necec.modifica(nece.getId(), nece2);
		necec.elimina(nece2.getId());
		*/
		
		

		System.out.println("FUNCIONO!!");
		

		System.exit(0);	
		
	}

	private static int proximo_id(Session sesion) {
		Query<Integer> q = sesion.createQuery("select max(codigo) from Libro");

		Integer res = q.getSingleResult();
		return res.intValue() + 1;
	}

}
