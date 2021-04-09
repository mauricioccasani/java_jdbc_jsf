package pe.com.mauricio.java.web;

import java.text.SimpleDateFormat;
import java.util.Date;

import pe.com.mauricio.java.web.dao.impl.CategoriaDaoImpl;
import pe.com.mauricio.java.web.dto.CategoriaDto;
import pe.com.mauricio.java.web.dto.CompraDto;
import pe.com.mauricio.java.web.dto.UsuarioDto;
import pe.com.mauricio.java.web.servicio.impl.CompraServicioImpl;
import pe.com.mauricio.java.web.servicio.impl.UsuarioServicioImpl;
import pe.com.mauricio.java.web.servicio.inf.CompraServicioInf;

public class Test {
	CompraDto compraDto = new CompraDto();

	public String getFecha() {
		String fechaFormat;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		fechaFormat = sdf.format(compraDto.getFecha().getTime());
		return fechaFormat;
	}

	public static void main(String[] args) {
		try {
			CategoriaDto categoriaDto=new CategoriaDto();
			categoriaDto.setIdCategoria(5);
			categoriaDto.setNombre("DEMO1");
			categoriaDto.setDescripcion("DEMO1");
			CategoriaDaoImpl categoriaDaoImpl = new CategoriaDaoImpl();
			System.out.println(categoriaDaoImpl.insertar(categoriaDto));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
