package com.controlador;

import com.dao.NecesidadDAO;
import com.dao.impl.NecesidadImplMySQL;
import com.dao.impl.NecesidadImplOO;
import com.dao.impl.NecesidadImplXML;
import com.modelo.Necesidad;
/**
 * Controlador de la clase Necesidad:
 * @author Alvaro
 *
 */
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
		
		return necesidad.inserta(elemento);
	}
	
	@Override
	public boolean elimina(Integer id) {
		
		return necesidad.elimina(id);
	}
	@Override
	public boolean modifica(Necesidad elemento) {
		
		return necesidad.modifica(elemento);
	}
	@Override
	public String consulta() {
		return necesidad.consulta();
	}

}
