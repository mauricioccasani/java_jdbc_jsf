package pe.com.mauricio.java.web.mbean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import pe.com.mauricio.java.web.dto.ProveedorDto;
import pe.com.mauricio.java.web.servicio.excepcion.ServicioExcepcion;
import pe.com.mauricio.java.web.servicio.impl.ProveedorSrviceImpl;
import pe.com.mauricio.java.web.servicio.inf.ProveedorServicioInf;

@ManagedBean(name = "proveedorMB")
@SessionScoped
public class ProveedorMB extends GenericoMsg {

	private ProveedorDto proveedorDto;
	private ProveedorServicioInf proveedorServicio = new ProveedorSrviceImpl();
	private List<ProveedorDto> lstProveedor;

	public ProveedorMB() {

	}

	@PostConstruct
	private void init() {
		this.setProveedorDto(new ProveedorDto());
		this.setLstProveedor(new ArrayList<ProveedorDto>());
		this.buscar();
		this.limpiar();
	}

	public void buscar() {
		try {
			this.lstProveedor = this.getProveedorServicio().listar(getProveedorDto());// getUsuarioServicio().listar(usuarioDto);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void grabar() {
		boolean sw = false;

		if (!this.validar()) {
			return;
		}
		super.auditoria(this.proveedorDto);
		if (this.getProveedorDto().getIdProveedor() > 0) {
			// Actualización
			try {
				sw = this.getProveedorServicio().actualizar(this.getProveedorDto());
				if (sw) {
					super.msgInfo("Proveedor actualizado satisfactoriamente");
				} else {
					super.msgAlert("Alerta al actualizar proveedor");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			// Inserción
			try {
				sw = this.getProveedorServicio().insertar(this.getProveedorDto());
				if (sw) {
					super.msgInfo("Proveedor creado satisfactoriamente");
				} else {
					super.msgAlert("Alerta al crear proveedor");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	public String modificar(ProveedorDto proveedorDto) {
		ProveedorDto proveedorDto2;
		String pagina = "proveedor_listado";
		try {
			proveedorDto2 = this.getProveedorServicio().obtenerPorId(proveedorDto);
			if (proveedorDto2 != null) {
				this.setProveedorDto(proveedorDto2);
				pagina = "proveedor_registro";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return pagina;
	}

	public void eliminar(ProveedorDto proveedorDto) {
		try {
			super.auditoria(this.proveedorDto);
			boolean sw = this.getProveedorServicio().eliminar(proveedorDto);
			if (sw) {
				super.msgAlert("Exito al eliminar proveedor");
				this.buscar();
			} else {
				super.msgError("Error al eliminar proveedor");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean validar() {
		if (!(this.getProveedorDto().getDniProveedor().trim().length() >= 8)) {
			super.msgAlert("El campo dni es obligatorio y debe tener máximo  8 dígitos  ");// +getProveedorDto().getDniProveedor()
			return false;
		}
		if (!(this.getProveedorDto().getNombreProveedor().trim().length() > 0)) {
			super.msgAlert("El campo nombre es obligatorio");
			return false;
		}
		if (!(this.getProveedorDto().getNombreProveedor().length() >= 3)) {
			super.msgAlert("El campo nombre debe ser máximo 3 digitos");
			return false;
		}
		if (!(this.getProveedorDto().getDireccion().length() > 8)) {
			super.msgAlert("El campo dirección debe ser máximo 8 dígitos");
			return false;
		}
		if (!(this.getProveedorDto().getTelefono().length() >= 7)) {
			super.msgAlert("El campo teléfon debe ser máximo 7 dígitos");
			return false;
		}
		return true;

	}

	public void limpiar() {
		this.setProveedorDto(new ProveedorDto());
	}

	public String nuevoProveedor() {
		this.setProveedorDto(new ProveedorDto());
		return "proveedor_registro";
	}

	public String cancelarProveedor() {
		return "proveedor_listado";
	}

	public ProveedorDto getProveedorDto() {
		return proveedorDto;
	}

	public void setProveedorDto(ProveedorDto proveedorDto) {
		this.proveedorDto = proveedorDto;
	}

	public ProveedorServicioInf getProveedorServicio() {
		return proveedorServicio;
	}

	public void setProveedorServicio(ProveedorServicioInf proveedorServicio) {
		this.proveedorServicio = proveedorServicio;
	}

	public List<ProveedorDto> getLstProveedor() {
		return lstProveedor;
	}

	public void setLstProveedor(List<ProveedorDto> lstProveedor) {
		this.lstProveedor = lstProveedor;
	}
}
