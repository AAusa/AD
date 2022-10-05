package ejercicios.secuencial.objetos;

import java.io.Serializable;

public class Empleado implements Serializable {
	private String nombre;
	private double sueldo;
	private int annoNacimiento, antiguedad;
	
	public Empleado(String nombre, double sueldo, int annoNacimiento, int antiguedad) {
		this.nombre = nombre;
		this.sueldo = sueldo;
		this.annoNacimiento = annoNacimiento;
		this.antiguedad = antiguedad;
	}
	
	public Empleado() {
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getSueldo() {
		return sueldo;
	}
	public void setSueldo(double sueldo) {
		this.sueldo = sueldo;
	}
	public int getAnnoNacimiento() {
		return annoNacimiento;
	}
	public void setAnnoNacimiento(int annoNacimiento) {
		this.annoNacimiento = annoNacimiento;
	}
	public int getAntiguedad() {
		return antiguedad;
	}
	public void setAntiguedad(int antiguedad) {
		this.antiguedad = antiguedad;
	}

	@Override
	public String toString() {
		return "Empleado [nombre=" + nombre + ", sueldo=" + sueldo + ", annoNacimiento=" + annoNacimiento
				+ ", antiguedad=" + antiguedad + "]";
	}
}
