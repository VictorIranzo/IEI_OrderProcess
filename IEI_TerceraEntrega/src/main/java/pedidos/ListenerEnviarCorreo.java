package pedidos;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

public class ListenerEnviarCorreo implements TaskListener {

	private static final long serialVersionUID = 1L;

	@Override
	public void notify(DelegateTask delegado) {

		System.out.println("Inicio de envío de correo");

		ServicioClientes service = new ServicioClientes();

		int idCliente = ((Number) delegado.getExecution().getVariable("IDCliente")).intValue();
		String email = service.obtenerCorreo(idCliente);
		
		String asunto = (String) delegado.getExecution().getVariable("IDAsunto");
		Date fechaEntrega = (Date) delegado.getExecution().getVariable("IDFechaEntrega");
		String cuerpo = (String) delegado.getExecution().getVariable("IDCuerpo");
		
		DateFormat formatter = new SimpleDateFormat("DD-MM-yyyy"); 
		
		cuerpo += "\nFecha de Entrega: " + formatter.format(fechaEntrega);
		
		ServicioCorreo servicioCorreo = new ServicioCorreo();
		servicioCorreo.enviarCorreo(email, asunto, cuerpo);
		
		System.out.println("Correo enviado");
	}

}
