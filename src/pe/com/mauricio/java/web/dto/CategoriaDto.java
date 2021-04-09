package pe.com.mauricio.java.web.dto;

public class CategoriaDto {
	private Integer idCategoria=0;
	private String nombre;
	private String descripcion;
	private Integer estado;

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
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

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "CategoriaDto [idCategoria=" + idCategoria + ", nombre=" + nombre + ", descripcion=" + descripcion
				+ ", estado=" + estado + "]";
	}

}
