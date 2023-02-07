package com.controlador;

import java.io.Serializable;

import com.dao.VoluntarioDAO;
import com.modelo.Voluntario;
import com.dao.impl.VoluntarioImplMySQL;
import com.dao.impl.VoluntarioImplOO;
import com.dao.impl.VoluntarioImplXML;

public class VoluntarioControlador implements VoluntarioDAO {
	private VoluntarioDAO voluntario;
	
	public VoluntarioControlador (String tipoDeBD) {
		if (tipoDeBD.equalsIgnoreCase("OO") ) {
			voluntario = new VoluntarioImplOO();
		} else if (tipoDeBD.equalsIgnoreCase("mysql") ) {
			voluntario = new VoluntarioImplMySQL();
		} else if (tipoDeBD.equalsIgnoreCase("xml") ) {
			voluntario = new VoluntarioImplXML();
		}
	}

	@Override
	public boolean inserta(Voluntario elemento) {
		voluntario.inserta(elemento);
		return false;
	}

	@Override
	public boolean elimina(Integer id) {
		voluntario.elimina(id);
		return false;
	}

	@Override
	public boolean modifica(Integer id, Voluntario elemento) {
		voluntario.modifica(id, elemento);
		return false;
	}

	@Override
	public Voluntario consulta(Integer id) {
		voluntario.consulta(id);
		return null;
	}


}
