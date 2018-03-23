package br.convidas.front.contact;

import java.io.IOException;
import java.net.URL;

import br.convidas.classes.Evento;
import br.convidas.front.contact.controller.ControllerConsultContacts;
import br.convidas.front.event.participation.controller.ControllerParticipation;
import br.convidas.tools.log.LogTools;
import br.convidas.utils.XmlPathUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TelaConsultContacts extends Application {

	private ControllerParticipation controllerParticipation;
	private Evento evento;
	
	@Override
	public void start(Stage stage) throws Exception {
		try {
			URL arquivoFXML = getClass().getResource(XmlPathUtils.CONSULT_CONTACTS);
			Parent fxmlParent;
			FXMLLoader loader = new FXMLLoader(arquivoFXML);
			fxmlParent = (Parent) loader.load();
			stage.setResizable(false);
			stage.setScene(new Scene(fxmlParent));
			ControllerConsultContacts control = (ControllerConsultContacts) loader.getController();
			control.setStage(stage);
			stage.setTitle("Contatos");
			control.setControllerParticipation(controllerParticipation);
			control.setEvento(evento);
			control.createTable();
			stage.show();
		
		} catch (IOException e) {
			LogTools.logError(e);
		}
		
	}

	public ControllerParticipation getControllerParticipation() {
		return controllerParticipation;
	}

	public void setControllerParticipation(ControllerParticipation controllerParticipation) {
		this.controllerParticipation = controllerParticipation;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

}
