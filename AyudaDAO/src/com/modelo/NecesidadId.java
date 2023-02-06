package com.modelo;

// default package
// Generated 6 feb 2023 11:51:31 by Hibernate Tools 5.6.12.Final

/**
 * NecesidadId generated by hbm2java
 */
public class NecesidadId implements java.io.Serializable {

	private int idVoluntario;
	private int idNecesitado;

	public NecesidadId() {
	}

	public NecesidadId(int idVoluntario, int idNecesitado) {
		this.idVoluntario = idVoluntario;
		this.idNecesitado = idNecesitado;
	}

	public int getIdVoluntario() {
		return this.idVoluntario;
	}

	public void setIdVoluntario(int idVoluntario) {
		this.idVoluntario = idVoluntario;
	}

	public int getIdNecesitado() {
		return this.idNecesitado;
	}

	public void setIdNecesitado(int idNecesitado) {
		this.idNecesitado = idNecesitado;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof NecesidadId))
			return false;
		NecesidadId castOther = (NecesidadId) other;

		return (this.getIdVoluntario() == castOther.getIdVoluntario())
				&& (this.getIdNecesitado() == castOther.getIdNecesitado());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getIdVoluntario();
		result = 37 * result + this.getIdNecesitado();
		return result;
	}

}
