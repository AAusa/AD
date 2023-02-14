package com.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.dao.NecesidadDAO;
import com.modelo.Necesidad;
import com.modelo.Voluntario;

public class NecesidadImplMySQL implements NecesidadDAO {
private static Session sesion;
	
	
	public NecesidadImplMySQL() {
		SessionFactory fabrica = com.controlador.HibernateUtil.getSessionFactory();
		sesion = fabrica.openSession();	
	}
	
	public static Session crearConexion() {
		return sesion;
	}
	@Override
	public boolean inserta(Necesidad elemento) {
		Transaction tx = sesion.beginTransaction();		
		sesion.save(elemento);
		tx.commit();
		return false;
	}

	@Override
	public boolean elimina(Integer id) {
		Transaction tx = sesion.beginTransaction();
		Necesidad n = (Necesidad)sesion.get(Necesidad.class, id);
		sesion.delete(n);
		tx.commit();
		return false;
	}

	@Override
	public boolean modifica(Integer id, Necesidad elemento) {
		Transaction tx = sesion.beginTransaction();
		//Voluntario v = (Voluntario)sesion.get(Voluntario.class, id);
		sesion.update(elemento);
		tx.commit();
		return false;
	}

	@Override
	public Necesidad consulta(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}



}
