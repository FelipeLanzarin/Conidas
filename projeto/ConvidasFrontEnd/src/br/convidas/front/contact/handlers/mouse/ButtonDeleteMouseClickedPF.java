package br.convidas.front.contact.handlers.mouse;

import br.convidas.classes.PessoaFisica;
import br.convidas.front.contact.controller.ControllerContacts;
import br.convidas.manager.ManagerPF;
import br.convidas.tools.log.LogTools;
import fx.tools.action.EventAction;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class ButtonDeleteMouseClickedPF implements EventAction{
	
	private PessoaFisica pessoaFisica;
	private ControllerContacts telaContact;
	
	@Override
	public void action() {
		try {
			final ButtonType btnSim = new ButtonType("Sim");
			final ButtonType btnNao = new ButtonType("Não");
			
			Alert alert = new Alert(AlertType.CONFIRMATION, "Você deseja excluir a pessoa "+ pessoaFisica.getName()+"?", btnSim, btnNao);
			alert.showAndWait();

			if (alert.getResult() == btnSim) {
				if(ManagerPF.delete(pessoaFisica)){
					Alert dialog = new Alert(Alert.AlertType.INFORMATION);
					dialog.setTitle("Sucesso!");
					dialog.setHeaderText("Pessoa excluída com sucesso");
					dialog.showAndWait();
					telaContact.updateTablePF(null);
				}else{
					Alert dialog = new Alert(Alert.AlertType.ERROR);
					dialog.setTitle("Erro!");
					dialog.setHeaderText("Pessoa já possui uma Ocorrência!");
					dialog.showAndWait();
				}
			}
		} catch (Exception e) {
			LogTools.logError(e);
		}
	}

	public PessoaFisica getPessoaFisica() {
		return pessoaFisica;
	}

	public void setPessoaFisica(PessoaFisica pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}

	public ControllerContacts getTelaContact() {
		return telaContact;
	}

	public void setTelaContact(ControllerContacts telaContact) {
		this.telaContact = telaContact;
	}


}
