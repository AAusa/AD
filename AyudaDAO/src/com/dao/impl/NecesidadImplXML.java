package com.dao.impl;

import com.dao.NecesidadDAO;
import com.modelo.Necesidad;

public class NecesidadImplXML implements NecesidadDAO {

	@Override
	public boolean inserta(Necesidad elemento) {
		System.out.println("Aquí se realizaría la operación");
		return false;
	}

	@Override
	public boolean elimina(Integer id) {
		System.out.println("Aquí se realizaría la operación");
		return false;
	}

	@Override
	public boolean modifica(Integer id, Necesidad elemento) {
		System.out.println("Aquí se realizaría la operación");
		return false;
	}

	@Override
	public String consulta(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}


}
