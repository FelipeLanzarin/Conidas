package br.convidas.front.contact.ocorrency;

import java.io.IOException;
import java.net.URL;

import br.convidas.classes.PessoaFisica;
import br.convidas.classes.PessoaJuridica;
import br.convidas.front.contact.ocorrency.controller.ControllerOcorrencyRelationManager;
import br.convidas.front.contact.ocorrency.controller.ControllerOcorrencys;
import br.convidas.tools.log.LogTools;
import br.convidas.utils.XmlPathUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TelaOcorrencys extends Application {
	
	private PessoaFisica pessoaFisica;
	private PessoaJuridica pessoaJuridica;
	
	@Override
	public void start(Stage stage) throws Exception {
		try {
			URL arquivoFXML = getClass().getResource(XmlPathUtils.OCORRENCYS);
			Parent fxmlParent;
			FXMLLoader loader = new FXMLLoader(arquivoFXML);
			fxmlParent = (Parent) loader.load();
			stage.setResizable(false);
			stage.setScene(new Scene(fxmlParent));
			ControllerOcorrencyRelationManager control = (ControllerOcorrencyRelationManager) loader.getController();
			control.setStage(stage);
			control.setPessoaFisica(pessoaFisica);
			control.setPessoaJuridica(pessoaJuridica);
			stage.setTitle("Contatos");
			stage.show();
		
		} catch (IOException e) {
			LogTools.logError(e);
		}
		
	}

	public PessoaFisica getPessoaFisica() {
		return pessoaFisica;
	}

	public void setPessoaFisica(PessoaFisica pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}

	public PessoaJuridica getPessoaJuridica() {
		return pessoaJuridica;
	}

	public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
		this.pessoaJuridica = pessoaJuridica;
	}
	
}
