package pe.com.mauricio.java.web.mbean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;

import pe.com.mauricio.java.web.dto.UsuarioDto;
import pe.com.mauricio.java.web.servicio.impl.UsuarioServicioImpl;
import pe.com.mauricio.java.web.servicio.inf.UsuarioServicioInf;

@ManagedBean(name = "loginMB")
@SessionScoped
public class LoginMB extends GenericoMsg implements Serializable {
	private static final long serialVersionUID = 1L;

	private UsuarioDto usuarioDto;
	private UsuarioServicioInf usuarioServicio = new UsuarioServicioImpl();

	@PostConstruct
	public void init() {
		this.setUsuarioDto(new UsuarioDto());
		//this.getUsuarioDto().setUsuario("NISA");
		//this.getUsuarioDto().setClave("NISA");
	}

	public String login() {
		String page = "login";

		if (!validar()) {
			return page;
		}
		try {
			this.usuarioDto = this.getUsuarioServicio().valida(this.getUsuarioDto());
			if (usuarioDto != null && !usuarioDto.equals(null)&& !usuarioDto.equals("null")) {
				super.msgInfo("Bienvenido" + usuarioDto.getNombres());

				HttpSession session = super.getRequest().getSession(true);
				//session.setAttribute("ID", session.getId());
				session.setAttribute("usuario", usuarioDto);

				page = "panel";

			} else {
				super.msgAlert("Usuario y/o clave incorrecto");

				System.out.println("ERROR.....AUTENTICACION");
			}

		} catch (Exception e) {
			this.msgError("Error al validar usuario " + e.getMessage());
			e.printStackTrace();
		}
		return page;
	}

	public String cerrarSesion() {

		System.out.println("cerrarSesion...");

		HttpSession session = super.getRequest().getSession();
		//session.removeAttribute("ID");
		session.removeAttribute("usuario");
		session.invalidate();

		return "login";
	}

	public boolean validar() {
		if (!(this.usuarioDto.getUsuario().trim().length() > 0)) {
			super.msgAlert("El campo usuario es obligatorio");
			return false;
		}
		if (!(this.usuarioDto.getClave().trim().length() > 0)) {
			super.msgAlert("El campo clave es obligatorio");
			return false;
		}
		return true;
	}

	public UsuarioDto getUsuarioDto() {
		return usuarioDto;
	}

	public void setUsuarioDto(UsuarioDto usuarioDto) {
		this.usuarioDto = usuarioDto;
	}

	public UsuarioServicioInf getUsuarioServicio() {
		return usuarioServicio;
	}

	public void setUsuarioServicio(UsuarioServicioInf usuarioServicio) {
		this.usuarioServicio = usuarioServicio;
	}
}
