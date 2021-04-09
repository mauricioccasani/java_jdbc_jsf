package pe.com.mauricio.java.web.mbean;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import pe.com.mauricio.java.web.dto.ProductoDto;
import pe.com.mauricio.java.web.dto.UsuarioDto;
import pe.com.mauricio.java.web.servicio.impl.ProductoServicioImpl;

@ManagedBean(name = "productoMB")
@SessionScoped
public class ProductoMB extends GenericoMsg{
	private ProductoDto productoDto;
	private UsuarioDto usuarioDto;
	private ProductoServicioImpl productoServicioImpl;
	private List<ProductoDto> lstProducto;
	private List<UsuarioDto> lstUsuarios;

	public ProductoMB() {
	}

	@PostConstruct
	private void init() {
		this.setProductoDto(new ProductoDto());
		this.setUsuarioDto(new UsuarioDto());
		this.setProductoServicioImpl(new ProductoServicioImpl());
		this.setLstProducto(new ArrayList<ProductoDto>());
		this.setLstUsuarios(new ArrayList<UsuarioDto>());
		this.listarUsuario();
		this.listarProducto();
		

	}

	public void listarProducto() {
		try {
			this.lstProducto = this.getProductoServicioImpl().listar(productoDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<UsuarioDto> listarUsuario() {
		try {
			this.lstUsuarios = getProductoServicioImpl().list();
			return lstUsuarios;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void grabar() {
		boolean sw = false;
		if (!this.validarProducto()) {
			return;
		}
		super.auditoriaProducto(this.getProductoDto());
		if (this.getProductoDto().getIdProducto()>0) {
			// Actualización
			try {
				sw = this.getProductoServicioImpl().actualizar(this.getProductoDto());
				if (sw) {
					super.msgInfo("Producto actualizado satisfactoriamente");
				} else {
					super.msgAlert("Alerta al actualizar Producto");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			// Inserción
			try {
				sw = this.getProductoServicioImpl().insertar(this.getProductoDto());
				if (sw) {
					super.msgInfo("Producto creado satisfactoriamente");
				} else {
					super.msgAlert("Alerta al crear producto");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
	public void eliminar(ProductoDto productoDto) {
		
		try {
			
			super.auditoriaProducto(productoDto);
			boolean sw=this.getProductoServicioImpl().eliminar(productoDto);
			if (sw) {
				super.msgAlert("Exito al eliminar proveedor");
				this.listarProducto();
			}else {
				super.msgError("Error al eliminar proveedor");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String modificar(ProductoDto productoDto){
		ProductoDto productoDto2;
		String pagina="producto_listado";
		try {
			productoDto2=this.getProductoServicioImpl().obtenerPorId(productoDto);
			if (productoDto2!=null) {
				//claveInicial=oUsuario.getClave();
				this.setProductoDto(productoDto2);
				System.out.println(productoDto2);
				pagina="producto_registro";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pagina;
	}
	public boolean validarProducto() {
		if (!(this.getProductoDto().getNombre().trim().length()>=5)) {
			super.msgAlert("El camo nombre es obligatorio y deber tener mayor a 4 dígitos");
			return false;
		}
		if (!(this.getProductoDto().getCantidad()>0)) {
			super.msgAlert("El camo cantidad es obligatori y debe ser positivo");
			return false;
		}
		if (!(this.getProductoDto().getPrecio()>0)) {
			super.msgAlert("El camo precio es obligatori y debe ser positivo");
			return false;
		}
		if (!(this.getProductoDto().getIdUsuario()>0)) {
			super.msgAlert("El camo usuario es obligatori");
			return false;
		}
		return true;
	}
	public String nuevoProducto() {
		return "producto_registro";
	}

	public String cancelarProducto() {
		return "producto_listado";
	}

	public void limparProducto() {
		this.setProductoDto(new ProductoDto());
	}
	 public void exportExcel() {

	        try {

	            HttpServletResponse response = super.getResponse();

	            response.setContentType("application/vnd.ms-excel");
	            response.setHeader("Content-Disposition", "attachment; filename=Listado_Productos.xls");

	            HSSFWorkbook workbook = new HSSFWorkbook();

	            if (lstProducto != null) {
	                workbook = this.exportExcelFormato(lstProducto);

	            }

	            OutputStream out = response.getOutputStream();
	            workbook.write(out);
	            out.close();
	            FacesContext.getCurrentInstance().responseComplete();

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    private HSSFWorkbook exportExcelFormato(List<ProductoDto> lstProducto) {
	        HSSFWorkbook workbook = new HSSFWorkbook();
	        HSSFSheet sheet = workbook.createSheet("Listado de Producto");

	        int countRow = 0;

	        // CABECERA
	        Row row = sheet.createRow(countRow);

	        Cell cell = row.createCell(0);
	        cell.setCellValue("Item");
	        super.setStyleLisCabecera(workbook, cell);

	        cell = row.createCell(1);
	        cell.setCellValue("Código");
	        super.setStyleLisCabecera(workbook, cell);

	        cell = row.createCell(2);
	        cell.setCellValue("Nombre");
	        super.setStyleLisCabecera(workbook, cell);

	        cell = row.createCell(3);
	        cell.setCellValue("Cantidad");
	        super.setStyleLisCabecera(workbook, cell);

	        cell = row.createCell(4);
	        cell.setCellValue("Precio");
	        super.setStyleLisCabecera(workbook, cell);

	        // LISTADO
	        int item = 0;

	        for (ProductoDto producto : this.lstProducto) {

	            countRow++;
	            item++;

	            row = sheet.createRow(countRow);

	            cell = row.createCell(0);
	            cell.setCellValue(item);

	            // CODIGO
	            cell = row.createCell(1);
	            cell.setCellValue(producto.getIdProducto());

	            // NOMBRE
	            cell = row.createCell(2);
	            cell.setCellValue(producto.getNombre());

	            // PRECIO
	            cell = row.createCell(3);
	            cell.setCellValue(producto.getCantidad());

	            // PRECIO
	            cell = row.createCell(4);
	            cell.setCellValue(producto.getPrecio());

	        }

	        return workbook;
	    }
	    public void exportPDF() {
	        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
	        httpServletResponse.addHeader("Content-disposition", "attachment; filename=producto_listado_rpt.pdf");
	        ServletOutputStream servletStream;
	        try {
	            servletStream = httpServletResponse.getOutputStream();
	            JasperPrint jasperPrint = this.buildReport();
	            try {
	                JasperExportManager.exportReportToPdfStream(jasperPrint, servletStream);

	            } catch (JRException e) {
	                e.printStackTrace();
	            }

	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        FacesContext.getCurrentInstance().responseComplete();
	    }

	    
	    @SuppressWarnings("unchecked")
		private JasperPrint buildReport() {
	        JasperPrint jasperPrint = null;
	        ServletContext sc=(ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext();
	        String path="WEB-INF\\classes\\pe\\com\\mauricio\\java\\web\\rpt\\";
	        ////                           pe//edu//galaxy//training//java.//web//presentacion//rpt//
	        //																							
	        String realPath = sc.getRealPath("/")+path;
	        System.out.println("realPath "+realPath);
	        String reporte = realPath + "producto_listado_rpt.jasper";
	        @SuppressWarnings("rawtypes")
			Map map = new HashMap();
	        String pathSO=sc.getRealPath("/");
	        
	        String logo =pathSO+ "resources/dist/img/user2-160x160.jpg";
	        //resources\\img\\galaxy-training-logo.png
	        System.out.println("logo "+logo);
	        map.put("prm_logo_izquierda", logo);
	        
	        map.put("prm_usuario", "ADMIN");//super.getUsuarioActivo().getNombre());
	        
	        String filtro="";
	        
	        if(this.getProductoDto().getNombre().trim().length()>0) {
	        	filtro=this.getProductoDto().getNombre();//Producto().getNombre();
	        }
	        
			map.put("prm_filtro", "Nombre: "+filtro);
			map.put("prm_sistema","Sistema de Ventas"); //super.getStringJSF("sistema.nombre"));

	        try {
	            jasperPrint = JasperFillManager.fillReport(reporte, map, new JRBeanCollectionDataSource(this.lstProducto));
	        } catch (JRException e) {
	            e.printStackTrace();
	        }

	        return jasperPrint;
	    }

	public ProductoDto getProductoDto() {
		return productoDto;
	}

	public void setProductoDto(ProductoDto productoDto) {
		this.productoDto = productoDto;
	}

	public ProductoServicioImpl getProductoServicioImpl() {
		return productoServicioImpl;
	}

	public void setProductoServicioImpl(ProductoServicioImpl productoServicioImpl) {
		this.productoServicioImpl = productoServicioImpl;
	}

	public List<ProductoDto> getLstProducto() {
		return lstProducto;
	}

	public void setLstProducto(List<ProductoDto> lstProducto) {
		this.lstProducto = lstProducto;
	}

	public List<UsuarioDto> getLstUsuarios() {
		this.listarProducto();
		return lstUsuarios;
	}

	public void setLstUsuarios(List<UsuarioDto> lstUsuarios) {
		this.lstUsuarios = lstUsuarios;
	}

	public UsuarioDto getUsuarioDto() {
		return usuarioDto;
	}

	public void setUsuarioDto(UsuarioDto usuarioDto) {
		this.usuarioDto = usuarioDto;
	}

}
