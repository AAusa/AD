package com.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

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
		Necesidad n = (Necesidad) sesion.get(Necesidad.class, id);
		sesion.delete(n);
		tx.commit();
		return false;
	}

	@Override
	public boolean modifica(Necesidad elemento) {
		Transaction tx = sesion.beginTransaction();
		Necesidad n = (Necesidad)sesion.get(Necesidad.class, elemento.getId());
		n.setId(elemento.getId());
		n.setDisponibilidad(elemento.getDisponibilidad());
		n.setNecesitado(elemento.getNecesitado());
		n.setNombre(elemento.getNombre());
		n.setVoluntario(elemento.getVoluntario());		
		sesion.update(n);
		tx.commit();
		return false;
	}

	@Override
	public String consulta() {
		return consulta1()+consulta2();
	}
	
	public String consulta1() {
		String resultado = "Nombre y apellido del voluntario correspondiente a la necesidad limpieza:\n";
		List<Object> listaNombres = new ArrayList<Object>();
		String queryTexto = "SELECT v.nombre, v.apellido, n.nombre FROM Necesidad as n INNER JOIN Voluntario as v ON v.id = n.voluntario.id WHERE n.nombre LIKE 'Limpieza'";
		Query query = sesion.createQuery(queryTexto);
		List<Object> lista = query.getResultList();
		Iterator<Object> iter = lista.iterator();
		while (iter.hasNext()) {
			Object objeto = (Object) iter.next();
			Object[] resultadosString = (Object[])objeto;
			resultado += "\tNombre = "+(String)resultadosString[0]+"\tApellido = "+(String)resultadosString[1]+"\tNecesidad="+(String)resultadosString[2]+"\n";
			listaNombres.add(objeto);
		}
		return resultado;
	}
	
	public String consulta2() {
		String resultado = "\nNombre y apellido del necesitado correspondiente a la necesidad a realizar por la tarde:\n";
		List<Object> listaNombres = new ArrayList<Object>();
		String queryTexto = "SELECT necesitado.nombre, necesitado.apellido, necesidad.disponibilidad FROM Necesidad as necesidad INNER JOIN Necesitado as necesitado ON necesitado.id = necesidad.necesitado.id WHERE necesidad.disponibilidad LIKE 'Tarde'";
		Query query = sesion.createQuery(queryTexto);
		List<Object> lista = query.getResultList();
		Iterator<Object> iter = lista.iterator();
		while (iter.hasNext()) {
			Object objeto = (Object) iter.next();
			Object[] resultadosString = (Object[])objeto;
			resultado += "\tNombre = "+(String)resultadosString[0]+"\tApellido = "+(String)resultadosString[1]+"\tDisponibilidad="+(String)resultadosString[2]+"\n";
			listaNombres.add(objeto);
		}
		return resultado;
	}

}
