package com.dao.impl;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;

import com.dao.VoluntarioDAO;
import com.modelo.Voluntario;

public class VoluntarioImplMySQL implements VoluntarioDAO {
	private static Session sesion;
	
	public VoluntarioImplMySQL() {
		SessionFactory fabrica = com.controlador.HibernateUtil.getSessionFactory();
		sesion = fabrica.openSession();	
	}
	
	public static Session crearConexion() {
		return sesion;
	}
	@Override
	public boolean inserta(Voluntario elemento) {
		sesion.save(elemento);
		return false;
	}

	@Override
	public boolean elimina(Integer id) {
		Voluntario v = (Voluntario)sesion.get(Voluntario.class, id);
		sesion.delete(v);
		return false;
	}

	@Override
	public boolean modifica(Integer id, Voluntario elemento) {
		Voluntario v = (Voluntario)sesion.get(Voluntario.class, id);
		sesion.update(elemento);
		return false;
	}

	@Override
	public Voluntario consulta(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
