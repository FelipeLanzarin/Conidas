package br.convidas.front.contact.cities;

import java.io.IOException;
import java.net.URL;

import br.convidas.front.contact.cities.controller.ControllerCities;
import br.convidas.front.contact.controller.ControllerModalPF;
import br.convidas.front.contact.controller.ControllerModalPJ;
import br.convidas.tools.log.LogTools;
import br.convidas.utils.XmlPathUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TelaCities extends Application {
	
	private ControllerModalPF controllerModalPF;
	private ControllerModalPJ controllerModalPJ;
	
	@Override
	public void start(Stage stage) throws Exception {
		try {
			URL arquivoFXML = getClass().getResource(XmlPathUtils.CITIES);
			Parent fxmlParent;
			FXMLLoader loader = new FXMLLoader(arquivoFXML);
			fxmlParent = (Parent) loader.load();
			stage.setResizable(false);
			stage.setScene(new Scene(fxmlParent));
			ControllerCities control = (ControllerCities) loader.getController();
			control.setStage(stage);
			control.setControllerModalPF(controllerModalPF);
			control.setControllerModalPJ(controllerModalPJ);
			control.updateTablePF(null);
			stage.setTitle("Cidades");
			stage.show();
		
		} catch (IOException e) {
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
	

}
