package com.controlador;

import com.dao.NecesitadoDAO;
import com.dao.impl.NecesitadoImplMySQL;
import com.dao.impl.NecesitadoImplOO;
import com.dao.impl.NecesitadoImplXML;
import com.modelo.Necesitado;
/**
 * Controlador de la clase Necesitado:
 * @author Alvaro
 *
 */
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
		return necesitado.inserta(elemento);
	}

	@Override
	public boolean elimina(Integer id) {
		return necesitado.elimina(id);
	}

	@Override
	public boolean modifica(Necesitado elemento) {
		return necesitado.modifica(elemento);
	}

	@Override
	public String consulta() {
		return necesitado.consulta();
	}

}
