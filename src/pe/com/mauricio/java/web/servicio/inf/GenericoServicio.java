package pe.com.mauricio.java.web.servicio.inf;

import java.util.List;

import pe.com.mauricio.java.web.servicio.excepcion.ServicioExcepcion;

public interface GenericoServicio<T> {
	public boolean insertar(T t) throws ServicioExcepcion;

	public boolean actualizar(T t) throws ServicioExcepcion;

	public boolean eliminar(T t) throws ServicioExcepcion;

	public T obtenerPorId(T t) throws ServicioExcepcion;

	public List<T> listar(T t) throws ServicioExcepcion;
}
