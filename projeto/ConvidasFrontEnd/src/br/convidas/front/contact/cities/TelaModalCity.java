package br.convidas.front.contact.cities;

import java.io.IOException;
import java.net.URL;

import br.convidas.classes.Cidade;
import br.convidas.front.cities.controller.ControllerCities;
import br.convidas.front.cities.controller.ControllerModalCity;
import br.convidas.tools.log.LogTools;
import br.convidas.utils.XmlPathUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TelaModalCity extends Application {
	
	private Boolean newCity;
	private Cidade cidade;
	private ControllerCities controllerCities;
	
	@Override
	public void start(Stage stage) throws Exception {
		try {
			URL arquivoFXML = getClass().getResource(XmlPathUtils.MODAL_MANAGER_CITIES);
			Parent fxmlParent;
			FXMLLoader loader = new FXMLLoader(arquivoFXML);
			fxmlParent = (Parent) loader.load();
			stage.setResizable(false);
			stage.setScene(new Scene(fxmlParent));
			ControllerModalCity control = (ControllerModalCity) loader.getController();
			control.setCities(controllerCities);
			control.setNewCity(newCity);
			control.setCidade(cidade);
			control.setStage(stage);
			
			stage.setTitle("Convidas");
			stage.show();
		
		} catch (IOException e) {
			LogTools.logError(e);
		}
		
	}

	public Boolean getNewCity() {
		return newCity;
	}

	public void setNewCity(Boolean newCity) {
		this.newCity = newCity;
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
