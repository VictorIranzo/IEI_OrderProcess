package pedidos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServicioClientes {
	public boolean buscarCliente(int codigoCliente) {
		boolean encontrado = false;

		Connection conn = Conexion.abrirConexion();

		if (conn != null) {
			String SQL = "SELECT idClientes FROM Cliente where idClientes =?";
			try {
				PreparedStatement statement = conn.prepareStatement(SQL);
				statement.setInt(1, codigoCliente);
				ResultSet result = statement.executeQuery();
				if (result.next())
					encontrado = true;
				else
					encontrado = false;
				Conexion.cerrarConexion();
				return (encontrado);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		Conexion.cerrarConexion();
		return false;
	}
}
