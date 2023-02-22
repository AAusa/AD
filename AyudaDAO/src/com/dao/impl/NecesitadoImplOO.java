package com.dao.impl;

import java.util.Iterator;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Or;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import com.dao.NecesitadoDAO;
import com.modelo.Necesitado;
import com.modelo.Voluntario;

public class NecesitadoImplOO implements NecesitadoDAO {
	private static ODB db;
	
	
	public NecesitadoImplOO(ODB bd) {
		this.db = bd;
	}
	
	public static ODB crearConexion() {
		return db;
	}
	
	@Override
	public boolean inserta(Necesitado elemento) {
		db.store(elemento);
		db.commit();
		System.out.println("Necesitado insertado");
		return true;
	}

	@Override
	public boolean elimina(Integer id) {
		boolean valor =false;
		IQuery query = new CriteriaQuery(Necesitado.class, Where.equal("id", id));
		Objects<Necesitado> objetos = db.getObjects(query);
		try {
			Necesitado necesitado = (Necesitado) objetos.getFirst();
			db.delete(necesitado);
			db.commit();
			valor = true;
			System.out.println("Necesitado eliminado");
		} catch (IndexOutOfBoundsException i) {
			i.printStackTrace();
		}
		
		return valor;
	}

	@Override
	public boolean modifica(Necesitado elemento) {
		boolean valor =false;
		IQuery query = new CriteriaQuery(Necesitado.class, Where.equal("id", elemento.getId()));
		Objects<Necesitado> objetos = db.getObjects(query);
		try {
			Necesitado necesitado = (Necesitado) objetos.getFirst();
			necesitado.setNombre(elemento.getNombre());
			necesitado.setApellido(elemento.getApellido());
			necesitado.setEdad(elemento.getEdad());
			necesitado.setEstadoCivil(elemento.getEstadoCivil());
			necesitado.setId(elemento.getId());
			necesitado.setSexo(elemento.getSexo());
			db.store(necesitado);
			db.commit();
			valor = true;
			System.out.println("Necesitado modificado");
		} catch (IndexOutOfBoundsException i) {
			i.printStackTrace();
		}
		return valor;
	}

	@Override
	public String consulta() {
		// TODO Auto-generated method stub
		return consulta1();
	}
	
	public String consulta1() {
		String resultado = "Nombre y apellido de los necesitados sean hombre o mujer que estan casados ordenados por apellido de forma ascendente:\n";
		//ODB odb = ODBFactory.open("VoluntarioOO.db");
		
		ICriterion criterio = new Or()
				.add(Where.equal("estadoCivil", "Casado"))
				.add(Where.equal("estadoCivil", "Casada"));
			
		IQuery query = new CriteriaQuery(Necesitado.class, criterio);
		//IQuery query = new CriteriaQuery(Voluntario.class,Where.equal("disponibilidad", "Mañana"));
		query.orderByAsc("apellido");
		Objects <Necesitado> necesitados = this.db.getObjects(query);
		System.out.println(necesitados.size() + " necesitados");
		Iterator<Necesitado> iter = necesitados.iterator();
		while (iter.hasNext()) {
			Necesitado n = iter.next();
			resultado += "Nombre: "+n.getNombre()+"\tApellido:"+n.getApellido()+"\n";
		}
		return resultado;
	}

}
