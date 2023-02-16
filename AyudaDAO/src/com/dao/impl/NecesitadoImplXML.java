package com.dao.impl;

import com.dao.NecesitadoDAO;
import com.modelo.Necesitado;

public class NecesitadoImplXML implements NecesitadoDAO {

	@Override
	public boolean inserta(Necesitado elemento) {
		System.out.println("Aquí se realizaría la operación");
		return false;
	}

	@Override
	public boolean elimina(Integer id) {
		System.out.println("Aquí se realizaría la operación");
		return false;
	}

	@Override
	public boolean modifica(Integer id, Necesitado elemento) {
		System.out.println("Aquí se realizaría la operación");
		return false;
	}

	@Override
	public String consulta(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
