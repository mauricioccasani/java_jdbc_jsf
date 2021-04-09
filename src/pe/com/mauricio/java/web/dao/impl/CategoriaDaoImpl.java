package pe.com.mauricio.java.web.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleTypes;
import pe.com.mauricio.java.web.dao.excepcion.DaoExcepcion;
import pe.com.mauricio.java.web.dao.inf.CategoriaDao;
import pe.com.mauricio.java.web.db.ConexionDB;
import pe.com.mauricio.java.web.dto.CategoriaDto;
import pe.com.mauricio.java.web.dto.UsuarioDto;

public class CategoriaDaoImpl implements CategoriaDao {

	public CategoriaDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean insertar(CategoriaDto categoriaDto) throws DaoExcepcion {
		try {

			String sql = "{ CALL PKG_CATEGORIA.SP_INSERTAR(?,?,?)}";
			Connection conn = ConexionDB.getConnection();
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, categoriaDto.getIdCategoria());
			cs.setString(2, categoriaDto.getNombre());
			cs.setString(3, categoriaDto.getDescripcion());
			//cs.setInt(4, categoriaDto.getEstado());
			cs.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			new DaoExcepcion("Error: "+e.getMessage());
		}
		return false;

	}

	@Override
	public boolean actualizar(CategoriaDto t) throws DaoExcepcion {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminar(CategoriaDto t) throws DaoExcepcion {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CategoriaDto obtenerPorId(CategoriaDto t) throws DaoExcepcion {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CategoriaDto> listar(CategoriaDto t) throws DaoExcepcion {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CategoriaDto> listAll() throws DaoExcepcion {
		CallableStatement cs = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			String sql = "{ CALL PKG_CATEGORIA.SP_LISTAR(?) }";
			conn = ConexionDB.getConnection();
			cs = conn.prepareCall(sql);
			cs.registerOutParameter(1, OracleTypes.CURSOR);
			cs.execute();
			rs = (ResultSet) cs.getObject(1);
			List<CategoriaDto> categoriaDtos = new ArrayList<CategoriaDto>();
			while (rs.next()) {
				CategoriaDto dto = new CategoriaDto();
				dto.setIdCategoria(rs.getInt(1));
				dto.setNombre(rs.getString(2).trim());
				dto.setDescripcion(rs.getString(3).trim());
				dto.setEstado(rs.getInt(4));

				categoriaDtos.add(dto);
			}
			return categoriaDtos;

		} catch (SQLException e) {
			new SQLException(e.getMessage());
			return null;
		}

	}

}
