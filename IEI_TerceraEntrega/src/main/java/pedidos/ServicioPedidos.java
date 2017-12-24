package pedidos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZoneId;
import java.util.Date;

public class ServicioPedidos {

	public int buscarArticuloPorCodigo(String codArticulo) {
		boolean encontrado = false;
		int idArticulo = -1;
		
		Connection conn = Conexion.abrirConexion();

		if (conn != null) {
			String SQL = "SELECT CodigoArticulo FROM Articulos  WHERE CodigoArticulo =?";

			try {
				PreparedStatement statement = conn.prepareStatement(SQL);
				statement.setString(1, codArticulo);
				ResultSet result = statement.executeQuery();
				
				if (result.next()) {
					encontrado = true;
					result.first();
					idArticulo = result.getInt(1);
				}else
					encontrado = false;
				
				Conexion.cerrarConexion();
				return idArticulo;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		Conexion.cerrarConexion();
		return idArticulo;
	}

	public int insertarCabeceraPedidos(Date fecha, int idCliente) {
		int clave = 0;
		Connection conn = Conexion.abrirConexion();
		if (conn != null) {
			String SQL = "INSERT INTO CabeceraPedidos (FechaPedido, Clientes_idClientes) VALUES (?,?)";
			try {
				PreparedStatement statement = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
				java.sql.Date sqlDate = java.sql.Date
						.valueOf(fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
				statement.setDate(1, sqlDate);
				statement.setInt(2, idCliente);

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

	public int insertarLineaPedido(int idCabecera, int idArticulo, int cantidad) {
		int clave = 0;
		Connection conn = Conexion.abrirConexion();
		if (conn != null) {
			try {
				String SQL = "INSERT INTO LineaPedidos (CabeceraPedidos_idCabeceraPedidos, Articulos_idArticulos, Cantidad) VALUES (?,?,?)";

				PreparedStatement statement = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
				statement.setInt(1, idCabecera);
				statement.setInt(2, idArticulo);
				statement.setInt(3, cantidad);

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

	public int obtenerNumeroPedidosDeCabecera(int idCabeceraPedido) {
		int numeroPedidos = 0;
		Connection conn = Conexion.abrirConexion();
		if (conn != null) {
			try {
				String SQL = "SELECT COUNT(*) FROM LineaPedidos WHERE CabeceraPedidos_idCabeceraPedidos = ?";

				PreparedStatement statement = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
				statement.setInt(1, idCabeceraPedido);
				
				ResultSet rs = statement.executeQuery();
				numeroPedidos = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				Conexion.cerrarConexion();
			}
		}
		return numeroPedidos;
	}
}
