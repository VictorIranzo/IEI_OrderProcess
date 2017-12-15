package pedidos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

	private static Connection conexion = null;

	public static Connection abrirConexion() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/entrega3procesos", "root", "root");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conexion;
	}

	public static void cerrarConexion() {
		if (conexion != null) {
			try {
				conexion.close();
			} catch (SQLException e) {
				System.out.println("Error al cerrar la conexión con la BD");
			}
		}
	}
}
