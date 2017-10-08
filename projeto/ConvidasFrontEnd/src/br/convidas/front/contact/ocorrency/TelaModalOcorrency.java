package br.convidas.front.contact.ocorrency;

import java.io.IOException;
import java.net.URL;

import br.convidas.classes.OcorrencyPF;
import br.convidas.classes.OcorrencyPJ;
import br.convidas.classes.PessoaFisica;
import br.convidas.classes.PessoaJuridica;
import br.convidas.front.contact.ocorrency.controller.ControllerModalOcorrency;
import br.convidas.front.contact.ocorrency.controller.ControllerOcorrencys;
import br.convidas.tools.log.LogTools;
import br.convidas.utils.XmlPathUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TelaModalOcorrency extends Application {
	
	private PessoaFisica pessoaFisica;
	private PessoaJuridica pessoaJuridica;
	private OcorrencyPF ocorrencyPF;
	private OcorrencyPJ ocorrencyPJ;
	private Boolean isPF;
	private Boolean isPJ;
	private ControllerOcorrencys controllerOcorrencys;
	
	@Override
	public void start(Stage stage) throws Exception {
		try {
			URL arquivoFXML = getClass().getResource(XmlPathUtils.MODAL_OCORRENCYS);
			Parent fxmlParent;
			FXMLLoader loader = new FXMLLoader(arquivoFXML);
			fxmlParent = (Parent) loader.load();
			stage.setResizable(false);
			stage.setScene(new Scene(fxmlParent));
			ControllerModalOcorrency control = (ControllerModalOcorrency) loader.getController();
			control.setStage(stage);
			control.setPessoaFisica(pessoaFisica);
			control.setPessoaJuridica(pessoaJuridica);
			control.setOcorrencyPF(ocorrencyPF);
			control.setOcorrencyPJ(ocorrencyPJ);
			control.setIsPF(isPF);
			control.setIsPJ(isPJ);
			control.setControllerOcorrencys(controllerOcorrencys);
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

	public OcorrencyPF getOcorrencyPF() {
		return ocorrencyPF;
	}

	public void setOcorrencyPF(OcorrencyPF ocorrencyPF) {
		this.ocorrencyPF = ocorrencyPF;
	}

	public ControllerOcorrencys getControllerOcorrencys() {
		return controllerOcorrencys;
	}

	public void setControllerOcorrencys(ControllerOcorrencys controllerOcorrencys) {
		this.controllerOcorrencys = controllerOcorrencys;
	}

	public OcorrencyPJ getOcorrencyPJ() {
		return ocorrencyPJ;
	}

	public void setOcorrencyPJ(OcorrencyPJ ocorrencyPJ) {
		this.ocorrencyPJ = ocorrencyPJ;
	}

	public PessoaJuridica getPessoaJuridica() {
		return pessoaJuridica;
	}

	public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
		this.pessoaJuridica = pessoaJuridica;
	}

	public Boolean getIsPF() {
		return isPF;
	}

	public void setIsPF(Boolean isPF) {
		this.isPF = isPF;
	}

	public Boolean getIsPJ() {
		return isPJ;
	}

	public void setIsPJ(Boolean isPJ) {
		this.isPJ = isPJ;
	}
	
}
