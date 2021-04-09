package pe.com.mauricio.java.web.dto;

public class RolDto {
	private int idRol;
	private String nombre;
	private String descripcion;
	private String estado;

	
	public RolDto(int idRol) {
		this.idRol = idRol;
	}
	

	

	public RolDto() {
		
	}
	

	public RolDto(String nombre) {
		this.nombre = nombre;
	}


	public int getIdRol() {
		return idRol;
	}

	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "RolDto [idRol=" + idRol + ", nombre=" + nombre + ", descripcion=" + descripcion + ", estado=" + estado
				+ "]";
	}
	
}
