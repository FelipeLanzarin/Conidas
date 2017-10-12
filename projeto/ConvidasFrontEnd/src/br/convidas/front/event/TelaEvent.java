package br.convidas.front.event;

import java.io.IOException;
import java.net.URL;

import br.convidas.front.event.controller.ControllerEvent;
import br.convidas.tools.log.LogTools;
import br.convidas.utils.XmlPathUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TelaEvent extends Application {
	
	
	@Override
	public void start(Stage stage) throws Exception {
		try {
			URL arquivoFXML = getClass().getResource(XmlPathUtils.MANAGER_EVENT);
			Parent fxmlParent;
			FXMLLoader loader = new FXMLLoader(arquivoFXML);
			fxmlParent = (Parent) loader.load();
			stage.setResizable(false);
			stage.setScene(new Scene(fxmlParent));
			ControllerEvent control = (ControllerEvent) loader.getController();
			control.setStage(stage);
//			control.updateTablePF(null);
			stage.setTitle("Cidades");
			stage.show();
		
		} catch (IOException e) {
			LogTools.logError(e);
		}
		
	}	

}
