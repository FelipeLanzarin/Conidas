package br.convidas.front.event.participation;

import java.io.IOException;
import java.net.URL;

import br.convidas.classes.Evento;
import br.convidas.front.event.participation.controller.ControllerParticipation;
import br.convidas.tools.log.LogTools;
import br.convidas.utils.XmlPathUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TelaParticipation extends Application{
	
	private Evento event;
	
	@Override
	public void start(Stage stage) throws Exception {
		try {
			URL arquivoFXML = getClass().getResource(XmlPathUtils.PARTICIPÁTION);
			Parent fxmlParent;
			FXMLLoader loader = new FXMLLoader(arquivoFXML);
			fxmlParent = (Parent) loader.load();
			stage.setResizable(false);
			stage.setScene(new Scene(fxmlParent));
			ControllerParticipation control = (ControllerParticipation) loader.getController();
			control.setEvent(event);
			control.setStage(stage);
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
