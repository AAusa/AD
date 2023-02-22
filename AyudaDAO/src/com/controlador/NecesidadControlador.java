package com.controlador;

import com.dao.NecesidadDAO;
import com.dao.impl.NecesidadImplMySQL;
import com.dao.impl.NecesidadImplOO;
import com.dao.impl.NecesidadImplXML;
import com.modelo.Necesidad;

public class NecesidadControlador implements NecesidadDAO {
	private NecesidadDAO necesidad;
	
	public NecesidadControlador (String tipoDeBD) {
		if (tipoDeBD.equalsIgnoreCase("OO") ) {
			necesidad = new NecesidadImplOO(NeodatisUtils.buildODBConection("bd/AyudaOO.db"));
		} else if (tipoDeBD.equalsIgnoreCase("mysql") ) {
			necesidad = new NecesidadImplMySQL();
		} else if (tipoDeBD.equalsIgnoreCase("xml") ) {
			necesidad = new NecesidadImplXML();
		}
	}
	@Override
	public boolean inserta(Necesidad elemento) {
		necesidad.inserta(elemento);
		return false;
	}
	
	@Override
	public boolean elimina(Integer id) {
		necesidad.elimina(id);
		return false;
	}
	@Override
	public boolean modifica(Necesidad elemento) {
		necesidad.modifica(elemento);
		return false;
	}
	@Override
	public String consulta() {
		return necesidad.consulta();
	}

}
