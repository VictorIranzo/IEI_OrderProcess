import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.joda.time.DateTime;

import pedidos.ServicioClientes;
import pedidos.ServicioCorreo;
import pedidos.ServicioPedidos;

public class test {
	public static void main(String args[]) throws SQLException {
		ServicioClientes servicioClientes = new ServicioClientes();
		ServicioPedidos servicioPedidos = new ServicioPedidos();
		ServicioCorreo servicioCorreo = new ServicioCorreo();
		
		 servicioClientes.obtenerCorreo(1);
		// servicioClientes.buscarCliente(300); OK
		// servicioClientes.insertarCliente("Jose","Direccion", DateTime.now().toDate(), "AA", "AA", "AA"); OK
		
		// servicioPedidos.obtenerNumeroPedidosDeCabecera(12); OK
		//ResultSet rs = servicioPedidos.obtenerLineasPedido(12);
		//while(rs.next()) {
		//	int idArticulo = rs.getInt(1);
		//	int cantidad = rs.getInt(2);
		//}
		
		// servicioPedidos.obtenerStock(1); OK
		// servicioPedidos.reservarArticulo(1, 10); OK
		// servicioPedidos.buscarArticuloPorCodigo("2"); OK
		// servicioPedidos.insertarCabeceraPedidos(DateTime.now().toDate(), 1); OK
		// servicioPedidos.insertarLineaPedido(12, 4, 2); OK
		
	    // servicioCorreo.enviarCorreo("viranzo96@gmail.com", "Nada", "Cuerpo"); OK	
	}
}
