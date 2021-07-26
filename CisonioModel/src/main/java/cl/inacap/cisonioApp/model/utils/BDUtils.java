package cl.inacap.cisonioApp.model.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDUtils {
	private final String servidor = "localhost";
	private final String usuario = "mom";
	private final String clave = "@Anatem4";
	private final String baseDeDatos = "cisoniodb";
	private Connection conexion;
	private Connection conn;
	
	public Connection getConexion() {
		return conexion;
	}
	
	public boolean conectar() {
		boolean resultado;
		try {
			String url = "jdbc:mysql://"+ servidor + ":3306/" + baseDeDatos;
			this.conexion = DriverManager.getConnection(url, usuario, clave);
			resultado = true;
		}catch(SQLException ex) {
			resultado = false;
			/*System.out.println("SQLException: " + ex.getMessage());  for testing the connection
			 *System.out.println("SQLState: " + ex.getSQLState());
			 *System.out.println("VendorError: " + ex.getErrorCode());
			 */
		}
		return resultado;
	}		
	public void desconnectar() {
		try {
			this.conexion.close();
			conn.close();
		}catch(Exception ex) {}
	}
}

