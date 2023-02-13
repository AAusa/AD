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
		SessionFactory fabrica = com.controlador.HibernateUtil.getSessionFactory();
		//------------------------------------------------------------
		// creamos la sesi�n
		Session sesion = fabrica.openSession();	
		// creamos la transacci�n de la sesi�n
		Transaction tx = sesion.beginTransaction();
		Voluntario vol = new Voluntario(3, "nombre", "Apellido", 19, "Masculino", "Soltero", "Mañana");
		Voluntario vol2 = new Voluntario(2, "nombre2", "Apellido", 19, "Masculino", "Soltero", "Mañana");
		VoluntarioControlador vc = new VoluntarioControlador("mysql");
		vc.inserta(vol);
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
		sesion.save(vol);
		

		System.out.println("FUNCIONO!!");
		
		//tx.commit();
		
		
		
		sesion.close();
		fabrica.close();
		System.exit(0);	
		
	}

	private static int proximo_id(Session sesion) {
		Query<Integer> q = sesion.createQuery("select max(codigo) from Libro");
		
		Integer res = q.getSingleResult();
		return res.intValue()+1;
	}

}
