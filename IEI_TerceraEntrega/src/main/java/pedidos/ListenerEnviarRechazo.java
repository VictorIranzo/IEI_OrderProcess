package pedidos;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

public class ListenerEnviarRechazo implements TaskListener {

	private static final long serialVersionUID = 1L;

	@Override
	public void notify(DelegateTask delegado) {

		System.out.println("Inicio de envío de correo");

		ServicioClientes service = new ServicioClientes();

		int idCliente = ((Number) delegado.getExecution().getVariable("IDCliente")).intValue();
		String email = service.obtenerCorreo(idCliente);

		String asunto = "Pedido Rechazado";
		String cuerpo = (String) delegado.getExecution().getVariable("IDDetallesRechazo");

		
		ServicioCorreo serviceCorreo = new ServicioCorreo();
		serviceCorreo.enviarCorreo(email, asunto, cuerpo);
	}
}
