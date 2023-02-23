package com.dao.impl;

import java.util.Iterator;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.And;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Or;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import com.dao.NecesidadDAO;
import com.modelo.Necesidad;
import com.modelo.Voluntario;
/**
 * Clase que implementa los metodos de NecesidadDAO con la BD OO:
 * @author Alvaro
 *
 */
public class NecesidadImplOO implements NecesidadDAO {
	private static ODB db;

	public NecesidadImplOO(ODB bd) {
		this.db = bd;
	}

	public static ODB crearConexion() {
		return db;
	}

	@Override
	public boolean inserta(Necesidad elemento) {
		try {
			db.store(elemento);
			db.commit();
			System.out.println("Necesidad insertado");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean elimina(Integer id) {
		boolean valor = false;
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
	public boolean modifica(Necesidad elemento) {
		boolean valor = false;
		IQuery query = new CriteriaQuery(Necesidad.class, Where.equal("id", elemento.getId()));
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
	public String consulta() {
		return "En esta BD hay consultas en las tablas: Voluntario y Necesitado";
	}

}
