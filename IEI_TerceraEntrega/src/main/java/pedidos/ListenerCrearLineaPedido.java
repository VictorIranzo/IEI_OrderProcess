package pedidos;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

public class ListenerCrearLineaPedido implements TaskListener {

	@Override
	public void notify(DelegateTask tareaDelegada) {
		ServicioPedidos service = new ServicioPedidos();

		// Acceso a las variables introducidas en el formulario.
		String codigoArticulo = (String) tareaDelegada.getExecution().getVariable("IDCodigoArticulo");
		int cantidad = (Integer) tareaDelegada.getExecution().getVariable("IDCantidad");
		boolean masArticulos = (Boolean) tareaDelegada.getExecution().getVariable("IDMasArticulos");

		System.out.println("Buscando el artículo con código: " + codigoArticulo);
		int idArticulo = service.buscarArticuloPorCodigo(codigoArticulo);

		if (idArticulo != -1) {
			System.out.println("Articulo encontrado, creando línea de pedido.");

			int idCabeceraPedido = (Integer) tareaDelegada.getExecution().getVariable("IDCabeceraPedido");
			service.insertarLineaPedido(idCabeceraPedido, idArticulo, cantidad);
		}

		System.out.println("Artículo no encontrado.");
	}
}
