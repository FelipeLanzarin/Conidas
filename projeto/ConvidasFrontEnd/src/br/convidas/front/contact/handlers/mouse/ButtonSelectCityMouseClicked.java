package br.convidas.front.contact.handlers.mouse;

import br.convidas.classes.Cidade;
import br.convidas.front.contact.cities.controller.ControllerCities;
import br.convidas.front.contact.controller.ControllerModalPF;
import br.convidas.front.contact.controller.ControllerModalPJ;
import br.convidas.front.event.controller.ControllerModalEvent;
import br.convidas.tools.log.LogTools;
import fx.tools.action.EventAction;

public class ButtonSelectCityMouseClicked implements EventAction {
	
	private ControllerModalPF controllerModalPF;
	private ControllerModalPJ controllerModalPJ;
	private ControllerModalEvent controllerModalEvent;
	private ControllerCities controllerCities;
	private Cidade cidade;
	
	@Override
	public void action() {
		try {
			if(controllerModalPF != null){
				controllerModalPF.setCidadeSelect(cidade);
			}else if(controllerModalPJ != null){
				controllerModalPJ.setCidadeSelect(cidade);
			}else{
				controllerModalEvent.setCidadeSelect(cidade);
			}
			controllerCities.getStage().close();
		}catch (Exception e) {
			LogTools.logError(e);
		}
	}

	public ControllerModalPF getControllerModalPF() {
		return controllerModalPF;
	}

	public void setControllerModalPF(ControllerModalPF controllerModalPF) {
		this.controllerModalPF = controllerModalPF;
	}

	public ControllerModalPJ getControllerModalPJ() {
		return controllerModalPJ;
	}

	public void setControllerModalPJ(ControllerModalPJ controllerModalPJ) {
		this.controllerModalPJ = controllerModalPJ;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public ControllerCities getControllerCities() {
		return controllerCities;
	}

	public void setControllerCities(ControllerCities controllerCities) {
		this.controllerCities = controllerCities;
	}

	public ControllerModalEvent getControllerModalEvent() {
		return controllerModalEvent;
	}

	public void setControllerModalEvent(ControllerModalEvent controllerModalEvent) {
		this.controllerModalEvent = controllerModalEvent;
	}
	
}
