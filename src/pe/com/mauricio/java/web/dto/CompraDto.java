package pe.com.mauricio.java.web.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CompraDto extends GenericoDto {
	private int idCompra;
	private Date fecha;
	private int idProveedor;
	private int idProducto;
	private int cantidad;
	private double valor;
	private int idUsuario;

	public CompraDto() {
		// TODO Auto-generated constructor stub
	}

	public int getIdCompra() {
		return idCompra;
	}

	public void setIdCompra(int idCompra) {
		this.idCompra = idCompra;
	}

	public Date getFecha() {
		//String fechaFormat;
		//SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		//fechaFormat = sdf.format(fecha.getTime());
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	@Override
	public String toString() {
		return "Compras [idCompra=" + idCompra + ", fecha=" + fecha + ", idProveedor=" + idProveedor + ", idProducto="
				+ idProducto + ", cantidad=" + cantidad + ", valor=" + valor + ", idUsuario=" + idUsuario + "]";
	}

}
