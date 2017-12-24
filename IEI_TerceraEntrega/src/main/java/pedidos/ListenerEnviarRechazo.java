package pedidos;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

public class ListenerEnviarRechazo implements TaskListener {

	private static final long serialVersionUID = 1L;

	@Override
	public void notify(DelegateTask delegado) {

		System.out.println("Inicio de envío de correo");

		ServicioClientes service = new ServicioClientes();

		int idCliente = (Integer) delegado.getExecution().getVariable("IDCliente");
		String email = service.obtenerCorreo(idCliente);

		String asunto = "Pedido Rechazado";
		String cuerpo = (String) delegado.getExecution().getVariable("IDDetallesRechazo");

		// Envío de correo.

		final String username = "ieiterceraentrega@gmail.com"; // desde donde se envía
		final String password = "claravictor"; // el password de Gmail.

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("ieiterceraentrega@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email)); // A quién se envía.
			message.setSubject(asunto);
			message.setText(cuerpo);

			Transport.send(message);

		} catch (MessagingException e) {
			System.out.println("Excepción detectada" + e);
			throw new RuntimeException(e);
		}
	}

}
