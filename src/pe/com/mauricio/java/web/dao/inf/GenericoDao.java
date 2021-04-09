package pe.com.mauricio.java.web.dao.inf;

import java.util.List;

import pe.com.mauricio.java.web.dao.excepcion.DaoExcepcion;


public interface GenericoDao<T> {
	public boolean insertar(T t) throws DaoExcepcion;

	public boolean actualizar(T t) throws DaoExcepcion;

	public boolean eliminar(T t) throws DaoExcepcion;

	public T obtenerPorId(T t) throws DaoExcepcion;

	public List<T> listar(T t) throws DaoExcepcion;
}
