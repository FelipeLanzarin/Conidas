package br.convidas.front.cities;

import java.io.IOException;
import java.net.URL;

import br.convidas.front.cities.controller.ControllerCityRelationManager;
import br.convidas.front.contact.controller.ControllerModalPF;
import br.convidas.front.contact.controller.ControllerModalPJ;
import br.convidas.front.event.controller.ControllerModalEvent;
import br.convidas.tools.log.LogTools;
import br.convidas.utils.XmlPathUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TelaCities extends Application {
	private ControllerModalPJ controllerModalPJ;
	private ControllerModalPF controllerModalPF;
	private ControllerModalEvent controllerModalEvent;
	private Boolean isSelect = false;
	
	@Override
	public void start(Stage stage) throws Exception {
		try {
			URL arquivoFXML = getClass().getResource(XmlPathUtils.MANAGER_CITIES);
			Parent fxmlParent;
			FXMLLoader loader = new FXMLLoader(arquivoFXML);
			fxmlParent = (Parent) loader.load();
			stage.setResizable(false);
			stage.setScene(new Scene(fxmlParent));
//			ControllerCities control = (ControllerCities) loader.getController();
//			control.setStage(stage);
//			control.updateTablePF(null);
//			stage.setTitle("Cidades");
			ControllerCityRelationManager control = (ControllerCityRelationManager) loader.getController();
			control.setStage(stage);
			control.createTable();
			control.setControllerModalEvent(controllerModalEvent);
			control.setControllerModalPF(controllerModalPF);
			control.setControllerModalPJ(controllerModalPJ);
			control.setIsSelect(isSelect);
			stage.setTitle("Cidades");
			stage.show();
		
		} catch (IOException e) {
			LogTools.logError(e);
		}
		
	}

	public ControllerModalPJ getControllerModalPJ() {
		return controllerModalPJ;
	}

	public void setControllerModalPJ(ControllerModalPJ controllerModalPJ) {
		this.controllerModalPJ = controllerModalPJ;
	}

	public ControllerModalPF getControllerModalPF() {
		return controllerModalPF;
	}

	public void setControllerModalPF(ControllerModalPF controllerModalPF) {
		this.controllerModalPF = controllerModalPF;
	}

	public ControllerModalEvent getControllerModalEvent() {
		return controllerModalEvent;
	}

	public void setControllerModalEvent(ControllerModalEvent controllerModalEvent) {
		this.controllerModalEvent = controllerModalEvent;
	}

	public Boolean getIsSelect() {
		return isSelect;
	}

	public void setIsSelect(Boolean isSelect) {
		this.isSelect = isSelect;
	}

}
