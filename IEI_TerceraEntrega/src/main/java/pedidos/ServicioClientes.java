package pedidos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZoneId;
import java.util.Date;

public class ServicioClientes {
	public boolean buscarCliente(int codigoCliente) {
		boolean encontrado = false;

		Connection conn = Conexion.abrirConexion();

		if (conn != null) {
			String SQL = "SELECT idClientes FROM clientes where idClientes =?";
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

	// A�ade un cliente y devuelve la clave autogenerada del mismo
	public int insertarCliente(String nombre, String direccion, Date fechaAlta, String numTarjetaCredito, String emisor,
			String correoElectronico) {
		int clave = 0;
		Connection conn = Conexion.abrirConexion();
		if (conn != null) {
			String SQL = "INSERT INTO clientes (Nombre, Direccion, FechaAlta, NumTarjeta, Emisor, Correoelectronico) VALUES (?,?,?,?,?,?)";
			try {
				PreparedStatement statement = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

				statement.setString(1, nombre);
				statement.setString(2, direccion);
				// Convertir java.util.Date a java.sql.Date.
				java.sql.Date sqlDate = java.sql.Date
						.valueOf(fechaAlta.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
				statement.setDate(3, sqlDate);
				statement.setString(4, numTarjetaCredito);
				statement.setString(5, emisor);
				statement.setString(6, correoElectronico);

				statement.executeUpdate();
				ResultSet claves = statement.getGeneratedKeys();
				claves.next();
				clave = claves.getInt(1);
				return clave;
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				Conexion.cerrarConexion();
			}
		}
		return clave;
	}

	public String obtenerCorreo(int idCliente) {
		String correo = "";
		Connection conn = Conexion.abrirConexion();

		if (conn != null) {
			String SQL = "SELECT Correoelectronico FROM clientes where idClientes =?";
			try {
				PreparedStatement statement = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

				statement.setInt(1, idCliente);

				ResultSet rs = statement.executeQuery();
				rs.first();
				correo = rs.getString(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		Conexion.cerrarConexion();
		return correo;
	}
}
