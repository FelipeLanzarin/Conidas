package br.convidas.front.contact;

import java.io.IOException;
import java.net.URL;

import br.convidas.front.contact.controller.ControllerContacts;
import br.convidas.tools.log.LogTools;
import br.convidas.utils.XmlPathUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TelaContacts extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		try {
			URL arquivoFXML = getClass().getResource(XmlPathUtils.CONTACTS);
			Parent fxmlParent;
			FXMLLoader loader = new FXMLLoader(arquivoFXML);
			fxmlParent = (Parent) loader.load();
			stage.setResizable(false);
			stage.setScene(new Scene(fxmlParent));
			ControllerContacts control = (ControllerContacts) loader.getController();
			control.setStage(stage);
			stage.setTitle("Contatos");
			stage.show();
		
		} catch (IOException e) {
			LogTools.logError(e);
		}
		
	}

}
