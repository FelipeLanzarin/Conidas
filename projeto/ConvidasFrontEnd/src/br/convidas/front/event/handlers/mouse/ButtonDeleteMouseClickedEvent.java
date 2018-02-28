package br.convidas.front.event.handlers.mouse;

import br.convidas.classes.Evento;
import br.convidas.front.event.controller.ControllerEvent;
import br.convidas.manager.ManagerEvento;
import br.convidas.tools.log.LogTools;
import fx.tools.action.EventAction;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class ButtonDeleteMouseClickedEvent implements EventAction{
	
	private Evento event;
	private ControllerEvent controllerEvent;
	
	@Override
	public void action() {
		try {
			final ButtonType btnSim = new ButtonType("Sim");
			final ButtonType btnNao = new ButtonType("Não");
			
			Alert alert = new Alert(AlertType.CONFIRMATION, "Você deseja excluir o evento "+ event.getName()+"?", btnSim, btnNao);
			alert.showAndWait();

			if (alert.getResult() == btnSim) {
				if(ManagerEvento.delete(event)){
					Alert dialog = new Alert(Alert.AlertType.INFORMATION);
					dialog.setTitle("Sucesso!");
					dialog.setHeaderText("Evento excluída com sucesso");
					dialog.showAndWait();
					controllerEvent.updateTableEvents(null);
				}else{
					Alert dialog = new Alert(Alert.AlertType.ERROR);
					dialog.setTitle("Erro!");
					dialog.setHeaderText("Evento já possui participantes!");
					dialog.showAndWait();
				}
			}
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
