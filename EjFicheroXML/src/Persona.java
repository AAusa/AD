/**
 * 
 */


/**
 * Plantilla de datos de una persona
 * @author alu
 *
 */
public class Persona {
	private String nombre, apellidos, tfno;
	private double altura;
	private int edad;

	/**
	 * Constructor con parametros
	 * @param nombre
	 * @param apellidos
	 * @param tfno
	 * @param altura
	 * @param edad
	 */
	public Persona(String nombre, String apellidos, String tfno, double altura, int edad) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.tfno = tfno;
		this.edad = edad;
		this.altura = altura;
	}
	/**
	 * Obtiene edad
	 * @return
	 */
	public int getEdad() {
		return edad;
	}
	/**
	 * Modifica edad
	 * @param edad
	 */
	public void setEdad(int edad) {
		this.edad = edad;
	}
	/**
	 * Obtiene nombre
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * Modifica nombre
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * Obtiene apellidos
	 * @return
	 */
	public String getApellidos() {
		return apellidos;
	}
	/**
	 * Modifica apellidos
	 * @param apellidos
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	/**
	 * Obtiene tfno
	 * @return
	 */
	public String getTfno() {
		return tfno;
	}
	/**
	 * Modifica tfno
	 * @param tfno
	 */
	public void setTfno(String tfno) {
		this.tfno = tfno;
	}
	/**
	 * Obtiene altura
	 * @return
	 */
	public double getAltura() {
		return altura;
	}
	/**
	 * Modifica altura
	 * @param altura
	 */
	public void setAltura(double altura) {
		this.altura = altura;
	}
}
