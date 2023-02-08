package com.dao.impl;

import java.io.Serializable;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import com.dao.VoluntarioDAO;
import com.modelo.Voluntario;


public class VoluntarioImplOO implements VoluntarioDAO {
	private static ODB db;

	
	public VoluntarioImplOO() {
		db = ODBFactory.open("bd//VoluntarioOO.db");
	}
	
	public static ODB crearConexion() {
		return db;
	}

	@Override
	public boolean inserta(Voluntario elemento) {
		db.store(elemento);
		db.commit();
		System.out.println("Voluntario insertado");
		return true;
	}

	@Override
	public boolean elimina(Integer id) {
		boolean valor =false;
		IQuery query = new CriteriaQuery(Voluntario.class, Where.equal("id", id));
		Objects<Voluntario> objetos = db.getObjects(query);
		try {
			Voluntario voluntario = (Voluntario) objetos.getFirst();
			db.delete(voluntario);
			db.commit();
			valor = true;
			System.out.println("Voluntario eliminado");
		} catch (IndexOutOfBoundsException i) {
			i.printStackTrace();
		}
		
		return valor;
	}

	@Override
	public boolean modifica(Integer id, Voluntario elemento) {
		boolean valor =false;
		IQuery query = new CriteriaQuery(Voluntario.class, Where.equal("id", id));
		Objects<Voluntario> objetos = db.getObjects(query);
		try {
			Voluntario voluntario = (Voluntario) objetos.getFirst();
			voluntario.setNombre(elemento.getNombre());
			voluntario.setApellido(elemento.getApellido());
			voluntario.setDisponibilidad(elemento.getDisponibilidad());
			voluntario.setEdad(elemento.getEdad());
			voluntario.setEstadoCivil(elemento.getEstadoCivil());
			voluntario.setId(elemento.getId());
			voluntario.setSexo(elemento.getSexo());
			db.store(voluntario);
			db.commit();
			valor = true;
			System.out.println("Voluntario modificado");
		} catch (IndexOutOfBoundsException i) {
			i.printStackTrace();
		}
		return valor;
	}

	@Override
	public Voluntario consulta(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
