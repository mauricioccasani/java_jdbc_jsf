package pe.com.mauricio.java.web.dao.inf;

import java.util.List;

import pe.com.mauricio.java.web.dao.excepcion.DaoExcepcion;
import pe.com.mauricio.java.web.dto.CategoriaDto;

public interface CategoriaDao extends GenericoDao<CategoriaDto> {
	public List<CategoriaDto> listAll() throws DaoExcepcion;
}
