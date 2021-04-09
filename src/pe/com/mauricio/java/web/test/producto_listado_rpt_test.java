package pe.com.mauricio.java.web.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import pe.com.mauricio.java.web.dto.ProductoDto;

public class producto_listado_rpt_test {

	@SuppressWarnings({ "unchecked", "deprecation" })
	public static void main(String[] args) {

		List<ProductoDto> lstProductos = new ArrayList<ProductoDto>();

		try {

			ProductoDto producto1 = new ProductoDto();
			producto1.setIdProducto(01);;
			producto1.setNombre("Java desde cero");
			producto1.setCantidad(100);;
			producto1.setPrecio(20);
			producto1.setEstado("1");
			producto1.setIdUsuario(001);

			lstProductos.add(producto1);

			
			

		} catch (Exception e) {
			System.out.println("Error List" + e.getMessage());
		}

		String url = "src/pe/com/mauricio/java/web/rpt/";
		try {
			JasperPrint jasperPrint;
			JasperDesign desing = null;
			try {
				String reporte = url + "producto_listado_rpt.jrxml";
				desing = JRXmlLoader.load(new FileInputStream(reporte));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			String reporte;
			JasperCompileManager.compileReportToFile(desing, url + "producto_listado_rpt.jasper");
			reporte = url + "producto_listado_rpt.jasper";

			@SuppressWarnings("rawtypes")
			Map map = new HashMap();

			map.put("prm_empresa", "Java 8 Training");
			map.put("prm_usuario", "Mauricio Ccasani");
			map.put("prm_filtro", "Situacion: Bloqueados");
			map.put("prm_logo_izquierda", "WebContent/resources/dist/img/user2-160x160.jpg");
			map.put("prm_sistema", "© Copyright 2016 - Sistema de Pedidos (SIPE) v1.0");

			jasperPrint = JasperFillManager.fillReport(reporte, map, new JRBeanCollectionDataSource(lstProductos));
			JasperViewer jv = new JasperViewer(jasperPrint, false);
			jv.show();

			System.out.println("Visualizando el reporte en Desktop");
			System.out.println("Agregado exitosamente");

		} catch (JRException e) {
			System.out.println("Error" + e.getMessage());
			// e.printStackTrace();
		}

	}

}
