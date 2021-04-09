package pe.com.mauricio.java.web.servicio.inf;

import java.util.List;

import pe.com.mauricio.java.web.dto.CategoriaDto;
import pe.com.mauricio.java.web.servicio.excepcion.ServicioExcepcion;

public interface CategoriaServicioInf extends GenericoServicio<CategoriaDto> {
	public List<CategoriaDto> listAll() throws ServicioExcepcion;
}
