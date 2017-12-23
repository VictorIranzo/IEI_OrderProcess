package pedidos;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

public class ListenerValidarPedido implements TaskListener {

	@Override
	public void notify(DelegateTask tareaDelegada) {
		
		ServicioPedidos service = new ServicioPedidos();
		
		// Acceso a las variables introducidas en el formulario.
		String CodigoArticulo = (String) tareaDelegada.getExecution().getVariable("IDCodigoArticulo");
		int Cantidad = (Integer) tareaDelegada.getExecution().getVariable("IDCantidad");
		boolean masArticulos = (Boolean) tareaDelegada.getExecution().getVariable("IDMasArticulos");
		
		 
		
	}

}
