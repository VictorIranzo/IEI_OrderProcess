package pedidos;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.delegate.TaskListener;

public class CrearLineaPedido implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		ServicioPedidos service = new ServicioPedidos();

		// Acceso a las variables introducidas en el formulario.
		String codigoArticulo = (String) execution.getVariable("IDCodigoArticulo");
		int cantidad = ((Number) execution.getVariable("IDCantidad")).intValue();

		System.out.println("Buscando el artículo con código: " + codigoArticulo);
		int idArticulo = service.buscarArticuloPorCodigo(codigoArticulo);

		if (idArticulo != -1) {
			System.out.println("Articulo encontrado, creando línea de pedido.");

			int idCabeceraPedido = ((Number) execution.getVariable("IDCabeceraPedido")).intValue();
			service.insertarLineaPedido(idCabeceraPedido, idArticulo, cantidad);
		}
		else {
		System.out.println("Artículo no encontrado.");
		}
	}
}
