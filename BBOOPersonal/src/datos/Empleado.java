package datos;

import java.util.Date;

public class Empleado {
	 private int id_emp;
	 private String apellido;
	 private String oficio;
	 private Date fecha_alta;
	 private float salario;
	 private float comision;
	 private Empleado jefe;
	 private Departamento departamento;
	public Empleado(int id_emp, String apellido, String oficio, Date fecha_alta, float salario, float comision,
			Empleado jefe, Departamento departamento) {
		super();
		this.id_emp = id_emp;
		this.apellido = apellido;
		this.oficio = oficio;
		this.fecha_alta = fecha_alta;
		this.salario = salario;
		this.comision = comision;
		this.jefe = jefe;
		this.departamento = departamento;
	}
	public Empleado() {
		super();
	}
	/**
	 * @return the id_emp
	 */
	public int getId_emp() {
		return id_emp;
	}
	/**
	 * @param id_emp the id_emp to set
	 */
	public void setId_emp(int id_emp) {
		this.id_emp = id_emp;
	}
	/**
	 * @return the apellido
	 */
	public String getApellido() {
		return apellido;
	}
	/**
	 * @param apellido the apellido to set
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	/**
	 * @return the oficio
	 */
	public String getOficio() {
		return oficio;
	}
	/**
	 * @param oficio the oficio to set
	 */
	public void setOficio(String oficio) {
		this.oficio = oficio;
	}
	/**
	 * @return the fecha_alta
	 */
	public Date getFecha_alta() {
		return fecha_alta;
	}
	/**
	 * @param fecha_alta the fecha_alta to set
	 */
	public void setFecha_alta(Date fecha_alta) {
		this.fecha_alta = fecha_alta;
	}
	/**
	 * @return the salario
	 */
	public float getSalario() {
		return salario;
	}
	/**
	 * @param salario the salario to set
	 */
	public void setSalario(float salario) {
		this.salario = salario;
	}
	/**
	 * @return the comision
	 */
	public float getComision() {
		return comision;
	}
	/**
	 * @param comision the comision to set
	 */
	public void setComision(float comision) {
		this.comision = comision;
	}
	/**
	 * @return the jefe
	 */
	public Empleado getJefe() {
		return jefe;
	}
	/**
	 * @param jefe the jefe to set
	 */
	public void setJefe(Empleado jefe) {
		this.jefe = jefe;
	}
	/**
	 * @return the departamento
	 */
	public Departamento getDepartamento() {
		return departamento;
	}
	/**
	 * @param departamento the departamento to set
	 */
	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	} 
}
