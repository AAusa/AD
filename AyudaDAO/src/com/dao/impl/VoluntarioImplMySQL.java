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
/**
 * Clase que implementa los metodos de VoluntarioDAO con la BD Mysql:
 * @author Alvaro
 *
 */
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
		try {
			Transaction tx = sesion.beginTransaction();
			sesion.save(elemento);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean elimina(Integer id) {
		try {
			Transaction tx = sesion.beginTransaction();
			Voluntario v = (Voluntario) sesion.get(Voluntario.class, id);
			sesion.delete(v);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean modifica(Voluntario elemento) {
		try {
			Transaction tx = sesion.beginTransaction();
			Voluntario v = (Voluntario) sesion.get(Voluntario.class, elemento.getId());
			v.setApellido(elemento.getApellido());
			v.setDisponibilidad(elemento.getDisponibilidad());
			v.setEdad(elemento.getEdad());
			v.setEstadoCivil(elemento.getEstadoCivil());
			v.setId(elemento.getId());
			v.setNecesidads(elemento.getNecesidads());
			v.setNombre(elemento.getNombre());
			v.setSexo(elemento.getSexo());
			sesion.update(v);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public String consulta() {
		return consulta1() + consulta2();
	}

	public String consulta1() {
		String resultado = "Nombre, apellido y necesidad de los voluntarios cuya disponibilidad es la tarde:\n";

		try {
			List<Object> listaNombres = new ArrayList<Object>();
			String queryTexto = "SELECT v.nombre, v.apellido, n.nombre FROM Voluntario as v INNER JOIN Necesidad as n ON v.id = n.voluntario.id WHERE v.disponibilidad LIKE 'Tarde'";
			Query query = sesion.createQuery(queryTexto);
			List<Object> lista = query.getResultList();
			Iterator<Object> iter = lista.iterator();
			while (iter.hasNext()) {
				Object objeto = (Object) iter.next();
				Object[] resultadosString = (Object[]) objeto;
				resultado += "\tNombre = " + (String) resultadosString[0] + "\tApellido = "
						+ (String) resultadosString[1] + "\tNecesidad=" + (String) resultadosString[2] + "\n";
				listaNombres.add(objeto);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "ERROR en la consulta1";
		}
		return resultado;
	}

	public String consulta2() {
		String resultado = "Nombre, apellido y necesidad de los voluntarios cuya edad es mayor de 40 años:\n";
		try {
			List<Object> listaNombres = new ArrayList<Object>();
			String queryTexto = "SELECT v.nombre, v.apellido, n.nombre FROM Voluntario as v INNER JOIN Necesidad as n ON v.id = n.voluntario.id WHERE v.edad > 40";
			Query query = sesion.createQuery(queryTexto);
			List<Object> lista = query.getResultList();
			Iterator<Object> iter = lista.iterator();
			while (iter.hasNext()) {
				Object objeto = (Object) iter.next();
				Object[] resultadosString = (Object[]) objeto;
				resultado += "\tNombre = " + (String) resultadosString[0] + "\tApellido = "
						+ (String) resultadosString[1] + "\tNecesidad=" + (String) resultadosString[2] + "\n";
				listaNombres.add(objeto);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return "ERROR en la consulta2";
		}
		return resultado;
	}

}
