package com.dao;

import java.io.Serializable;

public interface GenericoDAO<T, ID extends Serializable>  {

	public boolean inserta(T elemento);
	public boolean elimina(ID id);
	public boolean modifica(ID id, T elemento);
	public T consulta(ID id);
}