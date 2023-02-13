package com.dao.impl;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import com.dao.NecesidadDAO;
import com.modelo.Necesidad;
import com.modelo.Voluntario;

public class NecesidadImplOO implements NecesidadDAO {
	private static ODB db;

	
	public NecesidadImplOO() {
		db = ODBFactory.open("bd//NecesidadOO.db");
	}
	
	public static ODB crearConexion() {
		return db;
	}
	@Override
	public boolean inserta(Necesidad elemento) {
		db.store(elemento);
		db.commit();
		System.out.println("Necesidad insertado");
		return true;
	}

	@Override
	public boolean elimina(Integer id) {
		boolean valor =false;
		IQuery query = new CriteriaQuery(Necesidad.class, Where.equal("id", id));
		Objects<Necesidad> objetos = db.getObjects(query);
		try {
			Necesidad necesidad = (Necesidad) objetos.getFirst();
			db.delete(necesidad);
			db.commit();
			valor = true;
			System.out.println("Necesidad eliminado");
		} catch (IndexOutOfBoundsException i) {
			i.printStackTrace();
		}
		
		return valor;
	}

	@Override
	public boolean modifica(Integer id, Necesidad elemento) {
		boolean valor =false;
		IQuery query = new CriteriaQuery(Necesidad.class, Where.equal("id", id));
		Objects<Necesidad> objetos = db.getObjects(query);
		try {
			Necesidad necesidad = (Necesidad) objetos.getFirst();
			necesidad.setNombre(elemento.getNombre());
			necesidad.setNecesitado(elemento.getNecesitado());
			necesidad.setVoluntario(elemento.getVoluntario());
			necesidad.setDisponibilidad(elemento.getDisponibilidad());
			necesidad.setId(elemento.getId());
			db.store(necesidad);
			db.commit();
			valor = true;
			System.out.println("Voluntario modificado");
		} catch (IndexOutOfBoundsException i) {
			i.printStackTrace();
		}
		return valor;
	}

	@Override
	public Necesidad consulta(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
