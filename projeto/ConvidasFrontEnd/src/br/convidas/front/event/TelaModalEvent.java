package br.convidas.front.event;

import java.io.IOException;
import java.net.URL;

import br.convidas.classes.Evento;
import br.convidas.front.event.controller.ControllerEvent;
import br.convidas.front.event.controller.ControllerModalEvent;
import br.convidas.tools.log.LogTools;
import br.convidas.utils.XmlPathUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TelaModalEvent extends Application{
	
	private Boolean newEvent;
	private Evento event;
	private ControllerEvent controllerEvent;
	
	@Override
	public void start(Stage stage) throws Exception {
		try {
			URL arquivoFXML = getClass().getResource(XmlPathUtils.MODAL_EVENT);
			Parent fxmlParent;
			FXMLLoader loader = new FXMLLoader(arquivoFXML);
			fxmlParent = (Parent) loader.load();
			stage.setResizable(false);
			stage.setScene(new Scene(fxmlParent));
			ControllerModalEvent control = (ControllerModalEvent) loader.getController();
			control.setEvent(event);
			control.setNewEvent(newEvent);
			control.setControllerEvent(controllerEvent);
			control.setStage(stage);
			stage.setTitle("Cadastro Evento");
			stage.show();
		
		} catch (IOException e) {
			LogTools.logError(e);
		}
	}

	
	public Boolean getNewEvent() {
		return newEvent;
	}

	public void setNewEvent(Boolean newEvent) {
		this.newEvent = newEvent;
	}

	public Evento getEvent() {
		return event;
	}


	public void setEvent(Evento event) {
		this.event = event;
	}


	public ControllerEvent getControllerEvent() {
		return controllerEvent;
	}


	public void setControllerEvent(ControllerEvent controllerEvent) {
		this.controllerEvent = controllerEvent;
	}
}
	
