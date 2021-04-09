package pe.com.mauricio.java.web.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import oracle.jdbc.OracleTypes;
import pe.com.mauricio.java.web.dao.excepcion.DaoExcepcion;
import pe.com.mauricio.java.web.dao.inf.UsuarioDao;
import pe.com.mauricio.java.web.db.ConexionDB;
import pe.com.mauricio.java.web.dto.RolDto;
import pe.com.mauricio.java.web.dto.UsuarioDto;

public class UsuarioDaoImpl implements UsuarioDao {
	// private Logger log = Logger.getLogger(UsuarioDaoImpl.class);
	// private RolDto rolDto=new RolDto();

	@Override
	public boolean insertar(UsuarioDto usuarioDto) throws DaoExcepcion {
		try {

			String sql = "{ CALL PKG_USUARIO.SP_INSERT(?,?,?,?,?,?,?,?,?,?,?,?)}";
			Connection conn = ConexionDB.getConnection();
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, usuarioDto.getIdUsuario());
			cs.setString(2, usuarioDto.getUsuario());
			cs.setString(3, usuarioDto.getNombres());
			cs.setString(4, usuarioDto.getApellidos());
			cs.setString(5, usuarioDto.getNumeroDocumento());
			cs.setString(6, usuarioDto.getEmail());
			cs.setString(7, usuarioDto.getClave());
			cs.setInt(8, usuarioDto.getIdRol());
			cs.setInt(9, usuarioDto.getAudIdUsuario());
			cs.setString(10, usuarioDto.getAudSesion());
			cs.setString(11, usuarioDto.getAudIp());
			cs.setString(12, usuarioDto.getAudNombreEquipo());
			cs.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean actualizar(UsuarioDto usuarioDto) throws DaoExcepcion {
		try {

			String sql = "{ CALL PKG_USUARIO.SP_ACTUALIZAR(?,?,?,?,?,?,?,?,?,?,?,?)}";
			Connection conn = ConexionDB.getConnection();
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, usuarioDto.getIdUsuario());
			cs.setString(2, usuarioDto.getUsuario());
			cs.setString(3, usuarioDto.getNombres());
			cs.setString(4, usuarioDto.getApellidos());
			cs.setString(5, usuarioDto.getNumeroDocumento());
			cs.setString(6, usuarioDto.getEmail());
			cs.setString(7, usuarioDto.getClave());
			cs.setInt(8, usuarioDto.getIdRol());
			cs.setInt(9, usuarioDto.getAudIdUsuario());
			cs.setString(10, usuarioDto.getAudSesion());
			cs.setString(11, usuarioDto.getAudIp());
			cs.setString(12, usuarioDto.getAudNombreEquipo());
			cs.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean eliminar(UsuarioDto usuarioDto) throws DaoExcepcion {
		try {
			String sql = "{CALL PKG_USUARIO.SP_DELETE(?,?,?,?,?)}";
			Connection cn = ConexionDB.getConnection();
			CallableStatement cs = cn.prepareCall(sql);
			cs.setInt(1, usuarioDto.getIdUsuario());
			cs.setInt(2, usuarioDto.getAudIdUsuario());
			cs.setString(3, usuarioDto.getAudSesion());
			cs.setString(4, usuarioDto.getAudIp());
			cs.setString(5, usuarioDto.getAudNombreEquipo());
			cs.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public UsuarioDto obtenerPorId(UsuarioDto usuario) throws DaoExcepcion {
		try {
			CallableStatement cs = null;
			Connection conn = null;
			String sql = "{ CALL PKG_USUARIO.SP_BUSCARXID(?,?) }";
			ResultSet rs = null;
			conn = ConexionDB.getConnection();
			cs = conn.prepareCall(sql);
			cs.registerOutParameter(1, OracleTypes.CURSOR);
			cs.setInt(2, usuario.getIdUsuario());
			cs.execute();
			rs = (ResultSet) cs.getObject(1);
			if (rs.next()) {
				// rolDto=new RolDto();
				UsuarioDto usuarioDto = new UsuarioDto();
				usuarioDto.setIdUsuario(rs.getInt("ID_USUARIO"));
				usuarioDto.setUsuario(rs.getString("USUARIO"));
				usuarioDto.setNombres(rs.getString("NOMBRES"));
				usuarioDto.setApellidos(rs.getString("APELLIDOS"));
				usuarioDto.setNumeroDocumento(rs.getString("NUMERO_DOCUMENTO"));
				usuarioDto.setEmail(rs.getString("EMAIL"));
				usuarioDto.setClave(rs.getString("CLAVE"));
				usuarioDto.setIdRol(rs.getInt("ID_ROL"));
				usuarioDto.setEstado(rs.getString("ESTADO"));
				return usuarioDto;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public UsuarioDto valida(UsuarioDto usuarioDto) throws DaoExcepcion {
		UsuarioDto oUsuario = null;
		CallableStatement cs = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			String sql = "{ CALL PKG_USUARIO.SP_VALIDAR_ACCESO(?,?,?) }";
			conn = ConexionDB.getConnection();
			cs = conn.prepareCall(sql);
			cs.registerOutParameter(1, OracleTypes.CURSOR);
			cs.setString(2, usuarioDto.getUsuario());
			cs.setString(3, usuarioDto.getClave());
			cs.execute();
			rs = (ResultSet) cs.getObject(1);
			while (rs.next()) {
				oUsuario = new UsuarioDto();
				oUsuario.setIdUsuario(rs.getInt(1));
				oUsuario.setUsuario(rs.getString(2));
				oUsuario.setNombres(rs.getString(3));
				oUsuario.setApellidos(rs.getString(4));
				oUsuario.setNumeroDocumento(rs.getString(5));
				oUsuario.setEmail(rs.getString(6));
				oUsuario.setClave(rs.getString(7));
				oUsuario.setIdRol(rs.getInt(8));
				oUsuario.setEstado(rs.getString(9));
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoExcepcion(e.getMessage());
			
		}/*finally {
			ConexionDB.closing(conn, cs, rs);
		}*/
		return oUsuario;
	}

	@Override
	public List<UsuarioDto> listar(UsuarioDto usuario) throws DaoExcepcion {

		try {

			CallableStatement cs = null;
			Connection conn = null;
			String sql = "{ CALL PKG_USUARIO.SP_LISTAR(?,?,?) }";
			ResultSet rs = null;
			conn = ConexionDB.getConnection();
			cs = conn.prepareCall(sql);

			cs.registerOutParameter(1, OracleTypes.CURSOR);
			cs.setString(2, usuario.getNombres());
			cs.setString(3, usuario.getUsuario());
			cs.execute();
			rs = (ResultSet) cs.getObject(1);
			List<UsuarioDto> usuarios = new ArrayList<UsuarioDto>();
			while (rs.next()) {
				UsuarioDto usua = new UsuarioDto();
				usua.setIdUsuario(rs.getInt(1));
				usua.setUsuario(rs.getString(2));
				usua.setNombres(rs.getString(3));
				usua.setApellidos(rs.getString(4));
				usua.setNumeroDocumento(rs.getString(5));
				usua.setEmail(rs.getString(6));
				usua.setClave(rs.getString(7));
				usua.setIdRol(rs.getInt(8));
				usua.setEstado(rs.getString(9));
				usuarios.add(usua);

			}
			return usuarios;
			// ConexionDB.closing(conn, cs, rs);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public List<RolDto> listarUsuarioRol() throws DaoExcepcion {
		CallableStatement cs = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			String sql = "{ CALL PKG_USUARIO.SP_LISTAR_ROL(?) }";
			conn = ConexionDB.getConnection();
			cs = conn.prepareCall(sql);
			cs.registerOutParameter(1, OracleTypes.CURSOR);
			cs.execute();
			rs = (ResultSet) cs.getObject(1);
			List<RolDto> rolDtos = new ArrayList<RolDto>();
			while (rs.next()) {
				RolDto rolDto = new RolDto();
				rolDto.setIdRol(rs.getInt(1));
				rolDto.setNombre(rs.getString(2));
				rolDtos.add(rolDto);
			}
			return rolDtos;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
