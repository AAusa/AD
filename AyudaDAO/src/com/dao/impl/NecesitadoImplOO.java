package com.dao.impl;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import com.dao.NecesitadoDAO;
import com.modelo.Necesitado;
import com.modelo.Voluntario;

public class NecesitadoImplOO implements NecesitadoDAO {
	private static ODB db;
	
	public NecesitadoImplOO() {
		db = ODBFactory.open("bd//NecesitadoOO.db");
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
	public boolean modifica(Integer id, Necesitado elemento) {
		boolean valor =false;
		IQuery query = new CriteriaQuery(Necesitado.class, Where.equal("id", id));
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
	public Necesitado consulta(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
