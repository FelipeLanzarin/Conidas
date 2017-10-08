package br.convidas.front.contact.handlers.mouse;

import br.convidas.classes.PessoaJuridica;
import br.convidas.front.contact.TelaModalPJ;
import br.convidas.front.contact.controller.ControllerContacts;
import br.convidas.tools.log.LogTools;
import fx.tools.action.EventAction;
import javafx.stage.Stage;

public class ButtonEditMouseClickedPJ implements EventAction{
	
	private PessoaJuridica pessoaJuridica;
	private ControllerContacts telaContact;
	
	@Override
	public void action() {
		try {
			TelaModalPJ tme = new TelaModalPJ();
			tme.setPessoaJuridica(pessoaJuridica);
			tme.setControllerContacts(telaContact);
			tme.setNewPf(false);
			tme.start(new Stage());
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
