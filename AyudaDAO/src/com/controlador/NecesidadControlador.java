package com.controlador;

import com.dao.NecesidadDAO;
import com.dao.impl.NecesidadImplMySQL;
import com.dao.impl.NecesidadImplOO;
import com.dao.impl.NecesidadImplXML;
import com.modelo.Necesidad;
import com.modelo.NecesidadId;

public class NecesidadControlador implements NecesidadDAO {
	private NecesidadDAO necesidad;
	
	public NecesidadControlador (String tipoDeBD) {
		if (tipoDeBD.equalsIgnoreCase("OO") ) {
			necesidad = new NecesidadImplOO();
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
	public boolean elimina(NecesidadId id) {
		necesidad.elimina(id);
		return false;
	}

	@Override
	public boolean modifica(NecesidadId id, Necesidad elemento) {
		necesidad.modifica(id, elemento);
		return false;
	}

	@Override
	public Necesidad consulta(NecesidadId id) {
		necesidad.consulta(id);
		return null;
	}

}
