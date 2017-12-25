package pedidos;

import java.util.Date;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.joda.time.DateTime;

public class ListenerFechaEnvio implements TaskListener {
	private static final long serialVersionUID = 1L;

	@Override
	public void notify(DelegateTask delegado) {
		boolean todoReservado = (Boolean) delegado.getExecution().getVariable("IDReservado");
		Date fechaEntrega;
		if (todoReservado) {
			fechaEntrega = DateTime.now().plusDays(7).toDate();

		} else {
			fechaEntrega = DateTime.now().plusMonths(1).toDate();
		}
		delegado.getExecution().setVariable("IDFechaEntrega", fechaEntrega);
	}
}
