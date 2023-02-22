package ayuda.app;

import java.util.Iterator;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.neodatis.odb.ODB;
import org.neodatis.odb.Objects;
import com.controlador.NecesidadControlador;
import com.controlador.NecesitadoControlador;
import com.controlador.NeodatisUtils;
import com.controlador.VoluntarioControlador;
import com.dao.impl.VoluntarioImplMySQL;
import com.dao.impl.VoluntarioImplOO;
import com.modelo.Necesidad;
import com.modelo.Necesitado;
import com.modelo.Voluntario;


public class rellenarBDOO {

	public static void main(String[] args) {
		Voluntario vol1 = new Voluntario(1, "Juan", "Garcia", 31, "Masculino", "Soltero", "Mañana");
		Voluntario vol2 = new Voluntario(2, "Pepa", "Garrote", 64, "Femenino", "Casada", "Tarde");
		Voluntario vol3 = new Voluntario(3, "Jimena", "Valero", 43, "Femenino", "Casada", "Noche");
		VoluntarioControlador vc = new VoluntarioControlador("oo");
		vc.inserta(vol1);
		vc.inserta(vol2);
		vc.inserta(vol3);
		
		Necesitado nec1 = new Necesitado(1, "Alvaro", "Perez", 19, "Masculino", "Soltero");
		Necesitado nec2 = new Necesitado(2, "Francisca", "Legarre", 76, "Femenino", "Casada");
		Necesitado nec3 = new Necesitado(3, "Eustaquio", "Ferrer", 54, "Masculino", "Casado");
		NecesitadoControlador nc = new NecesitadoControlador("oo");
		nc.inserta(nec1);
		nc.inserta(nec2);
		nc.inserta(nec3);
		
		Necesidad nece1 = new Necesidad(1, nec1, vol1, "Transporte", "Mañana");
		Necesidad nece2 = new Necesidad(2, nec2, vol2, "Comida", "Tarde");
		Necesidad nece3 = new Necesidad(3, nec3, vol3, "Limpieza", "Noche");
		NecesidadControlador necec = new NecesidadControlador("oo");
		necec.inserta(nece1);
		necec.inserta(nece2);
		necec.inserta(nece3);
		
		System.out.println("FUNCIONO!!");
		

		System.exit(0);	
		
	}
}
