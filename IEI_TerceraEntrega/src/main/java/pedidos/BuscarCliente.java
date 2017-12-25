package pedidos;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class BuscarCliente implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		int codigoCliente = ((Number) execution.getVariable("IDCliente")).intValue();

		System.out.println("Buscando al cliente con código: " + codigoCliente);

		ServicioClientes servicio = new ServicioClientes();
		boolean encontrado = servicio.buscarCliente(codigoCliente);

		// Añadir el resultado al motor.
		System.out.println("Encontrado " + encontrado);

		execution.setVariable("IDEncontrado", encontrado);
	}
}
