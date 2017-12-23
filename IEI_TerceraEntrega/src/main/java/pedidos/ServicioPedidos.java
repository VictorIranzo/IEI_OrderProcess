package pedidos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZoneId;
import java.util.Date;

public class ServicioPedidos{

	public boolean buscarArticulo(String codArticulo) {
		boolean encontrado = false; 
		
		Connection conn = Conexion.abrirConexion();
		
		if(conn != null) {
			String SQL = "SELECT CodigoArticulo FROM Articulos  WHERE CodigoArticulo =?";
			
			try {
				PreparedStatement statement = conn.prepareStatement(SQL); 
				statement.setString(1, codArticulo);
				ResultSet result = statement.executeQuery();
				if (result.next()) encontrado= true;
				else encontrado = false; 
				Conexion.cerrarConexion(); 
				return (encontrado);
				} catch (SQLException e) { e.printStackTrace();
				}
				} Conexion.cerrarConexion(); 
				return false;
				}
	
	public int insertarCabeceraPedidos(Date fecha, int idCliente) {
		int clave = 0;
		Connection conn = Conexion.abrirConexion();
		if (conn != null) {
			String SQL = "INSERT INTO CabeceraPedidos (FechaPedido, _idClientes) VALUES (?,?)";
			try {
			PreparedStatement statement = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
			java.sql.Date sqlDate = java.sql.Date.valueOf(fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
			statement.setDate(1,sqlDate);
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
		}
		
	}

}
