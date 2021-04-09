package pe.com.mauricio.java.web.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import oracle.jdbc.OracleTypes;
import pe.com.mauricio.java.web.dao.excepcion.DaoExcepcion;
import pe.com.mauricio.java.web.dao.inf.ProveedorDao;
import pe.com.mauricio.java.web.db.ConexionDB;
import pe.com.mauricio.java.web.dto.ProveedorDto;
import pe.com.mauricio.java.web.dto.UsuarioDto;
import pe.com.mauricio.java.web.mbean.GenericoMsg;

public class ProveedorDaoImpl extends GenericoMsg implements ProveedorDao {
	private static final int UNIQUE_CONSTRAINT_VIOLATED = 00001;

	@Override
	public boolean insertar(ProveedorDto proveedorDto) throws DaoExcepcion {
		try {

			String sql = "{ CALL PKG_PROVEEDORES.SP_INSERTAR_PROVEEDORES(?,?,?,?,?,?,?,?,?)}";
			Connection conn = ConexionDB.getConnection();
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, proveedorDto.getIdProveedor());
			cs.setString(2, proveedorDto.getDniProveedor());
			cs.setString(3, proveedorDto.getNombreProveedor());
			cs.setString(4, proveedorDto.getDireccion());
			cs.setString(5, proveedorDto.getTelefono());

			cs.setInt(6, proveedorDto.getAudIdUsuario());
			cs.setString(7, proveedorDto.getAudSesion());
			cs.setString(8, proveedorDto.getAudIp());
			cs.setString(9, proveedorDto.getAudNombreEquipo());
			cs.executeUpdate();
			// return true;
		} catch (SQLIntegrityConstraintViolationException e2) {
			String msg;
			if (UNIQUE_CONSTRAINT_VIOLATED == e2.getErrorCode()) {
				msg = "El registro ya se encuentra en la base de datos";
				System.out.println(msg = "El registro ya se encuentra en la base de datos");

			}
			super.msgAlert("Campo dni ya exixte");
			return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;

	}

	@Override
	public boolean actualizar(ProveedorDto proveedorDto) throws DaoExcepcion {
		try {

			String sql = "{ CALL PKG_PROVEEDORES.SP_ACTUALIZAR_PROVEEDORES(?,?,?,?,?,?,?,?,?)}";
			Connection conn = ConexionDB.getConnection();
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, proveedorDto.getIdProveedor());
			cs.setString(2, proveedorDto.getDniProveedor());
			cs.setString(3, proveedorDto.getNombreProveedor());
			cs.setString(4, proveedorDto.getDireccion());
			cs.setString(5, proveedorDto.getTelefono());
			cs.setInt(6, proveedorDto.getAudIdUsuario());
			cs.setString(7, proveedorDto.getAudSesion());
			cs.setString(8, proveedorDto.getAudIp());
			cs.setString(9, proveedorDto.getAudNombreEquipo());
			cs.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean eliminar(ProveedorDto proveedorDto) throws DaoExcepcion {
		try {
			String sql = "{CALL PKG_PROVEEDORES.SP_ELIMINAR_PROVEEDORES(?,?,?,?,?)}";
			Connection cn = ConexionDB.getConnection();
			CallableStatement cs = cn.prepareCall(sql);
			cs.setInt(1, proveedorDto.getIdProveedor());
			cs.setInt(2, proveedorDto.getAudIdUsuario());
			cs.setString(3, proveedorDto.getAudSesion());
			cs.setString(4, proveedorDto.getAudIp());
			cs.setString(5, proveedorDto.getAudNombreEquipo());
			cs.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public ProveedorDto obtenerPorId(ProveedorDto proveedorDto) throws DaoExcepcion {
		try {
			CallableStatement cs = null;
			Connection conn = null;
			String sql = "{ CALL PKG_PROVEEDORES.SP_BUSCARXID_PROVEEDORES(?,?) }";
			ResultSet rs = null;
			conn = ConexionDB.getConnection();
			cs = conn.prepareCall(sql);
			cs.registerOutParameter(1, OracleTypes.CURSOR);
			cs.setInt(2, proveedorDto.getIdProveedor());
			cs.execute();
			rs = (ResultSet) cs.getObject(1);
			if (rs.next()) {
				// rolDto=new RolDto();
				ProveedorDto proveedorDto2 = new ProveedorDto();
				proveedorDto2.setIdProveedor(rs.getInt("ID_PROVEEDOR"));
				proveedorDto2.setDniProveedor(rs.getString("DNI"));
				proveedorDto2.setNombreProveedor(rs.getString("NOMBRE"));
				proveedorDto2.setDireccion((rs.getString("DIRECCION")));
				proveedorDto2.setTelefono((rs.getString("TELEFONO")));
				return proveedorDto2;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<ProveedorDto> listar(ProveedorDto proveedorDto) throws DaoExcepcion {

		try {

			CallableStatement cs = null;
			Connection conn = null;
			String sql = "{ CALL PKG_PROVEEDORES.SP_LISTAR_PROVEEDORES(?,?) }";
			ResultSet rs = null;
			conn = ConexionDB.getConnection();
			cs = conn.prepareCall(sql);

			cs.registerOutParameter(1, OracleTypes.CURSOR);
			cs.setString(2, proveedorDto.getDniProveedor());

			cs.execute();
			rs = (ResultSet) cs.getObject(1);
			List<ProveedorDto> proveedorDtos = new ArrayList<ProveedorDto>();
			while (rs.next()) {
				ProveedorDto proveedor = new ProveedorDto();
				proveedor.setIdProveedor(rs.getInt("ID_PROVEEDOR"));
				proveedor.setDniProveedor(rs.getString("DNI"));
				proveedor.setNombreProveedor(rs.getString("NOMBRE"));
				proveedor.setDireccion(rs.getString("DIRECCION"));
				proveedor.setTelefono(rs.getString("TELEFONO"));
				// proveedor.setEstado(rs.getString("ESTADO"));
				// proveedor.setAudIdUsuario(rs.getInt("AUD_IDUSUARIO"));
				// proveedor.setAudSesion(rs.getString("AUD_SESION"));

				proveedorDtos.add(proveedor);

			}
			return proveedorDtos;
			// ConexionDB.closing(conn, cs, rs);

		} catch (Exception e) {
			return null;
		}

	}

	@Override
	// ESTE METODO FALTA TERMINARS
	public boolean validarProveedor(ProveedorDto proveedorDto) throws DaoExcepcion {
		boolean sw = false;
		try {
			CallableStatement cs = null;
			Connection conn = null;
			String sql = "{ CALL PKG_USUARIO.SP_VALIDAR_ACCESO(?,?,?) }";
			ResultSet rs = null;
			conn = ConexionDB.getConnection();
			cs = conn.prepareCall(sql);
			cs.registerOutParameter(1, OracleTypes.INTEGER);
			cs.setInt(2, proveedorDto.getIdProveedor());
			cs.setString(3, proveedorDto.getDniProveedor());
			cs.execute();
			rs = (ResultSet) cs.getObject(1);
			// Object ret=cs.registerOutP
			// ConexionDB.closing(conn, cs, rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public List<ProveedorDto> listarProveedores() throws DaoExcepcion {
		CallableStatement cs = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			String sql = "{ CALL PKG_PROVEEDORES.SP_LISTAR_PROVEEDOR(?) }";
			conn = ConexionDB.getConnection();
			cs = conn.prepareCall(sql);
			cs.registerOutParameter(1, OracleTypes.CURSOR);
			
			cs.execute();
			rs = (ResultSet) cs.getObject(1);
			List<ProveedorDto> proveedorDtos = new ArrayList<ProveedorDto>();
			while (rs.next()) {
				ProveedorDto proveedor = new ProveedorDto();
				proveedor.setIdProveedor(rs.getInt("ID_PROVEEDOR"));
				proveedor.setNombreProveedor(rs.getString("NOMBRE"));
				proveedorDtos.add(proveedor);
			}
			return proveedorDtos;
		} catch (Exception e) {
			return null;
		}

	}

}
