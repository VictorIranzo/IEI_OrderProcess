package pedidos;

import java.time.ZoneId;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import java.util.Date;

public class CrearPedido implements TaskListener{

	@Override
	public void notify(DelegateTask tareaDelegada) {
		
		int idCliente = (Integer) tareaDelegada.getExecution().getVariable("IDCliente");
		Date fecha = new Date();
		
		ServicioPedidos servicio = new ServicioPedidos();
		int cabeceraPedido = servicio.insertarCabeceraPedidos(fecha, idCliente);
		
		
		
		
	}

}
