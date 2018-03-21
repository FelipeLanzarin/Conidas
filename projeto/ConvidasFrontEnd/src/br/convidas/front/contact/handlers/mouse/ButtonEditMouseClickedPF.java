package br.convidas.front.contact.handlers.mouse;

import br.convidas.classes.PessoaFisica;
import br.convidas.front.contact.TelaModalPF;
import br.convidas.front.contact.controller.ControllerContacts;
import br.convidas.tools.log.LogTools;
import fx.tools.action.EventAction;
import javafx.stage.Stage;

public class ButtonEditMouseClickedPF implements EventAction{
	
	private PessoaFisica pessoaFisica;
	private ControllerContacts telaContact;
	
	@Override
	public void action() {
		try {
			TelaModalPF tme = new TelaModalPF();
			tme.setPessoaFisica(pessoaFisica);
//			tme.setControllerContacts(telaContact);
			tme.setNewPf(false);
			tme.start(new Stage());
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
