package com.controlador;

import com.dao.NecesitadoDAO;
import com.dao.impl.NecesitadoImplMySQL;
import com.dao.impl.NecesitadoImplOO;
import com.dao.impl.NecesitadoImplXML;
import com.modelo.Necesitado;

public class NecesitadoControlador implements NecesitadoDAO {
	private NecesitadoDAO necesitado;
	
	public NecesitadoControlador (String tipoDeBD) {
		if (tipoDeBD.equalsIgnoreCase("OO") ) {
			necesitado = new NecesitadoImplOO(NeodatisUtils.buildODBConection("bd/AyudaOO.db"));
		} else if (tipoDeBD.equalsIgnoreCase("mysql") ) {
			necesitado = new NecesitadoImplMySQL();
		} else if (tipoDeBD.equalsIgnoreCase("xml") ) {
			necesitado = new NecesitadoImplXML();
		}
	}
	@Override
	public boolean inserta(Necesitado elemento) {
		necesitado.inserta(elemento);
		return false;
	}

	@Override
	public boolean elimina(Integer id) {
		necesitado.elimina(id);
		return false;
	}

	@Override
	public boolean modifica(Necesitado elemento) {
		necesitado.modifica(elemento);
		return false;
	}

	@Override
	public String consulta() {
		return necesitado.consulta();
	}

}
