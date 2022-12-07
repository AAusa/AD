package sqlite;

public class Cliente {
	private String nombre, apellidos, tfno, direccion, correoElectronico;
	private int id, vip; //0 false, 1 true
	
	public Cliente(int id, String nombre, String apellidos, String tfno, String direccion, String correoElectronico,
			int vip) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.tfno = tfno;
		this.direccion = direccion;
		this.correoElectronico = correoElectronico;
		this.vip = vip;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
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
	 * @return the apellidos
	 */
	public String getApellidos() {
		return apellidos;
	}
	/**
	 * @param apellidos the apellidos to set
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	/**
	 * @return the tfno
	 */
	public String getTfno() {
		return tfno;
	}
	/**
	 * @param tfno the tfno to set
	 */
	public void setTfno(String tfno) {
		this.tfno = tfno;
	}
	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}
	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	/**
	 * @return the correoElectronico
	 */
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	/**
	 * @param correoElectronico the correoElectronico to set
	 */
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	/**
	 * @return the vip
	 */
	public int getVip() {
		return vip;
	}
	/**
	 * @param vip the vip to set
	 */
	public void setVip(int vip) {
		this.vip = vip;
	}
}
