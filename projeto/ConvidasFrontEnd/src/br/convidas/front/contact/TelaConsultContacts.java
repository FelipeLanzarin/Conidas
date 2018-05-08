package br.convidas.front.contact;

import java.io.IOException;
import java.net.URL;

import br.convidas.classes.Evento;
import br.convidas.front.contact.controller.ControllerConsultContacts;
import br.convidas.front.event.participation.controller.ControllerParticipationRelationManager;
import br.convidas.tools.log.LogTools;
import br.convidas.utils.XmlPathUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TelaConsultContacts extends Application {

	private ControllerParticipationRelationManager controllerParticipation;
	private Evento evento;
	private Boolean isRelationConsult;
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
			control.setIsRelationConsult(isRelationConsult);
			if(isRelationConsult){
				control.consultRelation();
			}else{
				control.showAphabet();
			}
			control.createTable();
			stage.show();
		
		} catch (IOException e) {
			LogTools.logError(e);
		}
		
	}

	public ControllerParticipationRelationManager getControllerParticipation() {
		return controllerParticipation;
	}


	public void setControllerParticipation(ControllerParticipationRelationManager controllerParticipation) {
		this.controllerParticipation = controllerParticipation;
	}


	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Boolean getIsRelationConsult() {
		return isRelationConsult;
	}

	public void setIsRelationConsult(Boolean isRelationConsult) {
		this.isRelationConsult = isRelationConsult;
	}
	

}
