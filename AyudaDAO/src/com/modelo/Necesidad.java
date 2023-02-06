package com.modelo;

// default package
// Generated 6 feb 2023 11:51:31 by Hibernate Tools 5.6.12.Final

/**
 * Necesidad generated by hbm2java
 */
public class Necesidad implements java.io.Serializable {

	private NecesidadId id;
	private Necesitado necesitado;
	private Voluntario voluntario;
	private String nombre;
	private String disponibilidad;

	public Necesidad() {
	}

	public Necesidad(NecesidadId id, Necesitado necesitado, Voluntario voluntario, String nombre,
			String disponibilidad) {
		this.id = id;
		this.necesitado = necesitado;
		this.voluntario = voluntario;
		this.nombre = nombre;
		this.disponibilidad = disponibilidad;
	}

	public NecesidadId getId() {
		return this.id;
	}

	public void setId(NecesidadId id) {
		this.id = id;
	}

	public Necesitado getNecesitado() {
		return this.necesitado;
	}

	public void setNecesitado(Necesitado necesitado) {
		this.necesitado = necesitado;
	}

	public Voluntario getVoluntario() {
		return this.voluntario;
	}

	public void setVoluntario(Voluntario voluntario) {
		this.voluntario = voluntario;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDisponibilidad() {
		return this.disponibilidad;
	}

	public void setDisponibilidad(String disponibilidad) {
		this.disponibilidad = disponibilidad;
	}

}
