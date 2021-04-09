package pe.com.mauricio.java.web.servicio.inf;

import java.util.List;

import pe.com.mauricio.java.web.dto.ProveedorDto;
import pe.com.mauricio.java.web.servicio.excepcion.ServicioExcepcion;
public interface ProveedorServicioInf extends GenericoServicio<ProveedorDto> {
	public List<ProveedorDto> listarProveedores() throws ServicioExcepcion;
}
