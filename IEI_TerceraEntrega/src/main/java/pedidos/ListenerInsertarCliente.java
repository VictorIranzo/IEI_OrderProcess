package pedidos;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

public class ListenerInsertarCliente implements TaskListener {
	private static final long serialVersionUID = 1L;

	@Override
	public void notify(DelegateTask tareaDelegada) {
		// Acceso a las variables introducidas en el formulario.
		ServicioClientes servicio = new ServicioClientes();

		String Nombre = (String) tareaDelegada.getExecution().getVariable("IDNombre");
		String direccion = (String) tareaDelegada.getExecution().getVariable("IDDireccion");
		java.util.Date fechaAlta = (java.util.Date) tareaDelegada.getExecution().getVariable("IDFechaAlta");
		String correoElectronico = (String) tareaDelegada.getExecution().getVariable("IDEmail");
		String NumTarjeta = (String) tareaDelegada.getExecution().getVariable("IDTarjeta");
		String emisor = (String) tareaDelegada.getExecution().getVariable("IDEmisor");

		int idcliente = servicio.insertarCliente(Nombre, direccion, fechaAlta, NumTarjeta, emisor, correoElectronico);

		// No se utiliza en el ejemplo IDCliente.
		tareaDelegada.getExecution().setVariable("IDCliente", idcliente);
	}
}
