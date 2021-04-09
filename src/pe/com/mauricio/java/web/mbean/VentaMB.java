package pe.com.mauricio.java.web.mbean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "ventaMB")
public class VentaMB {
	@PostConstruct
	private void init() {

	}
	public String nuevo() {
		return"venta_registro";
	}
	public String cancelar() {
		return"venta_listado";
	}
}
