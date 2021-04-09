package pe.com.mauricio.java.web.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConexionDB {

	private static Connection con;
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:ORCL";
	private static final String DRIVER = "oracle.jdbc.OracleDriver";
	private static final String USER = "JAVAWEB";
	private static final String PASSWORD = "ORACLE";

	public static Connection getConnection() {
		try {
			if (con == null) {
				Class.forName(DRIVER);
				con = DriverManager.getConnection(URL, USER, PASSWORD);
				//System.out.println("Exito al conectase");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			//System.out.println("Error en ConnectionUtil>getConnection: " + e);
		}
		return con;
	}

	public static void closing(Connection cn, PreparedStatement ps, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (cn != null) {
				cn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
