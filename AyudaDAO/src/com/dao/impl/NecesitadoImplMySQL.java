package com.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.dao.NecesitadoDAO;
import com.modelo.Necesitado;
import com.modelo.Voluntario;

public class NecesitadoImplMySQL implements NecesitadoDAO {
	private static Session sesion;
	
	public NecesitadoImplMySQL() {
		SessionFactory fabrica = com.controlador.HibernateUtil.getSessionFactory();
		sesion = fabrica.openSession();	
	}
	
	public static Session crearConexion() {
		return sesion;
	}
	
	@Override
	public boolean inserta(Necesitado elemento) {
		Transaction tx = sesion.beginTransaction();		
		sesion.save(elemento);
		tx.commit();
		return false;
	}

	@Override
	public boolean elimina(Integer id) {
		Transaction tx = sesion.beginTransaction();
		Necesitado n = (Necesitado)sesion.get(Necesitado.class, id);
		sesion.delete(n);
		tx.commit();
		return false;
	}

	@Override
	public boolean modifica(Integer id, Necesitado elemento) {
		Transaction tx = sesion.beginTransaction();
		//Voluntario v = (Voluntario)sesion.get(Voluntario.class, id);
		sesion.update(elemento);
		tx.commit();
		return false;
	}

	@Override
	public Necesitado consulta(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
