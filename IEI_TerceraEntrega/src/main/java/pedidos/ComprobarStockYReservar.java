package pedidos;

import java.sql.ResultSet;
import java.util.Date;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class ComprobarStockYReservar implements JavaDelegate{

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		int idCabeceraPedido = (Integer) execution.getVariable("IDCabeceraPedido");
		boolean todoReservado = true;
		
		ServicioPedidos service = new ServicioPedidos();
		ResultSet rs = service.obtenerLineasPedido(idCabeceraPedido);
		while(rs.next()) {
			int idArticulo =  rs.getInt(1);
			int cantidad = rs.getInt(2);
			
			int stock = service.obtenerStock(idArticulo);
			
			if(stock - cantidad >= 0) {
				service.reservarArticulo(idArticulo, cantidad);
				System.out.println("Reservando " + cantidad + "unidades del articulo :" + idArticulo);
			}else { 
				System.out.println("No queda stock suficiente del articulo :" + idArticulo);
				todoReservado = false;
			}
			execution.setVariable("IDReservado", todoReservado);
			
		}
		
		
		
	}

}
