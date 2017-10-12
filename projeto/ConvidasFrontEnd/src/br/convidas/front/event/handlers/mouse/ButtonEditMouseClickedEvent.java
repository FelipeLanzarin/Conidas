package br.convidas.front.event.handlers.mouse;

import br.convidas.classes.Evento;
import br.convidas.front.event.TelaModalEvent;
import br.convidas.front.event.controller.ControllerEvent;
import br.convidas.tools.log.LogTools;
import fx.tools.action.EventAction;
import javafx.stage.Stage;

public class ButtonEditMouseClickedEvent implements EventAction{
	private Evento event;
	private ControllerEvent controllerEvent;
	
	@Override
	public void action() {
		try {
			TelaModalEvent tme = new TelaModalEvent();
			tme.setEvent(event);
			tme.setControllerEvent(controllerEvent);
			tme.setNewEvent(false);
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

	public ControllerEvent getControllerEvent() {
		return controllerEvent;
	}

	public void setControllerEvent(ControllerEvent controllerEvent) {
		this.controllerEvent = controllerEvent;
	}
	
}
