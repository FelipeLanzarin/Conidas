package br.convidas.front.event.handlers.mouse;

import br.convidas.classes.Evento;
import br.convidas.front.event.TelaEventDetail;
import br.convidas.front.event.controller.ControllerConsultEvent;
import br.convidas.tools.log.LogTools;
import fx.tools.action.EventAction;
import javafx.stage.Stage;

public class ButtonSeeMouseClickedEvent implements EventAction{
	private Evento event;
	private ControllerConsultEvent controllerEvent;
	
	@Override
	public void action() {
		try {
			TelaEventDetail tme = new TelaEventDetail();
			tme.setEvent(event);
			tme.start(new Stage());
		} catch (Exception e) {
			LogTools.logError(e);
		}
	}

	public Evento getEvent() {
		return event;
	}

	public void setEvent(Evento event) {
		this.event = event;
	}

	public ControllerConsultEvent getControllerEvent() {
		return controllerEvent;
	}

	public void setControllerEvent(ControllerConsultEvent controllerEvent) {
		this.controllerEvent = controllerEvent;
	}
	
}
