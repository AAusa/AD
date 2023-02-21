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


public class VoluntarioImplOO implements VoluntarioDAO {
	private ODB db;

	
	public VoluntarioImplOO() {
		db = ODBFactory.open("bd/AyudaOO.db");
	}
	
	public ODB crearConexion() {
		return db;
	}

	@Override
	public boolean inserta(Voluntario elemento) {
		db.store(elemento);
		db.commit();
		db.close();
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
	public String consulta(Integer id) {
		// TODO Auto-generated method stub
		return consulta1(id);
	}
	
	public String consulta1(Integer id) {
		String resultado = "Nombre y apellido de los voluntarios disponibles para ayudar por la mañana mayores de 18 años ordenados por nombre y apellidos de forma descendente:\n";
		//ODB odb = ODBFactory.open("VoluntarioOO.db");
		/*
		ICriterion criterio = new And()
				.add(Where.equal("disponibilidad", "Mañana"))
				.add(Where.gt("edad", 18));
				*/
		IQuery query = new CriteriaQuery(Voluntario.class,Where.equal("disponibilidad", "Mañana"));
		//query.orderByAsc("nombre, apellido");
		Objects <Voluntario> voluntarios = this.db.getObjects(query);
		System.out.println(voluntarios.size() + " voluntarios");
		Iterator<Voluntario> iter = voluntarios.iterator();
		while (iter.hasNext()) {
			Voluntario v = iter.next();
			resultado += "Nombre: "+v.getNombre()+"\tApellido:"+v.getApellido();
		}
		return resultado;
	}
	
	public void cerrarConexion() {
		db.close();
	}

	
}
