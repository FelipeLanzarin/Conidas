package br.convidas.front.cities.handlers.mouse;

import br.convidas.classes.Cidade;
import br.convidas.front.cities.controller.ControllerCities;
import br.convidas.front.contact.cities.TelaModalCity;
import br.convidas.tools.log.LogTools;
import fx.tools.action.EventAction;
import javafx.stage.Stage;

public class ButtonEditCityMouseClicked implements EventAction {
	
	private ControllerCities controllerCities;
	private Cidade cidade;
	
	@Override
	public void action() {
		try {
			TelaModalCity tmc = new TelaModalCity();
//			tmc.setControllerCities(controllerCities);
			tmc.setCidade(cidade);
			tmc.setNewCity(false);
			tmc.start(new Stage());
		}catch (Exception e) {
			LogTools.logError(e);
		}
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

}
