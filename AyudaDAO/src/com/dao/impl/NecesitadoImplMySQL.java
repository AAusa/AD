package com.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

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
	public String consulta(Integer id) {
		// TODO Auto-generated method stub
		return consulta1(id);
	}
	
	public String consulta1(Integer id) {
		String resultado = "Nombre, apellido y necesidad de las personas que necesitan comida por la tarde:\n";
		List<Object> listaNombres = new ArrayList<Object>();
		String queryTexto = "SELECT necesitado.nombre, necesitado.apellido, necesidad.nombre FROM Necesitado as necesitado INNER JOIN Necesidad as necesidad ON necesitado.id = necesidad.necesitado.id WHERE necesidad.nombre LIKE 'Comida' AND necesidad.disponibilidad LIKE 'Tarde'";
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

}
