package pedidos;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class ValidarPedido implements JavaDelegate{

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		String codigoArticulo = (String) execution.getVariable("IDCodigoArticulo");
		
		System.out.println("Buscando el artículo con código: " + codigoArticulo);
		ServicioPedidos service = new ServicioPedidos();
		
		boolean encontrado = service.buscarArticulo(codigoArticulo);
	}

}
