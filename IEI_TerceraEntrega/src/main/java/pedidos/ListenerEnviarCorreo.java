package pedidos;

import java.util.Date;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

public class ListenerEnviarCorreo implements TaskListener {

	private static final long serialVersionUID = 1L;

	@Override
	public void notify(DelegateTask delegado) {

		System.out.println("Inicio de envío de correo");

		String email = (String) delegado.getExecution().getVariable("IDEmail");
		String asunto = (String) delegado.getExecution().getVariable("IDAsunto");
		Date fechaEntrega = (Date) delegado.getExecution().getVariable("IDFechaEntrega");
		String cuerpo = (String) delegado.getExecution().getVariable("IDCuerpo");
		
		cuerpo += "\nFecha de Entrega: " + fechaEntrega.toString();
		
		ServicioCorreo servicioCorreo = new ServicioCorreo();
		servicioCorreo.enviarCorreo(email, asunto, cuerpo);
	}

}
