package datos;

public class Departamento {
	private int id_dep;
	private String nombre;
	private String localidad;
	public Departamento(int id_dep, String nombre, String localidad) {
		super();
		this.id_dep = id_dep;
		this.nombre = nombre;
		this.localidad = localidad;
	}
	public Departamento() {
		super();
	}
	/**
	 * @return the id_dep
	 */
	public int getId_dep() {
		return id_dep;
	}
	/**
	 * @param id_dep the id_dep to set
	 */
	public void setId_dep(int id_dep) {
		this.id_dep = id_dep;
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
	 * @return the localidad
	 */
	public String getLocalidad() {
		return localidad;
	}
	/**
	 * @param localidad the localidad to set
	 */
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
}
