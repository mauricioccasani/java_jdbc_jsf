package pe.com.mauricio.java.web.mbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.mindrot.jbcrypt.BCrypt;

import pe.com.mauricio.java.web.dto.RolDto;
import pe.com.mauricio.java.web.dto.UsuarioDto;
import pe.com.mauricio.java.web.servicio.impl.UsuarioServicioImpl;

@ManagedBean(name = "usuarioMB")
@SessionScoped
public class UsuarioMB extends GenericoMsg implements GenericoMB, Serializable {

	private static final long serialVersionUID = 1L;
	private UsuarioDto usuarioDto;
	private UsuarioServicioImpl usuarioServicioImpl;
	private List<UsuarioDto> lstUsuario;
	private List<RolDto> lstRol;

	@PostConstruct
	@Override
	public void init() {
		this.setUsuarioDto(new UsuarioDto());
		this.setUsuarioServicioImpl(new UsuarioServicioImpl());
		this.setLstUsuario(new ArrayList<UsuarioDto>());
		this.setLstRol(new ArrayList<RolDto>());
		this.buscar();
		this.listarConboRol();
		this.limpiar();

	}

	@Override
	public void buscar() {
		try {
			this.lstUsuario = this.getUsuarioServicioImpl().listar(this.getUsuarioDto());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void grabar() {
		boolean sw = false;
		if (!valiarUsuarios()) {
			return;
		}
		try {
			super.setAuditoria(this.getUsuarioDto());
			if (this.getUsuarioDto().getIdUsuario() == 0) {
				String clave =this.usuarioDto.getClave();
				String claveHash=BCrypt.hashpw(clave, BCrypt.gensalt());
				this.usuarioDto.setClave(claveHash);
				sw = this.getUsuarioServicioImpl().insertar(this.getUsuarioDto());
				if (sw) {
					super.msgAlert("Exito al insertar usuario");
				} else {
					super.msgError("Error al insertar usuario");
				}
			} else {
				sw = this.getUsuarioServicioImpl().actualizar(this.getUsuarioDto());
				if (sw) {
					super.msgAlert("Exito al actualizar usuario");
				} else {
					super.msgError("Error al actualizar usuario");
				}
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public List<RolDto> listarConboRol() {
		try {
			return this.lstRol = this.getUsuarioServicioImpl().listarUsuarioRol();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void eliminar(UsuarioDto usuarioDto) {

		try {
			super.setAuditoria(usuarioDto);
			boolean sw = this.usuarioServicioImpl.eliminar(usuarioDto);
			if (sw) {
				super.msgAlert("Exito al eliminar usuario");
				this.buscar();
			} else {
				super.msgError("Error al eliminar usuario");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String modificar(UsuarioDto usuarioDto) {
		UsuarioDto usuarioDto2;
		String pagina = "usuario_listado";
		try {
			usuarioDto2 = this.usuarioServicioImpl.obtenerPorId(usuarioDto);
			if (usuarioDto2 != null) {
				this.setUsuarioDto(usuarioDto2);
				pagina = "usuario_registro";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return pagina;
	}
	private boolean valiarUsuarios() {
		
		if (!(this.getUsuarioDto().getNombres().trim().length() > 0)) {
			super.msgAlert("El campo nommbre es obligatorio");
			return false;
		}
		if (!(this.getUsuarioDto().getNombres().trim().length() >=3)) {
			super.msgAlert("El campo nommbre debe tener máximo 4 dígitos");
			return false;
		}
		if (!(this.getUsuarioDto().getApellidos().trim().length() > 0)) {
			super.msgAlert("El campo apellidos es obligatorio");
			return false;
		}
		if (!(this.getUsuarioDto().getApellidos().trim().length() >=3)) {
			super.msgAlert("El campo apellidos debe tener máximo 4 dígitos");
			return false;
		}
		if (!(this.getUsuarioDto().getNumeroDocumento().trim().length() > 0)) {
			super.msgAlert("El campo dni es obligatorio");
			return false;
		}
		if (!(this.getUsuarioDto().getNumeroDocumento().trim().length()==8)) {
			super.msgAlert("El campo dni debe ser igual a  8 dígitos");
			return false;
		}
		if (!(this.getUsuarioDto().getEmail().trim().length() > 0)) {
			super.msgAlert("El campo email es obligatorio");
			return false;
		}
		if (!(this.getUsuarioDto().getEmail().trim().length()>=8)) {
			super.msgAlert("El campo email debe tener máximo 8 dígitos");
			return false;
		}
		if (!(this.getUsuarioDto().getUsuario().trim().length() > 0)) {
			super.msgAlert("El campo usuario es obligatorio");
			return false;
		}
		if (!(this.getUsuarioDto().getUsuario().trim().length() >=3)) {
			super.msgAlert("El campo usuario debe tener máximo 4 dígitos");
			return false;
		}
		if (!(this.getUsuarioDto().getClave().trim().length() > 0)) {
			super.msgAlert("El campo clave es obligatorio");
			return false;
		}
		if (!(this.getUsuarioDto().getClave().trim().length()>=8)) {
			super.msgAlert("El campo clave debe tener máximo 8 dígitos");
			return false;
		}
		if (!(this.getUsuarioDto().getIdRol()> 0)) {
			super.msgAlert("El campo rol es obligatorio");
			return false;
		}
		return true;
	}

	@Override
	public String nuevoUsuario() {
		this.setUsuarioDto(new UsuarioDto());
		return "usuario_registro";
	}

	@Override
	public String cancelar() {
		return "usuario_listado";
	}

	@Override
	public void limpiar() {
		this.setUsuarioDto(new UsuarioDto());
	}

	public UsuarioDto getUsuarioDto() {
		return usuarioDto;
	}

	public void setUsuarioDto(UsuarioDto usuarioDto) {
		this.usuarioDto = usuarioDto;
	}

	public UsuarioServicioImpl getUsuarioServicioImpl() {
		return usuarioServicioImpl;
	}

	public void setUsuarioServicioImpl(UsuarioServicioImpl usuarioServicioImpl) {
		this.usuarioServicioImpl = usuarioServicioImpl;
	}

	public List<UsuarioDto> getLstUsuario() {
		this.buscar();
		return lstUsuario;
	}

	public void setLstUsuario(List<UsuarioDto> lstUsuario) {
		this.lstUsuario = lstUsuario;
	}

	public List<RolDto> getLstRol() {
		return lstRol;
	}

	public void setLstRol(List<RolDto> lstRol) {
		this.lstRol = lstRol;
	}

}
