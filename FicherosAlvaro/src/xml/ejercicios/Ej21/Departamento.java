package xml.ejercicios.Ej21;

import java.io.Serializable;
import java.util.Objects;

public class Departamento implements Serializable {
	private int num;
	private String nombre, localidad;
	
	public Departamento(int num, String nombre, String localidad) {
		this.num = num;
		this.nombre = nombre;
		this.localidad = localidad;
	}
	public Departamento() {
	}
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	@Override
	public String toString() {
		return "Departamento [num=" + num + ", nombre=" + nombre + ", localidad=" + localidad + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Departamento other = (Departamento) obj;
		return num == other.num;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(num);
	}

}
