package pe.com.mauricio.java.web.mbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import pe.com.mauricio.java.web.dto.CategoriaDto;
import pe.com.mauricio.java.web.servicio.impl.CategoriaServicioImpl;

@ManagedBean(name = "categoriaMB")
public class CategoriaMB extends GenericoMsg {

	private CategoriaDto categoriaDto;
	private List<CategoriaDto> lstCategorias;
	private CategoriaServicioImpl categoriaServicioImpl;

	@PostConstruct
	public void init() {
		this.setCategoriaDto(new CategoriaDto());
		this.setLstCategorias(new ArrayList<CategoriaDto>());
		this.setCategoriaServicioImpl(new CategoriaServicioImpl());
		this.obtenerCategorias();
	}

	public void obtenerCategorias() {
		try {
			lstCategorias = this.getCategoriaServicioImpl().listAll();
			for (CategoriaDto categoriaDto : lstCategorias) {
				System.out.println(categoriaDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void grabar() {
		boolean sw = false;

		try {
			sw = this.getCategoriaServicioImpl().insertar(this.getCategoriaDto());
			if (sw) {
				super.msgInfo("Categoría se realizo con exito");
			} else {
				super.msgAlert("Error al realizar Categoría");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String nuevo() {
		return "categoria_registro";
	}

	public String cancelar() {
		return "categoria_listado";
	}

	public void limpiar() {
		this.setCategoriaDto(new CategoriaDto());
	}

	public CategoriaDto getCategoriaDto() {
		return categoriaDto;
	}

	public void setCategoriaDto(CategoriaDto categoriaDto) {
		this.categoriaDto = categoriaDto;
	}

	public List<CategoriaDto> getLstCategorias() {
		return lstCategorias;
	}

	public void setLstCategorias(List<CategoriaDto> lstCategorias) {
		this.lstCategorias = lstCategorias;
	}

	public CategoriaServicioImpl getCategoriaServicioImpl() {
		return categoriaServicioImpl;
	}

	public void setCategoriaServicioImpl(CategoriaServicioImpl categoriaServicioImpl) {
		this.categoriaServicioImpl = categoriaServicioImpl;
	}

}
