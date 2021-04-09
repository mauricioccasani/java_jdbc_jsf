package pe.com.mauricio.java.web.dao.inf;

import java.util.List;

import pe.com.mauricio.java.web.dao.excepcion.DaoExcepcion;
import pe.com.mauricio.java.web.dto.RolDto;
import pe.com.mauricio.java.web.dto.UsuarioDto;

public interface UsuarioDao extends GenericoDao<UsuarioDto> {
	public UsuarioDto valida(UsuarioDto usuario) throws DaoExcepcion;

	public List<RolDto> listarUsuarioRol() throws DaoExcepcion;
}
