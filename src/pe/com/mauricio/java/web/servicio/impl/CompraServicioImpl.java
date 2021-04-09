package pe.com.mauricio.java.web.servicio.impl;

import java.util.List;

import pe.com.mauricio.java.web.dao.impl.CompraDaoImpl;
import pe.com.mauricio.java.web.dao.inf.CompraDao;
import pe.com.mauricio.java.web.dto.CompraDto;
import pe.com.mauricio.java.web.servicio.excepcion.ServicioExcepcion;
import pe.com.mauricio.java.web.servicio.inf.CompraServicioInf;

public class CompraServicioImpl implements CompraServicioInf {
	private CompraDao dao=new CompraDaoImpl();

	public CompraServicioImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean insertar(CompraDto compraDto) throws ServicioExcepcion {
		try {
			return this.getDao().insertar(compraDto);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean actualizar(CompraDto t) throws ServicioExcepcion {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminar(CompraDto t) throws ServicioExcepcion {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CompraDto obtenerPorId(CompraDto t) throws ServicioExcepcion {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CompraDto> listar(CompraDto t) throws ServicioExcepcion {
		try {
			return this.getDao().listar(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public CompraDao getDao() {
		return dao;
	}

	public void setDao(CompraDao dao) {
		this.dao = dao;
	}
	
}
