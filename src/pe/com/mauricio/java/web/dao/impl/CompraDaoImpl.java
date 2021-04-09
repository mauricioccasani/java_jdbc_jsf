package pe.com.mauricio.java.web.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleTypes;
import pe.com.mauricio.java.web.dao.excepcion.DaoExcepcion;
import pe.com.mauricio.java.web.dao.inf.CompraDao;
import pe.com.mauricio.java.web.db.ConexionDB;
import pe.com.mauricio.java.web.dto.CompraDto;

public class CompraDaoImpl implements CompraDao {

	public CompraDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean insertar(CompraDto compraDto) throws DaoExcepcion {
		try {

			String sql = "{ CALL PKG_COMPRAS.SP_INSERTAR_ACTUALIZAR_COMPRAS(?,?,?,?,?,?,?,?,?,?)}";
			Connection conn = ConexionDB.getConnection();
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, compraDto.getIdCompra());
			cs.setInt(2, compraDto.getIdProveedor());
			cs.setInt(3, compraDto.getIdProducto());
			cs.setInt(4, compraDto.getCantidad());
			cs.setDouble(5, compraDto.getValor());
			cs.setInt(6, compraDto.getIdUsuario());
			cs.setInt(7, compraDto.getAudIdUsuario());
			cs.setString(8, compraDto.getAudSesion());
			cs.setString(9, compraDto.getAudIp());
			cs.setString(10, compraDto.getAudNombreEquipo());
			cs.executeUpdate();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

	@Override
	public boolean actualizar(CompraDto t) throws DaoExcepcion {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminar(CompraDto t) throws DaoExcepcion {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CompraDto obtenerPorId(CompraDto t) throws DaoExcepcion {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CompraDto> listar(CompraDto compraDto) throws DaoExcepcion {
		CallableStatement cs = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			String sql = "{ CALL PKG_COMPRAS.SP_LISTAR_COMPRAS(?,?) }";
			conn = ConexionDB.getConnection();
			cs = conn.prepareCall(sql);

			cs.registerOutParameter(1, OracleTypes.CURSOR);
			cs.setDate(2, (Date) compraDto.getFecha());
			cs.execute();
			rs = (ResultSet) cs.getObject(1);
			List<CompraDto> compraDtos = new ArrayList<CompraDto>();
			while (rs.next()) {
				CompraDto dto = new CompraDto();
				dto.setIdCompra(rs.getInt(1));
				dto.setFecha(rs.getDate(2));
				dto.setIdProveedor(rs.getInt(3));
				dto.setIdProducto(rs.getInt(4));
				dto.setCantidad(rs.getInt(5));
				dto.setValor(rs.getDouble(6));
				dto.setIdUsuario(rs.getInt(7));

				compraDtos.add(dto);

			}
			return compraDtos;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
