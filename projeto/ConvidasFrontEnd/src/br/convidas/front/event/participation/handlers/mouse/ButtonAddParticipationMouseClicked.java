package br.convidas.front.event.participation.handlers.mouse;

import br.convidas.classes.Evento;
import br.convidas.classes.Participacao;
import br.convidas.classes.PessoaFisica;
import br.convidas.classes.PessoaJuridica;
import br.convidas.front.event.participation.controller.ControllerSelectPessoas;
import br.convidas.manager.ManagerParticipacao;
import br.convidas.tools.log.LogTools;
import fx.tools.action.EventAction;
import javafx.scene.control.Alert;

public class ButtonAddParticipationMouseClicked implements EventAction{
	
	private PessoaFisica pessoaFisica;
	private PessoaJuridica pessoaJuridica;
	private Evento event;
	private ControllerSelectPessoas controller;
	
	@Override
	public void action() {
		try {
			if(pessoaFisica != null){
				Participacao participation = addParticipationPF();
				if(participation != null){
					controller.updateParticipationPF(participation);
					Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
					dialogoInfo.setTitle("Sucesso");
					dialogoInfo.setHeaderText("Participação adicionada com sucesso!");
					dialogoInfo.showAndWait();
				}else{
					Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
					dialogoInfo.setTitle("Erro");
					dialogoInfo.setHeaderText("Ocorreu um erro no processamento!");
					dialogoInfo.showAndWait();
				}
			}else{
				Participacao participation = addParticipationPJ();
				if(participation != null){
					controller.updateParticipationPJ(participation);
					Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
					dialogoInfo.setTitle("Sucesso");
					dialogoInfo.setHeaderText("Participação adicionada com sucesso!");
					dialogoInfo.showAndWait();
				}else{
					Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
					dialogoInfo.setTitle("Erro");
					dialogoInfo.setHeaderText("Ocorreu um erro no processamento!");
					dialogoInfo.showAndWait();
				}
			}
			
		} catch (Exception e) {
			LogTools.logError(e);
		}
	}
	
	private Participacao addParticipationPF(){
		Participacao participation = new Participacao();
		participation.setEvento(event);
		participation.setPessoaFisica(pessoaFisica);
		
		if(ManagerParticipacao.create(participation)){
			return participation;
		}
		return null;
	}
	
	private Participacao addParticipationPJ(){
		Participacao participation = new Participacao();
		participation.setEvento(event);
		participation.setPessoaJuridica(pessoaJuridica);
		
		if(ManagerParticipacao.create(participation)){
			return participation;
		}
		return null;
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

	public Evento getEvent() {
		return event;
	}

	public void setEvent(Evento event) {
		this.event = event;
	}

	public ControllerSelectPessoas getController() {
		return controller;
	}

	public void setController(ControllerSelectPessoas controller) {
		this.controller = controller;
	}

}
