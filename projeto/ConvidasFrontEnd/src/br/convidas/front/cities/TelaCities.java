package br.convidas.front.cities;

import java.io.IOException;
import java.net.URL;

import br.convidas.front.cities.controller.ControllerCities;
import br.convidas.tools.log.LogTools;
import br.convidas.utils.XmlPathUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TelaCities extends Application {
	
	
	@Override
	public void start(Stage stage) throws Exception {
		try {
			URL arquivoFXML = getClass().getResource(XmlPathUtils.MANAGER_CITIES);
			Parent fxmlParent;
			FXMLLoader loader = new FXMLLoader(arquivoFXML);
			fxmlParent = (Parent) loader.load();
			stage.setResizable(false);
			stage.setScene(new Scene(fxmlParent));
			ControllerCities control = (ControllerCities) loader.getController();
			control.setStage(stage);
			control.updateTablePF(null);
			stage.setTitle("Cidades");
			stage.show();
		
		} catch (IOException e) {
			LogTools.logError(e);
		}
		
	}	

}
