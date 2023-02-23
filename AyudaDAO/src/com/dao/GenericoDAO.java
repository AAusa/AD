package com.dao;

import java.io.Serializable;
/**
 * Intefaz con metodos a llevar a cabo en cada tabla con cada BD:
 * @author Alvaro
 *
 * @param <T>
 * @param <ID>
 */
public interface GenericoDAO<T, ID extends Serializable>  {

	public boolean inserta(T elemento);
	public boolean elimina(ID id);
	public boolean modifica(T elemento);
	public String consulta();
}