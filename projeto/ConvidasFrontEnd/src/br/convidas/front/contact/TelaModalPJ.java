package br.convidas.front.contact;

import java.io.IOException;
import java.net.URL;

import br.convidas.classes.PessoaJuridica;
import br.convidas.front.contact.controller.ControllerContacts;
import br.convidas.front.contact.controller.ControllerModalPJ;
import br.convidas.tools.log.LogTools;
import br.convidas.utils.XmlPathUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TelaModalPJ extends Application {
	
	private Boolean newPf;
	private ControllerContacts controllerContacts;
	private PessoaJuridica pessoaJuridica;
	
	@Override
	public void start(Stage stage) throws Exception {
		try {
			URL arquivoFXML = getClass().getResource(XmlPathUtils.CONTACT_MODAL_PJ);
			Parent fxmlParent;
			FXMLLoader loader = new FXMLLoader(arquivoFXML);
			fxmlParent = (Parent) loader.load();
			stage.setResizable(false);
			stage.setScene(new Scene(fxmlParent));
			ControllerModalPJ control = (ControllerModalPJ) loader.getController();
			control.setNewPF(newPf);
			control.setPessoa(pessoaJuridica);
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

	public ControllerContacts getControllerContacts() {
		return controllerContacts;
	}

	public void setControllerContacts(ControllerContacts controllerContacts) {
		this.controllerContacts = controllerContacts;
	}

	public PessoaJuridica getPessoaJuridica() {
		return pessoaJuridica;
	}

	public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
		this.pessoaJuridica = pessoaJuridica;
	}


}
