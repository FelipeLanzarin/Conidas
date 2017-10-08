package br.convidas.front.contact;

import java.io.IOException;
import java.net.URL;

import br.convidas.classes.PessoaJuridica;
import br.convidas.front.contact.controller.ControllerConsultModalPJ;
import br.convidas.front.contact.controller.ControllerContacts;
import br.convidas.tools.log.LogTools;
import br.convidas.utils.XmlPathUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TelaConsultModalPJ extends Application {
	
	private ControllerContacts controllerContacts;
	private PessoaJuridica pessoaJuridica;
	
	@Override
	public void start(Stage stage) throws Exception {
		try {
			URL arquivoFXML = getClass().getResource(XmlPathUtils.CONTACT_MODAL_CONSULT_PJ);
			Parent fxmlParent;
			FXMLLoader loader = new FXMLLoader(arquivoFXML);
			fxmlParent = (Parent) loader.load();
			stage.setResizable(false);
			stage.setScene(new Scene(fxmlParent));
			ControllerConsultModalPJ control = (ControllerConsultModalPJ) loader.getController();
			control.setPessoa(pessoaJuridica);
			stage.setTitle("Convidas");
			stage.show();
		
		} catch (IOException e) {
			LogTools.logError(e);
		}
		
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
