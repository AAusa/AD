package com.dao.impl;

import java.io.Serializable;

import com.dao.GenericoDAO;
import com.dao.VoluntarioDAO;
import com.modelo.Voluntario;

public class VoluntarioImplXML implements VoluntarioDAO {

	@Override
	public boolean inserta(Voluntario elemento) {
		System.out.println("Aquí se realizaría la operación");
		return false;
	}

	@Override
	public boolean elimina(Integer id) {
		System.out.println("Aquí se realizaría la operación");
		return false;
	}

	@Override
	public boolean modifica(Integer id, Voluntario elemento) {
		System.out.println("Aquí se realizaría la operación");
		return false;
	}

	@Override
	public String consulta(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
