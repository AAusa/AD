package aleatorio.encapsulado.ejemplos;

import java.time.LocalDateTime;

public class Persona {

	private String nombre;
	private int edad;
	private String email;

	/**
	 * Constructor vac�o
	 */
	public Persona() {
		// no hace nada
	}

	
	/**
	 * @param nombre
	 * @param fnacimiento
	 * @param email
	 */
	public Persona(String nombre, int edad, String email) {
		super();
		this.nombre = nombre;
		this.edad = edad;
		this.email = email;
	}
	

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
		
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}


	/**
	 * @return the edad
	 */
	public int getEdad() {
		return edad;
	}


	/**
	 * @param edad the edad to set
	 */
	public void setEdad(int edad) {
		this.edad = edad;
	}

	/**
	 * Devuelve el String con los datos de la mascota
	 */
	@Override
	public String toString() {
		return "Persona [nombre=" + nombre + ", edad=" + edad + ", email=" + email + "]";
	}


}
