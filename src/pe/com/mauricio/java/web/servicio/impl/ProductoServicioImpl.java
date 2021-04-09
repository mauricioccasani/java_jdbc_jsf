package pe.com.mauricio.java.web.servicio.impl;

import java.util.List;

import pe.com.mauricio.java.web.dao.impl.ProductoDaoImpl;
import pe.com.mauricio.java.web.dao.inf.ProductoDao;
import pe.com.mauricio.java.web.dto.ProductoDto;
import pe.com.mauricio.java.web.dto.UsuarioDto;
import pe.com.mauricio.java.web.servicio.excepcion.ServicioExcepcion;
import pe.com.mauricio.java.web.servicio.inf.ProductoServicioInf;

public class ProductoServicioImpl implements ProductoServicioInf {
	private ProductoDao productoDao = new ProductoDaoImpl();

	@Override
	public boolean insertar(ProductoDto productoDto) throws ServicioExcepcion {
		try {
			return this.productoDao.insertar(productoDto);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServicioExcepcion(e);

		}
	}

	@Override
	public boolean actualizar(ProductoDto productoDto) throws ServicioExcepcion {
		try {
			return this.productoDao.actualizar(productoDto);
		} catch (Exception e) {
			e.printStackTrace();
			//throw new ServicioExcepcion(e);
		}
		return false;
	}

	@Override
	public boolean eliminar(ProductoDto productoDto) throws ServicioExcepcion {
		try {
			return this.productoDao.eliminar(productoDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public ProductoDto obtenerPorId(ProductoDto productoDto) throws ServicioExcepcion {
		ProductoDto productoDto2=null;
		try {
			productoDto2=this.getProductoDao().obtenerPorId(productoDto);
			//return this.getProductoDao().obtenerPorId(productoDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productoDto2;
	}

	@Override
	public List<ProductoDto> listar(ProductoDto productoDto) throws ServicioExcepcion {
		try {
			return this.getProductoDao().listar(productoDto);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public List<UsuarioDto> list()throws ServicioExcepcion {
		try {
			return this.productoDao.list();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return null;
	}
	@Override
	public List<ProductoDto> listarProductos() throws ServicioExcepcion {
		try {
			return this.getProductoDao().listarProductos();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServicioExcepcion(e.getMessage());
		}
		
	}


	public ProductoDao getProductoDao() {
		return productoDao;
	}

	public void setProductoDao(ProductoDao productoDao) {
		this.productoDao = productoDao;
	}

	
}
