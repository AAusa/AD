package com.dao.impl;

import java.io.Serializable;
import java.util.Iterator;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.And;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import com.dao.VoluntarioDAO;
import com.modelo.Voluntario;
/**
 * Clase que implementa los metodos de VoluntarioDAO con la BDOO:
 * @author Alvaro
 *
 */
public class VoluntarioImplOO implements VoluntarioDAO {
	private static ODB db;

	public VoluntarioImplOO(ODB bd) {
		this.db = bd;
	}

	public static ODB crearConexion() {
		return db;
	}

	@Override
	public boolean inserta(Voluntario elemento) {
		try {
			db.store(elemento);
			db.commit();
			System.out.println("Voluntario insertado");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean elimina(Integer id) {
		boolean valor = false;
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
	public boolean modifica(Voluntario elemento) {
		boolean valor = false;
		IQuery query = new CriteriaQuery(Voluntario.class, Where.equal("id", elemento.getId()));
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
	public String consulta() {
		// TODO Auto-generated method stub
		return consulta1();
	}

	public String consulta1() {
		String resultado = "Nombre y apellido de los voluntarios disponibles para ayudar por la mañana mayores de 18 años ordenados por apellidos de forma ascendente:\n";
		try {
			ICriterion criterio = new And().add(Where.equal("disponibilidad", "Mañana")).add(Where.gt("edad", 18));
			IQuery query = new CriteriaQuery(Voluntario.class, criterio);
			query.orderByAsc("apellido");
			Objects<Voluntario> voluntarios = this.db.getObjects(query);
			System.out.println(voluntarios.size() + " voluntarios");
			Iterator<Voluntario> iter = voluntarios.iterator();
			while (iter.hasNext()) {
				Voluntario v = iter.next();
				resultado += "Nombre: " + v.getNombre() + "\tApellido:" + v.getApellido() + "\n";
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return "ERROR en la consulta1";
		}
		return resultado;
	}

	public void cerrarConexion() {
		db.close();
	}

}
