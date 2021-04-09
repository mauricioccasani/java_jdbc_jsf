package pe.com.mauricio.java.web.servicio.impl;

import java.util.List;

import pe.com.mauricio.java.web.dao.impl.CategoriaDaoImpl;
import pe.com.mauricio.java.web.dao.inf.CategoriaDao;
import pe.com.mauricio.java.web.dto.CategoriaDto;
import pe.com.mauricio.java.web.servicio.excepcion.ServicioExcepcion;
import pe.com.mauricio.java.web.servicio.inf.CategoriaServicioInf;

public class CategoriaServicioImpl implements CategoriaServicioInf {
	private CategoriaDao categoriaDao=new CategoriaDaoImpl();
	public CategoriaServicioImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean insertar(CategoriaDto t) throws ServicioExcepcion {
		try {
			return this.getCategoriaDao().insertar(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean actualizar(CategoriaDto t) throws ServicioExcepcion {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminar(CategoriaDto t) throws ServicioExcepcion {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CategoriaDto obtenerPorId(CategoriaDto t) throws ServicioExcepcion {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CategoriaDto> listar(CategoriaDto t) throws ServicioExcepcion {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CategoriaDto> listAll() throws ServicioExcepcion {
		try {
			return this.getCategoriaDao().listAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public CategoriaDao getCategoriaDao() {
		return categoriaDao;
	}

	public void setCategoriaDao(CategoriaDao categoriaDao) {
		this.categoriaDao = categoriaDao;
	}

}
