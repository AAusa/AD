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
			voluntario = new VoluntarioImplOO(NeodatisUtils.buildODBConection("bd/AyudaOO.db"));
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
	public boolean modifica(Voluntario elemento) {
		voluntario.modifica(elemento);
		return false;
	}

	@Override
	public String consulta() {
		
		return voluntario.consulta();
	}
	
	public void cerrarConexion() {
		//voluntario.cerrarConexion();
	}

}
