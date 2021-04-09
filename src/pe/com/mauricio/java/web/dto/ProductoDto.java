package pe.com.mauricio.java.web.dto;

public class ProductoDto extends GenericoDto {
	private int idProducto;
	private String nombre;
	private int cantidad;
	private double precio;
	private String estado;
	private int idUsuario;

	public ProductoDto() {
		// TODO Auto-generated constructor stub
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	@Override
	public String toString() {
		return "ProductoDto [idProducto=" + idProducto + ", nombre=" + nombre + ", cantidad=" + cantidad + ", precio="
				+ precio + ", estado=" + estado + ", idUsuario=" + idUsuario + "]";
	}

}
