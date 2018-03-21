package br.convidas.front.contact;

import java.io.IOException;
import java.net.URL;

import br.convidas.classes.PessoaFisica;
import br.convidas.front.contact.controller.ControllerContacts;
import br.convidas.front.contact.controller.ControllerContactsRelationManager;
import br.convidas.front.contact.controller.ControllerModalPF;
import br.convidas.tools.log.LogTools;
import br.convidas.utils.XmlPathUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TelaModalPF extends Application {
	
	private Boolean newPf;
	private ControllerContactsRelationManager controllerContacts;
	private PessoaFisica pessoaFisica;
	
	@Override
	public void start(Stage stage) throws Exception {
		try {
			URL arquivoFXML = getClass().getResource(XmlPathUtils.CONTACT_MODAL_PF);
			Parent fxmlParent;
			FXMLLoader loader = new FXMLLoader(arquivoFXML);
			fxmlParent = (Parent) loader.load();
			stage.setResizable(false);
			stage.setScene(new Scene(fxmlParent));
			ControllerModalPF control = (ControllerModalPF) loader.getController();
			control.setNewPF(newPf);
			control.setPessoa(pessoaFisica);
			control.setContacts(controllerContacts);
			control.setStage(stage);
			
			stage.setTitle("Convidas");
			stage.show();
		
		} catch (IOException e) {
			LogTools.logError(e);
		}
		
	}

	public Boolean getNewPf() {
		return newPf;
	}

	public void setNewPf(Boolean newPf) {
		this.newPf = newPf;
	}

	public PessoaFisica getPessoaFisica() {
		return pessoaFisica;
	}

	public void setPessoaFisica(PessoaFisica pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}

	public ControllerContactsRelationManager getControllerContacts() {
		return controllerContacts;
	}

	public void setControllerContacts(ControllerContactsRelationManager controllerContacts) {
		this.controllerContacts = controllerContacts;
	}
	
}
