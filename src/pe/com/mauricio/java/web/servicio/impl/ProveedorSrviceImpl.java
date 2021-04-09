package pe.com.mauricio.java.web.servicio.impl;

import java.util.List;

import pe.com.mauricio.java.web.dao.excepcion.DaoExcepcion;
import pe.com.mauricio.java.web.dao.impl.ProveedorDaoImpl;
import pe.com.mauricio.java.web.dao.inf.ProveedorDao;
import pe.com.mauricio.java.web.dto.ProveedorDto;
import pe.com.mauricio.java.web.servicio.excepcion.ServicioExcepcion;
import pe.com.mauricio.java.web.servicio.inf.ProveedorServicioInf;

public class ProveedorSrviceImpl implements ProveedorServicioInf {
	private ProveedorDao proveedorDao = new ProveedorDaoImpl();

	public ProveedorSrviceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean insertar(ProveedorDto proveedorDto) throws ServicioExcepcion {
		try {
			return this.proveedorDao.insertar(proveedorDto);// .eliminar(usuarioDto);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServicioExcepcion(e);

		}
	}

	@Override
	public boolean actualizar(ProveedorDto proveedorDto) throws ServicioExcepcion {
		try {
			return this.getProveedorDao().actualizar(proveedorDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean eliminar(ProveedorDto proveedorDto) throws ServicioExcepcion {
		try {
			return this.getProveedorDao().eliminar(proveedorDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
}

	@Override
	public ProveedorDto obtenerPorId(ProveedorDto proveedorDto) throws ServicioExcepcion {
		try {
			return this.getProveedorDao().obtenerPorId(proveedorDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<ProveedorDto> listar(ProveedorDto proveedorDto) throws ServicioExcepcion {
		try {
			return this.getProveedorDao().listar(proveedorDto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public List<ProveedorDto> listarProveedores() throws ServicioExcepcion {
		try {
		return this.getProveedorDao().listarProveedores();
		} catch (Exception e) {
			throw new ServicioExcepcion(e.getMessage());
		}
		
	}

	public ProveedorDao getProveedorDao() {
		return proveedorDao;
	}

	public void setProveedorDao(ProveedorDao proveedorDao) {
		this.proveedorDao = proveedorDao;
	}

	

}
