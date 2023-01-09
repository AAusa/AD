package tablas;
// Generated 9 ene 2023 12:00:49 by Hibernate Tools 5.6.12.Final

import java.util.Date;

/**
 * Venta generated by hbm2java
 */
public class Venta implements java.io.Serializable {

	private int idVenta;
	private Cliente cliente;
	private Producto producto;
	private Date fecha;
	private Integer cantidad;

	public Venta() {
	}

	public Venta(int idVenta, Cliente cliente, Producto producto) {
		this.idVenta = idVenta;
		this.cliente = cliente;
		this.producto = producto;
	}

	public Venta(int idVenta, Cliente cliente, Producto producto, Date fecha, Integer cantidad) {
		this.idVenta = idVenta;
		this.cliente = cliente;
		this.producto = producto;
		this.fecha = fecha;
		this.cantidad = cantidad;
	}

	public int getIdVenta() {
		return this.idVenta;
	}

	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

}
