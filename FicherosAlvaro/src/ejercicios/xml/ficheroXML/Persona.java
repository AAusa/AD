/**
 * 
 */
package ejercicios.xml.ficheroXML;

/**
 * @author alu
 *
 */
public class Persona {
	String nombre, apellidos, tfno;
	double altura;
	int edad;
	
	
	public Persona(String nombre, String apellidos, String tfno, double altura, int edad) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.tfno = tfno;
		this.edad = edad;
		this.altura = altura;
	}
	
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getTfno() {
		return tfno;
	}
	public void setTfno(String tfno) {
		this.tfno = tfno;
	}
	public double getAltura() {
		return altura;
	}
	public void setAltura(double altura) {
		this.altura = altura;
	}
}
