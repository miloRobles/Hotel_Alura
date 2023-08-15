package modelo;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionBD {
	
	private String url = "jdbc:mysql://localhost/hotel_alura?serverTimezone=UTC";	
	private String user = "root";
	private String pass = "";
	private Connection con;
	
	public Connection getConnection() {
		
		try {
			con = DriverManager.getConnection(url,user,pass);
			return con;
		} catch (Exception e) {
			System.out.println("Error en la conexion"+e.getMessage());
		}
		return null;
		
	}
	
	
}
