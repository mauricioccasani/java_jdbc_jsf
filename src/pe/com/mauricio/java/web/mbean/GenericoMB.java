package pe.com.mauricio.java.web.mbean;

import pe.com.mauricio.java.web.dto.UsuarioDto;

public interface GenericoMB {
	public void init();
	public void buscar();
	public String nuevoUsuario();
	public String cancelar();
	public void limpiar();

}
