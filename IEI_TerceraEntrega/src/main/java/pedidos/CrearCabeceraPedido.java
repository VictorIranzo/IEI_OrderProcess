package pedidos;

import java.time.ZoneId;
import java.util.Date;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.delegate.TaskListener;

public class CrearCabeceraPedido implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {

		int idCliente = ((Number) execution.getVariable("IDCliente")).intValue();
		Date fecha = new Date();

		ServicioPedidos servicio = new ServicioPedidos();
		int cabeceraPedido = servicio.insertarCabeceraPedidos(fecha, idCliente);

		// TODO: Definir variable.
		execution.setVariable("IDCabeceraPedido", cabeceraPedido);

	}

}
