package br.convidas.front.contact;

import java.io.IOException;
import java.net.URL;

import br.convidas.classes.PessoaFisica;
import br.convidas.front.contact.controller.ControllerConsultModalPF;
import br.convidas.front.contact.controller.ControllerContacts;
import br.convidas.tools.log.LogTools;
import br.convidas.utils.XmlPathUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TelaConsultModalPF extends Application {
	
	private ControllerContacts controllerContacts;
	private PessoaFisica pessoaFisica;
	
	@Override
	public void start(Stage stage) throws Exception {
		try {
			URL arquivoFXML = getClass().getResource(XmlPathUtils.CONTACT_MODAL_CONSULT_PF);
			Parent fxmlParent;
			FXMLLoader loader = new FXMLLoader(arquivoFXML);
			fxmlParent = (Parent) loader.load();
			stage.setResizable(false);
			stage.setScene(new Scene(fxmlParent));
			ControllerConsultModalPF control = (ControllerConsultModalPF) loader.getController();
			control.setPessoa(pessoaFisica);
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

	public PessoaFisica getPessoaFisica() {
		return pessoaFisica;
	}

	public void setPessoaFisica(PessoaFisica pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}
	

}
