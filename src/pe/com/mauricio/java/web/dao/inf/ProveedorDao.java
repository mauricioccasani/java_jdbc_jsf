package pe.com.mauricio.java.web.dao.inf;

import java.util.List;

import pe.com.mauricio.java.web.dao.excepcion.DaoExcepcion;
import pe.com.mauricio.java.web.dto.ProductoDto;
import pe.com.mauricio.java.web.dto.ProveedorDto;

public interface ProveedorDao extends GenericoDao<ProveedorDto>{

	public boolean validarProveedor(ProveedorDto proveedorDto)throws DaoExcepcion;
	public List<ProveedorDto> listarProveedores() throws DaoExcepcion;
}
