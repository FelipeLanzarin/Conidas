package br.convidas.front.event;

import java.io.IOException;
import java.net.URL;

import br.convidas.classes.Evento;
import br.convidas.front.event.controller.ControllerEventDetail;
import br.convidas.tools.log.LogTools;
import br.convidas.utils.XmlPathUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TelaEventDetail extends Application{
	
	private Evento event;


	@Override
	public void start(Stage stage) throws Exception {
		try {
			URL arquivoFXML = getClass().getResource(XmlPathUtils.EVENT_DETAIL);
			Parent fxmlParent;
			FXMLLoader loader = new FXMLLoader(arquivoFXML);
			fxmlParent = (Parent) loader.load();
			stage.setResizable(false);
			stage.setScene(new Scene(fxmlParent));
			ControllerEventDetail control = (ControllerEventDetail) loader.getController();
			control.setEvent(event);
			stage.setTitle("Evento");
			stage.show();
		
		} catch (IOException e) {
			LogTools.logError(e);
		}
		
	}	
	
	public Evento getEvent() {
		return event;
	}

	public void setEvent(Evento event) {
		this.event = event;
	}
	
}