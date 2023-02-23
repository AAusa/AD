package com.controlador;

import java.io.Serializable;

import com.dao.VoluntarioDAO;
import com.modelo.Voluntario;
import com.dao.impl.VoluntarioImplMySQL;
import com.dao.impl.VoluntarioImplOO;
import com.dao.impl.VoluntarioImplXML;
/**
 * Controlador de la clase Voluntario:
 * @author Alvaro
 *
 */
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
		
		return voluntario.inserta(elemento);
	}

	@Override
	public boolean elimina(Integer id) {
		return voluntario.elimina(id);
	}

	@Override
	public boolean modifica(Voluntario elemento) {
		
		return voluntario.modifica(elemento);
	}

	@Override
	public String consulta() {
		return voluntario.consulta();
	}
}
