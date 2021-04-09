package pe.com.mauricio.java.web.servicio.impl;

import java.util.List;
import pe.com.mauricio.java.web.dao.impl.UsuarioDaoImpl;
import pe.com.mauricio.java.web.dao.inf.UsuarioDao;
import pe.com.mauricio.java.web.dto.RolDto;
import pe.com.mauricio.java.web.dto.UsuarioDto;
import pe.com.mauricio.java.web.servicio.excepcion.ServicioExcepcion;
import pe.com.mauricio.java.web.servicio.inf.UsuarioServicioInf;

public class UsuarioServicioImpl implements UsuarioServicioInf {

	private UsuarioDao usuarioDao = new UsuarioDaoImpl();

	public UsuarioServicioImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean insertar(UsuarioDto usuarioDto) throws ServicioExcepcion {
		try {
			return this.getUsuarioDao().insertar(usuarioDto);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServicioExcepcion(e.getMessage());
		}
	}

	@Override
	public boolean actualizar(UsuarioDto t) throws ServicioExcepcion {
		try {
			return this.getUsuarioDao().actualizar(t);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServicioExcepcion(e.getMessage());
		}
	}

	@Override
	public boolean eliminar(UsuarioDto usuarioDto) throws ServicioExcepcion {
		try {
			return this.usuarioDao.eliminar(usuarioDto);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServicioExcepcion(e);

		}
	}

	@Override
	public UsuarioDto obtenerPorId(UsuarioDto usuarioDto) throws ServicioExcepcion {
		try {
			return this.getUsuarioDao().obtenerPorId(usuarioDto);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServicioExcepcion(e.getMessage());
		}
		
	}

	@Override
	public List<UsuarioDto> listar(UsuarioDto usuarioDto) throws ServicioExcepcion {
		try {
			return this.getUsuarioDao().listar(usuarioDto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public UsuarioDto valida(UsuarioDto usuarioDao) throws ServicioExcepcion {
		UsuarioDto oUsuario = null;
		try {
			oUsuario = this.getUsuarioDao().valida(usuarioDao);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServicioExcepcion(e);
		}
		return oUsuario;
	}

	@Override
	public List<RolDto> listarUsuarioRol() throws ServicioExcepcion {
		try {
			return this.getUsuarioDao().listarUsuarioRol();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServicioExcepcion(e.getMessage());
		}
	}

	public UsuarioDao getUsuarioDao() {
		return usuarioDao;
	}

	public void setUsuarioDao(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

}
