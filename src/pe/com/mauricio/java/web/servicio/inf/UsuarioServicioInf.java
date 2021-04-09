package pe.com.mauricio.java.web.servicio.inf;

import java.rmi.ServerException;
import java.util.List;

import pe.com.mauricio.java.web.dto.RolDto;
import pe.com.mauricio.java.web.dto.UsuarioDto;
import pe.com.mauricio.java.web.servicio.excepcion.ServicioExcepcion;

public interface UsuarioServicioInf extends GenericoServicio<UsuarioDto> {
	public UsuarioDto valida(UsuarioDto usuarioDao) throws ServicioExcepcion ;
	public List<RolDto> listarUsuarioRol() throws ServicioExcepcion;
}
