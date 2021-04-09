package pe.com.mauricio.java.web.dto;

public class GenericoDto {

	private int audIdUsuario;
	private String audSesion;
	private String audIp;
	private String audNombreEquipo;
	private int codRespuesta;
	private String msgRespuesta;

	public int getAudIdUsuario() {
		return audIdUsuario;
	}

	public void setAudIdUsuario(int audIdUsuario) {
		this.audIdUsuario = audIdUsuario;
	}

	public String getAudSesion() {
		return audSesion;
	}

	public void setAudSesion(String audSesion) {
		this.audSesion = audSesion;
	}

	public String getAudIp() {
		return audIp;
	}

	public void setAudIp(String audIp) {
		this.audIp = audIp;
	}

	public String getAudNombreEquipo() {
		return audNombreEquipo;
	}

	public void setAudNombreEquipo(String audNombreEquipo) {
		this.audNombreEquipo = audNombreEquipo;
	}

	public int getCodRespuesta() {
		return codRespuesta;
	}

	public void setCodRespuesta(int codRespuesta) {
		this.codRespuesta = codRespuesta;
	}

	public String getMsgRespuesta() {
		return msgRespuesta;
	}

	public void setMsgRespuesta(String msgRespuesta) {
		this.msgRespuesta = msgRespuesta;
	}

	@Override
	public String toString() {
		return "GenericoDto [audIdUsuario=" + audIdUsuario + ", audSesion=" + audSesion + ", audIp=" + audIp
				+ ", audNombreEquipo=" + audNombreEquipo + ", codRespuesta=" + codRespuesta + ", msgRespuesta="
				+ msgRespuesta + "]";
	}

}
