package br.convidas.front.contact.handlers.mouse;

import br.convidas.classes.PessoaFisica;
import br.convidas.front.contact.TelaConsultModalPF;
import br.convidas.front.contact.controller.ControllerConsultContacts;
import br.convidas.tools.log.LogTools;
import fx.tools.action.EventAction;
import javafx.stage.Stage;

public class ButtonSeeContactMouseClickedPF implements EventAction{
	
	private PessoaFisica pessoaFisica;
	private ControllerConsultContacts telaContact;
	
	@Override
	public void action() {
		try {
			TelaConsultModalPF tme = new TelaConsultModalPF();
			tme.setPessoaFisica(pessoaFisica);
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

	public ControllerConsultContacts getTelaContact() {
		return telaContact;
	}

	public void setTelaContact(ControllerConsultContacts telaContact) {
		this.telaContact = telaContact;
	}
	
}
