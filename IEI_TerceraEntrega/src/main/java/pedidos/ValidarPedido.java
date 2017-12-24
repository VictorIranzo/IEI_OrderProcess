package pedidos;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class ValidarPedido implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		int idCabeceraPedido = (Integer) execution.getVariable("IDCabeceraPedido");

		ServicioPedidos service = new ServicioPedidos();
		int numPedidosValidos = service.obtenerNumeroPedidosDeCabecera(idCabeceraPedido);

		System.out.println("Pedidos válidos: " + numPedidosValidos);

		if (numPedidosValidos <= 0) {
			execution.setVariable("IDValido", false);
		}
	}

}
