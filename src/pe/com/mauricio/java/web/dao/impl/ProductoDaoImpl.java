package pe.com.mauricio.java.web.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleTypes;
import pe.com.mauricio.java.web.dao.excepcion.DaoExcepcion;
import pe.com.mauricio.java.web.dao.inf.ProductoDao;
import pe.com.mauricio.java.web.dao.inf.ProveedorDao;
import pe.com.mauricio.java.web.db.ConexionDB;
import pe.com.mauricio.java.web.dto.ProductoDto;
import pe.com.mauricio.java.web.dto.ProveedorDto;
import pe.com.mauricio.java.web.dto.UsuarioDto;
import pe.com.mauricio.java.web.dto.ProductoDto;

public class ProductoDaoImpl implements ProductoDao {

	@Override
	public boolean insertar(ProductoDto productoDto) throws DaoExcepcion {
		try {

			String sql = "{ CALL PKG_PRODUCTO.SP_INSERTAR(?,?,?,?,?,?,?,?,?)}";
			Connection conn = ConexionDB.getConnection();
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, productoDto.getIdProducto());
			cs.setString(2, productoDto.getNombre());
			cs.setInt(3, productoDto.getCantidad());
			cs.setDouble(4, productoDto.getPrecio());
			cs.setInt(5, productoDto.getIdUsuario());
			cs.setInt(6, productoDto.getAudIdUsuario());
			cs.setString(7, productoDto.getAudSesion());
			cs.setString(8, productoDto.getAudIp());
			cs.setString(9, productoDto.getAudNombreEquipo());
			cs.executeUpdate();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

	@Override
	public boolean actualizar(ProductoDto productoDto) throws DaoExcepcion {
		try {

			String sql = "{ CALL PKG_PRODUCTO.SP_ACTUALIZAR(?,?,?,?,?,?,?,?)}";
			Connection conn = ConexionDB.getConnection();
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, productoDto.getIdProducto());
			cs.setString(2, productoDto.getNombre());
			cs.setInt(3, productoDto.getCantidad());
			cs.setDouble(4, productoDto.getPrecio());
			cs.setInt(5, productoDto.getAudIdUsuario());
			cs.setString(6, productoDto.getAudSesion());
			cs.setString(7, productoDto.getAudIp());
			cs.setString(8, productoDto.getAudNombreEquipo());
			cs.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean eliminar(ProductoDto productoDto) throws DaoExcepcion {
		try {
			String sql = "{CALL PKG_PRODUCTO.SP_DELETE(?,?,?,?,?)}";
			Connection cn = ConexionDB.getConnection();
			CallableStatement cs = cn.prepareCall(sql);
			cs.setInt(1, productoDto.getIdProducto());
			cs.setInt(2, productoDto.getAudIdUsuario());
			cs.setString(3, productoDto.getAudSesion());
			cs.setString(4, productoDto.getAudIp());
			cs.setString(5, productoDto.getAudNombreEquipo());
			cs.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public ProductoDto obtenerPorId(ProductoDto productoDto) throws DaoExcepcion {

		try {
			CallableStatement cs = null;
			Connection conn = null;
			String sql = "{ CALL PKG_PRODUCTO.SP_BUSCARXID(?,?) }";
			ResultSet rs = null;
			conn = ConexionDB.getConnection();
			cs = conn.prepareCall(sql);
			cs.registerOutParameter(1, OracleTypes.CURSOR);
			cs.setInt(2, productoDto.getIdProducto());
			// cs.registerOutParameter(3, OracleTypes.INTEGER);
			// cs.registerOutParameter(4, OracleTypes.VARCHAR);
			cs.execute();
			rs = (ResultSet) cs.getObject(1);
			if (rs.next()) {
				// rolDto=new RolDto();
				ProductoDto productoDto2 = new ProductoDto();
				productoDto2.setIdProducto(rs.getInt("ID_PRODUCTO"));
				productoDto2.setNombre(rs.getString("NOMBRE"));
				productoDto2.setCantidad(rs.getInt("CANTIDAD"));
				productoDto2.setPrecio((rs.getDouble("PRECIO")));
				productoDto2.setEstado(rs.getString("ESTADO"));
				productoDto2.setIdUsuario(rs.getInt("ID_USUARIO"));
				return productoDto2;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	@Override
	public List<ProductoDto> listar(ProductoDto productoDto) throws DaoExcepcion {

		try {

			CallableStatement cs = null;
			Connection conn = null;
			String sql = "{ CALL PKG_PRODUCTO.SP_LISTAR_PRODUCTO(?,?) }";
			ResultSet rs = null;
			conn = ConexionDB.getConnection();
			cs = conn.prepareCall(sql);

			cs.registerOutParameter(1, OracleTypes.CURSOR);
			cs.setString(2, productoDto.getNombre());
			cs.execute();
			rs = (ResultSet) cs.getObject(1);
			List<ProductoDto> productoDtos = new ArrayList<ProductoDto>();
			while (rs.next()) {
				ProductoDto dto = new ProductoDto();
				dto.setIdProducto(rs.getInt(1));
				dto.setNombre(rs.getString(2).trim());
				dto.setCantidad(rs.getInt(3));
				dto.setPrecio(rs.getDouble(4));
				dto.setEstado(rs.getString(5));
				dto.setIdUsuario(rs.getInt(6));
				productoDtos.add(dto);

			}
			return productoDtos;
			// ConexionDB.closing(conn, cs, rs);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public List<UsuarioDto> list() {

		try {

			CallableStatement cs = null;
			Connection conn = null;
			String sql = "{ CALL PKG_PRODUCTO.SP_LISTAR_USUARIO_PRODUCTO(?) }";
			ResultSet rs = null;
			conn = ConexionDB.getConnection();
			cs = conn.prepareCall(sql);

			cs.registerOutParameter(1, OracleTypes.CURSOR);
			cs.execute();
			rs = (ResultSet) cs.getObject(1);
			List<UsuarioDto> usuarioDtos = new ArrayList<UsuarioDto>();
			while (rs.next()) {
				UsuarioDto dto = new UsuarioDto();
				dto.setIdUsuario(rs.getInt(1));
				dto.setUsuario(rs.getString(2).trim());

				usuarioDtos.add(dto);
			}
			return usuarioDtos;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public List<ProductoDto> listarProductos() throws DaoExcepcion {
		CallableStatement cs = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			String sql = "{ CALL PKG_PRODUCTO.SP_LISTAR_PRODUCTO(?) }";
			conn = ConexionDB.getConnection();
			cs = conn.prepareCall(sql);
			cs.registerOutParameter(1, OracleTypes.CURSOR);
			cs.execute();
			rs = (ResultSet) cs.getObject(1);
			List<ProductoDto> productoDtos = new ArrayList<ProductoDto>();
			while (rs.next()) {
				ProductoDto dto = new ProductoDto();
				dto.setIdProducto(rs.getInt(1));
				dto.setNombre(rs.getString(2).trim());
				//dto.setPrecio(rs.getDouble(3));;
				productoDtos.add(dto);
			}
			return productoDtos;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}
