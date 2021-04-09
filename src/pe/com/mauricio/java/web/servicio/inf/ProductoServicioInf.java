package pe.com.mauricio.java.web.servicio.inf;

import java.util.List;

import pe.com.mauricio.java.web.dto.ProductoDto;
import pe.com.mauricio.java.web.dto.UsuarioDto;
import pe.com.mauricio.java.web.servicio.excepcion.ServicioExcepcion;

public interface ProductoServicioInf extends GenericoServicio<ProductoDto> {
	public List<UsuarioDto>list() throws ServicioExcepcion;
	public List<ProductoDto>listarProductos()throws ServicioExcepcion ;
}
