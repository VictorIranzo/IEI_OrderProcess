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
		String email = (String) tareaDelegada.getExecution().getVariable("IDDireccion");
		java.util.Date fechaAlta = (java.util.Date) tareaDelegada.getExecution().getVariable("IDFechaAlta");
		String correoElectronico = (String) tareaDelegada.getExecution().getVariable("IDCorreoElectronico");
		String NumTarjeta = (String) tareaDelegada.getExecution().getVariable("IDNumeroTC");
		String emisor = (String) tareaDelegada.getExecution().getVariable("IDEmisor");
		
		String caducidad = (String) tareaDelegada.getExecution().getVariable("IDCaducidad");
		int idcliente = servicio.insertarCliente(Nombre, email, fechaAlta, NumTarjeta, emisor, caducidad);
		
		// No se utiliza en el ejemplo IDCliente
		tareaDelegada.getExecution().setVariable("IDCliente", idcliente);
	}
}
