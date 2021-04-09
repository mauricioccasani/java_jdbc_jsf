package pe.com.mauricio.java.web.mbean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import pe.com.mauricio.java.web.dto.CompraDto;
import pe.com.mauricio.java.web.dto.ProductoDto;
import pe.com.mauricio.java.web.dto.ProveedorDto;
import pe.com.mauricio.java.web.dto.UsuarioDto;
import pe.com.mauricio.java.web.servicio.impl.CompraServicioImpl;
import pe.com.mauricio.java.web.servicio.impl.ProductoServicioImpl;
import pe.com.mauricio.java.web.servicio.impl.ProveedorSrviceImpl;

@ManagedBean(name = "compraMB")
public class CompraMB extends GenericoMsg {
	private CompraDto compraDto;
	private CompraServicioImpl compraServicioImpl;
	private List<CompraDto> lstCompras;
	private List<UsuarioDto> listUsuario;
	private List<ProductoDto> listProducto;
	private List<ProveedorDto> lstProveedores;
	private ProductoServicioImpl productoServicioImpl;
	private ProveedorSrviceImpl proveedorSrviceImpl;

	public CompraMB() {

	}

	@PostConstruct
	public void init() {
		this.setCompraDto(new CompraDto());
		this.setCompraServicioImpl(new CompraServicioImpl());
		this.setLstCompras(new ArrayList<CompraDto>());
		this.setListUsuario(new ArrayList<UsuarioDto>());
		this.setListProducto(new ArrayList<ProductoDto>());
		this.setLstProveedores(new ArrayList<ProveedorDto>());
		this.setProductoServicioImpl(new ProductoServicioImpl());
		this.setProveedorSrviceImpl(new ProveedorSrviceImpl());
		this.buscar();
		this.listarUsuario();
	}

	public void buscar() {
		try {
			lstCompras = this.getCompraServicioImpl().listar(this.getCompraDto());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void grabar() {
		boolean sw = false;
		if (!(this.validarCompras())) {
			return;
		}
		super.auditoriaCompras(this.getCompraDto());
		if (this.getCompraDto().getIdCompra() > 0) {
			try {
				sw = this.getCompraServicioImpl().actualizar(this.getCompraDto());
				if (sw) {
					super.msgInfo("Compra se actualizó con exito");
				} else {
					super.msgAlert("Error al actualizar compras");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				sw = this.getCompraServicioImpl().insertar(this.getCompraDto());
				if (sw) {
					super.msgInfo("Compra se realizo con exito");
				} else {
					super.msgAlert("Error al realizar compras");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public List<UsuarioDto> listarUsuario() {
		try {
			this.listUsuario = getProductoServicioImpl().list();
			return listUsuario;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<ProductoDto> listarProducto() {
		try {
			this.listProducto = getProductoServicioImpl().listarProductos();
			return listProducto;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<ProveedorDto> listarProveedores() {
		try {
			this.lstProveedores = getProveedorSrviceImpl().listarProveedores();// listProducto =
																				// getProductoServicioImpl().listarProductos();
			return lstProveedores;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String nuevoCompra() {
		try {
			return "compra_registro";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public boolean validarCompras() {
		if (!(this.getCompraDto().getIdProveedor() > 0)) {
			super.msgInfo("El campo proveedor es obligatorio");
			return false;
		}
		if (!(this.getCompraDto().getIdProducto() > 0)) {
			super.msgInfo("El campo producto es obligatorio");
			return false;

		}
		if (!(this.getCompraDto().getCantidad() > 0)) {
			super.msgInfo("El campo cantidad es obligatorio");
			return false;

		}
		if (!(this.getCompraDto().getValor() > 0)) {
			super.msgInfo("El campo valor es obligatorio");
			return false;

		}
		if (!(this.getCompraDto().getIdUsuario() > 0)) {
			super.msgInfo("El campo usuario es obligatorio");
			return false;

		}
		return true;
	}

	public void limpiarCompra() {
		this.setCompraDto(new CompraDto());

	}

	public String cancelarCompras() {
		return "compra_listado";
	}

	public CompraDto getCompraDto() {
		return compraDto;
	}

	public void setCompraDto(CompraDto compraDto) {
		this.compraDto = compraDto;
	}

	public CompraServicioImpl getCompraServicioImpl() {
		return compraServicioImpl;
	}

	public void setCompraServicioImpl(CompraServicioImpl compraServicioImpl) {
		this.compraServicioImpl = compraServicioImpl;
	}

	public List<CompraDto> getLstCompras() {
		return lstCompras;
	}

	public void setLstCompras(List<CompraDto> lstCompras) {
		this.lstCompras = lstCompras;
	}

	public List<UsuarioDto> getListUsuario() {
		return listUsuario;
	}

	public void setListUsuario(List<UsuarioDto> listUsuario) {
		this.listUsuario = listUsuario;
	}

	public ProductoServicioImpl getProductoServicioImpl() {
		return productoServicioImpl;
	}

	public void setProductoServicioImpl(ProductoServicioImpl productoServicioImpl) {
		this.productoServicioImpl = productoServicioImpl;
	}

	public List<ProductoDto> getListProducto() {
		return listProducto;
	}

	public void setListProducto(List<ProductoDto> listProducto) {
		this.listProducto = listProducto;
	}

	public List<ProveedorDto> getLstProveedores() {
		return lstProveedores;
	}

	public void setLstProveedores(List<ProveedorDto> lstProveedores) {
		this.lstProveedores = lstProveedores;
	}

	public ProveedorSrviceImpl getProveedorSrviceImpl() {
		return proveedorSrviceImpl;
	}

	public void setProveedorSrviceImpl(ProveedorSrviceImpl proveedorSrviceImpl) {
		this.proveedorSrviceImpl = proveedorSrviceImpl;
	}

}
