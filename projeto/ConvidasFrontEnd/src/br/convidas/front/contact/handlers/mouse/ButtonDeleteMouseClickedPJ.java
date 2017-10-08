package br.convidas.front.contact.handlers.mouse;

import br.convidas.classes.PessoaJuridica;
import br.convidas.front.contact.controller.ControllerContacts;
import br.convidas.manager.ManagerPJ;
import br.convidas.tools.log.LogTools;
import fx.tools.action.EventAction;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class ButtonDeleteMouseClickedPJ implements EventAction{
	
	private PessoaJuridica pessoaJuridica;
	private ControllerContacts telaContact;
	
	@Override
	public void action() {
		try {
			final ButtonType btnSim = new ButtonType("Sim");
			final ButtonType btnNao = new ButtonType("Não");
			
			Alert alert = new Alert(AlertType.CONFIRMATION, "Você deseja excluir a pessoa "+ pessoaJuridica.getName()+"?", btnSim, btnNao);
			alert.showAndWait();

			if (alert.getResult() == btnSim) {
				if(ManagerPJ.delete(pessoaJuridica)){
					Alert dialog = new Alert(Alert.AlertType.INFORMATION);
					dialog.setTitle("Sucesso!");
					dialog.setHeaderText("Pessoa excluída com sucesso");
					dialog.showAndWait();
					telaContact.updateTablePJ(null);
				}else{
					Alert dialog = new Alert(Alert.AlertType.ERROR);
					dialog.setTitle("Erro!");
					dialog.setHeaderText("Pessoa já possui uma ocorrência!");
					dialog.showAndWait();
				}
			}
		} catch (Exception e) {
			LogTools.logError(e);
		}
	}

	public PessoaJuridica getPessoaJuridica() {
		return pessoaJuridica;
	}

	public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
		this.pessoaJuridica = pessoaJuridica;
	}

	public ControllerContacts getTelaContact() {
		return telaContact;
	}

	public void setTelaContact(ControllerContacts telaContact) {
		this.telaContact = telaContact;
	}


}
