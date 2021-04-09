package pe.com.mauricio.java.web.dao.inf;

import java.util.List;

import pe.com.mauricio.java.web.dao.excepcion.DaoExcepcion;
import pe.com.mauricio.java.web.dto.ProductoDto;
import pe.com.mauricio.java.web.dto.RolDto;
import pe.com.mauricio.java.web.dto.UsuarioDto;

public interface ProductoDao extends GenericoDao<ProductoDto> {
	public List<UsuarioDto>list();
	public List<ProductoDto> listarProductos() throws DaoExcepcion;
}
