package pe.com.mauricio.java.web.mbean;

import java.net.Inet4Address;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;

import pe.com.mauricio.java.web.dto.CompraDto;
import pe.com.mauricio.java.web.dto.ProductoDto;
import pe.com.mauricio.java.web.dto.ProveedorDto;
import pe.com.mauricio.java.web.dto.UsuarioDto;

public class GenericoMsg {

	public GenericoMsg() {

	}

	protected void msgInfo(String msg) {
		FacesMessage message = null;
		message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", msg);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	protected void msgAlert(String msg) {
		FacesMessage message = null;
		message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerta", msg);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	protected void msgError(String msg) {
		FacesMessage message = null;
		message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", msg);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	protected HttpServletRequest getRequest() {
		FacesContext fctx = FacesContext.getCurrentInstance();
		ExternalContext ectx = fctx.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ectx.getRequest();
		return request;
	}

	protected HttpServletResponse getResponse() {

		FacesContext fctx = FacesContext.getCurrentInstance();
		ExternalContext ectx = fctx.getExternalContext();

		HttpServletResponse response = (HttpServletResponse) ectx.getResponse();
		return response;

	}

	public void setAuditoria(UsuarioDto genericEntity) {
		try {
			// Usuario y Sesion
			UsuarioDto oUsuario = new UsuarioDto();
			HttpSession session = this.getRequest().getSession();
			if (session != null) {
				// Usuario
				Object obj = session.getAttribute("usuario");
				if (obj != null) {
					oUsuario = (UsuarioDto) obj;
					genericEntity.setAudIdUsuario(oUsuario.getIdUsuario());
					genericEntity.setAudSesion(oUsuario.getUsuario());
					genericEntity.setAudIp(Inet4Address.getLocalHost().getHostAddress());
					genericEntity.setAudNombreEquipo(Inet4Address.getLocalHost().getHostName());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void auditoria(ProveedorDto genericEntity) {
		try {
			// Usuario y Sesion
			UsuarioDto oUsuario = new UsuarioDto();
			HttpSession session = this.getRequest().getSession();

			if (session != null) {

				// Usuario
				Object obj = session.getAttribute("usuario");
				if (obj != null) {
					oUsuario = (UsuarioDto) obj;

					genericEntity.setAudIdUsuario(oUsuario.getIdUsuario());
					genericEntity.setAudSesion(oUsuario.getUsuario());
					genericEntity.setAudIp(Inet4Address.getLocalHost().getHostAddress());
					genericEntity.setAudNombreEquipo(Inet4Address.getLocalHost().getHostName());

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void auditoriaProducto(ProductoDto genericEntity) {
		try {
			UsuarioDto oUsuario = new UsuarioDto();
			HttpSession session = this.getRequest().getSession();
			if (session != null) {
				Object obj = session.getAttribute("usuario");
				if (obj != null) {
					oUsuario = (UsuarioDto) obj;
					genericEntity.setAudIdUsuario(oUsuario.getIdUsuario());
					genericEntity.setAudSesion(oUsuario.getUsuario());
					genericEntity.setAudIp(Inet4Address.getLocalHost().getHostAddress());
					genericEntity.setAudNombreEquipo(Inet4Address.getLocalHost().getHostName());
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void auditoriaCompras(CompraDto genericEntity) {
		try {
			UsuarioDto oUsuario = new UsuarioDto();
			HttpSession session = this.getRequest().getSession();
			if (session != null) {
				Object obj = session.getAttribute("usuario");
				if (obj != null) {
					oUsuario = (UsuarioDto) obj;
					genericEntity.setAudIdUsuario(oUsuario.getIdUsuario());
					genericEntity.setAudSesion(oUsuario.getUsuario());
					genericEntity.setAudIp(Inet4Address.getLocalHost().getHostAddress());
					genericEntity.setAudNombreEquipo(Inet4Address.getLocalHost().getHostName());
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void setStyleFormat(HSSFWorkbook workbook, Cell cell) {
		HSSFFont font = workbook.createFont();
		font.setFontHeightInPoints((short) 10);
		font.setFontName("Arial");
		font.setColor(IndexedColors.BLACK.getIndex());
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setItalic(false);

		CellStyle newCellStyle = workbook.createCellStyle();
		newCellStyle.cloneStyleFrom(cell.getCellStyle());

//		newCellStyle.setFillBackgroundColor(IndexedColors.DARK_BLUE.getIndex());
//		newCellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		newCellStyle.setAlignment(CellStyle.ALIGN_LEFT);
		newCellStyle.setFont(font);

		cell.setCellStyle(newCellStyle);
	}

	protected void setStyleLisCabecera(HSSFWorkbook workbook, Cell cell) {
		HSSFFont font = workbook.createFont();
		font.setFontHeightInPoints((short) 10);
		font.setFontName("Arial");
		font.setColor(IndexedColors.WHITE.getIndex());
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setItalic(false);

		CellStyle newCellStyle = workbook.createCellStyle();
		newCellStyle.cloneStyleFrom(cell.getCellStyle());

		newCellStyle.setFillForegroundColor(HSSFColor.CORAL.index);
		newCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		newCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		newCellStyle.setBorderTop((short) 1); // single line border
		newCellStyle.setBorderBottom((short) 1); // single line border
		newCellStyle.setBorderRight((short) 1);
		newCellStyle.setBorderLeft((short) 1);
		newCellStyle.setFont(font);

		cell.setCellStyle(newCellStyle);
	}

}
