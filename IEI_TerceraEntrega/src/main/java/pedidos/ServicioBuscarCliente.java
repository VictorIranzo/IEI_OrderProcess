package pedidos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class ServicioBuscarCliente implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		String codigoCliente = (String) execution.getVariable("IDCodigoCliente");

		System.out.println("Buscando al cliente con c�digo: " + codigoCliente);
		
		ServicioClientes servicio = new ServicioClientes();
		boolean encontrado = servicio.buscarCliente(Integer.parseInt(codigoCliente));
	}
}
