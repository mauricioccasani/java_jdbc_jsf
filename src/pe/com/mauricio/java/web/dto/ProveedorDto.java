package pe.com.mauricio.java.web.dto;

public class ProveedorDto extends GenericoDto{
	private int idProveedor;
	private String dniProveedor;
	private String nombreProveedor;
	private String direccion;
	private String telefono;
	private String estado;

	public ProveedorDto() {
	}

	public int getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}

	public String getDniProveedor() {
		return dniProveedor;
	}

	public void setDniProveedor(String dniProveedor) {
		this.dniProveedor = dniProveedor;
	}

	public String getNombreProveedor() {
		return nombreProveedor;
	}

	public void setNombreProveedor(String nombreProveedor) {
		this.nombreProveedor = nombreProveedor;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "ProveedorDto [idProveedor=" + idProveedor + ", dniProveedor=" + dniProveedor + ", nombreProveedor="
				+ nombreProveedor + ", direccion=" + direccion + ", telefono=" + telefono + ", estado=" + estado + "]"
				;
	}

	
}
