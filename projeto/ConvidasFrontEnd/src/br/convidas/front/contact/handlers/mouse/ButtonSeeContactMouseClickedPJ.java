package br.convidas.front.contact.handlers.mouse;

import br.convidas.classes.PessoaJuridica;
import br.convidas.front.contact.TelaConsultModalPJ;
import br.convidas.tools.log.LogTools;
import fx.tools.action.EventAction;
import javafx.stage.Stage;

public class ButtonSeeContactMouseClickedPJ implements EventAction{
	
	private PessoaJuridica pessoaJuridica;
//	private ControllerConsultContacts telaContact;
	
	@Override
	public void action() {
		try {
			TelaConsultModalPJ tme = new TelaConsultModalPJ();
			tme.setPessoaJuridica(pessoaJuridica);
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
}
