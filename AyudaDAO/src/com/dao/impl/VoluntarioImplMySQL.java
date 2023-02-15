package com.dao.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;

import com.dao.VoluntarioDAO;
import com.modelo.Necesidad;
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
		Transaction tx = sesion.beginTransaction();		
		sesion.save(elemento);
		tx.commit();
		return false;
	}

	@Override
	public boolean elimina(Integer id) {
		Transaction tx = sesion.beginTransaction();
		Voluntario v = (Voluntario)sesion.get(Voluntario.class, id);
		sesion.delete(v);
		tx.commit();
		return false;
	}

	@Override
	public boolean modifica(Integer id, Voluntario elemento) {
		Transaction tx = sesion.beginTransaction();
		//Voluntario v = (Voluntario)sesion.get(Voluntario.class, id);
		sesion.update(elemento);
		tx.commit();
		return false;
	}

	@Override
	public Voluntario consulta(Integer id) {
		List<Object> listaNombres = new ArrayList<Object>();
		String queryTexto = "SELECT v.nombre, v.apellido, n.nombre FROM Voluntario as v INNER JOIN Necesidad as n ON v.id = n.voluntario.id WHERE v.disponibilidad LIKE 'Tarde'";
		
		Query query = sesion.createQuery(queryTexto);
		List<Object> lista = query.getResultList();
		Iterator<Object> iter = lista.iterator();
		while (iter.hasNext()) {
			Object objeto = (Object) iter.next();
			listaNombres.add(objeto);
		}
		return listaNombres;
	}
	

}
